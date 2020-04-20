package com.japhibernatespringdata.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.japhibernatespringdata.dao.ICatagorieRepository;
import com.japhibernatespringdata.dao.IProduitRepository;
import com.japhibernatespringdata.entities.Categorie;
import com.japhibernatespringdata.entities.Produit;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProduitRestController {

	@Autowired
	IProduitRepository produitRepository;

	@Autowired
	ICatagorieRepository catagorieRepository;

	@PostMapping(path = "/saveProduitWithCatgorie")
	public FormUIProduit saveProduitWithCatgorie(@RequestBody FormUIProduit formUIProduit) {

		try {

			
			System.out.println("------------------------------------------");
			System.out.println("formUIProduit: " + formUIProduit.getDesignation() + " " + formUIProduit.getPrix() + " "
					+ formUIProduit.getQuantite() + " " + formUIProduit.getNomCategorie() );

			Categorie categorie = catagorieRepository.findByNomCategorie(formUIProduit.getNomCategorie());

			System.out.println("categorie: " + categorie.getNomCategorie() + " id: " + categorie.getId());

			Produit produit = new Produit(formUIProduit.getDesignation(), formUIProduit.getPrix(),
					formUIProduit.getQuantite(), categorie);
			
			
			Produit produitSave = produitRepository.save(produit);
			
			formUIProduit.setId(produitSave.getId());
			
			return formUIProduit;
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("probleme d'inseration du produit");
		}
	}
	
	


	
	
}
