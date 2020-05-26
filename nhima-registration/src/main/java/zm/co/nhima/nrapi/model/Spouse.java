package zm.co.nhima.nrapi.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

public class Spouse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="spouse_id")
	private Long id;
	private String identificationNumber;
	private String firstName;
	private String lastName;
	private String dob;
	private String emailAddress;
	private String phone;
	@NotNull(message = "Street is mandatory")
	private String street;
	@NotNull(message = "town is mandatory")
	private String town;
	@NotNull(message = "province is mandatory")
	private String province;
	private String marriageCertificate;
	
	
//	private BioInformation bioInformation;
//	private Principal principal;

}
