package tableviews;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Acticle;

public class ArticleUpdateFormController implements Initializable {

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
    
    int id;  
    String codeArticle;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub	
	}

	public void displayValues(int id, String codeAtricle, String libelle, String codeFamille, String codeMarque, String codeModele,
			String uniteMesure, String codeTVA, String uniteCondit, String reference, String description, String quantite) {
		setId(id);
		this.codeArticle = codeAtricle;
		tf_code_article.setText(codeAtricle);
		tf_libelle.setText(libelle);
		tf_code_famille.setText(codeFamille);
		tf_code_marque.setText(codeMarque);
		tf_code_modele.setText(codeModele);
		tf_unite_mesure.setText(uniteMesure);
		tf_code_tva.setText(codeTVA);
		tf_unite_condit.setText(uniteCondit);
		tf_reference.setText(reference);
		tf_description.setText(description);
		tf_quantite.setText(quantite);	
	}

	@FXML
    void close(ActionEvent event) {
    	exitScreen();
    }
	
	public void exitScreen() {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		stage.close();
	}

    @FXML
    void modifierUnArticle(ActionEvent event) throws SQLException {
    	
    	if (DBConnection.getConnection() != null) {
    		
			if (!this.tf_code_article.getText().isBlank()) {
				
				Acticle updatedActicle = new Acticle();
		    	updatedActicle.setCodeAtricle(tf_code_article.getText());
		    	updatedActicle.setCodeFamille(tf_code_famille.getText());
		    	updatedActicle.setLibelle(tf_libelle.getText());
		    	updatedActicle.setCodeMarque(tf_code_marque.getText());
		    	updatedActicle.setCodeModele(tf_code_modele.getText());
		    	updatedActicle.setUniteMesure(tf_unite_mesure.getText());
		    	updatedActicle.setPrixUnitaire(tf_code_tva.getText());
		    	updatedActicle.setUniteCondit(tf_unite_condit.getText());
		    	updatedActicle.setReference(tf_reference.getText());
		    	updatedActicle.setDescription(tf_description.getText());
		    	updatedActicle.setQuantite(tf_quantite.getText());
				
		    	if (DBConnection.checkArticleExistance(updatedActicle.getCodeAtricle()) &&
		    			!updatedActicle.getCodeAtricle().equals(codeArticle)) {
		    		Alert alert = new Alert(AlertType.WARNING);
		    		alert.setTitle("Warning");
		    		alert.setHeaderText("Code Article Already existed !");
		    		alert.show();
		    		tf_code_article.setText(codeArticle);
				} else if (DBConnection.updateArticle(updatedActicle, getId())) {
					updateFields();
				}
					
			}else {
				Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Column empty");
	    		alert.setHeaderText("You're obligated to fill code article !");
	    		alert.show();
	    		tf_code_article.setText(codeArticle);
			}
							
		}
    }
	
	private void updateFields() {
		// TODO Auto-generated method stub
		clearFields();
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Mise à jour");
		alert.setHeaderText("Updated successfully !");
		alert.show();
		exitScreen();
		ArticleController articleController = new ArticleController();
		articleController.refreshTable();	
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

	
}
