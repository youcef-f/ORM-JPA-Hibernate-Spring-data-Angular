package com.japhibernatespringdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.japhibernatespringdata.entities.Categorie;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RepositoryRestResource
public interface ICatagorieRepository extends JpaRepository<Categorie, Long> {

	@RestResource(path = "/byNameCategorie")
	public Categorie findByNomCategorie(@Param("nomCategorie")String nomCategorie);

}
