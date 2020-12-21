package gui;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import db.OracleConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Plan;
import model.entities.User;

public class QueryViewController implements Initializable {
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
		setUpValidationTextFieldCheckBox(cbName);
		setUpValidationTextFieldCheckBox(cbEmail);
		setUpValidationTextFieldCheckBox(cbCpf);
		setUpValidationDatePickerCheckBox(cbDateOfBirth);
		setUpValidationComboBoxCheckBox(cbPlan);
	}
	
	private void setUpValidationTextFieldCheckBox(final CheckBox cb) {
		cb.selectedProperty().addListener(new ChangeListener<>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				setEditableTextField();
			}
		});
		setEditableTextField();
	}
	
	private void setUpValidationDatePickerCheckBox(final CheckBox cb) {
		cb.selectedProperty().addListener(new ChangeListener<>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				setEditableDatePicker();
			}
		});
		setEditableDatePicker();
	}
	
	private void setUpValidationComboBoxCheckBox(final CheckBox cb) {
		cb.selectedProperty().addListener(new ChangeListener<>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				setEditableComboBox();
			}
		});
		setEditableComboBox();
	}
	
	private void setEditableTextField() {
		if(cbName.isSelected()) queryNameField.setEditable(true);
		else {
			queryNameField.setEditable(false);
			queryNameField.clear();
		}
		if(cbEmail.isSelected()) queryEmailField.setEditable(true);
		else {
			queryEmailField.setEditable(false);
			queryEmailField.clear();
		}
		if(cbCpf.isSelected()) {
			queryCpfField.setEditable(true);
			queryNameField.setEditable(false);
			queryNameField.clear();
			queryEmailField.setEditable(false);
			queryEmailField.clear();
			queryCpfField.setEditable(false);
			queryCpfField.clear();
			queryDateOfBirthDatePicker.setEditable(false);
			queryDateOfBirthDatePicker.getEditor().clear();
			queryPlanComboBox.setEditable(false);
			queryPlanComboBox.getSelectionModel().clearSelection();
		}
		else {
			queryCpfField.setEditable(false);
			queryCpfField.clear();
		}
	}
	
	private void setEditableDatePicker() {
		if(cbDateOfBirth.isSelected() && cbCpf.isSelected() == false) queryDateOfBirthDatePicker.setEditable(true);
		else {
			queryDateOfBirthDatePicker.setEditable(false);
			queryDateOfBirthDatePicker.getEditor().clear();
		}
	}
	
	private void setEditableComboBox() {
		if(cbPlan.isSelected() && cbCpf.isSelected() == false) queryPlanComboBox.setEditable(true);
		else {
			queryPlanComboBox.setEditable(false);
			queryPlanComboBox.getSelectionModel().clearSelection();
		}
	}
	
	public void onQueryButton() {
		String query = "SELECT * FROM Usuario u WHERE ";
		if(cbCpf.isSelected()) {
			query = query.concat("u.CPF = "+queryCpfField.getText()+";");
		}else {
			if(cbName.isSelected()) query = query.concat("u.Nome LIKE '%"+queryNameField.getText()+"%' AND ");
			if(cbEmail.isSelected()) query = query.concat("u.Email LIKE '%"+queryEmailField.getText()+"%' AND ");
			if(cbDateOfBirth.isSelected()) query = query.concat("u.DataNasc = TO_DATE('"+queryDateOfBirthDatePicker.getEditor().getText()+"', 'dd/mm/yyy') AND ");
			if(cbPlan.isSelected()) query = query.concat("u.plano = "+queryPlanComboBox.getSelectionModel().getSelectedItem().getId()+" AND ");
			query = query.substring(0, query.length()-5);
		}
		try {
			stmt = OracleConnection.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			MainViewController.getUserObsList().clear();
	        while (rs.next()) {
	        	MainViewController.getUserObsList().add(new User(rs.getString("Nome"), rs.getString("CPF"), rs.getString("Email"), sdf.parse(rs.getString("DataNasc")), Integer.parseInt(rs.getString("Plano"))));    
	        }
	        stmt.close();
			rs.close();
		} catch (SQLException | NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
		((Stage)queryButton.getScene().getWindow()).close();
	}
     
}
