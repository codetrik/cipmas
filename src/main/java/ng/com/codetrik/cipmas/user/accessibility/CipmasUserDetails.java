package ng.com.codetrik.cipmas.user.accessibility;
/*
  Hamzat Habibllahi Adewale
*/
import ng.com.codetrik.cipmas.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CipmasUserDetails implements UserDetails {

    private final User user;

    CipmasUserDetails(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRole().getAuthoritiesOfRole();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
