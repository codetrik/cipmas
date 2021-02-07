package ng.com.codetrik.cipmas.user.service;

import ng.com.codetrik.cipmas.user.accessibility.Role;
import ng.com.codetrik.cipmas.user.dto.UserUpdate;
import ng.com.codetrik.cipmas.user.entity.User;
import ng.com.codetrik.cipmas.user.repository.CipmasUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ng.com.codetrik.cipmas.exceptions.UserAlreadyExistException;

import java.util.Optional;

@Service
public class CipmasUserService {

    @Autowired
    CipmasUserRepo cipmasUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser(User newUser) {
        Optional<User> nullableUser = Optional.ofNullable(cipmasUserRepo.findByUsername(newUser.getUsername()));
        Optional<Role> nullableRole = Optional.ofNullable(newUser.getRole());
        if(!nullableUser.isPresent()) {

            if(nullableRole.isPresent() && nullableRole.get().equals(Role.ADMIN)){
                Optional<User> nullableUserbyRole = Optional.ofNullable(cipmasUserRepo.findByRole(newUser.getRole()));
                if(nullableUserbyRole.isPresent()){
                    throw new UserAlreadyExistException("There cant be more than one ADMIN in the system. We found one already");
                }else{
                    newUser.setEnabled(true);
                    newUser.setCredentialNonExpired(true);
                    newUser.setAccountNonExpired(true);
                    newUser.setAccountNonLocked(true);
                }
            }else {
                newUser.setRole(Role.ENGINEER);
                newUser.setEnabled(false);
                newUser.setCredentialNonExpired(false);
                newUser.setAccountNonExpired(false);
                newUser.setAccountNonLocked(false);
            }
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            return cipmasUserRepo.save(newUser);
        } else
            throw new UserAlreadyExistException("This user or username already exist");
    }

    public User updateUser(UserUpdate userDto) {
        Optional<User> nullableUser = Optional.ofNullable(cipmasUserRepo.findByUsername(userDto.getUsername()));
        Optional<Role> nullableRole = Optional.ofNullable(userDto.getRole());
        return nullableUser.map(user -> {
            user.setAccountNonExpired(userDto.isAccountNonExpired());
            user.setAccountNonLocked(userDto.isAccountNonLocked());
            user.setCredentialNonExpired(userDto.isCredentialNonExpired());
            user.setEnabled(userDto.isEnabled());
            nullableRole.ifPresent(user::setRole);
            return cipmasUserRepo.save(user);
        }).orElseThrow(()-> new UsernameNotFoundException("THe username which is provided to update a user is not found"));
    }
}
