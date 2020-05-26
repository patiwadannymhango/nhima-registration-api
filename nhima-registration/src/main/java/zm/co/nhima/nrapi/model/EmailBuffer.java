package zm.co.nhima.nrapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "EmailBuffer")
@Table(name = "email_buffer")
public class EmailBuffer {

	@Id
	@Column(name = "email_id")
	private String emailId;

	@Column(name = "email_message", length = 2000)
	private String emailMessage;

	@Column(name = "receipients")
	private String receipients;

	@Column(name = "emailStatus")
	private boolean emailStatus;

	public EmailBuffer(String emailId, String emailMessage, String receipients, boolean emailStatus) {
		super();
		this.emailId = emailId;
		this.emailMessage = emailMessage;
		this.receipients = receipients;
		this.emailStatus = emailStatus;
	}

	public EmailBuffer() {

	}

}
