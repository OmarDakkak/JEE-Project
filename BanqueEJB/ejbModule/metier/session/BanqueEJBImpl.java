package metier.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Compte;

@Stateless(name="BQ")
public class BanqueEJBImpl implements IBanqueLocal, IBanqueRemote{
	@PersistenceContext(unitName = "UP_BANQUE")
	private EntityManager em;
	@Override
	public void addCompte(Compte c) {
		em.persist(c);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> getAllComptes() {
		// criteriaBuilder
		Query req = em.createQuery("SELECT c FROM Compte c WHERE C.active=TRUE");
		return req.getResultList();
	}

	@Override
	public Compte getCompte(Long code) {
		Compte compte = em.find(Compte.class, code);
		if(compte == null) throw new RuntimeException("Compte introuvable!");
		return compte;
	}

	@Override
	public void verser(double montant, Long code) {
		Compte compte = getCompte(code);
		compte.setSolde(compte.getSolde()+montant);
		//em.persist(compte);
	}

	@Override
	public void retirer(double montant, Long code) {
		Compte compte = getCompte(code);
		compte.setSolde(compte.getSolde()-montant);
		//em.persist(compte);
	}

	@Override
	public void virement(double montant, Long code1, Long code2) {
		retirer(montant, code1);
		verser(montant, code2);
	}

	@Override
	public void updateCompte(Compte c) {
		em.merge(c);
		
	}

	@Override
	public void supprimerCompte(Long code) {
		Compte compte = getCompte(code);
		em.remove(compte);
	}

	@Override
	public void updateCategorie(Long code, String categorie) {
		// TODO Auto-generated method stub
		Compte compte = getCompte(code);
		compte.setCategorie(categorie);
		
	}

}
