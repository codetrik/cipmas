package ng.com.codetrik.cipmas.quotation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import ng.com.codetrik.cipmas.material.entity.Material;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "quote")
@JsonPropertyOrder(alphabetic = true)
public class Quote {

    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue(generator = "UUID")
    @Id
    UUID id;

    //estimated price base on quantity and material unit price, putting in logic if the unit price of the material is
    //dealership price
    @Column(name = "price",nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double price;

    @Column(name = "quantity")
    private double quantity;

    @ManyToOne
    @JsonIgnore
    private Quotation quotation;

    @ManyToOne
    private Material material;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID materialId;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID quotationId;

}
