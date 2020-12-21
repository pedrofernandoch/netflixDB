package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import db.OracleConnection;
import gui.util.Alerts;
import gui.util.TooltippedTableCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import model.entities.Plan;
import model.entities.User;
import model.enums.LogActivities;
import model.enums.LogTypes;

public class MainViewController implements Initializable {

	private ArrayList<String> statements = new ArrayList<>(); 
	private ArrayList<User> users = new ArrayList<>();
	private ObservableList<User> userObsList;
	private String currentUserCpf = null;
	private ArrayList<Plan> plans = new ArrayList<>();
	private ObservableList<Plan> planObsList;
	private final String SQUARE_BUBBLE = "M24 1h-24v16.981h4v5.019l7-5.019h13z";
	private boolean connected = false;
	
	// Connection tab
	// Forms
	@FXML
	private TextField connectionUserNameField;
	@FXML
	private PasswordField connectionPasswordField;
	@FXML
	private TextField connectionHostNameField;
	@FXML
	private TextField connectionPortField;
	@FXML
	private TextField connectionSidField;
	// Operators
	@FXML
	private Button connectionCleanButton;
	@FXML
	private Button connectionConnectButton;
	@FXML
	private Button connectionShowTableButton;
	@FXML
	private TextArea connectionTableTextArea;

	// User tab
	// Table and TableCollumns
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, CheckBox> userSelectionTableColumn;
	@FXML
	private TableColumn<User, String> userNameTableColumn;
	@FXML
	private TableColumn<User, String> userEmailTableColumn;
	@FXML
	private TableColumn<User, String> userCpfTableColumn;
	@FXML
	private TableColumn<User, String> userDateOfBirthTableColumn;
	@FXML
	private TableColumn<User, String> userPlanTableColumn;

	// Forms
	@FXML
	private TextField userNameField;
	@FXML
	private TextField userEmailField;
	@FXML
	private TextField userCpfField;
	@FXML
	private TextField userDateOfBirthField;
	@FXML
	private ComboBox<Plan> userPlanComboBox;

	// Operators
	@FXML
	private TextField userSearchField;
	@FXML
	private Button userSearchButton;
	@FXML
	private Button userSaveButton;
	@FXML
	private Button userCleanButton;
	@FXML
	private Button userDeleteButton;

