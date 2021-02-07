package ng.com.codetrik.cipmas.quotation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import ng.com.codetrik.cipmas.customer.entity.Customer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@JsonPropertyOrder(alphabetic = true)
@Table(name = "quotation")
public class Quotation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    @CreationTimestamp
    @Column(name="creation_timestamp")
    private LocalDateTime creationTimestamp;

    @UpdateTimestamp
    @Column(name = "update_timestamp")
    private LocalDateTime updateTimestamp;

    @OneToMany(mappedBy = "quotation")
    private List<Quote> quotes;

    @OneToOne
    private Customer customer;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "fulfil",nullable = false)
    private boolean fulfil;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID customerId;
}
