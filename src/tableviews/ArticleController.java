package tableviews;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import database.DBConnection;
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
import models.Acticle;

public class ArticleController implements Initializable {

    @FXML
    private TableView<models.Acticle> tableviewAtricle;
    
    @FXML
    private TableColumn<models.Acticle, Integer> column_id;

    @FXML
    private TableColumn<models.Acticle, String> column_code_article;

    @FXML
    private TableColumn<models.Acticle, String> column_libelle;

    @FXML
    private TableColumn<models.Acticle, String> column_code_famille;

    @FXML
    private TableColumn<models.Acticle, String> column_code_marque;

    @FXML
    private TableColumn<models.Acticle, String> column_code_modele;

    @FXML
    private TableColumn<models.Acticle, String> column_unite_mesure;

    @FXML
    private TableColumn<models.Acticle, String> column_prix_unitaire;

    @FXML
    private TableColumn<models.Acticle, String> column_unite_condit;

    @FXML
    private TableColumn<models.Acticle, String> column_reference;

    @FXML
    private TableColumn<models.Acticle, String> column_description;

    @FXML
    private TableColumn<models.Acticle, String> column_quantite;

    @FXML
    private TextField tf_rechercher;
    
    static ObservableList<models.Acticle> observableList = FXCollections.observableArrayList();
        
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub	
		refreshTable();
	}

    public void refreshTable() {
    	
    	observableList.clear();
    	
		// TODO Auto-generated method stub
    	String sql = "SELECT * FROM articles";
		
		ResultSet rSet;
		try {
			rSet = DBConnection.getConnection().createStatement().executeQuery(sql);
			
			while (rSet.next()) {
				observableList.add(new Acticle(
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
						rSet.getString(12)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		column_code_article.setCellValueFactory(new PropertyValueFactory<>("codeAtricle"));
		column_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
		column_code_famille.setCellValueFactory(new PropertyValueFactory<>("codeFamille"));
		column_code_marque.setCellValueFactory(new PropertyValueFactory<>("codeMarque"));
		column_code_modele.setCellValueFactory(new PropertyValueFactory<>("codeModele"));
		column_unite_mesure.setCellValueFactory(new PropertyValueFactory<>("uniteMesure"));
		column_prix_unitaire.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
		column_unite_condit.setCellValueFactory(new PropertyValueFactory<>("uniteCondit"));
		column_reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
		column_description.setCellValueFactory(new PropertyValueFactory<>("description"));
		column_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

		tableviewAtricle.setItems(observableList);
		
		FilteredList<Acticle> filteredList = new FilteredList<>(observableList, e -> true);
		tf_rechercher.setOnKeyReleased(e -> {
			tf_rechercher.textProperty().addListener((observableValue, oldValue, newValue) -> {
				filteredList.setPredicate((Predicate<? super Acticle>) article -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					
					String lowerCaseFilter = newValue.toLowerCase();
					
					if (String.valueOf(article.getId()).contains(lowerCaseFilter) ||
							article.getCodeAtricle().contains(lowerCaseFilter) ||
							article.getLibelle().contains(lowerCaseFilter) ||
							article.getCodeFamille().contains(lowerCaseFilter) ||
							article.getCodeMarque().contains(lowerCaseFilter) ||
							article.getCodeModele().contains(lowerCaseFilter) ||
							article.getUniteMesure().contains(lowerCaseFilter) ||
							article.getPrixUnitaire().contains(lowerCaseFilter) ||
							article.getUniteCondit().contains(lowerCaseFilter) ||
							article.getReference().contains(lowerCaseFilter) ||
							article.getDescription().contains(lowerCaseFilter) ||
							article.getQuantite().contains(lowerCaseFilter)) {
						return true;	
					}					
					return false;
				});
			});
		});
		
		SortedList<models.Acticle> sortedList = new SortedList<Acticle>(filteredList);
		sortedList.comparatorProperty().bind(tableviewAtricle.comparatorProperty());
		tableviewAtricle.setItems(sortedList);
	}

	@FXML
    void ajouterArticle(ActionEvent event) {
    	Parent rootParent;
		try {
			rootParent = FXMLLoader.load(getClass().getResource("ArticleFormAjouter.fxml"));
			Stage stage = new Stage();
			
			Scene scene = new Scene(rootParent);
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void articleModifier(ActionEvent event) throws IOException {
    	    	
    	Acticle article = tableviewAtricle.getSelectionModel().getSelectedItem();
    	
    	if (article == null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Table Selection");
    		alert.setHeaderText("You're not selection a row ?");
    		alert.show();
		} else {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ArticleFormUpdate.fxml"));			
			try {
				loader.load();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
				
			ArticleUpdateFormController updateFormController = loader.getController();
			updateFormController.displayValues(article.getId(),
					article.getCodeAtricle(),
					article.getLibelle(),
					article.getCodeFamille(),
					article.getCodeMarque(),
					article.getCodeModele(),
					article.getUniteMesure(),
					article.getPrixUnitaire(),
					article.getUniteCondit(),
					article.getReference(),
					article.getDescription(),
					article.getQuantite());
			
			Parent root = loader.getRoot();
			Stage stage = new Stage();			
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();		
		}  	
    }

    @FXML
    void articleSupprimer(ActionEvent event) {    	
    	Acticle article = tableviewAtricle.getSelectionModel().getSelectedItem();
    	
    	if (article == null) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setHeaderText("You need select the row ?");
    		alert.show();
		}else {
			DBConnection.deleteArticleRow(article.getId());
			refreshTable();
		}
    }
    
    @FXML
    void exportTableIntoExcel(ActionEvent event) {
    	if (observableList.isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("INFORMATION");
    		alert.setHeaderText("Le Tableau est vide !");
    		alert.show();
		}else {
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Les details d'articles");
			XSSFRow headeRow = sheet.createRow(0);
			
			for(int i = 0; i<tableviewAtricle.getColumns().size(); i++) {
				headeRow.createCell(i).setCellValue(tableviewAtricle.getColumns().get(i).getText().toString().toUpperCase());
				System.out.println("Columns : "+ i + "/" + tableviewAtricle.getColumns().get(i).getText().toString());
			}
			
			List<Acticle> acticles = DBConnection.getActiclesList();
			
			for(int i=0; i<acticles.size(); i++) {
				XSSFRow row = sheet.createRow(i+1);
				row.createCell(0).setCellValue(acticles.get(i).getId());
				row.createCell(1).setCellValue(acticles.get(i).getCodeAtricle());
				row.createCell(2).setCellValue(acticles.get(i).getLibelle());
				row.createCell(3).setCellValue(acticles.get(i).getCodeFamille());
				row.createCell(4).setCellValue(acticles.get(i).getCodeMarque());
				row.createCell(5).setCellValue(acticles.get(i).getCodeModele());
				row.createCell(6).setCellValue(acticles.get(i).getUniteMesure());
				row.createCell(7).setCellValue(acticles.get(i).getPrixUnitaire());
				row.createCell(8).setCellValue(acticles.get(i).getUniteCondit());
				row.createCell(9).setCellValue(acticles.get(i).getReference());
				row.createCell(10).setCellValue(acticles.get(i).getDescription());
				row.createCell(11).setCellValue(acticles.get(i).getQuantite());
			}
			
			for(int i=0; i<12; i++ ) {
				sheet.autoSizeColumn(i);
				sheet.setColumnWidth(i, 270*25);
			}
			
			sheet.setZoom(115);
		
			
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(createDirectoryAndFile("Excel Files", "Article.xlsx"));
				wb.write(fileOutputStream);
				fileOutputStream.close();
				
				Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("INFORMATION");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Fichier excel creé !");
	    		alert.showAndWait();
	    		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("WARNING");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Le fichie excel déja ouvert essayer \n de quitter enfin exporter");
	    		alert.showAndWait();
			}
		}
    }

    @FXML
    void imprimerTable(ActionEvent event) {
    	
    }
    
    /** Creates parent directories if necessary. Then returns file */
    private static File createDirectoryAndFile(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        return new File(directory + "/" + filename);
    }
    
}
