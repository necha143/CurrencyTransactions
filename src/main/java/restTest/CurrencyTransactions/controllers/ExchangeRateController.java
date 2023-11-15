package restTest.CurrencyTransactions.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restTest.CurrencyTransactions.models.ExchangeRate;
import restTest.CurrencyTransactions.services.ExchangeRateService;

import java.util.List;

@RestController
@RequestMapping("/exrate")
@Tag(name = "Контроллер для обменного курса")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping
    @Operation(summary = "Получение всех записей по обменному курсу")
    public List<ExchangeRate> getExchangeRates() {
        return exchangeRateService.findAll();
    }

    @GetMapping("/{currency}/{date}") // Date getting in the form of ddmmyyyy
    @Operation(summary = "Получение записи по определенному обменному курсу в определенный день")
    public ExchangeRate getExchangeRate(@PathVariable("currency") String currency, @PathVariable("date") String date) {
        return exchangeRateService.findOne(date, currency);
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление обменного курса")
    public ResponseEntity<HttpStatus> create() {
        exchangeRateService.save();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
