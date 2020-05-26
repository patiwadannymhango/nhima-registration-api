package zm.co.nhima.nrapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Relationship {
	@Id
	private String value;
	private String description;
	
}
