package ng.com.codetrik.cipmas.customer.entity;
/*
 @Author Hamzat Habibllahi
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import ng.com.codetrik.cipmas.quotation.entity.Quotation;
import ng.com.codetrik.cipmas.utility.embeddable.Address;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@JsonPropertyOrder(alphabetic = true)
@Table(name="customer",schema = "codetrik_server")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    //must to specify customer name
    @Column(name = "customer_name",nullable = false)
    @NotBlank
    private String name;

    @Embedded
    @NotNull
    Address address;

    @OneToOne(mappedBy = "customer",fetch = FetchType.LAZY)
    @JsonIgnore
    private Quotation quotation;
}
