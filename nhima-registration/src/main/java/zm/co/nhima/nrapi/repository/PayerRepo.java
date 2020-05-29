package zm.co.nhima.nrapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import zm.co.nhima.nrapi.model.Payer;

public interface PayerRepo extends JpaRepository<Payer, String>{
	
	List<Payer> findByOrderByNameAsc();
	Payer findByName(String name);

}
