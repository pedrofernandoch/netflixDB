package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.stage.PopupWindow;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Circle;

public class MainViewController implements Initializable {

	
	private static final String SQUARE_BUBBLE = "M24 1h-24v16.981h4v5.019l7-5.019h13z";
	private boolean connected = false;
	@FXML
    private Circle light;
	@FXML
	private TextArea logsTextArea;
	@FXML
    private Tab logTab;
	public static ArrayList<String> logs = new ArrayList<>();
	
	public void addToLogTextAreaButton() {
		//logsTextArea.clear();
		//for(String log : logs) logsTextArea.appendText(log+"\n");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {		
		//initializeData();
		//initializeLight();
	}
	
	public void initializeLight() {
		
	}

	public static void clearObjects() {
		
	}

	public void initializeData() {
		//logsTextArea.setEditable(false);	
	}

	public void onActuationSaveButton() {

	}

	public void onActuationCleanButton() {
		
	}

	public void onActuationDeleteButton() {

	}


	/*private void setUpValidationTF(final TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				validateTF(tf);
			}
		});
		validateTF(tf);
	}*/

	/*private void setUpValidationHostComboBox(final ComboBox<Host> cb) {
		cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Host>() {
			@Override
			public void changed(ObservableValue<? extends Host> arg0, Host arg1, Host arg2) {
				validateHostComboBox(cb);
			}
		});
		validateHostComboBox(cb);
	}*/

	/*private void setUpValidationTelnetCommandListView(final ListView<TelnetCommand> lv) {
		lv.getItems().addListener(new ListChangeListener<TelnetCommand>() {
			@Override
			public void onChanged(Change<? extends TelnetCommand> arg0) {
				validateListView(lv);
			}
		});
		validateListView(lv);
	}*/

	/*private void setUpValidationCheckBox(final CheckBox cb, final Label lb) {
		cb.selectedProperty().addListener(new ChangeListener<>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				validateLabel(lb);
			}
		});
		validateLabel(lb);
	}
	
	private void validateTF(TextField tf) {
		ObservableList<String> styleClass = tf.getStyleClass();
		if (tf.getText().trim().length() == 0) {
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		} else {
			styleClass.removeAll(Collections.singleton("error"));
		}
	}

	private void validateListView(ListView<?> lv) {
		ObservableList<String> styleClass = lv.getStyleClass();
		if (lv.getItems().size() == 0) {
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		} else {
			styleClass.removeAll(Collections.singleton("error"));
		}
	}*/

	/*private void validateLabel(Label lb) {
		ObservableList<String> styleClass = lb.getStyleClass();
		if (!udpTriggerSunCheckBox.isSelected() && !udpTriggerMonCheckBox.isSelected()
				&& !udpTriggerTueCheckBox.isSelected() && !udpTriggerWedCheckBox.isSelected()
				&& !udpTriggerThuCheckBox.isSelected() && !udpTriggerFriCheckBox.isSelected()
				&& !udpTriggerSatCheckBox.isSelected()) {
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		} else {
			styleClass.removeAll(Collections.singleton("error"));
		}
	}*/

	/*private void validateUdpComboBox(ComboBox<UDPCommand> cb) {
		ObservableList<String> styleClass = cb.getStyleClass();
		if (cb.getSelectionModel().isEmpty() == true) {
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		} else {
			styleClass.removeAll(Collections.singleton("error"));
		}
	}*/

	/*private void cleanRed(TextField tf) {
		tf.getStyleClass().removeAll(Collections.singleton("error"));
	}*/

	/*private void cleanRedBox(ComboBox<?> cb) {
		cb.getStyleClass().removeAll(Collections.singleton("error"));
	}*/

	/*private void cleanLabel(Label lb) {
		lb.getStyleClass().removeAll(Collections.singleton("error"));
	}*/

	/*private void cleanList(ListView<?> lv) {
		lv.getStyleClass().removeAll(Collections.singleton("error"));
	}*/
	
	private Tooltip makeBubble(Tooltip tooltip) {
	        tooltip.setStyle("-fx-font-size: 12px; -fx-shape: \"" + SQUARE_BUBBLE + "\";");
	        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
	        return tooltip;
	}
	
	public void validateCircle(Circle c) {
		if(connected) {
			light.setFill(javafx.scene.paint.Color.LIMEGREEN);
			Tooltip.install(light, makeBubble(new Tooltip("Conectado")));
		}else{
			Tooltip.install(light, makeBubble(new Tooltip("Desconectado, por favor se concete ao banco através do botão xxx")));
			light.setFill(javafx.scene.paint.Color.RED);
		}
	}

}