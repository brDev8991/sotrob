package models;

public class Entres {
	private String date;
	private String numBon;
	private String codeArticle;
	private String designation;
	private String qte;
	private String prixUnitaire;
	private String valeur;
	private String fournisseur;
	private String facture;
	
	public Entres(String date, String numBon, String codeArticle, String designation, String qte,
			String prixUnitaire, String valeur, String fournisseur, String facture) {
		super();
		this.date = date;
		this.numBon = numBon;
		this.codeArticle = codeArticle;
		this.designation = designation;
		this.qte = qte;
		this.prixUnitaire = prixUnitaire;
		this.valeur = valeur;
		this.fournisseur = fournisseur;
		this.facture = facture;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNumBon() {
		return numBon;
	}

	public void setNumBon(String numBon) {
		this.numBon = numBon;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getQte() {
		return qte;
	}

	public void setQte(String qte) {
		this.qte = qte;
	}

	public String getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(String prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	public String getFacture() {
		return facture;
	}

	public void setFacture(String facture) {
		this.facture = facture;
	}
	
	

}
