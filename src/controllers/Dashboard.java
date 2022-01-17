package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Dashboard implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    void basesPane(ActionEvent event) {
    	try {
			openPane("Bases");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void comptaPane(ActionEvent event) {
    	try {
			openPane("Comptabilisation");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void comptesPane(ActionEvent event) {
    	try {
			openPane("Comptes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void statsPane(ActionEvent event) {
    	try {
			openPane("Statistiques");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void stockPane(ActionEvent event) {
    	try {
			openPane("Stock");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void dashboardPane(ActionEvent event) {
    	try {
			openPane("WelcomePage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void openPane(String paneName) throws IOException {
    	AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(paneName + ".fxml"));
    	borderPane.setCenter(anchorPane);
    }
    
    @FXML
    void exit(ActionEvent event) {
    	Stage stage = (Stage) borderPane.getScene().getWindow();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Close Window");
		alert.setHeaderText("You're about to close window ?");
		
		if (alert.showAndWait().get() == ButtonType.OK) {
			stage.close();
			System.out.println("You successfully logged out !");
		}
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			openPane("WelcomePage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
