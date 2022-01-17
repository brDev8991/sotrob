package stock;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import database.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Entres;

public class ListeDesEntrees implements Initializable{

    @FXML
    private TextField tf_rechercher;
    
    @FXML
    private TableView<Entres> tableEntres;
    
    @FXML
    private TableColumn<Entres, String> column_date;

    @FXML
    private TableColumn<Entres, String> column_num_bon;

    @FXML
    private TableColumn<Entres, String> column_code_article;

    @FXML
    private TableColumn<Entres, String> column_designation;

    @FXML
    private TableColumn<Entres, String> column_qte;

    @FXML
    private TableColumn<Entres, String> column_p_u;

    @FXML
    private TableColumn<Entres, String> column_valeur;

    @FXML
    private TableColumn<Entres, String> column_fournisseur;

    @FXML
    private TableColumn<Entres, String> column_facture;
    
    static ObservableList<Entres> observableList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		refreshTable();
	}
	
    public void refreshTable() {
    	
    	observableList.clear();
    	
		// TODO Auto-generated method stub
    	String sql = "SELECT * FROM entres";
		
		ResultSet rSet;
		try {
			rSet = DBConnection.getConnection().createStatement().executeQuery(sql);
			
			while (rSet.next()) {
				observableList.add(new Entres(
						rSet.getString(1),
						rSet.getString(2),
						rSet.getString(3),
						rSet.getString(4),
						rSet.getString(5),
						rSet.getString(6),
						rSet.getString(7),
						rSet.getString(8),
						rSet.getString(9)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		column_date.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
		column_num_bon.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumBon()));
		column_code_article.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodeArticle()));
		column_designation.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDesignation()));
		column_qte.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getQte()));
		column_p_u.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrixUnitaire()));
		column_valeur.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValeur()));
		column_fournisseur.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFournisseur()));
		column_facture.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFacture()));
		
		tableEntres.setItems(observableList);
		
		FilteredList<Entres> filteredList = new FilteredList<>(observableList, e -> true);
		tf_rechercher.setOnKeyReleased(e -> {
			tf_rechercher.textProperty().addListener((observableValue, oldValue, newValue) -> {
				filteredList.setPredicate((Predicate<? super Entres>) entres -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (String.valueOf(entres.getDate()).contains(lowerCaseFilter) ||
							entres.getNumBon().contains(lowerCaseFilter) ||
							entres.getCodeArticle().contains(lowerCaseFilter) ||
							entres.getDesignation().contains(lowerCaseFilter) ||
							entres.getQte().contains(lowerCaseFilter) ||
							entres.getPrixUnitaire().contains(lowerCaseFilter) ||
							entres.getValeur().contains(lowerCaseFilter) ||
							entres.getFacture().contains(lowerCaseFilter) ||
							entres.getFournisseur().contains(lowerCaseFilter)) {
						return true;	
					}					
					return false;
				});
			});
		});
		
		SortedList<Entres> sortedList = new SortedList<Entres>(filteredList);
		sortedList.comparatorProperty().bind(tableEntres.comparatorProperty());
		tableEntres.setItems(sortedList);
	}


}
