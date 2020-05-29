package zm.co.nhima.nrapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Spouse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="temp_spouse_id")
	private Long id;
	@Column(unique=true)
	private String principalNhimaNumber;
	private String firstName;
	private String lastName;
	private String idType;
	@Column(unique=true)
	private String idNumber;
	private String gender;
	@DateTimeFormat(pattern = "dd-MMMM-yy")
	private Date dob;
	private String relationship;
	@Column(unique=true)
	private String email;
	@Column(unique=true)
	private String phone;
	private String residentialAddress;
	private String province;
	private String town;
	private String dependentType;
	private int dependentRef;
	@Column(unique=true)
	private String passportSizePhoto;
	@Column(unique=true)
	private String supportingDocument;
	private String status;
	private String createdBy;
	private Date createdDate;
	private Date modifiedDate;
	
}
