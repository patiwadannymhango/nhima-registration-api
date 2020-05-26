package zm.co.nhima.nrapi.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zm.co.nhima.nrapi.model.Principal;

@Service
public class PrincipalService {
	
	@Autowired
	private PrincipalRepo principalRepo;

	public List<Principal>  findAll() {
		return principalRepo.findAll();
	}
	
	public Principal getOne(Long id) {
		return principalRepo.getOne(id);
	}
	
	public Principal getByNhimaNumber(String nhimaNumber) {
		return principalRepo.findByNhimaNumber(nhimaNumber);
	}
	
	public Principal findByIdNumber(String id) {
		return principalRepo.findByIdNumberIgnoreCaseContaining(id);
	}
	
	public List<Principal> getByCreatedBy(String username) {
		List<Principal> principal = new ArrayList<>();
		findAll().stream().filter(t -> username.equals(t.getCreatedBy())).forEach(principal::add);
		return principal;
	}
	
	
	public Principal findByNhimaNumber(String nhimaNumber) {
		return principalRepo.findByNhimaNumber(nhimaNumber);
	}
	
	public List<Principal> getByCreatedByAndStatus(String username, String status){
		return principalRepo.findByCreatedByAndStatus(username, status);
	}
	
	public void delete(Long id) {
		Principal principal = getOne(id);
		principalRepo.delete(principal);
	}
	public Principal save(Principal principal) {
	return principalRepo.save(principal);
	}
}