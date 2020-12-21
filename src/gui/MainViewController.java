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
import model.entities.Access;
import model.entities.Actor;
import model.entities.Actuation;
import model.entities.Adult;
import model.entities.AutomaticDebit;
import model.entities.CardNumber;
import model.entities.Child;
import model.entities.CreditCard;
import model.entities.Device;
import model.entities.Direction;
import model.entities.Director;
import model.entities.Evaluation;
import model.entities.Exhibition;
import model.entities.Friendship;
import model.entities.GenderPreference;
import model.entities.Genre;
import model.entities.Invoice;
import model.entities.Language;
import model.entities.Media;
import model.entities.MediaAudio;
import model.entities.MediaGender;
import model.entities.MediaSubtitle;
import model.entities.Opinion;
import model.entities.PaymentMethod;
import model.entities.Paypal;
import model.entities.Plan;
import model.entities.Profile;
import model.entities.Recommendation;
import model.entities.Season;
import model.entities.Serie;
import model.entities.User;
import model.enums.LogActivities;
import model.enums.LogTypes;

public class MainViewController implements Initializable {

	private ArrayList<User> users = new ArrayList<>();
	private ArrayList<Access> accesses = new ArrayList<>();
	private ArrayList<Actor> actors = new ArrayList<>();
	private ArrayList<Actuation> actuations = new ArrayList<>();
	private ArrayList<Adult> adults = new ArrayList<>();
	private ArrayList<AutomaticDebit> automaticDebits = new ArrayList<>();
	private ArrayList<CardNumber> cardNumbers = new ArrayList<>();
	private ArrayList<Child> childs = new ArrayList<>();
	private ArrayList<CreditCard> creditCards = new ArrayList<>();
	private ArrayList<Device> devices = new ArrayList<>();
	private ArrayList<Direction> directions = new ArrayList<>();
	private ArrayList<Director> directors = new ArrayList<>();
	private ArrayList<Evaluation> evaluations = new ArrayList<>();
	private ArrayList<Exhibition> exhibitions = new ArrayList<>();
	private ArrayList<Friendship> friendships = new ArrayList<>();
	private ArrayList<GenderPreference> genderPreferences = new ArrayList<>();
	private ArrayList<Genre> genres = new ArrayList<>();
	private ArrayList<Invoice> invoices = new ArrayList<>();
	private ArrayList<Language> languages = new ArrayList<>();
	private ArrayList<Media> medias = new ArrayList<>();
	private ArrayList<MediaAudio> mediaAudios = new ArrayList<>();
	private ArrayList<MediaGender> mediaGenders = new ArrayList<>();
	private ArrayList<MediaSubtitle> mediaSubtitles = new ArrayList<>();
	private ArrayList<Opinion> opinions = new ArrayList<>();
	private ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
	private ArrayList<Paypal> paypals = new ArrayList<>();
	private ArrayList<Profile> profiles = new ArrayList<>();
	private ArrayList<Recommendation> recommendations = new ArrayList<>();
	private ArrayList<Season> seasons = new ArrayList<>();
	private ArrayList<Serie> series = new ArrayList<>();
	private ArrayList<Plan> plans = new ArrayList<>();
	
	private static ObservableList<User>userObsList;
	private ObservableList<Plan> planObsList;
	
