package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
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
import pdf.PDFManager;

public class MainViewController implements Initializable {
	
	private static ObservableList<User>userObsList;
	private static ObservableList<Plan> planObsList;
	
	private ArrayList<String> createStatements = new ArrayList<>();
	private ArrayList<String> populatStatements = new ArrayList<>();
	private ArrayList<String> searchStatements = new ArrayList<>();
	private ArrayList<String> dropStatements = new ArrayList<>();
	private static ArrayList<String> searchQuerys = new ArrayList<>();
	private String currentUserCpf = null;
	private final String SQUARE_BUBBLE = "M24 1h-24v16.981h4v5.019l7-5.019h13z";
	private boolean connected = false;
	private boolean created = false;
	private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection connection;

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
	private Button connectionCreateTablesButton;
	@FXML
	private Button connectionShowTableButton;
	@FXML
	private Button connectionDeleteTablesButton;
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
	private DatePicker userDateOfBirthField;
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
	private Button userGeneratePDFeButton;

	@FXML
	private Circle light;
	@FXML
	private TextArea logsTextArea;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Tooltip.install(light,
				makeBubble(new Tooltip("Desconectado, por favor se concete ao banco através do botão Conectar na aba de Conexão")));
		light.setFill(javafx.scene.paint.Color.RED);
		initializeData();
	}

	private void initializeData() {
		userObsList = FXCollections.observableArrayList(new ArrayList<User>());
		planObsList = FXCollections.observableArrayList(new ArrayList<Plan>());
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
		userPlanTableColumn.setCellValueFactory(new PropertyValueFactory<>("plan"));
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
					userDateOfBirthField.setValue(LocalDate.of(Integer.parseInt(rowData.getDateOfBirth().substring(0, 4)),
						Integer.parseInt(rowData.getDateOfBirth().substring(5, 7)),
							Integer.parseInt(rowData.getDateOfBirth().substring(8, 10))));				
					int index = 0;
					for (int i = 0; i < planObsList.size(); i++) {
						if (planObsList.get(i).getId() == rowData.getPlan()) {
							index = i;
							break;
						}
					}
					userPlanComboBox.getSelectionModel().select(planObsList.get(index));
					userSaveButton.setText("Salvar");
					userCleanButton.setText("Cancelar");
				}
			});
			return row;
		});
	}

	// CRUD
	public void onUserSaveButton() {
		if(created) {
			if (userNameField.getText().length() != 0 && userEmailField.getText().length() != 0
					&& userCpfField.getText().length() != 0 && userDateOfBirthField.getEditor().getText().length() != 0
					&& userPlanComboBox.getSelectionModel().isEmpty() == false) {
				if (userSaveButton.getText().equals("Salvar")) {
					String updateQuery = "UPDATE Usuario u SET u.CPF = " + userCpfField.getText() 
						+ ", u.Nome = '" + userNameField.getText() + "', u.Email = '" + userEmailField.getText() 
						+ "', u.DataNasc = TO_DATE('" + userDateOfBirthField.getEditor().getText() + "', '" + "dd/mm/yyyy" +  "')"
						+ ", u.Plano = " + userPlanComboBox.getSelectionModel().getSelectedItem().getId() 
						+ " WHERE " + "u.CPF = " + currentUserCpf;
					newCRUDQuery(LogActivities.UPDATE, LogActivities.CREATE.getLogDescription(), updateQuery);
					PreparedStatement pstmt;
					try {
						pstmt = connection.prepareStatement(updateQuery);
						pstmt.executeUpdate();
						pstmt.close();
						Alerts.showAlert("Atualização", "Usuário atualizado", "O usuário foi editado com sucesso!",
								AlertType.INFORMATION);
					} catch (SQLException e) {
						Alerts.showAlert("Exceção", "Erro ao atualizar usuário", e.getMessage(),
								AlertType.ERROR);
					}
				} else {
					String insertQuery = "INSERT INTO Usuario VALUES (" 
							+ userCpfField.getText() + ", '" + userNameField.getText() + "', '"
							+ userEmailField.getText()  + "', " + "TO_DATE('" + userDateOfBirthField.getEditor().getText() + "', '" + "dd/mm/yyyy" +  "')" + ", " + userPlanComboBox.getSelectionModel().getSelectedItem().getId() + ")";
					newCRUDQuery(LogActivities.UPDATE, LogActivities.CREATE.getLogDescription(), insertQuery);
					PreparedStatement pstmt;
					try {
						pstmt = connection.prepareStatement(insertQuery);
						pstmt.executeUpdate();
						pstmt.close();
						Alerts.showAlert("Cadastro", "Novo usuário criado", "O usuário foi salvo com sucesso!",
								AlertType.INFORMATION);
					} catch (SQLException e) {
						Alerts.showAlert("Exceção", "Erro ao cadastrar usuário", e.getMessage(),
								AlertType.ERROR);
					}
				}
				onUserCleanButton();
			} else {
				setUpValidationTextField(userNameField);
				setUpValidationTextField(userEmailField);
				setUpValidationTextField(userCpfField);
				setUpValidationPlanComboBox(userPlanComboBox);
				Alerts.showAlert("Erro", "Preencha todos os campos obrigatórios",
						"Os campos que possuem '*' são obrigatórios", AlertType.ERROR);
			}
		}else {
			Alerts.showAlert("Erro", "Você não criou as tabelas ainda",
					"Para realizar operações de crud, consulta e visualização das tabelas, você precisa estar conectado ao banco"
					+ "e ter criado as tabelas, vá até a aba de Conexão e preencha os campos necessários para se conectar caso não o tenha feito"
					+ "e clique no botão Criar e Popular Tabelas", AlertType.ERROR);
		}
	}

	public void onUserCleanButton() {
		userNameField.clear();
		userEmailField.clear();
		userCpfField.clear();
		userDateOfBirthField.getEditor().clear();
		userPlanComboBox.getSelectionModel().clearSelection();
		userSaveButton.setText("Adicionar");
		userCleanButton.setText("Limpar");
		removeErrorTextField(userNameField);
		removeErrorTextField(userEmailField);
		removeErrorTextField(userCpfField);
		removeErrorComboBox(userPlanComboBox);
	}

	public void onUserDeleteButton() {
		if(userObsList.size() !=0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmar exclusão");
			alert.setHeaderText("Você tem certeza que quer deletar esses usuários?");
			alert.setContentText(
					"Ao deletado um usuário ele será permanentemente removido do banco de dados juntamente com seus perfis, preferências e quaisquer daods coletados sobre o mesmo");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				boolean deleted = false;
				String deleteQuery;
				for (User user : userObsList) {
					if(user.getCheckBox().isSelected()) {
						deleteQuery = "DELETE FROM Usuario u WHERE u.CPF = "+user.getCpf();
						newCRUDQuery(LogActivities.DELETE, LogActivities.DELETE.getLogDescription(), deleteQuery);
						try {
							pstmt = connection.prepareStatement(deleteQuery);
							pstmt.executeUpdate();
							pstmt.close();
							deleted = true;
						} catch (SQLException e) {
							Alerts.showAlert("Exceção", "Erro ao deletar usuário de CPF:" + user.getCpf(), e.getMessage(),
									AlertType.INFORMATION);
						}
						userObsList.remove(user);
					}
				}
				if(deleted)Alerts.showAlert("Exclusão", "Usuários deletados", "Os usuários foram deletados com sucesso!",
						AlertType.INFORMATION);
			} else {
				for (User user : userObsList) {
					user.getCheckBox().setSelected(false);
				}
			}
			alert = null;
		}
	}

	public void onUserSearchButton() {
		if(created) {
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
			Alerts.showAlert("Erro", "Você não criou as tabelas ainda",
					"Para realizar operações de crud, consulta e visualização das tabelas, você precisa estar conectado ao banco"
					+ "e ter criado as tabelas, vá até a aba de Conexão e preencha os campos necessários para se conectar caso não o tenha feito"
					+ "e clique no botão Criar e Popular Tabelas", AlertType.ERROR);
		}
	}
	
	public void onUserGeneratePDF() {
		try {
            PDFManager pdf = new PDFManager();
            //String query = "SELECT Usuario u, Media m WHERE"
            //+"u.Cpf = cpf JOIN Adulto a ON a.Usuario = u.Cpf"
            //+"JOIN LegendaMedia lm ON m.id = lm.Media WHERE lm.Idioma = a.idioma OR lm.Idioma = a.legenda;"
            //stmt = connection.createStatement();
            //rs = stmt.executeQuery(query);
            //while(rs.next()) {
            //}
            pdf.createPDF("Não deu tempo, sorry :(");
            Alerts.showAlert("PDF", "O PDF com nome relatoriofilmes foi gerado com sucesso e se encontra na sua área de trabalho!","Consulta para: Recupere os filmes na base de dados que atendam as preferências de legenda e idioma definidas por\r\n" + 
            		"um dado perfil e que pelo menos um amigo tenha assistido ou comentado",	AlertType.INFORMATION);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}

	// CONNECTION
	public void onConnectionConnectButton() {
		if (connectionUserNameField.getText().length() != 0 && connectionPasswordField.getText().length() != 0
				&& connectionHostNameField.getText().length() != 0 && connectionPortField.getText().length() != 0
				&& connectionSidField.getText().length() != 0) {
			connected = OracleConnection.createConnection(connectionUserNameField.getText(), connectionPasswordField.getText(),
					connectionHostNameField.getText(), connectionPortField.getText(), connectionSidField.getText());
			if(connected) {
				connection = OracleConnection.getConnection();
				DatabaseMetaData dbm;
				try {
					dbm = connection.getMetaData();
					rs = dbm.getTables(null, null, "USUARIO", null);
					if(rs.next()) {
						created = true;
						fillPlanObsList();
						connectionDeleteTablesButton.setDisable(false);
						connectionShowTableButton.setDisable(false);
						userGeneratePDFeButton.setDisable(false);
						userSaveButton.setDisable(false);
						userDeleteButton.setDisable(false);
						userSearchButton.setDisable(false);
						connectionCreateTablesButton.setDisable(true);
					}else {
						connectionCreateTablesButton.setDisable(false);	
					}
					dbm = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				connectionConnectButton.setDisable(true);
				connectionCleanButton.setDisable(true);
				connectionUserNameField.setEditable(false);
				connectionPasswordField.setEditable(false);
				connectionHostNameField.setEditable(false);
				connectionPortField.setEditable(false);
				connectionSidField.setEditable(false);			
				Alerts.showAlert("Conexão", "Conexão realizada!","A conexão foi realizada com sucesso, caso não tenha criada/populado"
						+ "as tabelas, clique no botão Criar e Popular Tabelas",	AlertType.INFORMATION);
				onConnectionCleanButton();
				light.setFill(javafx.scene.paint.Color.LIMEGREEN);
				Tooltip.install(light, makeBubble(new Tooltip("Conectado")));
			}
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
	
	public void onConnectionCreateTablesButton() {
		if(!created  && connected) {
			sqlParser(System.getProperty("user.dir")+"/CreateDB.sql", createStatements);
			executeStatements(createStatements);
			sqlParser(System.getProperty("user.dir")+"/PopulateDB.sql", populatStatements);
			executeStatements(populatStatements);
			fillPlanObsList();
			connectionDeleteTablesButton.setDisable(false);
			connectionShowTableButton.setDisable(false);
			userGeneratePDFeButton.setDisable(false);
			userSaveButton.setDisable(false);
			userDeleteButton.setDisable(false);
			userSearchButton.setDisable(false);
			connectionCreateTablesButton.setDisable(true);
			created = true;
			Alerts.showAlert("Create", "Criação e população da tabela feita!","As tabelas foram criadas e populadas"
					+ " usando dos scripts: CreateDB.sql e PopulateDB.sql",	AlertType.INFORMATION);
		}else{
			Alerts.showAlert("Erro", "Erro ao tentar criar as tabelas","Não foi possível criar as tabelas pois"
					+ "você ou se não concetou ao banco ou já criou as tabelas",	AlertType.ERROR);
		}
	}
	
	public void onConnectionDeleteTablesButton() {
		if(created && connected) {
			sqlParser(System.getProperty("user.dir")+"/DropDB.sql", dropStatements);
			executeStatements(dropStatements);
			planObsList.clear();
			userObsList.clear();
			created = false;
			connectionDeleteTablesButton.setDisable(true);
			connectionShowTableButton.setDisable(true);
			userGeneratePDFeButton.setDisable(true);
			userSaveButton.setDisable(true);
			userDeleteButton.setDisable(true);
			userSearchButton.setDisable(true);
			connectionCreateTablesButton.setDisable(false);
			connectionTableTextArea.clear();
			Alerts.showAlert("Delete", "Exclusão das tabelas feita!","Todas as tabelas do banco foram excluídas com sucesso",AlertType.INFORMATION);
		}else {
			Alerts.showAlert("Erro", "Erro ao tentar deletar as tabelas","Não foi possível deletar as tabelas pois "
					+ "você ou não concetou ao banco ou já deletou as tabelas",	AlertType.ERROR);
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
		if(created) {
			connectionTableTextArea.clear();
			sqlParser(System.getProperty("user.dir")+"/ShowAllDB.sql", searchStatements);
			try {
				String columnLabel;
				stmt = connection.createStatement();
				for(String query : searchStatements) {
					rs = stmt.executeQuery(query);
					newCRUDQuery(LogActivities.READ, LogActivities.READ.getLogDescription(), query);
					int numCols = rs.getMetaData().getColumnCount();
					connectionTableTextArea.appendText(query.substring(13, query.length())+"\n<");
					while(rs.next()) {
						for(int i=0;i<numCols;i++) {
							columnLabel = rs.getMetaData().getColumnName(i+1);
							connectionTableTextArea.appendText(rs.getString(columnLabel)+",");
						}
						connectionTableTextArea.deletePreviousChar();
						connectionTableTextArea.appendText(">\n");
					}
				}
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			Alerts.showAlert("Erro", "Você não criou as tabelas ainda",
					"Para realizar operações de crud, consulta e visualização das tabelas, você precisa estar conectado ao banco"
					+ "e ter criado as tabelas, vá até a aba de Conexão e preencha os campos necessários para se conectar caso não o tenha feito"
					+ "e clique no botão Criar e Popular Tabelas", AlertType.ERROR);
		}
	}
	
	private void sqlParser(String filePath, ArrayList<String> statements) {
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
    	        buffer.append(line.replace(";", ""));
    	        if (line.endsWith(delimiter)) {    	
    	            statements.add(buffer.toString());    	       
    	            buffer.setLength(0);
    	        }
    	    }
    	}catch (IOException e) {
    		Alerts.showAlert("Erro", "Erro ao tentar abrir arquivo",e.getMessage(),	AlertType.ERROR);
    	}
	}
	
	private void executeStatements(ArrayList<String> statements) {
		if(connected) {
			try {
				for(String query : statements) {
					newCRUDQuery(LogActivities.CREATE, LogActivities.CREATE.getLogDescription(), query);
					pstmt = connection.prepareStatement(query);
					pstmt.executeUpdate();
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void fillPlanObsList() {
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Plano");
			newCRUDQuery(LogActivities.READ, LogActivities.READ.getLogDescription(), "SELECT * FROM Plano");
			while(rs.next()) {
				planObsList.add(new Plan(Integer.parseInt(rs.getString("ID")), rs.getString("NOME"), 
						Float.parseFloat(rs.getString("VALOR")), Integer.parseInt(rs.getString("QUALIDADEMAX")), 
						Integer.parseInt(rs.getString("QTDTELAS")), Integer.parseInt(rs.getString("NUMMAXPERFIS"))));
			}
		} catch (SQLException e) {
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

	private void newCRUDQuery(LogActivities activity, String description, String query) {
		logsTextArea.appendText("\"" + new Date().toString() + "\", \"," + activity.toString() + "\",\""
				+ description + "\"\n"+query+"\n");
	}
	
	public void newSearchQuery() {
		for(String query: searchQuerys) {
			newCRUDQuery(LogActivities.SELECT, LogActivities.SELECT.getLogDescription(), query+"\n");
		}
		searchQuerys.clear();
	}

	public void clearObjects() {
		userObsList.clear();
		planObsList.clear();
	}

	public static ObservableList<User> getUserObsList() {
		return userObsList;
	}

	public static ObservableList<Plan> getPlanObsList() {
		return planObsList;
	}
	
	public static ArrayList<String> getSearchQuerys() {
		return searchQuerys;
	}

}