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
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Acticle;
import models.Row;

public class Cession implements Initializable {

    @FXML
    private TextField tf_imputation;

    @FXML
    private Spinner<Integer> sp_destination;

    @FXML
    private TextField tf_destination;

    @FXML
    private TextField tf_facture;

    @FXML
    private Label lb_date;

    @FXML
    private Label lb_type_bon;

    @FXML
    private Spinner<Integer> sp_cession_type;

    @FXML
    private Label lb_num_bon;

    @FXML
    private TableView<Row> tableCession;

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

	int currentSpinnerValue;
	int currentSpinnerValueCession;
	
    @FXML
    void ajouter(ActionEvent event) {
    	Parent rootParent;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddingArticle.fxml"));
			rootParent = loader.load();
			AddingArticle addingArticle = loader.getController();
			addingArticle.setParentController(this);
			addingArticle.setParentName("Cession");
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
    	tableCession.setItems(observableList);
    }

    @FXML
    void checkClient(ActionEvent event) throws SQLException {
    	if(tf_imputation.getText().isEmpty()) {
    		showMessage("s.v.p saisie le code de client !", "WARNING");
    	}else {
    		if(DBConnection.getConnection() != null && 
    				DBConnection.checkClientExistance(tf_imputation.getText())) {
    			showMessage("LIBELLE : " + DBConnection.getFournisseurLibelle(tf_imputation.getText()), "INFORMATION");
    		}else {
    			showMessage("Client N'existe pas !", "WARNING");
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
    	tableCession.setItems(observableList);
    }

    @FXML
    void visualiser(ActionEvent event) throws SQLException {
    	if(tableCession.getSelectionModel().getSelectedItem() != null) {
    		Row row = tableCession.getSelectionModel().getSelectedItem();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		lb_date.setText(getCurrentDate());
		
		//Set spinner values for destination
		SpinnerValueFactory<Integer> valueFactory = 
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4);
		
		valueFactory.setValue(1);
		sp_destination.setValueFactory(valueFactory);		
		currentSpinnerValue = sp_destination.getValue();
		tf_destination.setText("ATELIER 1");
		
		sp_destination.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				currentSpinnerValue = sp_destination.getValue();

				switch(currentSpinnerValue) {
					case 1 : tf_destination.setText("ATELIER 1");
					break;
					case 2 : tf_destination.setText("ATELIER 2");
					break;
					case 3 : tf_destination.setText("ATELIER 3");
					break;
					case 4 : tf_destination.setText("ATELIER 4");
					break;
				}
			}
		});
		
		//Set spinner values for type de bon
		SpinnerValueFactory<Integer> valueFactoryCession = 
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2);
		
		valueFactoryCession.setValue(1);
		sp_cession_type.setValueFactory(valueFactoryCession);		
		currentSpinnerValueCession = sp_cession_type.getValue();
		
		lb_type_bon.setText("CESSION INTERNE");
		try {
			if(DBConnection.getConnection() != null) {
				int bonNum = DBConnection.getNumBon("cession_interne") + 1;
				lb_num_bon.setText(String.valueOf(bonNum));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showMessage("Error with bon number", "WARNING");
		}
		
		sp_cession_type.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				currentSpinnerValueCession = sp_cession_type.getValue();

				switch(currentSpinnerValueCession) {
					case 1 : lb_type_bon.setText("CESSION INTERNE");
					try {
						if(DBConnection.getConnection() != null) {
							int bonNum = DBConnection.getNumBon("cession_interne") + 1;
							lb_num_bon.setText(String.valueOf(bonNum));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						showMessage("Error with bon number", "WARNING");
					}
					break;
					case 2 : lb_type_bon.setText("CESSION EXTERNE");
					try {
						if(DBConnection.getConnection() != null) {
							int bonNum = DBConnection.getNumBon("cession_externe") + 1;
							lb_num_bon.setText(String.valueOf(bonNum));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						showMessage("Error with bon number", "WARNING");
					}
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