	private ArrayList<String> statements = new ArrayList<>(); 
	private String currentUserCpf = null;
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
		Tooltip.install(light,
				makeBubble(new Tooltip("Desconectado, por favor se concete ao banco atravï¿½s do botï¿½o xxx")));
		light.setFill(javafx.scene.paint.Color.RED);
		initializeData();
	}

	private void initializeData() {
		userObsList = FXCollections.observableArrayList(new ArrayList<User>());
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
		if(connected) {
			if (userNameField.getText().length() != 0 && userEmailField.getText().length() != 0
					&& userCpfField.getText().length() != 0 && userDateOfBirthField.getText().length() != 0
					&& userPlanComboBox.getSelectionModel().isEmpty() == false) {
				if (userSaveButton.getText().equals("Salvar")) {
					newEventLog("Usuï¿½rio", LogActivities.UPDATE,
							LogActivities.UPDATE.getLogDescription() + " usuï¿½rio de cpf: " + currentUserCpf, LogTypes.CRUD);
					String queryInsert = "UPDATE Usuario u SET u.CPF = " + userCpfField.getText() 
						+ ", u.Nome = " + userNameField.getText() + ", u.Email = " + userEmailField.getText() 
						+ ", u.DataNasc = TO_DATE('" + userDateOfBirthField.getText() + "', '" + "yyyy/mm/dd" +  "')" 
						+ ", u.Plano = " + userPlanComboBox.getSelectionModel().getSelectedItem().getId() 
						+ " WHERE " + "u.CPF = " + userCpfField.getText();
					System.out.println("RUNNING QUERY: " + queryInsert);
					PreparedStatement pstmt;
					try {
						pstmt = OracleConnection.getConnection().prepareStatement(queryInsert);
						pstmt.executeUpdate();
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					Alerts.showAlert("Atualizaï¿½ï¿½o", "Usuï¿½rio atualizado", "O usuï¿½rio foi editado com sucesso!",
							AlertType.INFORMATION);
				} else {
//					String newDate = new SimpleDateFormat("yyyy-MM-dd").format(userDateOfBirthField.getText());
					System.out.println(userDateOfBirthField.getText());
					String queryInsert = "INSERT INTO Usuario VALUES ('" 
							+ userCpfField.getText() + "', '" + userNameField.getText() + "', '"
							+ userEmailField.getText()  + "', " + "TO_DATE('" + userDateOfBirthField.getText() + "', '" + "yyyy/mm/dd" +  "')" + ", '" + userPlanComboBox.getSelectionModel().getSelectedItem().getId() + "')";
					System.out.println("RUNNING QUERY: " + queryInsert);
					PreparedStatement pstmt;
					try {
						pstmt = OracleConnection.getConnection().prepareStatement(queryInsert);
						pstmt.executeUpdate();
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					Alerts.showAlert("Cadastro", "Novo usuï¿½rio criado", "O usuï¿½rio foi salvo com sucesso!",
							AlertType.INFORMATION);
					
					newEventLog("Usuï¿½rio", LogActivities.CREATE,
							LogActivities.CREATE.getLogDescription() + " novo usuï¿½rio: " + userNameField.getText(),
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
						"Os campos que possuem '*' são obrigatórios", AlertType.ERROR);
			}
		}else {
			Alerts.showAlert("Erro", "Você não se conectou ao banco!",
					"Para realizar operações de crud,consulta e visualização das tabelas, você precisa se conectar ao banco primeiro, vá até a aba de Conexão e preenche os campos necessários para se conectar", AlertType.ERROR);
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
				"Ao deletado um usuário ele será permanentemente removido do banco de dados juntamente com seus perfis, preferências e quaisquer daods coletados sobre o mesmo");
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
		if(connected) {
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
		}else {
			Alerts.showAlert("Erro", "Você não se conectou ao banco!",
					"Para realizar operações de crud,consulta e visualização das tabelas, você precisa se conectar ao banco primeiro, vá até a aba de Conexão e preenche os campos necessários para se conectar", AlertType.ERROR);
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
			sqlParser(System.getProperty("user.dir")+"/BD.sql");
			newEventLog("Usuï¿½rio", LogActivities.CONNECTING_DB,
					LogActivities.CONNECTING_DB.getLogDescription() + " no host " + connectionHostNameField
							+ ", na porta " + connectionPortField + ", no SID " + connectionSidField,
					LogTypes.CONNECTION);
			Alerts.showAlert("Conexão", "Conexão realizada!",
					"A conexão foi realizada com sucesso, e tabelas foram criadas e populadas usando do script: BD.sql",
					AlertType.INFORMATION);
			onConnectionCleanButton();
			connected = true;
			light.setFill(javafx.scene.paint.Color.LIMEGREEN);
			Tooltip.install(light, makeBubble(new Tooltip("Conectado")));
		} else {
			setUpValidationTextField(connectionUserNameField);
			setUpValidationTextField(connectionPasswordField);
			setUpValidationTextField(connectionHostNameField);
			setUpValidationTextField(connectionPortField);
			setUpValidationTextField(connectionSidField);
			Alerts.showAlert("Erro", "Preencha todos os campos obrigatórios",
					"Os campos que possuem '*' são obrigatórios", AlertType.ERROR);
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
		if(connected) {
			
		}else {
			Alerts.showAlert("Erro", "Você não se conectou ao banco!",
					"Para realizar operações de crud,consulta e visualização das tabelas, você precisa se conectar ao banco primeiro, vá até a aba de Conexão e preenche os campos necessários para se conectar", AlertType.ERROR);
		}
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

	public static ObservableList<User> getUserObsList() {
		return userObsList;
	}

}