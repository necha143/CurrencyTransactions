package restTest.CurrencyTransactions.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "ruble_operations")
public class RubleOperations {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int id;
    @Column(name = "time")
    private Date time;
    @Column(name = "description")
    private String description;
    @Column(name = "amount")
    private int amount;

    public RubleOperations() {
    }

    public RubleOperations(Date time, String description, int amount) {
        this.time = time;
        this.description = description;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "RubleOperations{" +
                "time=" + time +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
