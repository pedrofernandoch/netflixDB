package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import db.OracleConnection;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.entities.Adult;
import pdf.PDFManager;

public class GeneratePDFViewController implements Initializable {

	private String profileName;
	private String userCpf = MainViewController.getUserCpf();
	private ObservableList<Adult> adultObsList = FXCollections.observableArrayList(new ArrayList<Adult>());
	@FXML
	private ComboBox<Adult> profileComboBox;
	@FXML
	private Button searchButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Statement stmt;
		ResultSet rs;
		Connection connection = OracleConnection.getConnection();
		String query = "SELECT * FROM Adulto u WHERE u.usuario = " + userCpf;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				adultObsList.add(new Adult(rs.getString("ALIAS"), rs.getString("USUARIO"),
						Integer.parseInt(rs.getString("QUALIDSTREAMING")), Integer.parseInt(rs.getString("LEGENDA")),
						Integer.parseInt(rs.getString("IDIOMA"))));
			}
			profileComboBox.setItems(adultObsList);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			Alerts.showAlert("Erro", "Erro ao buscar perfis adultos para esse usuário", e.getMessage(),
					AlertType.ERROR);
		}
	}

	public void onSearchButton() {
		if(profileComboBox.getSelectionModel().getSelectedItem() != null) {
			profileName = profileComboBox.getSelectionModel().getSelectedItem().getAlias();
			String query = "SELECT m.ID, m.TITULO, m.ANOLANCAMENTO, m.THUMB,m.DURACAO, m.SINOPSE, m.CLASSIFICACAOETARIA, m.AVALIACAO"
					+ " FROM (((((((ADULTO a JOIN USUARIO u ON a.USUARIO = u.CPF)"
					+ " JOIN IDIOMA i ON i.ID = a.IDIOMA OR i.ID = a.LEGENDA)"
					+ " JOIN AUDIOMEDIA ad ON ad.IDIOMA = a.IDIOMA)"
					+ " JOIN LEGENDAMEDIA lm ON lm.IDIOMA = a.LEGENDA)"
					+ " JOIN MEDIA m ON m.ID = ad.MEDIA AND m.ID = lm.MEDIA)"
					+ " JOIN AMIZADE am ON (am.PERFIL1 = a.ALIAS AND am.USUARIO1 = a.USUARIO) OR (am.PERFIL2 = a.ALIAS AND am.USUARIO2 = a.USUARIO))"
					+ " JOIN EXIBICAO e ON (e.ALIAS = am.PERFIL2 AND e.USUARIO = am.USUARIO2 AND e.MEDIA = m.ID) OR (e.ALIAS = am.PERFIL1 AND e.USUARIO = am.USUARIO1))"
					+ " LEFT JOIN OPINIAO o ON (o.ALIAS = am.PERFIL2 AND o.USUARIO = am.USUARIO2 AND o.MEDIA = m.ID) OR (o.ALIAS = am.PERFIL1 AND o.USUARIO = am.USUARIO2 AND o.MEDIA = m.ID)"
					+ " WHERE a.ALIAS = '" + profileName + "' AND a.USUARIO = " + userCpf
					+ " GROUP BY m.ID, m.TITULO, m.ANOLANCAMENTO, m.THUMB,m.DURACAO, m.SINOPSE, m.CLASSIFICACAOETARIA, m.AVALIACAO";
			Statement stmt;
			ResultSet rs;
			Connection connection = OracleConnection.getConnection();
			try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery(query);
				MainViewController.getSearchQuerys().add(query);
				ArrayList<String> pdfContent =  new ArrayList<>();
				pdfContent.add("QUERY REALIZADA:");
				pdfContent.add(" ");
				pdfContent.add("SELECT m.ID, m.TITULO, m.ANOLANCAMENTO, m.THUMB,m.DURACAO, m.SINOPSE, m.CLASSIFICACAOETARIA, m.AVALIACAO");
				pdfContent.add("    FROM (((((((ADULTO a JOIN USUARIO u ON a.USUARIO = u.CPF)");
				pdfContent.add("    JOIN IDIOMA i ON i.ID = a.IDIOMA OR i.ID = a.LEGENDA)");
				pdfContent.add("    JOIN AUDIOMEDIA ad ON ad.IDIOMA = a.IDIOMA)");
				pdfContent.add("    JOIN LEGENDAMEDIA lm ON lm.IDIOMA = a.LEGENDA)");
				pdfContent.add("    JOIN MEDIA m ON m.ID = ad.MEDIA AND m.ID = lm.MEDIA)");
				pdfContent.add("    JOIN AMIZADE am ON (am.PERFIL1 = a.ALIAS AND am.USUARIO1 = a.USUARIO)");
				pdfContent.add("OR (am.PERFIL2 = a.ALIAS AND am.USUARIO2 = a.USUARIO))");
				pdfContent.add("    JOIN EXIBICAO e ON (e.ALIAS = am.PERFIL2 AND e.USUARIO = am.USUARIO2 AND e.MEDIA = m.ID)");
				pdfContent.add("OR (e.ALIAS = am.PERFIL1 AND e.USUARIO = am.USUARIO1))");
				pdfContent.add("    LEFT JOIN OPINIAO o ON (o.ALIAS = am.PERFIL2 AND o.USUARIO = am.USUARIO2 AND o.MEDIA = m.ID)");
				pdfContent.add("OR (o.ALIAS = am.PERFIL1 AND o.USUARIO = am.USUARIO2 AND o.MEDIA = m.ID)");
				pdfContent.add("    WHERE a.ALIAS = '" + profileName + "' AND a.USUARIO = " + userCpf);
				pdfContent.add("    GROUP BY m.ID, m.TITULO, m.ANOLANCAMENTO, m.THUMB,m.DURACAO, m.SINOPSE, m.CLASSIFICACAOETARIA, m.AVALIACAO");
				pdfContent.add(" ");
				int i=0;
				boolean founded = false;
				while (rs.next()) {
					founded = true;
					pdfContent.add("FILME "+i);
					pdfContent.add("Id: "+rs.getString("ID"));
					pdfContent.add("Titulo: "+rs.getString("TITULO"));
					pdfContent.add("AnoLancamento: "+rs.getString("ANOLANCAMENTO"));
					pdfContent.add("Thumb: "+rs.getString("THUMB"));
					pdfContent.add("Duracao: "+rs.getString("DURACAO"));
					pdfContent.add("Sinopse: "+rs.getString("SINOPSE"));
					pdfContent.add("ClassificacaoEtaria: "+rs.getString("CLASSIFICACAOETARIA"));
					pdfContent.add("Avaliacao: "+rs.getString("AVALIACAO"));
					pdfContent.add(" ");
					i++;
				}
				if(!founded) pdfContent.add("Nenhum filme encontrado");
				try {
		            PDFManager pdf = new PDFManager();		    
		            pdf.createPDF(pdfContent);
		            Alerts.showAlert("PDF", "PDF criado","O PDF com nome relatoriofilmes foi gerado com sucesso e se encontra na sua área de trabalho",AlertType.INFORMATION);
		        } catch (Exception e) {
		        	e.printStackTrace();
		        	Alerts.showAlert("Erro", "Erro ao tentar gerar o PDF", e.getMessage(),
							AlertType.ERROR);
		        }
				stmt.close();
				((Stage)searchButton.getScene().getWindow()).close();
			} catch (SQLException e) {
				e.printStackTrace();
				Alerts.showAlert("Erro", "Erro ao buscar os filmes", e.getMessage(),
						AlertType.ERROR);
			}
		}else {
			Alerts.showAlert("Erro", "Selecione um perfil!", "Para realizar a busca selecione um dos perfis do usuário selecionado",
					AlertType.ERROR);
		}
	}

}
