package ng.com.codetrik.cipmas.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class Login {

    @Email
    private String username;
    @NotEmpty
    private String password;
}
