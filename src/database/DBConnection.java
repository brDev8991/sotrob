package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.Acticle;

public class DBConnection {
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";
	private static final String DB_JDBC_PATH = "jdbc:mysql://localhost/db_sotrob_oeb";
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		
		connection = DriverManager.getConnection(DB_JDBC_PATH, DB_USERNAME, DB_PASSWORD);
		
		if (connection != null) {
			System.out.println("Connected !");
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Connection Problem");
			alert.setHeaderText("Check your database program ?");
			alert.setContentText("Please open firstly xampp or wampp server !");
			alert.show();
		}

		return connection;
	}
	
	public static void closeConnection() throws SQLException {
		connection.close();
	}
	
	public static boolean checkArticleExistance(String codeArticle) {	
		String sql = "SELECT * FROM articles WHERE code_article = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, codeArticle);                
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }       
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public static boolean checkClientExistance(String codeClient) {	
		String sql = "SELECT * FROM clients WHERE code_client=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, codeClient);                
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }       
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public static boolean checkFournisseurExistance(String codeFournisseur) {	
		String sql = "SELECT * FROM fournisseurs WHERE code_fournisseur=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, codeFournisseur);                
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }       
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public static String getFournisseurLibelle(String codeFournisseur) {
		String sql = "SELECT libelle FROM fournisseurs WHERE code_fournisseur=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, codeFournisseur);
                       
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("libelle");
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "";
	}
	
	public static boolean userDBLogin(String username, String password) {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
                       
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return false;
	}

	public static boolean ajouterNouveauArticle(String codeArticle, String libelle, String codeFamille, String codeMarque, 
			String codeModele, String uniteMesure, String codeTVA, String uniteCondit, String reference, String description,
			String quantite) {
		
		String sql = "INSERT INTO articles (code_article, libelle, code_famille, code_marque,"
				+ "code_modele, unite_mesure, code_tva, unite_condit, reference, description, quantite) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, codeArticle);
			ps.setString(2, libelle);
			ps.setString(3, codeFamille);
			ps.setString(4, codeMarque);
			ps.setString(5, codeModele);
			ps.setString(6, uniteMesure);
			ps.setString(7, codeTVA);
			ps.setString(8, uniteCondit);
			ps.setString(9, reference);
			ps.setString(10, description);
			ps.setString(11, quantite);
			
			int row = ps.executeUpdate();
						
			if (row > 0) {
				System.out.println("Article Inserted ");
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Article Not Inserted");
			e.printStackTrace();
		}
		
		return false;
	}

	public static boolean updateArticle(Acticle article, int id){
		
		String sql = "UPDATE `articles` SET `code_article`=?, `libelle`=?, `code_famille`=?, `code_marque`=?,"
				+ " `code_modele`=?, `unite_mesure`=?, `prix_unitaire`=? , `unite_condit`=? , `reference`=? "
				+ ", `description`=? , `quantite`=? WHERE id=?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, article.getCodeAtricle());
			ps.setString(2, article.getLibelle());
			ps.setString(3, article.getCodeFamille());
			ps.setString(4, article.getCodeMarque());
			ps.setString(5, article.getCodeModele());
			ps.setString(6, article.getUniteMesure());
			ps.setString(7, article.getPrixUnitaire());
			ps.setString(8, article.getUniteCondit());
			ps.setString(9, article.getReference());
			ps.setString(10, article.getDescription());
			ps.setString(11, article.getQuantite());
			ps.setInt(12, id);
			
			int row = ps.executeUpdate();
						
			if (row > 0) {
				System.out.println("Article Updated ");
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Article Not Updated");
			e.printStackTrace();
		}
	
		return false;
    }

	public static Acticle getAtricleByID(int id){
		
		Acticle acticle = new Acticle();
		
		String sql = "SELECT * FROM articles WHERE id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
                       
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	System.out.println(rs.getString(2) + " : Whats that");
            	acticle.setCodeAtricle(rs.getString(2));
            	acticle.setCodeFamille(rs.getString(3));
            	acticle.setLibelle(rs.getString(4));
            	acticle.setCodeMarque(rs.getString(5));
            	acticle.setCodeModele(rs.getString(6));
            	acticle.setUniteMesure(rs.getString(7));
            	acticle.setPrixUnitaire(rs.getString(8));
            	acticle.setUniteCondit(rs.getString(9));
            	acticle.setReference(rs.getString(10));
            	acticle.setDescription(rs.getString(11));
            	acticle.setQuantite(rs.getString(12));
            	return acticle;
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return acticle;
    }
	
	public static Acticle getAtricleByCode(String codeArticle){
		
		Acticle acticle = new Acticle();
		
		String sql = "SELECT * FROM articles WHERE code_article=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, codeArticle);
                       
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	acticle.setCodeAtricle(rs.getString(2));
            	acticle.setCodeFamille(rs.getString(3));
            	acticle.setLibelle(rs.getString(4));
            	acticle.setCodeMarque(rs.getString(5));
            	acticle.setCodeModele(rs.getString(6));
            	acticle.setUniteMesure(rs.getString(7));
            	acticle.setPrixUnitaire(rs.getString(8));
            	acticle.setUniteCondit(rs.getString(9));
            	acticle.setReference(rs.getString(10));
            	acticle.setDescription(rs.getString(11));
            	acticle.setQuantite(rs.getString(12));
            }
        	return acticle;

            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return acticle;
    }
	
	public static int getNumBon(String bonNom){
		
		String sql = "SELECT "+bonNom+" FROM bon";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);                       
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	return rs.getInt(bonNom);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return 1;
    }
	
	public static boolean deleteArticleRow(int id){
		
		String sql = "DELETE FROM `articles` WHERE Id=?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
                       
            int row = ps.executeUpdate();

            if (row>0) {
            	System.out.println("Row with id : " + id + "deleted");
                return true;
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return false;
		
	}
	
	public static boolean deleteClient(int id){
		
		String sql = "DELETE FROM `clients` WHERE id=?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
                       
            int row = ps.executeUpdate();

            if (row>0) {
            	//System.out.println("Row with id : " + id + "deleted");
                return true;
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public static boolean deleteFournisseur(int id){
		
		String sql = "DELETE FROM `fournisseurs` WHERE id=?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
                       
            int row = ps.executeUpdate();

            if (row>0) {
            	//System.out.println("Row with id : " + id + "deleted");
                return true;
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public static boolean deleteUser(int id){
		
		String sql = "DELETE FROM `users` WHERE id=?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
                       
            int row = ps.executeUpdate();

            if (row>0) {
                return true;
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public static List<Acticle> getActiclesList() {
		
		List<Acticle> articleList = new ArrayList<Acticle>();
		
		String sql = "SELECT * FROM articles";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Acticle acticle = new Acticle();
				acticle.setId(rs.getInt(1));
				acticle.setCodeAtricle(rs.getString(2));
            	acticle.setCodeFamille(rs.getString(3));
            	acticle.setLibelle(rs.getString(4));
            	acticle.setCodeMarque(rs.getString(5));
            	acticle.setCodeModele(rs.getString(6));
            	acticle.setUniteMesure(rs.getString(7));
            	acticle.setPrixUnitaire(rs.getString(8));
            	acticle.setUniteCondit(rs.getString(9));
            	acticle.setReference(rs.getString(10));
            	acticle.setDescription(rs.getString(11));
            	acticle.setQuantite(rs.getString(12));
            	articleList.add(acticle);
			}
			
			ps.close();
			rs.close();
			
			return articleList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;	
	}

	public static boolean ajouterFournisseur(String codeFournisseur, String libelle, String adresse, String codePostal,
			String ville, String telephone,	String fax, String email, String observation, String description, String codeFiscal,
			String artImpos, String numRegistre, String numStatistique, String activite) {
		
		String sql =  "INSERT INTO fournisseurs (code_fournisseur, libelle, adress, code_postal,"
				+ "ville, telephone, fax, email, observation, description, code_fiscale,"
				+ "art_impos, num_registre, num_statistique, activite) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, codeFournisseur);
			ps.setString(2, libelle);
			ps.setString(3, adresse);
			ps.setString(4, codePostal);
			ps.setString(5, ville);
			ps.setString(6, telephone);
			ps.setString(7, fax);
			ps.setString(8, email);
			ps.setString(9, observation);
			ps.setString(10, description);
			ps.setString(11, codeFiscal);
			ps.setString(12, artImpos);
			ps.setString(13, numRegistre);
			ps.setString(14, numStatistique);
			ps.setString(15, activite);

			int row = ps.executeUpdate();
						
			if (row > 0) {
				System.out.println("Founisseur Inserted ");
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Founisseur Not Inserted");
			e.printStackTrace();
		}
		
		return false;
	}

	public static boolean ajouterClient(String codeClient, String libelle, String adresse, String codePostal,
			String ville, String telephone, String fax, String email, String observation, String description, 
			String codeFiscal, String artImpos, String numRegistre, String numStatistique, String activite) {
		
		String sql =  "INSERT INTO clients (code_client, libelle, adress, code_postal,"
				+ "ville, telephone, fax, email, observation, description, code_fiscale,"
				+ "art_impos, num_registre, num_statistique, activite) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, codeClient);
			ps.setString(2, libelle);
			ps.setString(3, adresse);
			ps.setString(4, codePostal);
			ps.setString(5, ville);
			ps.setString(6, telephone);
			ps.setString(7, fax);
			ps.setString(8, email);
			ps.setString(9, observation);
			ps.setString(10, description);
			ps.setString(11, codeFiscal);
			ps.setString(12, artImpos);
			ps.setString(13, numRegistre);
			ps.setString(14, numStatistique);
			ps.setString(15, activite);

			int row = ps.executeUpdate();
						
			if (row > 0) {
				System.out.println("Client Inserted ");
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Client Not Inserted");
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean ajouterUser(String username, String password, String type, String date) {
		
		String sql =  "INSERT INTO users (username, password, type, date) "
				+ "VALUES (?,?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, type);
			ps.setString(4, date);

			int row = ps.executeUpdate();
						
			if (row > 0) {
				System.out.println("User Inserted ");
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("User Not Inserted");
			e.printStackTrace();
		}
		
		return false;
	}


}
