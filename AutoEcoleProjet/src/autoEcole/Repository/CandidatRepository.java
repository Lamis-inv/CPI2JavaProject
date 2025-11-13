package autoEcole.Repository;

import autoEcole.Entities.Candidat;

public class CandidatRepository {
	
	public void save(Candidat c) {
		System.out.println("nom:"+c.getNom()+" prenom: "+c.getPrenom()+" Tel: "+c.getTelephone()+
				" Adresse: "+c.getAdresse()+"Type permit : "+c.getTypePermis());
	}
	
	public void findAll() {
		
	}
	
	public void findByCin(String cin) {
		
	}
	
	public void update(Candidat c) {
		
	}
	
	public void delete(String cin) {
		
	}
}
