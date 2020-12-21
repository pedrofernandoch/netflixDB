package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.Plan;

public class QueryViewController implements Initializable {
	
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
	
	public String onQueryButton() {
		String statement = "SELECT * FROM Usuario u WHERE ";
		if(cbCpf.isSelected()) {
			statement = statement.concat("u.CPF = "+queryCpfField.getText());
			return statement;
		}else {
			
		}
		return statement;
	}
     
}
