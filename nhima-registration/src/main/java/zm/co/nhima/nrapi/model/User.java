package zm.co.nhima.nrapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="user_id")
	private Long id;
	@NotNull
	private String firstName;
	private String username;
	@NotNull
	private String lastName;
	@NotNull
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String phone;	
	private String forgotPasswordCode;
	private Date forgotPasswordExpiryDate;
	private Date lastPasswordChangeDate;
	private String createdBy;
	private Date dateCreated = new Date(System.currentTimeMillis());
	private boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(referencedColumnName = "role_id"))
	@JsonIgnoreProperties("users")
	private List<Role> roles = new ArrayList<>();
	
	@OneToOne(mappedBy = "user")
    private PasswordResetToken passwordResetToken;
	
}
