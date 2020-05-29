package zm.co.nhima.nrapi.model.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class BulkDto {
	private MultipartFile bulkFile;
}
