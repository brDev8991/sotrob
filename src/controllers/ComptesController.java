package controllers;

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
import models.User;

public class ComptesController implements Initializable {

    @FXML
    private TableView<User> tableviewUsers;

    @FXML
    private TableColumn<User, String> column_id;

    @FXML
    private TableColumn<User, String> column_nom;

    @FXML
    private TableColumn<User, String> column_password;

    @FXML
    private TableColumn<User, String> column_type;

    @FXML
    private TableColumn<User, String> column_date;

    @FXML
    private TextField tf_rechercher;

    static ObservableList<User> observableList = FXCollections.observableArrayList();

    @FXML
    void ajouterUser(ActionEvent event) {
    	Parent rootParent;
		try {
			rootParent = FXMLLoader.load(getClass().getResource("/tableviews/UserAdding.fxml"));
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
    void userModifier(ActionEvent event) {

    }

    @FXML
    void userSupprimer(ActionEvent event) {
    	User user = tableviewUsers.getSelectionModel().getSelectedItem();
    	if (user == null) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setHeaderText("You need select the row ?");
    		alert.show();
		}else {
			DBConnection.deleteUser(user.getId());
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
    	String sql = "SELECT * FROM users";
		
		ResultSet rSet;
		try {
			rSet = DBConnection.getConnection().createStatement().executeQuery(sql);
			
			while (rSet.next()) {
				observableList.add(new User(
						rSet.getInt(1),
						rSet.getString(2),
						rSet.getString(3),
						rSet.getString(4),
						rSet.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		column_nom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
		column_password.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPassword()));
		column_type.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getType()));
		column_date.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
		

		tableviewUsers.setItems(observableList);
		
		FilteredList<User> filteredList = new FilteredList<>(observableList, e -> true);
		tf_rechercher.setOnKeyReleased(e -> {
			tf_rechercher.textProperty().addListener((observableValue, oldValue, newValue) -> {
				filteredList.setPredicate((Predicate<? super User>) user -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (String.valueOf(user.getId()).contains(lowerCaseFilter) ||
							user.getName().contains(lowerCaseFilter) ||
							user.getPassword().contains(lowerCaseFilter) ||
							user.getType().contains(lowerCaseFilter) ||
							user.getDate().contains(lowerCaseFilter) ) {
						return true;	
					}					
					return false;
				});
			});
		});
		
		SortedList<User> sortedList = new SortedList<User>(filteredList);
		sortedList.comparatorProperty().bind(tableviewUsers.comparatorProperty());
		tableviewUsers.setItems(sortedList);
	}


}
