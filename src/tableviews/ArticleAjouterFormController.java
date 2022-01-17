package tableviews;

import java.sql.SQLException;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ArticleAjouterFormController {

    @FXML
    private BorderPane borderPane;
    
    @FXML
    private TextField tf_code_article;

    @FXML
    private TextField tf_libelle;

    @FXML
    private TextField tf_code_famille;

    @FXML
    private TextField tf_code_marque;

    @FXML
    private TextField tf_code_modele;

    @FXML
    private TextField tf_unite_mesure;

    @FXML
    private TextField tf_code_tva;

    @FXML
    private TextField tf_unite_condit;

    @FXML
    private TextField tf_reference;

    @FXML
    private TextField tf_description;

    @FXML
    private TextField tf_quantite;
    
    @FXML
    void ajouterUnArticle() throws SQLException {
    	if (DBConnection.getConnection() != null) {
			if (!tf_code_article.getText().equals("") || !tf_reference.getText().equals("")) {
				if (DBConnection.checkArticleExistance(tf_code_article.getText())) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Close Window");
					alert.setHeaderText("This article already exist !");
					alert.show();
				}
				DBConnection.ajouterNouveauArticle(tf_code_article.getText(),
		    			tf_libelle.getText(),
		    			tf_code_famille.getText(),
		    			tf_code_marque.getText(),
		    			tf_code_modele.getText(),
		    			tf_unite_mesure.getText(),
		    			tf_code_tva.getText(),
		    			tf_unite_condit.getText(),
		    			tf_reference.getText(),
		    			tf_description.getText(),
		    			tf_quantite.getText());
				clearFields();
				System.out.println("New article added");
				ArticleController articleController = new ArticleController();
				articleController.refreshTable();				
			}
		}	
    }

	private void clearFields() {
		// TODO Auto-generated method stub
    	tf_code_article.setText("");
		tf_libelle.setText("");
		tf_code_famille.setText("");
		tf_code_marque.setText("");
		tf_code_modele.setText("");
		tf_unite_mesure.setText("");
		tf_code_tva.setText("");
		tf_unite_condit.setText("");
		tf_reference.setText("");
		tf_description.setText("");
		tf_quantite.setText("");	
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
