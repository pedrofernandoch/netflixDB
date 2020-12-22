package pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.PDFAIdentificationSchema;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.xml.XmpSerializer;

public class PDFManager {
	String file = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "relatoriofilmes.pdf";
	String fontfile = System.getProperty("user.dir")+"/verdana-4.ttf";

	public PDFManager() {
		File pdf = new File(file);
		try {

			if (pdf.createNewFile()) {
				//System.out.println("PDF criado em: " + file);
			} else {
				//System.out.println("PDF ja existe");
			}

		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
	}

	public void createPDF(String message) throws IOException, TransformerException {
		try (PDDocument doc = new PDDocument()) {
			PDPage page = new PDPage();
			doc.addPage(page);

			PDFont font = PDType0Font.load(doc, new File(fontfile));
			if (!font.isEmbedded()) {
				throw new IllegalStateException("PDF/A compliance requires that all fonts used for"
						+ " text rendering in rendering modes other than rendering mode 3 are embedded.");
			}

			try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
				contents.beginText();
				contents.setFont(font, 12);
				contents.newLineAtOffset(50, 720);
				contents.showText(message);
				contents.endText();
			}
			XMPMetadata xmp = XMPMetadata.createXMPMetadata();
			try {
				DublinCoreSchema dc = xmp.createAndAddDublinCoreSchema();
				dc.setTitle(file);

				PDFAIdentificationSchema id = xmp.createAndAddPFAIdentificationSchema();
				id.setPart(1);
				id.setConformance("B");

				XmpSerializer serializer = new XmpSerializer();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				serializer.serialize(xmp, baos, true);

				PDMetadata metadata = new PDMetadata(doc);
				metadata.importXMPMetadata(baos.toByteArray());
				doc.getDocumentCatalog().setMetadata(metadata);
			} catch (BadFieldValueException e) {
				throw new IllegalArgumentException(e);
			}

			doc.save(file);
		}
	}

}
