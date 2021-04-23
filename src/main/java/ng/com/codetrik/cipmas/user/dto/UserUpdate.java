package ng.com.codetrik.cipmas.user.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import ng.com.codetrik.cipmas.user.accessibility.Role;

import javax.validation.constraints.NotNull;

@Data
@JsonPropertyOrder(alphabetic = true)
public class UserUpdate {

    @NotNull
    private String username;
    @NotNull
    private boolean enabled;
    @NotNull
    private boolean credentialNonExpired;
    @NotNull
    private boolean accountNonExpired;
    @NotNull
    private boolean accountNonLocked;

    private Role role;
}
