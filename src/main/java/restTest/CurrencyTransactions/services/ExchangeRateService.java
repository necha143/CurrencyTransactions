package restTest.CurrencyTransactions.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restTest.CurrencyTransactions.models.ExchangeRate;
import restTest.CurrencyTransactions.models.ValCursCBRF;
import restTest.CurrencyTransactions.repositories.ExchangeRateRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional(readOnly = true)
@EnableScheduling
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }
    
    public ExchangeRate findOne(String dateS, String name){
        Date date = getDate(dateS);
        return exchangeRateRepository.findFirstByDateAndNameCurrency(date, name);
    }
    
    public List<ExchangeRate> findAll(){
        return exchangeRateRepository.findAll();
    }
    
    public ExchangeRate findByDateAndNameCurrency(Date date, String name){
        return exchangeRateRepository.findFirstByDateAndNameCurrency(date, name);
    }
    
    @Transactional
    @Scheduled(cron = "0 0 8,20 * * *")
    public void save(){
        LocalDateTime dateTime = LocalDateTime.now();
        try {
            ExchangeRate exchangeRateUSD = uploadInfo(dateTime, "USD");
            exchangeRateRepository.save(exchangeRateUSD);
            ExchangeRate exchangeRateEUR = uploadInfo(dateTime, "EUR");
            exchangeRateRepository.save(exchangeRateEUR);
        } catch (MalformedURLException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    
    private ExchangeRate uploadInfo(LocalDateTime date, String currency) throws JAXBException, MalformedURLException {
        String day = String.valueOf(date.getDayOfMonth());
        String month = String.valueOf(date.getMonthValue());
        String year = String.valueOf(date.getYear());
        String id = currency.equals("USD") ? "R01235" : "R01239";
        String url = String.format("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=%s/%s/%s&date_req2=%s/%s/%s&VAL_NM_RQ=%s",
                day, month, year, day, month, year, id);
        
        URL url_web = new URL(url);
        JAXBContext jaxbContext = JAXBContext.newInstance(ValCursCBRF.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        ValCursCBRF valCursCBRF = (ValCursCBRF) unmarshaller.unmarshal(url_web);

        Calendar calendar = new GregorianCalendar(date.getYear(), date.getMonthValue()-1, date.getDayOfMonth());
        Date date1 = calendar.getTime();
        
        ExchangeRate exchangeRate = new ExchangeRate();
        
        exchangeRate.setDate(date1);
        exchangeRate.setIdCurrency(valCursCBRF.getExchangeRate().getId());

        if (currency.equals("USD")) {
            exchangeRate.setNameCurrency("USD");
        } else {
            exchangeRate.setNameCurrency("EUR");
        }
        
        double value = Double.parseDouble(valCursCBRF.getExchangeRate().getValue().replace(",", "."));
        exchangeRate.setValue(value);
        
        return exchangeRate;
    }

    private Date getDate(String date) {
        String day = date.substring(0, 2);
        String month = date.substring(2, 4);
        String year = date.substring(4, 8);

        return new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day)).getTime();
    }
}
