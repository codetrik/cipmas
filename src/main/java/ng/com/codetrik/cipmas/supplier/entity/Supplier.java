package ng.com.codetrik.cipmas.supplier.entity;
/*
 @Author Hamzat Habibllahi
 */

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import ng.com.codetrik.cipmas.material.entity.Material;
import ng.com.codetrik.cipmas.utility.embeddable.Address;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@JsonPropertyOrder(alphabetic = true)
@Table(name="supplier",schema = "codetrik_server")
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    //must to specify Supplier name
    @Column(name = "supplier_name",nullable = false,unique = true)
    @NotBlank
    private String name;

    //must to specify supplier address as embeddable
    @Embedded
    @NotNull
    private Address address;

    @OneToMany(mappedBy = "supplier")
    private List<Material> materials;

}
