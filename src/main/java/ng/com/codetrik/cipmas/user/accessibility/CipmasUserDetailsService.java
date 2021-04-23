package ng.com.codetrik.cipmas.user.accessibility;
/*
    Hamzat Habibllahi Adewale
*/
import ng.com.codetrik.cipmas.user.entity.User;
import ng.com.codetrik.cipmas.user.repository.CipmasUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CipmasUserDetailsService implements UserDetailsService {

    private User user;

    @Autowired
    CipmasUserRepo cipmasUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> nullableUser = Optional.ofNullable(cipmasUserRepo.findByUsername(username));
        if(nullableUser.isPresent())
            return new CipmasUserDetails(nullableUser.get());
        else
            throw  new UsernameNotFoundException("User is not previously created");

    }
}
