package metier.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import metier.entities.Compte;
import metier.session.IBanqueLocal;

@Stateless
@WebService
public class BanqueService {
	@EJB
	private IBanqueLocal metier;
	
	@WebMethod
	public void addCompte(@WebParam(name="solde") double soldeInitial) {
		Compte compte = new Compte(soldeInitial, new Date(), true);
		metier.addCompte(compte);
	}
	@WebMethod
	public List<Compte> listComptes() {
		return metier.getAllComptes();
	}
	@WebMethod
	public Compte getCompte(@WebParam(name="code") Long code) {
		return metier.getCompte(code);
	}
	
	@WebMethod
	public void verser(@WebParam(name = "montant") double montant,@WebParam(name="code") Long code) {
		metier.verser(montant, code);
	}
	
	@WebMethod
	public void retirer(@WebParam(name = "montant") double montant,@WebParam(name="code") Long code) {
		metier.retirer(montant, code);
	}
	
	@WebMethod
	public void virement(
			@WebParam(name = "montant") double montant,
			@WebParam(name= "compte1") Long code1,
			@WebParam(name= "compte2") Long code2) {
		metier.virement(montant, code1, code2);
	}
	
	@WebMethod
	public void updateCategorie(
			@WebParam(name="code") Long code,
			@WebParam(name="categorie") String categorie
			) {
		metier.updateCategorie(code, categorie);
	}
	
	
}
