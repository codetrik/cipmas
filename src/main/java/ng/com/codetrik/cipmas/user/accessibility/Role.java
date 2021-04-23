package ng.com.codetrik.cipmas.user.accessibility;
/*
 @Author Hamzat Habibllahi
 @Date November 12, 2020
*/

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static ng.com.codetrik.cipmas.user.accessibility.Permission.*;

public enum Role {
    ADMIN(new HashSet(Arrays.asList(WRITE,READ,UPDATE,DELETE,SPECIAL))),
    MANAGER(new HashSet(Arrays.asList(WRITE,READ,UPDATE,DELETE))),
    DEVELOPER(new HashSet(Arrays.asList(WRITE,READ,UPDATE))),
    ENGINEER(new HashSet(Arrays.asList(READ)));

    private final Set<Permission> permission;

    Role(Set<Permission> permission){
        this.permission =permission;
    }

    public Set<SimpleGrantedAuthority> getAuthoritiesOfRole(){
        Set<SimpleGrantedAuthority> sga = this.permission.stream().map( permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toSet());
        sga.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return sga;
    }

}
