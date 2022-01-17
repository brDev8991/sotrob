package stock;

import java.io.IOException;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Row;

public class AddingArticle {

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
    
    public Reception reception;
    public Cession cession;
    public String nameParent;

    @FXML
    void addArticleRow(ActionEvent event) throws IOException {
    	if(tf1.getText().isEmpty() ||
			tf2.getText().isEmpty() ||
			tf3.getText().isEmpty() ||
			tf4.getText().isEmpty() ) {
    		showMessage("s.v.p remplir tous les fileds", "WARNING");
    	}else if(DBConnection.checkArticleExistance(tf1.getText())){
    		Row row = new Row(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText());
    		if(nameParent.equals("Reception"))
    			reception.addingRow(row);
    		else if(nameParent.equals("Cession"))
    			cession.addingRow(row);
    		exitScreen();
    	}else {
    		showMessage("Artice N'existe Pas à la base !", "WARNING");
    	}
    }
    


    @FXML
    void close(ActionEvent event) {
    	exitScreen();
    }
	
	public void exitScreen() {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		stage.close();
	}
    
    private void showMessage(String msg, String header) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(header);
		alert.setContentText(msg);
		alert.show();
	}

	public void setParentController(Reception reception) {
		// TODO Auto-generated method stub
		this.reception = reception;
	}

	public void setParentController(Cession cession) {
		// TODO Auto-generated method stub
		this.cession = cession;
	}

	public void setParentName(String controllerName) {
		// TODO Auto-generated method stub
		this.nameParent = controllerName;

	}

}
