package restTest.CurrencyTransactions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restTest.CurrencyTransactions.models.ExchangeRate;

import java.util.Date;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
    ExchangeRate findFirstByDateAndNameCurrency(Date date, String name);
}
