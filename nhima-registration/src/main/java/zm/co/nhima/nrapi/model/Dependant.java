package zm.co.nhima.nrapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;


@Data
@Entity
@Table
public class Dependant {
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
	private String guardianId;
	private String birthCertificate;
	
	//private BioInformation bioInformation;
	

}
