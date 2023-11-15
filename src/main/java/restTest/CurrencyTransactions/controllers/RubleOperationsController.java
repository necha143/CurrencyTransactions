package restTest.CurrencyTransactions.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import restTest.CurrencyTransactions.models.RubleOperations;
import restTest.CurrencyTransactions.services.RubleOperationsService;

import java.util.List;

@RestController
@RequestMapping("/ruble")
@Tag(name = "Контроллер для финансовых операций")
public class RubleOperationsController {
    private final RubleOperationsService rubleOperationsService;

    @Autowired
    public RubleOperationsController(RubleOperationsService rubleOperationsService) {
        this.rubleOperationsService = rubleOperationsService;
    }
    
    @GetMapping
    @Operation(summary = "Получение всех записей по финансовым операциям")
    public List<RubleOperations> getRubleOperations(){
        return rubleOperationsService.findAll();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Получение записи по финансовой операции по её ID")
    public RubleOperations getgetRubleOperation(@PathVariable("id") int id){
        return rubleOperationsService.findOne(id);
    }
    
    @PostMapping("/add")
    @Operation(summary = "Добавление финансовой операции")
    public ResponseEntity<HttpStatus> create(@RequestBody RubleOperations rubleOperations, BindingResult bindingResult){
        rubleOperationsService.save(rubleOperations);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
