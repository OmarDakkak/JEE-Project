package metier.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COMPTES")
public class Compte implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODE")
	private Long code;
	@Column(name = "SOLDE")
	private double solde;
	@Column(name = "CATEGORIE")
	private String categorie;
	
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	private boolean active;
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Compte(double solde, Date dateCreation, boolean active) {
		super();
		this.solde = solde;
		this.dateCreation = dateCreation;
		this.active = active;
	}
	public Compte(double solde, String categorie, Date dateCreation, boolean active) {
		super();
		this.solde = solde;
		this.categorie = categorie;
		this.dateCreation = dateCreation;
		this.active = active;
	}
	
	
}
