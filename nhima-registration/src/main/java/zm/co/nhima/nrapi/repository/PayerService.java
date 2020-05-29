package zm.co.nhima.nrapi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zm.co.nhima.nrapi.model.Payer;

@Service
public class PayerService {
	@Autowired
	private PayerRepo payerRepo;

	public List<Payer>  findAll() {
	return payerRepo.findAll();
	}
	public Payer getOne(String id) {
		return payerRepo.getOne(id);
	}
	
	public Payer findByName(String name) {
		return payerRepo.findByName(name);
	}
	public void delete(String value) {
		Payer payer = getOne(value);
		payerRepo.delete(payer);
	}
	public Payer save(Payer payer) {
		return payerRepo.save(payer);
	}
}

