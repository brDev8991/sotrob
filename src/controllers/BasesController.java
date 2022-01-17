package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class BasesController implements Initializable {
	
	@FXML BorderPane borderPane;

    @FXML
    private Button btn_article;

    @FXML
    void article(ActionEvent event) {
    	try {
			openPane("/tableviews/Article");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void client(ActionEvent event) {
    	try {
			openPane("/tableviews/Client");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void fournisseur(ActionEvent event) {
    	try {
			openPane("/tableviews/Fournisseur");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void openPane(String paneName) throws IOException {
    	AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(paneName + ".fxml"));
    	borderPane.setCenter(anchorPane);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		try {
//			openPane("/tableviews/Article");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
