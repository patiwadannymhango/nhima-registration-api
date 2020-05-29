package zm.co.nhima.nrapi.controllers;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import zm.co.nhima.nrapi.model.Payer;
import zm.co.nhima.nrapi.model.Principal;
import zm.co.nhima.nrapi.model.dto.BulkDto;
import zm.co.nhima.nrapi.model.dto.PrincipalDto;
import zm.co.nhima.nrapi.repository.PayerService;
import zm.co.nhima.nrapi.repository.PrincipalService;
import zm.co.nhima.nrapi.util.AppConstants;
import zm.co.nhima.nrapi.util.FileStorageServiceImpl;
import zm.co.nhima.nrapi.util.Utilz;

@RestController
@CrossOrigin
@RequestMapping("/principals")
public class PrincipalController {
	
	@Autowired
	PayerService payerService;
	
	@Autowired
	PrincipalService principalService;
	
	@Autowired
	private FileStorageServiceImpl fileStorageServiceImpl;
	
	
	@GetMapping(path="/{nhimaNumber}")
	public Principal getPrincipal(@PathVariable String nhimaNumber) {
		return principalService.getByNhimaNumber(nhimaNumber);
	}
	
	@GetMapping
	public List<Principal> getPrincipals() {
		return principalService.findAll();
	}
	
	@PostMapping
	public Principal createPrincipal(@Valid @RequestBody PrincipalDto principalDto,
			@AuthenticationPrincipal UserDetails userDetails) {
		
			Principal principal = new Principal();
				
			 try {
					
				  Date d = principalDto.getDob();
				  Calendar c = Calendar.getInstance();
				  c.setTime(d);
				  int year = c.get(Calendar.YEAR);
				  int month = c.get(Calendar.MONTH) + 1;
				  int date = c.get(Calendar.DATE);
				  LocalDate l1 = LocalDate.of(year, month, date);
				  LocalDate now1 = LocalDate.now();
				  
				  Period diff1 = Period.between(l1, now1);
				  
				  if( diff1.getYears() < 16) {
					  throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,AppConstants.AGE_ERROR);
				   }
				  
				if(principalDto.getEmploymentType().equals("FORMAL")) {
					principal.setPayerRef(principalDto.getPayer());
					principal.setCompanyId(principalDto.getPayer());
					Payer payer = payerService.getOne(principalDto.getPayer());
					principal.setCompanyName(payer.getName());
				}
		
				principal.setNhimaNumber(principalDto.getNhimaNumber());
				principal.setFirstName(principalDto.getFirstName());
				principal.setLastName(principalDto.getLastName());
				principal.setGender(principalDto.getGender());
				principal.setDob(principalDto.getDob());
				principal.setIdType(principalDto.getIdType());
				principal.setIdNumber(principalDto.getIdNumber());
				principal.setEmploymentType(principalDto.getEmploymentType());
				principal.setSalary(principalDto.getSalary());
				principal.setEmail(principalDto.getEmail());
				principal.setPhone(principalDto.getPhone());
				principal.setResidentialAddress(principalDto.getResidentialAddress());
				principal.setProvince(principalDto.getProvince());
				principal.setTown(principalDto.getTown());
				principal.setEmployeeNumber(principalDto.getNhimaNumber());
				
				double prem = (0.01*principalDto.getSalary());		
				principal.setPremium(prem);
				
				principal.setDependentRef("0");
				principal.setDependentType("H");
				
				String username = userDetails.getUsername();
				principal.setCreatedBy(username);
				
				if (principalDto.getPassportSizePhoto().getSize() <= 0) {
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,AppConstants.NO_FILE_ATTACHED_ERROR);
				}else {
					String fileName = fileStorageServiceImpl.storeFile(principalDto.getPassportSizePhoto());
					principal.setPassportSizePhoto(fileName);
					principal.setPassportSizePhotoUploadDate(new Date());
				}
				
