package ng.com.codetrik.cipmas.utility.embeddable;
/*
 @Author Hamzat Habibllahi
 */
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;


@Embeddable
@JsonPropertyOrder({"countryCode","areaCode","exchangeCode","lineNumber"})
@Data
public class Telephone {

    @NotBlank
    @Column(name = "telephone_country_code",length = 5,nullable = false)
    private String countryCode;

    @NotBlank
    @Column(name = "telephone_area_code",length = 5,nullable = false)
    private String areaCode;

    @NotBlank
    @Column(name = "telephone_exchange_code",length = 5,nullable = false)
    private String exchangeCode;

    @NotBlank
    @Column(name = "telephone_line_number",length = 5,nullable = false)
    private String lineNumber;

    @Column(name = "telephone_number",length = 25,nullable = false,unique = true)
    @NotBlank
    private String telephone;

}
