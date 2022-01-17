package models;

public class Acticle {
	
	private int id;
	private String codeAtricle;
	private String libelle;
	private String codeFamille;
	private String codeMarque;
	private String codeModele;
	private String uniteMesure;
	private String prixUnitaire;
	private String uniteCondit;
	private String reference;
	private String description;
	private String quantite;


	public Acticle() {
		super();
	}

	public Acticle(String codeAtricle, String libelle, String codeFamille, String codeMarque, String codeModele,
			String uniteMesure, String prixUnitaire, String uniteCondit, String reference, String description,
			String quantite) {
		super();
		this.codeAtricle = codeAtricle;
		this.libelle = libelle;
		this.codeFamille = codeFamille;
		this.codeMarque = codeMarque;
		this.codeModele = codeModele;
		this.uniteMesure = uniteMesure;
		this.prixUnitaire = prixUnitaire;
		this.uniteCondit = uniteCondit;
		this.reference = reference;
		this.description = description;
		this.quantite = quantite;
	}

	public Acticle(int id, String codeAtricle, String libelle, String codeFamille, String codeMarque, String codeModele,
			String uniteMesure, String prixUnitaire, String uniteCondit, String reference, String description,
			String quantite) {
		super();
		this.id = id;
		this.codeAtricle = codeAtricle;
		this.libelle = libelle;
		this.codeFamille = codeFamille;
		this.codeMarque = codeMarque;
		this.codeModele = codeModele;
		this.uniteMesure = uniteMesure;
		this.prixUnitaire = prixUnitaire;
		this.uniteCondit = uniteCondit;
		this.reference = reference;
		this.description = description;
		this.quantite = quantite;
	}

	public String getCodeAtricle() {
		return codeAtricle;
	}

	public void setCodeAtricle(String codeAtricle) {
		this.codeAtricle = codeAtricle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCodeFamille() {
		return codeFamille;
	}

	public void setCodeFamille(String codeFamille) {
		this.codeFamille = codeFamille;
	}

	public String getCodeMarque() {
		return codeMarque;
	}

	public void setCodeMarque(String codeMarque) {
		this.codeMarque = codeMarque;
	}

	public String getCodeModele() {
		return codeModele;
	}

	public void setCodeModele(String codeModele) {
		this.codeModele = codeModele;
	}

	public String getUniteMesure() {
		return uniteMesure;
	}

	public void setUniteMesure(String uniteMesure) {
		this.uniteMesure = uniteMesure;
	}

	public String getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(String prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getUniteCondit() {
		return uniteCondit;
	}

	public void setUniteCondit(String uniteCondit) {
		this.uniteCondit = uniteCondit;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuantite() {
		return quantite;
	}

	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