				principal.setStatus(AppConstants.INCOMPLETE);
				principal.setCreatedDate(new Date());
						
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,AppConstants.FILE_UPLOAD_ERROR,ex);
				}
				
				return principalService.save(principal);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping("/bulk-upload")
	public void registerBulk(@Valid @RequestBody BulkDto bulkDto,
			@AuthenticationPrincipal UserDetails userDetails,
			RedirectAttributes attributes,
			BindingResult result) {
		
		try {
			
			final MultipartFile file = bulkDto.getBulkFile();
			
			if (file.isEmpty()){
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,AppConstants.NO_FILE_ATTACHED_ERROR);
			}else {

				List<Principal> bulkSpouse = Utilz.readPrincipal(Principal.class, bulkDto.getBulkFile().getInputStream());
				bulkSpouse.forEach((principalRow) -> {
					
						Principal principal = new Principal();
					
						principal.setPayerRef(principalRow.getPayerRef());
						principal.setCompanyId(principalRow.getPayerRef());
						
						Payer payer = payerService.getOne(principalRow.getPayerRef());
						
						principal.setCompanyName(payer.getName());
						principal.setNhimaNumber(principalRow.getNhimaNumber());
						principal.setFirstName(principalRow.getFirstName());
						principal.setLastName(principalRow.getLastName());
						principal.setGender(principalRow.getGender());
						principal.setDob(principalRow.getDob());
						principal.setIdType(principalRow.getIdType());
						principal.setIdNumber(principalRow.getIdNumber());
						principal.setEmploymentType(principalRow.getEmploymentType());
						principal.setSalary(principalRow.getSalary());
						principal.setEmail(principalRow.getEmail());
						principal.setPhone(principalRow.getPhone());
						principal.setResidentialAddress(principalRow.getResidentialAddress());
						principal.setProvince(principalRow.getProvince());
						principal.setTown(principalRow.getTown());
						principal.setEmployeeNumber(principalRow.getNhimaNumber());
						
						double prem = (0.01*principalRow.getSalary());		
						
						principal.setPremium(prem);
						principal.setDependentRef("0");
						principal.setDependentType("H");
						
						String username = userDetails.getUsername();
						
						principal.setCreatedBy(username);
						principal.setStatus(AppConstants.INCOMPLETE);
						principal.setCreatedDate(new Date());
						principalService.save(principal);
				});	
			}
				
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,AppConstants.FILE_UPLOAD_ERROR,ex);
		}	
	}
	
	@PutMapping(path="/{nhimaNumber}")
	public Principal updatePrincipal(@PathVariable String nhimaNumber, 
			@Valid @RequestBody PrincipalDto principalDto,
			@AuthenticationPrincipal UserDetails userDetails) {
		
		Principal principal = principalService.getByNhimaNumber(nhimaNumber);
		
		 try {
				
			  Date d = principalDto.getDob();
			  Calendar c = Calendar.getInstance();
			  c.setTime(d);
			  int year = c.get(Calendar.YEAR);
			  int month = c.get(Calendar.MONTH) + 1;
			  int date = c.get(Calendar.DATE);
			  LocalDate l1 = LocalDate.of(year, month, date);
			  LocalDate now1 = LocalDate.now();
			  
			  Period diff1 = Period.between(l1, now1);
			  
			  if( diff1.getYears() < 16) {
				  throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,AppConstants.AGE_ERROR);
			   }
			  
			if(principalDto.getEmploymentType().equals("FORMAL")) {
				principal.setPayerRef(principalDto.getPayer());
				principal.setCompanyId(principalDto.getPayer());
				Payer payer = payerService.getOne(principalDto.getPayer());
				principal.setCompanyName(payer.getName());
			}
	
			principal.setNhimaNumber(principalDto.getNhimaNumber());
			principal.setFirstName(principalDto.getFirstName());
			principal.setLastName(principalDto.getLastName());
			principal.setGender(principalDto.getGender());
			principal.setDob(principalDto.getDob());
			principal.setIdType(principalDto.getIdType());
			principal.setIdNumber(principalDto.getIdNumber());
			principal.setEmploymentType(principalDto.getEmploymentType());
			principal.setSalary(principalDto.getSalary());
			principal.setEmail(principalDto.getEmail());
			principal.setPhone(principalDto.getPhone());
			principal.setResidentialAddress(principalDto.getResidentialAddress());
			principal.setProvince(principalDto.getProvince());
			principal.setTown(principalDto.getTown());
			principal.setEmployeeNumber(principalDto.getNhimaNumber());
			
			double prem = (0.01*principalDto.getSalary());		
			principal.setPremium(prem);
			
			principal.setDependentRef("0");
			principal.setDependentType("H");
			
			String username = userDetails.getUsername();
			principal.setLastModifiedBy(username);
			principal.setModifiedDate(new Date());
			
			if (principalDto.getPassportSizePhoto().getSize() <= 0) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,AppConstants.FILE_UPLOAD_ERROR);
			}else {
				String fileName = fileStorageServiceImpl.storeFile(principalDto.getPassportSizePhoto());
				principal.setPassportSizePhoto(fileName);
				principal.setPassportSizePhotoUploadDate(new Date());
			}

			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,AppConstants.FILE_UPLOAD_ERROR,ex);
			}
			
			return principalService.save(principal);
	}
	
	@PostMapping("/submit/{nhimaNumber}")
	public Principal submitPrincipal(@PathVariable String nhimaNumber) {
		try {
			Principal principal = principalService.getByNhimaNumber(nhimaNumber);
			principal.setStatus(AppConstants.COMPLETED);
			return principalService.save(principal);
		}catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,AppConstants.SUBMIT_ERROR,ex);
		}
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(path="/{nhimaNumber}")
	public void deletePrincipal(@PathVariable String nhimaNumber) {
		try {
			principalService.delete(nhimaNumber);
		}catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,AppConstants.DELETE_ERROR,ex);
		}
	}
	
}
