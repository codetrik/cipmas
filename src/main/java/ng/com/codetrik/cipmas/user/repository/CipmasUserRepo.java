package ng.com.codetrik.cipmas.user.repository;
/*
    Hamzat Habibllahi Adewale
*/
import ng.com.codetrik.cipmas.user.accessibility.Role;
import ng.com.codetrik.cipmas.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CipmasUserRepo extends JpaRepository<User, UUID>{
    User findByUsername(String username);
    User findByRole(Role role);
}
