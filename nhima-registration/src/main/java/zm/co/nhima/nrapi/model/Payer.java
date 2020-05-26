package zm.co.nhima.nrapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Payer {
	
	@Id
	private String ref;
	private String name;

}
