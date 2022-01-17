package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class StockController {

    @FXML
    private BorderPane borderPane;

    @FXML
    void cession(ActionEvent event) {
    	try {
			openPane("/stock/Cession");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void listeEntrees(ActionEvent event) {
    	try {
			openPane("/stock/ListeDesEntrees");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void listeSorties(ActionEvent event) {
    	try {
			openPane("/stock/ListeDesSorties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void reception(ActionEvent event) {
    	try {
			openPane("/stock/Reception");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void openPane(String paneName) throws IOException {
    	AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(paneName + ".fxml"));
    	borderPane.setCenter(anchorPane);
    }
}
