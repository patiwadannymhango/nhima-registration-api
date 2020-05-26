package zm.co.nhima.nrapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zm.co.nhima.nrapi.model.Principal;
import zm.co.nhima.nrapi.repository.PrincipalService;

@RestController
@RequestMapping("/principals")
public class PrincipalController {
	
	@Autowired
	PrincipalService principalService;
	
	
	@GetMapping(path="/{nhimaNumber}")
	public Principal getPrincipal(@PathVariable String nhimaNumber) {
		return principalService.getByNhimaNumber(nhimaNumber);
	}
	
	@GetMapping
	public List<Principal> getPrincipals() {
		return principalService.findAll();
	}
	
	@PostMapping
	public String createPrincipal() {
		return"user has been created";
	}
	
	@PutMapping(path="/{nhimaNumber}")
	public String updatePrincipal(@PathVariable String nhimaNumber) {
		return"user has been updated";
	}
	
	@DeleteMapping(path="/{nhimaNumber}")
	public String deletePrincipal(@PathVariable String nhimaNumber) {
		return "user has been deleted";
	}

}
