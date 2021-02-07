package ng.com.codetrik.cipmas.material.entity;
/*
 @Author Hamzat Habibllahi
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import ng.com.codetrik.cipmas.quotation.entity.Quote;
import ng.com.codetrik.cipmas.supplier.entity.Supplier;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@JsonPropertyOrder(alphabetic = true)
@Table(name="material")
public class Material {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    //must to specify material generic name e.g Inverter
    @Column(name="generic_name",nullable = false)
    @NotBlank
    private String genericName;

    //must to specify material product name e.g Techfine
    @Column(name="product_name",nullable = false)
    @NotBlank
    private String productName;

    //must to specify material gauge e.g 3.5 KVA
    @Column(name="gauge", nullable = false)
    @NotBlank
    private String gauge;

    //must to specify price e.g 23000.00
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    //must to specify unit to be related with the price e.g Unit or Yard
    @Column(name = "unit",nullable = false)
    private String unit;

    @Column(name = "is_dealer_price")
    private boolean dealerPrice;

    @ManyToOne
    @NotNull
    @JsonIgnore
    private Supplier supplier;

    @JsonIgnore
    @OneToMany(mappedBy = "material")
    private List<Quote> quotes;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID supplierId;
}
