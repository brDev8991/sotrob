package models;

public class Sorties {
	
	private String date;
	private String numBonExt;
	private String designation;
	private String qte;
	private String prixUnitaire;
	private String destination;
	private String imputation;
	private String cessionType;
	
	public Sorties(String date, String numBonExt, String designation, String qte, String prixUnitaire,
			String destination, String imputation, String cessionType) {
		super();
		this.date = date;
		this.numBonExt = numBonExt;
		this.designation = designation;
		this.qte = qte;
		this.prixUnitaire = prixUnitaire;
		this.destination = destination;
		this.imputation = imputation;
		this.cessionType = cessionType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNumBonExt() {
		return numBonExt;
	}

	public void setNumBonExt(String numBonExt) {
		this.numBonExt = numBonExt;
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

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getImputation() {
		return imputation;
	}

	public void setImputation(String imputation) {
		this.imputation = imputation;
	}

	public String getCessionType() {
		return cessionType;
	}

	public void setCessionType(String cessionType) {
		this.cessionType = cessionType;
	}
	
	
	
}
