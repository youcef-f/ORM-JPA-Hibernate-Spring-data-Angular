package com.japhibernatespringdata.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.japhibernatespringdata.entities.Produit;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RepositoryRestResource
public interface IProduitRepository extends JpaRepository<Produit, Long> {

	// Pas besoin de faire une implementation des methodes de base spring car elles
	// sont faite par spring.

	// Ajouter nos propres interface qui sont specifiques n'existant parmis les
	// methode bases dans spring/
	@RestResource(path = "/byDesgination")
	@Query("select p from Produit p where p.designation like :design")
	public List<Produit> findByDesignation(@Param("design") String design);

	@RestResource(path = "/byDesginationContain")
	public List<Produit> findByDesignationContains(@Param("design")String design);
	
	@RestResource(path = "/byDesginationContainPage")
	public Page<Produit> findByDesignationContains(@Param("design")String design, Pageable pageable);
	
	
	
}
