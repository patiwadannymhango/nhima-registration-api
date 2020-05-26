package zm.co.nhima.nrapi.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@GetMapping(path="/{userId}")
	public String getUser(@PathVariable String userID) {
		return"get user ID";
	}
	
	@GetMapping
	public String getUsers() {
		return"get user ID";
	}
	
	@PostMapping
	public String creatUser() {
		return"user has been created";
	}
	
	@PutMapping(path="/{userId}")
	public String updateUser() {
		return"user has been updated";
	}
	
	@DeleteMapping(path="/{userId}")
	public String deleteUser() {
		return "user has been deleted";
	}

}
