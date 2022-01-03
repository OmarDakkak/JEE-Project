package web.mb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import metier.entities.Compte;
import metier.session.IBanqueRemote;

@ManagedBean(name="banqueMB")
@RequestScoped
public class BanqueMB {
	@EJB
	private IBanqueRemote metier;
	private Long code1;
	private Long code2;
	private double montant;
	
	public String verser() {
		metier.verser(montant, code1);
		return "success";
	}
	
	public String retirer() {
		metier.retirer(montant, code1);
		return "success";
	}
	
	public String virement() {
		metier.virement(montant, code1, code2);
		return "success";
	}
	
	public String ajouter() {
		metier.addCompte(new Compte(0, new Date(), true));
		return "success";
	}
	
	public List<Compte> getlistComptes(){
		return metier.getAllComptes();
	}

	public Long getCode1() {
		return code1;
	}

	public void setCode1(Long code1) {
		this.code1 = code1;
	}

	public Long getCode2() {
		return code2;
	}

	public void setCode2(Long code2) {
		this.code2 = code2;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
}
