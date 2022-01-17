package tableviews;

import java.sql.SQLException;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClientFormController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField tf1;

    @FXML
    private TextField tf2;

    @FXML
    private TextField tf3;

    @FXML
    private TextField tf4;

    @FXML
    private TextField tf5;

    @FXML
    private TextField tf6;

    @FXML
    private TextField tf7;

    @FXML
    private TextField tf8;

    @FXML
    private TextField tf9;

    @FXML
    private TextField tf10;

    @FXML
    private TextField tf11;

    @FXML
    private TextField tf12;

    @FXML
    private TextField tf13;

    @FXML
    private TextField tf14;

    @FXML
    private TextField tf15;

    @FXML
    void ajouterClient(ActionEvent event) throws SQLException {
    	if (DBConnection.getConnection() != null) {
			if (!tf1.getText().equals("")) {
				if (DBConnection.checkClientExistance(tf1.getText())) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("WARNING");
					alert.setHeaderText("This CLIENT already exist !");
					alert.show();
				}else {
					DBConnection.ajouterClient(tf1.getText(),
			    			tf2.getText(),
			    			tf3.getText(),
			    			tf4.getText(),
			    			tf5.getText(),
			    			tf6.getText(),
			    			tf7.getText(),
			    			tf8.getText(),
			    			tf9.getText(),
			    			tf10.getText(),
			    			tf11.getText(),
			    			tf12.getText(),
			    			tf13.getText(),
			    			tf14.getText(),
			    			tf15.getText());
					clearFields();
				}
				ClientController clientController = new ClientController();
				clientController.refreshTable();				
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING");
				alert.setHeaderText("Code CLient Obligatoire");
				alert.show();
			}
		}	
    }
    
	private void clearFields() {
		tf1.setText("");
		tf2.setText("");
		tf3.setText("");
		tf4.setText("");
		tf5.setText("");
		tf6.setText("");
		tf7.setText("");
		tf8.setText("");
		tf9.setText("");
		tf10.setText("");
		tf11.setText("");
		tf12.setText("");
		tf13.setText("");
		tf14.setText("");
		tf15.setText("");
	}

    @FXML
    void close(ActionEvent event) {
    	exitScreen();
    }
	
	public void exitScreen() {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		stage.close();
	}

}
