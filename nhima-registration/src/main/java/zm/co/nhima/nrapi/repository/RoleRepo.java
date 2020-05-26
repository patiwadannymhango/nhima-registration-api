package zm.co.nhima.nrapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zm.co.nhima.nrapi.model.Role;



public interface RoleRepo extends JpaRepository<Role, Long> {
	
	Role findByName(String name);

}
