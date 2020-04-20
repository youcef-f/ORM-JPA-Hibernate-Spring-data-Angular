package com.japhibernatespringdata.dao;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import com.japhibernatespringdata.entities.Produit;


/*
 * Cette classe permet de retourner le id de Entité. Par defaut rest data ne renvoi pas cette attribut.
 * 
 */
@Component
public class ConfigRepositoryRest  implements RepositoryRestConfigurer {

    @Override
      public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Produit.class);  // export l'attribut ID de l'entité
        
        config.getCorsRegistry()
        .addMapping("/**")
        .allowedOrigins("*") //
        .allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH") //
        .allowedHeaders("*"); //
        
        
      }

}
