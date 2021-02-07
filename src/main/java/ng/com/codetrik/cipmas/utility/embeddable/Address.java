package ng.com.codetrik.cipmas.utility.embeddable;
/*
 @Author Hamzat Habibllahi
 */
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;

@Embeddable
@JsonPropertyOrder({"houseNumber","streetName","streetType","city","state","postalCode","telephone"})
@Data
public class Address {

    @Column(name ="house_number", nullable = false)
    @NotBlank
    private String houseNumber;

    @Column(name="street_name", nullable = false)
    @NotBlank
    private String streetName;

    @Column(name = "street_type")
    @NotBlank
    private String streetType;

    @Column(name = "city",nullable = false)
    @NotBlank
    private String city;

    @Column(name = "state",nullable = false)
    @NotBlank
    private String state;

    @Column(name = "postal_code",nullable = false)
    @NotBlank
    private String postalCode;

    @Embedded
    @Column(name = "telephone", nullable = false)
    private Telephone telephone;
}
