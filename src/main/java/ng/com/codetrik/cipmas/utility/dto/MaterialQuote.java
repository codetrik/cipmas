package ng.com.codetrik.cipmas.utility.dto;
/*
 @Author Hamzat Habibllahi
 */
import lombok.Data;

//Wrapper class for Joined Table result of Material and Quote
@Data
public class MaterialQuote {
    private String materialGenericName;//e.g Inverter
    private String materialGauge; //e.g 3.5 KVA
    private double materialUnitPrice;// e.g N90000
    private String materialUnit;//e.g Unit
    private double quotePrice;// e.g N90000 * 1 = N90000
    private double quoteQuantity;// e.g 1
}
