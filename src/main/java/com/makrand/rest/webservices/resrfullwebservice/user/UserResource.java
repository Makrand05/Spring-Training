package com.makrand.rest.webservices.resrfullwebservice.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

import org.hibernate.metamodel.mapping.EntityValuedModelPart;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service=service;
	}
	//Get USERS
	@GetMapping("/users")
	public List<User> retriveUsers(){
		return service.findAll();
		
	}
	//GET USER
	@GetMapping("/users/{id}")
	public  EntityModel<User> retrieveUser(@PathVariable int id){
		User user =	service.findOne(id);
		
		if(user==null)
		{
			throw new UserNotFoundException("id : " +id);
		}
		EntityModel<User> entityModel= EntityModel.of(user);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveUsers());
		entityModel.add(link.withRel("all-user"));
		
		return entityModel;
		
	}
	
	//GET USER
		@DeleteMapping("/users/{id}")
		public void DeleteUser(@PathVariable int id){
				service.deleteById(id);
		
		}
	
	//POST /Users
	@PostMapping("/users")
	public ResponseEntity<Object> createUser (@Valid @RequestBody User user ){
	 User savedUser=	service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
	// /user/4
	 return	ResponseEntity.created(location ).build();
	}
	

}
