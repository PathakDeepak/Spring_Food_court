package com.example.taco.data;


/*
 * CrudRepository declares about a dozen methods for CRUD (create, read, update, delete) operations. Notice that it’s parameterized, 
 * with the first parameter being the entity type the repository is to persist, and the second parameter being the type of the entity ID property.
 * */
import org.springframework.data.repository.CrudRepository;

import com.example.taco.Taco;

public interface TacoRepository 
         extends CrudRepository<Taco, Long> {

}
