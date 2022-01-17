package models;

public class Row {
	
	private String codeArticle;
	private String qte;
	private String prix_unitaire;
	private String montant;
	
	
	
	public Row() {
		super();
	}

	public Row(String codeArticle, String qte, String prix_unitaire, String montant) {
		super();
		this.codeArticle = codeArticle;
		this.qte = qte;
		this.prix_unitaire = prix_unitaire;
		this.montant = montant;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public String getQte() {
		return qte;
	}

	public void setQte(String qte) {
		this.qte = qte;
	}

	public String getPrix_unitaire() {
		return prix_unitaire;
	}

	public void setPrix_unitaire(String prix_unitaire) {
		this.prix_unitaire = prix_unitaire;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}
	
	
	

}
