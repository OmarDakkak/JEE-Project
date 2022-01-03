package metier.session;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Compte;

@Local
public interface IBanqueLocal {
	public void addCompte(Compte c);
	public List<Compte> getAllComptes();
	public Compte getCompte(Long code);
	public void verser(double montant, Long code);
	public void retirer(double montant, Long code);
	public void virement(double montant, Long code1, Long code2);
	public void updateCompte(Compte c);
	public void updateCategorie(Long code, String categorie);
	public void supprimerCompte(Long code);
}
