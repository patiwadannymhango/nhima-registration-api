package zm.co.nhima.nrapi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zm.co.nhima.nrapi.model.Role;



@Service
public class RoleService {
	@Autowired
	private RoleRepo roleRepo;

	public List<Role> findAll() {
		return roleRepo.findAll();
	}

	public Role getOne(Long id) {
		return roleRepo.getOne(id);
	}

	public void delete(Role role) {
		roleRepo.delete(role);
	}

	public Role save(Role role) {
		return roleRepo.save(role);
	}

	public Role findByName(String role) {
		return roleRepo.findByName(role);
	}
}
