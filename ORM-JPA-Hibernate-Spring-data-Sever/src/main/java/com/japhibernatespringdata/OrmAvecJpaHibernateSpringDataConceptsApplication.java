package com.japhibernatespringdata;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.japhibernatespringdata.dao.ICatagorieRepository;
import com.japhibernatespringdata.dao.IProduitRepository;
import com.japhibernatespringdata.entities.Categorie;
import com.japhibernatespringdata.entities.Produit;

@SpringBootApplication
public class OrmAvecJpaHibernateSpringDataConceptsApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(OrmAvecJpaHibernateSpringDataConceptsApplication.class, args);

		// -------------------------------------------------------------------
		ICatagorieRepository categorieDao = ctx.getBean(ICatagorieRepository.class); // renvoi une instance de
																						// ICatagorieRepository
		Categorie categ1 = new Categorie("Ordinateur");
		Categorie categ2 = new Categorie("imprimante");
		Categorie categ3 = new Categorie("photo");

		categorieDao.save(categ1);
		categorieDao.save(categ2);
		categorieDao.save(categ3);

		// -------------------------------------------------------------------

		IProduitRepository produitDao = ctx.getBean(IProduitRepository.class); // renvoi une instance de
																				// IProduitRepository
		produitDao.save(new Produit("Ordinateur LX567", 7000, 7, categ1));
		produitDao.save(new Produit("Ordinateur HP pavillon 645", 5000, 8, categ1));
		produitDao.save(new Produit("Dell Latitude 2120 Netbook 10.1", 85, 20, categ1));
		produitDao.save(new Produit("portable netbook Dell e7240", 249, 20, categ1));
		produitDao.save(new Produit("portable netbook Dell e7240 Core i5", 24, 7, categ1));
		produitDao.save(new Produit("Latitude 3180 CEL N3350 11.6 HD 4GO", 189, 11, categ1));
		produitDao.save(new Produit("Pc portable hp probook 6570b core i5", 180, 21, categ1));
		produitDao.save(new Produit("Ordinateur Portable DELL E6420 i7 8Go+SSD", 150, 14, categ1));
		produitDao.save(new Produit("MIni Ordinateur PC NUC Intel", 120, 4, categ1));
		produitDao.save(new Produit("Ordinateur PC fixe Fujitsu Esprimo E710", 50, 3, categ1));
		produitDao.save(new Produit("Barrette de RAM", 1, 100, categ1));
		produitDao.save(new Produit("Ordinateur portable dell latitude e5420, 14", 90, 25, categ1));
		produitDao.save(new Produit("Ordinateur Toshiba pour pièces", 45, 3, categ1));

		
		
		produitDao.save(new Produit("Imprimante lastjet HP 785", 2000, 6, categ2));
		produitDao.save(new Produit("Imprimante lastjet HP 8794", 2000, 6, categ2));
		produitDao.save(new Produit("Imprimante Epson", 50, 45, categ2));
		produitDao.save(new Produit("Brother DCP-9055CDN - IMPRIMANTE MULTIFONCTION LASER COULEUR", 250, 10, categ2));
		produitDao.save(new Produit("Imprimante Canon Pixma iP2850", 10, 2, categ2));
		produitDao.save(new Produit("Imprimante-photocopieur laser", 120, 12, categ2));
		produitDao.save(new Produit("Photocopieur couleur A3 & A4 multifonction Toshiba économique", 750, 7, categ2));
		produitDao.save(new Produit("Imprimante BROTHER MFC-J5335DW", 200, 20, categ2));
		produitDao.save(new Produit("Imprimante HP officejet Pro", 0, 1, categ2));
		produitDao.save(new Produit("Imprimante canon très peu utilisé", 35, 3, categ2));
		produitDao.save(new Produit("Photocopieur couleur A3 & A4 multifonction Ricoh mp c2003", 800, 2, categ2));

		
		
	
		produitDao.save(new Produit("Camescope de chez CANON +1 appareil photo", 85, 1, categ3));
		produitDao.save(new Produit("Canon 5D Mark II Eos Pack Objectifs + Accessoires", 2700, 20, categ3));
		produitDao.save(new Produit("RARE Canon EF 200mm f/2L IS USM", 5200, 45, categ3));
		produitDao.save(new Produit("Nikon D7000 + Accessoires", 800, 5, categ3));
		produitDao.save(new Produit("Nikon D90", 200, 2, categ3));
		produitDao.save(new Produit("Polaroid EE44 complet en etat de marche", 50, 2, categ3));
		produitDao.save(new Produit("Sac professionnel Photo K&F Concept", 55, 4, categ3));
		
		

		List<Produit> produits = produitDao.findAll();
		for (Produit p : produits) {
			System.out.println("Des:" + p.getDesignation() + " prix: " + p.getPrix() + " quantité: " + p.getQuantite()
					+ " categorie: " + p.getCateg().getNomCategorie());
		}

		List<Produit> produits1 = produitDao.findByDesignation("%H%");
		for (Produit p : produits1) {
			System.out.println("Des:" + p.getDesignation() + " prix: " + p.getPrix() + " quantité: " + p.getQuantite()
					+ " categorie: " + p.getCateg().getNomCategorie());
		}

	}

}
