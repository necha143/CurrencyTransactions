package restTest.CurrencyTransactions.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restTest.CurrencyTransactions.models.RubleOperations;
import restTest.CurrencyTransactions.repositories.RubleOperationsRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RubleOperationsService {
    private final RubleOperationsRepository rubleOperationsRepository;

    @Autowired
    public RubleOperationsService(RubleOperationsRepository rubleOperationsRepository) {
        this.rubleOperationsRepository = rubleOperationsRepository;
    }
    
    public List<RubleOperations> findAll(){
        return rubleOperationsRepository.findAll();
    }
    
    public RubleOperations findOne(int id) {
        return rubleOperationsRepository.findById(id).get();
    }
    
    public List<RubleOperations> findAllByTimeBetween(Date from, Date to){
        return rubleOperationsRepository.findAllByTimeBetween(from, to);
    }

    @Transactional
    public void save(RubleOperations rubleOperations) {
        rubleOperationsRepository.save(rubleOperations);
    }
}