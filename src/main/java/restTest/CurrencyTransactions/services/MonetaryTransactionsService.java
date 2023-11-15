package restTest.CurrencyTransactions.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restTest.CurrencyTransactions.models.ExchangeRate;
import restTest.CurrencyTransactions.models.MonetaryTransactions;
import restTest.CurrencyTransactions.models.RubleOperations;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MonetaryTransactionsService {
    private final RubleOperationsService rubleOperationsService;
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public MonetaryTransactionsService(RubleOperationsService rubleOperationsService, ExchangeRateService exchangeRateService) {
        this.rubleOperationsService = rubleOperationsService;
        this.exchangeRateService = exchangeRateService;
    }

    public List<MonetaryTransactions> getTransactions(String fromS, String toS, String nameCurr) {
        Date from = getDate(fromS);
        Date to = getDate(toS);

        List<MonetaryTransactions> monetaryTransactionsList = new ArrayList<>();

        List<RubleOperations> rubleOperations = rubleOperationsService.findAllByTimeBetween(from, to);

        Calendar calendar = new GregorianCalendar(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue() - 1, LocalDateTime.now().getDayOfMonth());
        Date todayDate = calendar.getTime();
        ExchangeRate exchangeRate = exchangeRateService.findByDateAndNameCurrency(todayDate, nameCurr);

        for (RubleOperations rubleOperation : rubleOperations) {
            MonetaryTransactions monetaryTransactions = new MonetaryTransactions(
                    rubleOperation.getTime(), rubleOperation.getDescription(),
                    rubleOperation.getAmount() / exchangeRate.getValue(), exchangeRate.getNameCurrency());

            monetaryTransactionsList.add(monetaryTransactions);
        }

        return monetaryTransactionsList;
    }

    private Date getDate(String date) {
        String day = date.substring(0, 2);
        String month = date.substring(2, 4);
        String year = date.substring(4, 8);

        return new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day)).getTime();
    }
}
