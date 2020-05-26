package zm.co.nhima.nrapi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import zm.co.nhima.nrapi.model.Principal;

public interface PrincipalRepo extends JpaRepository<Principal, Long>{
	Principal findByNhimaNumber(String nhimaNumber);
	Principal findByIdNumberIgnoreCaseContaining(String id);
	List<Principal> findByCreatedByAndStatus(String username, String status);
}
