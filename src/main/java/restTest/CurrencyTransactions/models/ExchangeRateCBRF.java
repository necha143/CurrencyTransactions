package restTest.CurrencyTransactions.models;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRateCBRF {
    @XmlAttribute(name = "Date")
    private String date;
    @XmlAttribute(name = "Id")
    private String id;
    @XmlElement(name = "Nominal")
    private int nominal;
    @XmlElement(name = "Value")
    private String value;
    @XmlElement(name = "VunitRate")
    private String vUnitRate;

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "date=" + date +
                ", id=" + id +
                ", nominal=" + nominal +
                ", value=" + value +
                ", vUnitRate=" + vUnitRate +
                '}';
    }
}
