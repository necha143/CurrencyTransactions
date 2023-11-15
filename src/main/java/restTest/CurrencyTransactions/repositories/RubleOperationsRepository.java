package restTest.CurrencyTransactions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restTest.CurrencyTransactions.models.RubleOperations;

import java.util.Date;
import java.util.List;

@Repository
public interface RubleOperationsRepository extends JpaRepository<RubleOperations, Integer> {
    public List<RubleOperations> findAllByTimeBetween(Date from, Date to);
}
