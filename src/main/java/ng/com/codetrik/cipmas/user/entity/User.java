package ng.com.codetrik.cipmas.user.entity;
/*
    Hamzat Habibllahi Adewale
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import ng.com.codetrik.cipmas.user.accessibility.Role;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonPropertyOrder(alphabetic = true)
@Entity
@Table(name="user",schema = "codetrik_server")
@Data
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    private String first_name;

    @Column(nullable = false)
    @NotBlank
    private String last_name;

    @Column(nullable = false,unique = true)
    @NotBlank
    @Email
    private String username;

    @Column(nullable = false)
    @NotBlank
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Role role;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean enabled;

    @Column(name="credential_non_expired",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean credentialNonExpired;

    @Column(name="account_non_expired",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean accountNonExpired;

    @Column(name="account_non_locked",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean accountNonLocked;

    @Transient
    private boolean isAdmin;
}
