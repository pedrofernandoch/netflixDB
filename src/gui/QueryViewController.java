package gui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import db.OracleConnection;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Plan;
import model.entities.User;

public class QueryViewController implements Initializable {
	
	private Statement stmt;
	private ResultSet rs;
	
	@FXML
    private CheckBox cbName;

    @FXML
    private TextField queryNameField;

    @FXML
    private CheckBox cbEmail;

    @FXML
    private TextField queryEmailField;

    @FXML
    private CheckBox cbCpf;

    @FXML
    private TextField queryCpfField;

    @FXML
    private CheckBox cbDateOfBirth;
    
    @FXML
    private DatePicker queryDateOfBirthDatePicker;

    @FXML
    private CheckBox cbPlan;

    @FXML
    private ComboBox<Plan> queryPlanComboBox;
    
    @FXML
    private Button queryButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		queryPlanComboBox.setItems(MainViewController.getPlanObsList());
	}
	
	public void onQueryButton() {
		boolean valid = false;
		if(!cbCpf.isSelected() && !cbName.isSelected() && !cbEmail.isSelected() && !cbDateOfBirth.isSelected() && !cbPlan.isSelected()) {
			Alerts.showAlert("Busca", "Busca inválida!",
					"Você precisa selecionar ao menos um campo para realizar a consulta",
					AlertType.ERROR);
		}else {
			String query = "SELECT * FROM Usuario u WHERE ";
			if(cbCpf.isSelected()) {
				if(queryCpfField.getLength() != 11) {
					Alerts.showAlert("Busca", "Formato de CPF inválido!",
							"Para buscar por um CPF digite apenas os 11 dígitos, sem pontos ou traços",
							AlertType.ERROR);
				}else {
					query = query.concat("u.CPF = "+queryCpfField.getText()+";");
					executeQuery(query);
					MainViewController.getSearchQuerys().add(query);
					valid = true;
				}
			}else {
				if(cbName.isSelected()) {
					query = query.concat("u.Nome LIKE '%"+queryNameField.getText()+"%' AND ");
					valid = true;
				}
				if(cbEmail.isSelected()) {
					query = query.concat("u.Email LIKE '%"+queryEmailField.getText()+"%' AND ");
					valid = true;
				}
				if(cbDateOfBirth.isSelected()) {
					query = query.concat("u.DataNasc = TO_DATE('"+queryDateOfBirthDatePicker.getEditor().getText()+"', 'dd/mm/yyyy') AND ");
					valid = true;
				}
				if(cbPlan.isSelected()) {
					if(queryPlanComboBox.getSelectionModel().getSelectedItem() == null) {
						Alerts.showAlert("Busca", "Plano inválido",
								"Selecione algum plano para realizar a busca",
								AlertType.ERROR);
					}else {
						query = query.concat("u.plano = "+queryPlanComboBox.getSelectionModel().getSelectedItem().getId()+" AND ");
						valid = true;
					}
				}
				query = query.substring(0, query.length()-5);
				MainViewController.getSearchQuerys().add(query);
				if(valid)executeQuery(query);
			}
			if(valid)((Stage)queryButton.getScene().getWindow()).close();
		}
	}
	
	private void executeQuery(String query) {
		try {
			stmt = OracleConnection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			MainViewController.getUserObsList().clear();
	        while (rs.next()) {
	        	String date = rs.getString("DataNasc").substring(0,10);
	        	MainViewController.getUserObsList().add(new User(rs.getString("Nome"), rs.getString("CPF"), rs.getString("Email"), date, Integer.parseInt(rs.getString("Plano"))));    
	        }
	        stmt.close();
			rs.close();
		} catch (SQLException | NumberFormatException e) {
			MainViewController.getUserObsList().clear();
			Alerts.showAlert("Busca", "Exceção",e.getMessage(),AlertType.INFORMATION);
		}
	}
     
}
