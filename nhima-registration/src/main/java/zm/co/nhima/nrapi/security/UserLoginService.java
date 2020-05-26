package zm.co.nhima.nrapi.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zm.co.nhima.nrapi.model.PasswordResetToken;
import zm.co.nhima.nrapi.model.Role;
import zm.co.nhima.nrapi.model.User;
import zm.co.nhima.nrapi.repository.PasswordResetTokenRepo;
import zm.co.nhima.nrapi.repository.RoleService;
import zm.co.nhima.nrapi.repository.UserRepo;



@Service
public class UserLoginService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	RoleService roleService;
	
	@Autowired
	private PasswordResetTokenRepo passwordTokenRepository;


	@Transactional
	public User createUserIfNotFound(User newUser, Long[] roleIds) {
		User user = userRepo.findByUsername(newUser.getEmail());
		Role role = null;
		for (Long id : roleIds) {
			role = roleService.getOne(id);
			newUser.getRoles().add(role);
			role.getUsers().add(newUser);
			roleService.save(role);
		}

		if (user == null) {
			newUser.setUsername(newUser.getEmail());
			newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
			user = userRepo.save(newUser);
		}

		return user;
	}

	@Transactional
	public User createUserIfNotFound(User newUser, String userRole) {
		User user = userRepo.findByUsername(newUser.getEmail());

		if (user == null) {
			Role role = roleService.findByName(userRole);
			newUser.getRoles().add(role);
			role.getUsers().add(newUser);
			roleService.save(role);
			newUser.setUsername(newUser.getEmail());
			newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
			user = userRepo.save(newUser);
		}

		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User doesn't exist");
		}
		return new UserPrincipal(user);
	}
	
	 
    public User getUserByPasswordResetToken(final String token) {
    	
        return passwordTokenRepository.findByToken(token).getUser();
    }
	
	public void createPasswordResetTokenForUser(User user, String token) {
	    PasswordResetToken myToken = new PasswordResetToken(token, user);
	    passwordTokenRepository.save(myToken);
	}
	
	public void resetPassword(User user, String password) {
	    user.setPassword(passwordEncoder.encode(password));
	    userRepo.save(user);
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	
	public void delete(Long id) {
		userRepo.deleteById(id);
	}

	public User getOne(Long id) {
		return userRepo.getOne(id);
	}
	
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public User save(User user) {
		return userRepo.save(user);
	}
	
	public User getByUsername(String username) {
		return userRepo.findByUsername(username);
	}


}
