package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {

	@FXML
    private AnchorPane anchorPane;
	
    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_password;
    
    @FXML
    private Label label_error;

    @FXML
    void exit(ActionEvent event) {
    	Stage stage = (Stage) anchorPane.getScene().getWindow();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Close Window");
		alert.setHeaderText("You're about to close window ?");
		
		if (alert.showAndWait().get() == ButtonType.OK) {
			stage.close();
			System.out.println("You successfully logged out !");
		}
    }

    @FXML
    void login(ActionEvent event) {
    	if (!tf_username.getText().isEmpty() && !tf_password.getText().isEmpty()) {
    		try {
    			if (DBConnection.getConnection() != null) {
    				boolean connected = DBConnection.userDBLogin(tf_username.getText().trim(), tf_password.getText().trim());
            		if (!connected) {
						label_error.setText("User : " + tf_username.getText() + " Not found");
					}else {
						label_error.setText("User : " + tf_username.getText() + " Connected");

						Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.setTitle("Dashboard");
						stage.setMaximized(true);
						stage.setScene(scene);
						stage.initStyle(StageStyle.UNDECORATED);
						//stage.setFullScreen(true);
						stage.show();
					}
                	DBConnection.closeConnection();
    			}
			} catch (Exception e) {
				e.printStackTrace();
				label_error.setText("non connexion !");
			}
		}else {
			label_error.setText("svp saisie tous les champ !");
		}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


}
