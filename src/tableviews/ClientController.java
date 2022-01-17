package tableviews;

import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Client;

public class ClientController implements Initializable {

    @FXML
    private TableView<Client> tableviewClient;

    @FXML
    private TableColumn<Client, Integer> column_id;

    @FXML
    private TableColumn<Client, String> column_libelle;

    @FXML
    private TableColumn<Client, String> column_code_client;

    @FXML
    private TableColumn<Client, String> column_adresse;

    @FXML
    private TableColumn<Client, String> column_code_postal;

    @FXML
    private TableColumn<Client, String> column_ville;

    @FXML
    private TableColumn<Client, String> column_telephone;

    @FXML
    private TableColumn<Client, String> column_fax;

    @FXML
    private TableColumn<Client, String> column_email;

    @FXML
    private TableColumn<Client, String> column_observation;

    @FXML
    private TableColumn<Client, String> column_dscription;

    @FXML
    private TableColumn<Client, String> column_code_fiscale;

    @FXML
    private TableColumn<Client, String> column_art_impos;

    @FXML
    private TableColumn<Client, String> column_num_registre;

    @FXML
    private TableColumn<Client, String> column_num_statistique;

    @FXML
    private TableColumn<Client, String> column_activite;

    @FXML
    private TextField tf_rechercher_client;
    
    static ObservableList<models.Client> observableList = FXCollections.observableArrayList();

    @FXML
    void ajouterClient(ActionEvent event) {  	
    	Parent rootParent;
		try {
			rootParent = FXMLLoader.load(getClass().getResource("ClientForm.fxml"));
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

    @FXML
    void exportTableIntoExcel(ActionEvent event) {

    }

    @FXML
    void modifierClient(ActionEvent event) {

    }

    @FXML
    void supprimerClient(ActionEvent event) {
    	Client client = tableviewClient.getSelectionModel().getSelectedItem();
    	if (client == null) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setHeaderText("You need select the row ?");
    		alert.show();
		}else {
			DBConnection.deleteClient(client.getId());
			refreshTable();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		refreshTable();
	}
	
    public void refreshTable() {	
    	observableList.clear();
    	
		// TODO Auto-generated method stub
    	String sql = "SELECT * FROM clients";
		
		ResultSet rSet;
		try {
			rSet = DBConnection.getConnection().createStatement().executeQuery(sql);
			
			while (rSet.next()) {
				observableList.add(new Client(
						rSet.getInt(1),
						rSet.getString(2),
						rSet.getString(3),
						rSet.getString(4),
						rSet.getString(5),
						rSet.getString(6),
						rSet.getString(7),
						rSet.getString(8),
						rSet.getString(9),
						rSet.getString(10),
						rSet.getString(11),
						rSet.getString(12),
						rSet.getString(13),
						rSet.getString(14),
						rSet.getString(15),
						rSet.getString(16)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		column_code_client.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodeClient()));
		column_libelle.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLibelle()));
		column_adresse.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAdresse()));
		column_code_postal.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodePostal()));
		column_ville.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVille()));
		column_telephone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelephone()));
		column_fax.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFax()));
		column_email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
		column_observation.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getObservation()));
		column_dscription.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
		column_code_fiscale.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodeFiscal()));
		column_art_impos.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getArtImpos()));
		column_num_registre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumRegistre()));
		column_num_statistique.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumStatistique()));
		column_activite.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getActivite()));

		tableviewClient.setItems(observableList);
		
		FilteredList<Client> filteredList = new FilteredList<>(observableList, e -> true);
		tf_rechercher_client.setOnKeyReleased(e -> {
			tf_rechercher_client.textProperty().addListener((observableValue, oldValue, newValue) -> {
				filteredList.setPredicate((Predicate<? super Client>) client -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (String.valueOf(client.getId()).contains(lowerCaseFilter) ||
							client.getLibelle().contains(lowerCaseFilter) ||
							client.getCodeClient().contains(lowerCaseFilter) ||
							client.getAdresse().contains(lowerCaseFilter) ||
							client.getCodePostal().contains(lowerCaseFilter) ||
							client.getVille().contains(lowerCaseFilter) ||
							client.getTelephone().contains(lowerCaseFilter) ||
							client.getFax().contains(lowerCaseFilter) ||
							client.getEmail().contains(lowerCaseFilter) ||
							client.getObservation().contains(lowerCaseFilter) ||
							client.getDescription().contains(lowerCaseFilter) ||
							client.getCodeFiscal().contains(lowerCaseFilter) ||
							client.getArtImpos().contains(lowerCaseFilter) ||
							client.getNumRegistre().contains(lowerCaseFilter) ||
							client.getNumStatistique().contains(lowerCaseFilter) ||
							client.getActivite().contains(lowerCaseFilter)) {
						return true;	
					}					
					return false;
				});
			});
		});
		
		SortedList<models.Client> sortedList = new SortedList<Client>(filteredList);
		sortedList.comparatorProperty().bind(tableviewClient.comparatorProperty());
		tableviewClient.setItems(sortedList);
	}
    
}
