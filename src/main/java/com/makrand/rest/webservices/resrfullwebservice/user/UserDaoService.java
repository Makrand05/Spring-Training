package com.makrand.rest.webservices.resrfullwebservice.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Predicate;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private static  int count =0;
	static {
		users.add(new User(++count,"Amar",LocalDate.now().minusYears(30)) );
		users.add(new User(++count,"Amit",LocalDate.now().minusYears(25)) );
		users.add(new User(++count,"Ram",LocalDate.now().minusYears(40)) );
		
	}

	public List<User> findAll(){
		return users;
		
	}
	public User findOne(int id) {	
		
	return	users.stream().filter(p->p.getId().equals(id)).findFirst().orElse(null);
		
	}
	
	public User save(User user) {
		user.setId(++count);
		users.add(user);
		return user;
	}
	
	public void deleteById(int id) {	
	//	Predi< ? super User> p = user->user.getId().equals(id);
		
		java.util.function.Predicate<? super User> p=user->user.getId().equals(id);
		
	users.removeIf(p);
			
		}
}
