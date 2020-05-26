package zm.co.nhima.nrapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zm.co.nhima.nrapi.model.PasswordResetToken;

public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Long>{
	PasswordResetToken findByToken(String token);
}
