package zm.co.nhima.nrapi.security;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zm.co.nhima.nrapi.model.PasswordResetToken;
import zm.co.nhima.nrapi.repository.PasswordResetTokenRepo;

@Service
public class SecurityService {
	
	
	@Autowired
	private PasswordResetTokenRepo passwordResetTokenRepo;
	
	public String validatePasswordResetToken(String token) {
		
	    final PasswordResetToken passToken = passwordResetTokenRepo.findByToken(token);
	 
	    return !isTokenFound(passToken) ? "invalidToken"
	            : isTokenExpired(passToken) ? "expired"
	            : null;
	}
	 
	private boolean isTokenFound(PasswordResetToken passToken) {
	    return passToken != null;
	}
	 
	private boolean isTokenExpired(PasswordResetToken passToken) {
	    final Calendar cal = Calendar.getInstance();
	    return passToken.getExpiryDate().before(cal.getTime());
	    
	}

}
