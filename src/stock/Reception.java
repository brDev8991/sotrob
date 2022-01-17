package stock;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


import database.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Acticle;
import models.Row;

public class Reception implements Initializable {

    @FXML
    private TextField tf_code_fournisseur;

    @FXML
    private Spinner<Integer> sp_mode_paiement;
    
    int currentSpinnerValue;

    @FXML
    private TextField tf_paiement;

    @FXML
    private TextField tf_facture;

    @FXML
    private Label lb_date;

    @FXML
    private Label lb_type_bon;

    @FXML
    private Label lb_num_bon;
    
    @FXML
    private TableView<Row> tableReception;

    @FXML
    private TableColumn<Row, String> col_code_article;

    @FXML
    private TableColumn<Row, String> col_qte;

    @FXML
    private TableColumn<Row, String> col_p_u;

    @FXML
    private TableColumn<Row, String> col_montant;

    @FXML
    private Label lb_totale;
    
    static ObservableList<Row> observableList = FXCollections.observableArrayList();

    @FXML
    void ajouter(ActionEvent event) {
    	Parent rootParent;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddingArticle.fxml"));
			rootParent = loader.load();
			AddingArticle addingArticle = loader.getController();
			addingArticle.setParentController(this);
			addingArticle.setParentName("Reception");
			Scene scene = new Scene(rootParent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 	
    }
    
    public void addingRow(Row row) {
    	observableList.add(row);
    	col_code_article.setCellValueFactory(new PropertyValueFactory<Row, String>("codeArticle"));
    	col_qte.setCellValueFactory(new PropertyValueFactory<Row, String>("qte"));
    	col_p_u.setCellValueFactory(new PropertyValueFactory<Row, String>("prix_unitaire"));
    	col_montant.setCellValueFactory(new PropertyValueFactory<Row, String>("montant"));
    	tableReception.setItems(observableList);
    }

    @FXML
    void checkFournisseur(ActionEvent event) throws SQLException {
    	if(tf_code_fournisseur.getText().isEmpty()) {
    		showMessage("s.v.p saisie le code de fournisseur !", "WARNING");
    	}else {
    		if(DBConnection.getConnection() != null && 
    				DBConnection.checkFournisseurExistance(tf_code_fournisseur.getText())) {
    			showMessage("LIBELLE : " + DBConnection.getFournisseurLibelle(tf_code_fournisseur.getText()), "INFORMATION");
    		}else {
    			showMessage("Fournisseur N'existe pas !", "WARNING");
    		}
    	}
    }

    private void showMessage(String msg, String header) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(header);
		alert.setContentText(msg);
		alert.show();
	}

	@FXML
    void vider(ActionEvent event) {
    	observableList.clear();
    	tableReception.setItems(observableList);
    }

    @FXML
    void visualiser(ActionEvent event) throws SQLException {
    	if(tableReception.getSelectionModel().getSelectedItem() != null) {
    		Row row = tableReception.getSelectionModel().getSelectedItem();
        	System.out.print("Selected row article : " + row.getCodeArticle());
        	if(DBConnection.getConnection() != null) {
        		Acticle article = DBConnection.getAtricleByCode(row.getCodeArticle());
            	showMessage("Code Article :      " + article.getCodeAtricle() + "\n"
            			+ "Designation :         " + article.getLibelle() + "\n" 
            			+ "Unite De Mesure :       " + article.getUniteMesure() + "\n" 
            			+ "Quantite En Stock :      " + article.getQuantite() + "\n", "INFORAMTION");
        	}
        	
    	}else
    		showMessage("s.v.p choisissez un article !", "WARNING");
    }
    
    @FXML
    void faireLeBon(ActionEvent event) {

    }

    @FXML
    void sauvgarder(ActionEvent event) {

    }

	@SuppressWarnings("null")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		lb_type_bon.setText("Reception");
		lb_date.setText(getCurrentDate());
		try {
			if(DBConnection.getConnection() != null) {
				int bonNum = DBConnection.getNumBon("reception") + 1;
				lb_num_bon.setText(String.valueOf(bonNum));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showMessage("Error with bon number", "WARNING");
		}
		
		//Set spinner values
		SpinnerValueFactory<Integer> valueFactory = 
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4);
		
		valueFactory.setValue(1);
		sp_mode_paiement.setValueFactory(valueFactory);		
		currentSpinnerValue = sp_mode_paiement.getValue();
		
		sp_mode_paiement.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				currentSpinnerValue = sp_mode_paiement.getValue();

				switch(currentSpinnerValue) {
				case 2 : tf_paiement.setPromptText("CHEQUE");
				break;
				case 3 : tf_paiement.setPromptText("CPA");
				break;
				case 4 : tf_paiement.setPromptText("BNA");
				break;
			}
			}
		});
		
	}
	
	public String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
	}

}
