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
import models.Sorties;

public class ListeDesSorties implements Initializable{

    @FXML
    private TextField tf_rechercher;

    @FXML
    private TableView<Sorties> tableSorties;

    @FXML
    private TableColumn<Sorties, String> col1;

    @FXML
    private TableColumn<Sorties, String> col2;

    @FXML
    private TableColumn<Sorties, String> col3;

    @FXML
    private TableColumn<Sorties, String> col4;

    @FXML
    private TableColumn<Sorties, String> col5;

    @FXML
    private TableColumn<Sorties, String> col6;

    @FXML
    private TableColumn<Sorties, String> col7;
    
    @FXML
    private TableColumn<Sorties, String> col8;
    
    static ObservableList<Sorties> observableList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		refreshTable();
	}

    public void refreshTable() {
    	
    	observableList.clear();
    	
		// TODO Auto-generated method stub
    	String sql = "SELECT * FROM sorties";
		
		ResultSet rSet;
		try {
			rSet = DBConnection.getConnection().createStatement().executeQuery(sql);
			
			while (rSet.next()) {
				observableList.add(new Sorties(
						rSet.getString(1),
						rSet.getString(2),
						rSet.getString(3),
						rSet.getString(4),
						rSet.getString(5),
						rSet.getString(6),
						rSet.getString(7),
						rSet.getString(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		col1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
		col2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumBonExt()));
		col3.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDesignation()));
		col4.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getQte()));
		col5.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrixUnitaire()));
		col6.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDestination()));
		col7.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getImputation()));
		col8.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCessionType()));
		
		tableSorties.setItems(observableList);
		
		FilteredList<Sorties> filteredList = new FilteredList<>(observableList, e -> true);
		tf_rechercher.setOnKeyReleased(e -> {
			tf_rechercher.textProperty().addListener((observableValue, oldValue, newValue) -> {
				filteredList.setPredicate((Predicate<? super Sorties>) sorties -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (String.valueOf(sorties.getNumBonExt()).contains(lowerCaseFilter) ||
							sorties.getDesignation().contains(lowerCaseFilter) ||
							sorties.getQte().contains(lowerCaseFilter) ||
							sorties.getPrixUnitaire().contains(lowerCaseFilter) ||
							sorties.getDestination().contains(lowerCaseFilter) ||
							sorties.getImputation().contains(lowerCaseFilter) ||
							sorties.getDate().contains(lowerCaseFilter) ||
							sorties.getCessionType().contains(lowerCaseFilter)) {
						return true;	
					}					
					return false;
				});
			});
		});
		
		SortedList<Sorties> sortedList = new SortedList<Sorties>(filteredList);
		sortedList.comparatorProperty().bind(tableSorties.comparatorProperty());
		tableSorties.setItems(sortedList);
	}


}
