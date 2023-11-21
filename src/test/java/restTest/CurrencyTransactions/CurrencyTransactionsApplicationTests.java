package restTest.CurrencyTransactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import restTest.CurrencyTransactions.models.ExchangeRate;
import restTest.CurrencyTransactions.models.RubleOperations;

import java.util.GregorianCalendar;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyTransactionsApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Проверка получения записи по финансовой операции по её ID")
    void checkGetInRubleOperationsController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ruble/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("Проверка ответа post-запроса на добавление фин. операции")
    void checkPostInRubleOperationsController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/ruble/add")
                        .content(asJsonString(new RubleOperations(
                                new GregorianCalendar(2023, 10, 14).getTime(), "Test", 1500)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    @DisplayName("Проверка получения записи по обменному курсу в определенный день")
    void checkGetInInExchangeRateController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/exrate/EUR/15112023")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2023-11-14T21:00:00.000+00:00"));
    }

    @Test
    @DisplayName("Проверка ответа post-запроса на добавление обменного курса")
    void checkPostInExchangeRateController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/exrate/add")
                        .content(asJsonString(new ExchangeRate(
                                new GregorianCalendar(2023, 10, 14).getTime(),
                                "R01235", "USD", 100)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    @DisplayName("Проверка ответа get-запроса на список финансовых операций за заданный период с суммами, пересчитанными по курсу заданной валюты")
    void checkGetInMonetaryTransactionsController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/transactions/{currency}/{from}/{to}", "EUR", "13112023", "15112023")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}