package zm.co.nhima.nrapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zm.co.nhima.nrapi.model.User;


public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
