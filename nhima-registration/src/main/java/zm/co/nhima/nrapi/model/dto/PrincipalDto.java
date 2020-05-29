package zm.co.nhima.nrapi.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PrincipalDto {
	
	private Long id;
	private String payer;
	
	@NotNull(message = "nhima Number is requried")
	private String nhimaNumber;
	
	@NotNull(message = "First Name is requried")
	private String firstName;
	
	@NotNull(message = "Last Name is requried")
	private String lastName;
	
	@NotNull(message = "Gender is requried")
	private String gender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	@NotNull(message = "Employment Type is requried")
	private String employmentType;
	
	@NotNull(message = "Salary is requried")
	private double salary;
	
	private String email;
	
	@NotNull(message = "ID Type is requried")
	private String idType;
	
	@NotNull(message = "ID Number is requried")
	private String idNumber;
	
	@NotNull(message = "Phone is requried")
	private String phone;
	
	@NotNull(message = "Residential Address is requried")
	private String residentialAddress;
	
	@NotNull(message = "Province is requried")
	private String province;
	
	@NotNull(message = "Town is requried")
	private String town;
	
//	@NotNull(message = "Passport Size photo is Required")
	private MultipartFile passportSizePhoto;
	
}