	@FXML
	private Circle light;
	@FXML
	private TextArea logsTextArea;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeData();
		initializeLight();
	}

	private void initializeLight() {
		userObsList.addListener(new ListChangeListener<User>() {
			public void onChanged(Change<? extends User> arg0) {
				validateCircle(light);
			}
		});
		validateCircle(light);
	}

	private void initializeData() {
		userObsList = FXCollections.observableArrayList(users);
		planObsList = FXCollections.observableArrayList(plans);
		FilteredList<User> userFilteredList = new FilteredList<>(userObsList, b -> true);
		SortedList<User> userSortedList = new SortedList<>(userFilteredList);
		userSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
			userFilteredList.setPredicate(user -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (user.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (user.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (user.getCpf().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (user.getDateOfBirth().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(user.getPlan()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false;
			});
		});

		userSortedList.comparatorProperty().bind(userTable.comparatorProperty());
		userTable.setItems(userSortedList);
		userNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		userEmailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		userCpfTableColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		userDateOfBirthTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		userSelectionTableColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
		userPlanComboBox.setItems(planObsList);
		userNameTableColumn.setCellFactory(TooltippedTableCell.forTableColumn());
		userEmailTableColumn.setCellFactory(TooltippedTableCell.forTableColumn());
		userCpfTableColumn.setCellFactory(TooltippedTableCell.forTableColumn());
		userDateOfBirthTableColumn.setCellFactory(TooltippedTableCell.forTableColumn());

		userTable.setRowFactory(tv -> {
			TableRow<User> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					User rowData = row.getItem();
					currentUserCpf = rowData.getCpf();
					userNameField.setText(rowData.getName());
					userEmailField.setText(rowData.getEmail());
					userCpfField.setText(rowData.getCpf());
					userDateOfBirthField.setText(rowData.getDateOfBirth().toString());
					int index = 0;
					for (int i = 0; i < plans.size(); i++)
						if (plans.get(i).getId() == rowData.getPlan())
							index = i;
					userPlanComboBox.getSelectionModel().select(plans.get(index));
					userSaveButton.setText("Salvar");
					userCleanButton.setText("Cancelar");
				}
			});
			return row;
		});
	}

	// CRUD
	public void onUserSaveButton() {
		if (userNameField.getText().length() != 0 && userEmailField.getText().length() != 0
				&& userCpfField.getText().length() != 0 && userDateOfBirthField.getText().length() != 0
				&& userPlanComboBox.getSelectionModel().isEmpty() == false) {
			if (userSaveButton.getText().equals("Salvar")) {
				newEventLog("Usuário", LogActivities.UPDATE,
						LogActivities.UPDATE.getLogDescription() + " usuário de cpf: " + currentUserCpf, LogTypes.CRUD);
				Alerts.showAlert("Atualização", "Usuário atualizado", "O usuário foi editado com sucesso!",
						AlertType.INFORMATION);
			}else {
				String queryInsert = null;
				PreparedStatement pstmt;
				try {
					pstmt = OracleConnection.getConnection().prepareStatement(queryInsert);
					pstmt.executeUpdate();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				Alerts.showAlert("Cadastro", "Novo usuário criado", "O usuário foi salvo com sucesso!",
						AlertType.INFORMATION);
				newEventLog("Usuário", LogActivities.CREATE,
						LogActivities.CREATE.getLogDescription() + " novo usuário: " + userNameField.getText(),
						LogTypes.CRUD);
			}
			onUserCleanButton();
		} else {
			setUpValidationTextField(userNameField);
			setUpValidationTextField(userEmailField);
			setUpValidationTextField(userCpfField);
			setUpValidationTextField(userDateOfBirthField);
			setUpValidationPlanComboBox(userPlanComboBox);
			Alerts.showAlert("Erro", "Preencha todos os campos obrigatórios",
					"Os campos que possuem '*' são obrigátorios", AlertType.ERROR);
		}
	}

	public void onUserCleanButton() {
		userNameField.clear();
		userEmailField.clear();
		userCpfField.clear();
		userDateOfBirthField.clear();
		userPlanComboBox.getSelectionModel().clearSelection();
		userSaveButton.setText("Adicionar");
		userCleanButton.setText("Limpar");
		removeErrorTextField(userNameField);
		removeErrorTextField(userEmailField);
		removeErrorTextField(userCpfField);
		removeErrorTextField(userDateOfBirthField);
		removeErrorComboBox(userPlanComboBox);
	}

	public void onUserDeleteButton() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmar exclusão");
		alert.setHeaderText("Você tem certeza que quer deletar esses usuários?");
		alert.setContentText(
				"Ao deletado um usuário ele será permanentemente removido do banco de dados juntamente com seus perfis, preferências e quaisquer daods coletados sobre esse usuário");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Alerts.showAlert("Exclusão", "Usuários deletados", "Os usuários foram deletados com sucesso!",
					AlertType.INFORMATION);
		} else {
			for (User user : userObsList) {
				user.getCheckBox().setSelected(false);
			}
		}
		alert = null;
	}

	public void onUserSearchButton() {
		AnchorPane queryPane = null;
		Scene queryScene = null;
		try {
			queryPane = FXMLLoader.load(getClass().getResource("/gui/QueryView.fxml"));
			queryScene = new Scene(queryPane);
			Stage stage = new Stage();
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/netflixIcon.png")));
			stage.setScene(queryScene);
			stage.setResizable(false);
			stage.setTitle("Query");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// CONNECTION
	// connectionTableTextArea;
	public void onConnectionConnectButton() {
		if (connectionUserNameField.getText().length() != 0 && connectionPasswordField.getText().length() != 0
				&& connectionHostNameField.getText().length() != 0 && connectionPortField.getText().length() != 0
				&& connectionSidField.getText().length() != 0) {
			OracleConnection.createConnection(connectionUserNameField.getText(), connectionPasswordField.getText(),
					connectionHostNameField.getText(), connectionPortField.getText(), connectionSidField.getText());
			//System.out.println("Conectou\n"+System.getProperty("user.dir")+"/BD.sql");
			sqlParser(System.getProperty("user.dir")+"/BD.sql");
			newEventLog("Usuário", LogActivities.CONNECTING_DB,
					LogActivities.CONNECTING_DB.getLogDescription() + " no host " + connectionHostNameField
							+ ", na porta " + connectionPortField + ", no SID " + connectionSidField,
					LogTypes.CONNECTION);
			Alerts.showAlert("Conexão", "Conexão realizada!",
					"A conexão foi realizada com sucesso, e tabelas foram criadas e populadas usando do script: BD.sql",
					AlertType.INFORMATION);
			onConnectionCleanButton();
		} else {
			setUpValidationTextField(connectionUserNameField);
			setUpValidationTextField(connectionPasswordField);
			setUpValidationTextField(connectionHostNameField);
			setUpValidationTextField(connectionPortField);
			setUpValidationTextField(connectionSidField);
			Alerts.showAlert("Erro", "Preencha todos os campos obrigatórios",
					"Os campos que possuem '*' são obrigátorios", AlertType.ERROR);
		}
	}

	public void onConnectionCleanButton() {
		connectionUserNameField.clear();
		connectionPasswordField.clear();
		connectionHostNameField.clear();
		connectionPortField.clear();
		connectionSidField.clear();
		removeErrorTextField(connectionUserNameField);
		removeErrorTextField(connectionPasswordField);
		removeErrorTextField(connectionHostNameField);
		removeErrorTextField(connectionPortField);
		removeErrorTextField(connectionSidField);
	}

	public void onConnectionShowTableButton() {
		
	}
	
	private void sqlParser(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
    	    String line;
    	    String delimiter = ";";
    	    StringBuilder buffer = new StringBuilder();
    	    while ((line = reader.readLine()) != null) {
    	        if (buffer.length() == 0 && line.startsWith(");")) {
    	            delimiter = line.split(" ")[1];
    	            continue;
    	        }
    	        if (line.isEmpty()) {
    	            continue;
    	        }
    	        buffer.append(line.replaceFirst("\\h+$", "")).append("\n");
    	        if (line.endsWith(delimiter)) {    	        	
    	            statements.add(buffer.toString());    	       
    	            buffer.setLength(0);
    	        }
    	    }
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
	}

	// Validators
	private void setUpValidationTextField(final TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				validateTextField(tf);
			}
		});
		validateTextField(tf);
	}

	private void setUpValidationPlanComboBox(final ComboBox<Plan> cb) {
		cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Plan>() {
			@Override
			public void changed(ObservableValue<? extends Plan> arg0, Plan arg1, Plan arg2) {
				validatePlanComboBox(cb);
			}
		});
		validatePlanComboBox(cb);
	}

	private void validateTextField(TextField tf) {
		ObservableList<String> styleClass = tf.getStyleClass();
		if (tf.getText().trim().length() == 0) {
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		} else {
			styleClass.removeAll(Collections.singleton("error"));
		}
	}

	private void validatePlanComboBox(ComboBox<Plan> cb) {
		ObservableList<String> styleClass = cb.getStyleClass();
		if (cb.getSelectionModel().isEmpty() == true) {
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		} else {
			styleClass.removeAll(Collections.singleton("error"));
		}
	}

	private void validateCircle(Circle c) {
		if (connected) {
			light.setFill(javafx.scene.paint.Color.LIMEGREEN);
			Tooltip.install(light, makeBubble(new Tooltip("Conectado")));
		} else {
			Tooltip.install(light,
					makeBubble(new Tooltip("Desconectado, por favor se concete ao banco através do botão xxx")));
			light.setFill(javafx.scene.paint.Color.RED);
		}
	}

	// Error removers
	private void removeErrorTextField(TextField tf) {
		tf.getStyleClass().removeAll(Collections.singleton("error"));
	}

	private void removeErrorComboBox(ComboBox<?> cb) {
		cb.getStyleClass().removeAll(Collections.singleton("error"));
	}

	private Tooltip makeBubble(Tooltip tooltip) {
		tooltip.setStyle("-fx-font-size: 12px; -fx-shape: \"" + SQUARE_BUBBLE + "\";");
		tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
		return tooltip;
	}

	private void newEventLog(String resource, LogActivities activity, String description, LogTypes logType) {
		logsTextArea.appendText("\"" + new Date().toString() + "\",\"" + resource + "\"," + activity.toString() + ",\""
				+ description + "\"," + logType.toString());
	}

	public void clearObjects() {
		users.clear();
		plans.clear();
		userObsList.clear();
		planObsList.clear();
	}

}