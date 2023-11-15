package restTest.CurrencyTransactions.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restTest.CurrencyTransactions.models.MonetaryTransactions;
import restTest.CurrencyTransactions.services.MonetaryTransactionsService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Контроллер для перевода финансовых операций в другую валюту")
public class MonetaryTransactionsController {
    private final MonetaryTransactionsService monetaryTransactionsService;

    @Autowired
    public MonetaryTransactionsController(MonetaryTransactionsService monetaryTransactionsService) {
        this.monetaryTransactionsService = monetaryTransactionsService;
    }

    @GetMapping("/{currency}/{from}/{to}") // Date getting in the form of ddmmyyyy
    @Operation(summary = "Список финансовых операций за заданный период с суммами, пересчитанными по курсу заданной валюты")
    public List<MonetaryTransactions> getTransactions(@PathVariable("currency") String currency, 
                                                      @PathVariable("from") String from, @PathVariable("to") String to) {
        return monetaryTransactionsService.getTransactions(from, to, currency);
    }
}
