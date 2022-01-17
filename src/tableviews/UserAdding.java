package tableviews;

import java.sql.SQLException;

import controllers.ComptesController;
import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserAdding {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField tf1;

    @FXML
    private TextField tf2;

    @FXML
    private TextField tf3;

    @FXML
    void ajouterUser(ActionEvent event) throws SQLException {
    	if (DBConnection.getConnection() != null) {
			if (!tf1.getText().equals("")) {
				if (DBConnection.checkClientExistance(tf1.getText())) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("WARNING");
					alert.setHeaderText("This CLIENT already exist !");
					alert.show();
				}else {
					DBConnection.ajouterUser(tf1.getText(),
			    			tf2.getText(),
			    			tf3.getText(),
			    			"");
					clearFields();
				}
				ComptesController comptesController = new ComptesController();
				comptesController.refreshTable();				
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
