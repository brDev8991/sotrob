package models;

public class Client {
	
	private int id;
	private String codeClient;
	private String libelle;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephone;
	private String fax;
	private String email;
	private String observation;
	private String description;
	private String codeFiscal;
	private String artImpos;
	private String numRegistre;
	private String numStatistique;
	private String activite;	
	
	public Client() {
		super();
	}

	public Client(int id, String codeClient, String libelle, String adresse, String codePostal, String ville,
			String telephone, String fax, String email, String observation, String description, String codeFiscal,
			String artImpos, String numRegistre, String numStatistique, String activite) {
		super();
		this.id = id;
		this.codeClient = codeClient;
		this.libelle = libelle;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.fax = fax;
		this.email = email;
		this.observation = observation;
		this.description = description;
		this.codeFiscal = codeFiscal;
		this.artImpos = artImpos;
		this.numRegistre = numRegistre;
		this.numStatistique = numStatistique;
		this.activite = activite;
	}


	public Client(String codeClient, String libelle, String adresse, String codePostal, String ville, String telephone,
			String fax, String email, String observation, String description, String codeFiscal, String artImpos,
			String numRegistre, String numStatistique, String activite) {
		super();
		this.codeClient = codeClient;
		this.libelle = libelle;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.fax = fax;
		this.email = email;
		this.observation = observation;
		this.description = description;
		this.codeFiscal = codeFiscal;
		this.artImpos = artImpos;
		this.numRegistre = numRegistre;
		this.numStatistique = numStatistique;
		this.activite = activite;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeClient() {
		return codeClient;
	}


	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getObservation() {
		return observation;
	}


	public void setObservation(String observation) {
		this.observation = observation;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCodeFiscal() {
		return codeFiscal;
	}


	public void setCodeFiscal(String codeFiscal) {
		this.codeFiscal = codeFiscal;
	}


	public String getArtImpos() {
		return artImpos;
	}


	public void setArtImpos(String artImpos) {
		this.artImpos = artImpos;
	}


	public String getNumRegistre() {
		return numRegistre;
	}


	public void setNumRegistre(String numRegistre) {
		this.numRegistre = numRegistre;
	}


	public String getNumStatistique() {
		return numStatistique;
	}


	public void setNumStatistique(String numStatistique) {
		this.numStatistique = numStatistique;
	}


	public String getActivite() {
		return activite;
	}


	public void setActivite(String activite) {
		this.activite = activite;
	}
	

}
