package zm.co.nhima.nrapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Principal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="principal_id")
	private Long id;
	private String payerRef;
	private String nhimaNumber;
	private String firstName;
	private String lastName;
	private String gender;
	private String idType;
	private String idNumber;
	@DateTimeFormat(pattern = "dd-MMMM-yy")
	private Date dob;
	private String employmentType;
	private double salary;
	private String email;
	private String phone;
	private String residentialAddress;
	private String province;
	private String town;
	private String employeeNumber;
	private double premium;
	private String dependentType;
	private String dependentRef;
	private String companyId;
	private String companyName;
	private String passportSizePhoto;  //Passport Size Photo.
	private String status;
	private String createdBy;
	private Date createdDate;
	private Date modifiedDate;
}
