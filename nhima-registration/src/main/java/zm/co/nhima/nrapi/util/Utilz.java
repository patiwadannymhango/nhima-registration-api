package zm.co.nhima.nrapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import zm.co.nhima.nrapi.model.User;
import zm.co.nhima.nrapi.security.UserLoginService;

@Service
public class Utilz {
	
	@Autowired
	private UserLoginService userService;
	
//	@Autowired
//	private TempDependentService tempDependentService;
//	
//	@Autowired
//	private RelationshipRepo relationshipRepo;
//	
//	@Autowired
//	private PayerService payerService;
	
	private static final CsvMapper mapper = new CsvMapper();
	 
    public static <T> List<T> readDependent(Class<T> type, InputStream stream) throws IOException {
        CsvSchema schema = mapper.schemaFor(type).withHeader()
        		.sortedBy("principalNhimaNumber","firstName","lastName","gender","dob",
        				"relationship","email","phone","idType","idNumber",
        				"identificationNumber","residentialAddress","province","town","dependentType"); 
        ObjectReader reader = mapper.readerFor(type).with(schema);
        return reader.<T>readValues(stream).readAll();
    }
    
    public static <T> List<T> readSpouse(Class<T> type, InputStream stream) throws IOException {
        CsvSchema schema = mapper.schemaFor(type).withHeader()
        		.sortedBy("principalNhimaNumber","firstName","lastName","gender","dob",
        				"relationship","email","phone","idType","idNumber","residentialAddress","province","town");
        ObjectReader reader = mapper.readerFor(type).with(schema);
        return reader.<T>readValues(stream).readAll();
    }
    
    public static <T> List<T> readPrincipal(Class<T> type, InputStream stream) throws IOException {
        CsvSchema schema = mapper.schemaFor(type).withHeader()
        		.sortedBy("payerRef","employmentType","salary","nhimaNumber","firstName","lastName","gender","dob",
        				"email","phone","idType","idNumber","residentialAddress","province","town");
        ObjectReader reader = mapper.readerFor(type).with(schema);
        return reader.<T>readValues(stream).readAll();
    }
	
    public String getUserFullname(UserDetails userDetails) {
		String username = userDetails.getUsername();
		User user = userService.getByUsername(username);

		return user != null ? user.getFirstName() + " " + user.getLastName() : "";

	}

	public String getUserFullname(Long userId) {

		User user = userService.getOne(userId);

		return user != null ? user.getFirstName() + " " + user.getLastName() : "";

	}
		
//	public String getDependentRelationship(Long id) {
//		
//		TempDependent tempDependent = tempDependentService.getOne(id);
//		String relationshipValue = tempDependent.getRelationship();
//		return relationshipRepo.findByValue(relationshipValue).getDescription();
//
//	}
	
	public String resolveGenderName(String code) {
		
		switch (code)
		{
		     case "M":
		    	 return "Male";
		     case "F":
		    	 return "Female";
		     default:
		    	 return "Undefined";
		}
		
	}
	
	public String resolveGenderCode(String name) {
		
		switch (name)
		{
		     case "Male":
		    	 return "F";
		     case "Female":
		    	 return "F";
		     default:
		    	 return "U";
		}
		
	}
	
	public String resolveIdName(String idType) {
		switch (idType)
		{
		     case "NRC":
		    	 return "NATIONAL REGISTRATION CARD";
		     case "PASSPORT":
		    	 return "PASSPORT";
		     default:
		    	 return "DRVING LICENSE";
		}
		
	}
	
	public String resolveDependentType(String depType) {
		
		switch(depType)
		{
			case "D":
				return "ADULT";
			case "C":
				return "CHILD";
			case "H":
				return "HOLDER";
			default:
				return "NONE";	
		}
	}
}
