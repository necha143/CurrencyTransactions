package restTest.CurrencyTransactions.models;

import javax.xml.bind.annotation.*;

/*https://cbr.ru/scripts/XML_dynamic.asp?date_req1=14/11/2023&date_req2=14/11/2023&VAL_NM_RQ=R01235 = usd*/
/*https://cbr.ru/scripts/XML_dynamic.asp?date_req1=14/11/2023&date_req2=14/11/2023&VAL_NM_RQ=R01239 = euro*/


@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValCursCBRF {
    @XmlAttribute(name = "ID")
    private String id;
    @XmlAttribute(name = "DateRange1")
    private String date1;
    @XmlAttribute(name = "DateRange2")
    private String date2;
    @XmlAttribute(name = "name")
    private String name;
    
    @XmlElement(name = "Record")
    private ExchangeRateCBRF exchangeRateCBRF;

    public ExchangeRateCBRF getExchangeRate() {
        return exchangeRateCBRF;
    }
}
