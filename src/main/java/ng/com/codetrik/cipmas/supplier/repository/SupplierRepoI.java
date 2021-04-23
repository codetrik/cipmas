package ng.com.codetrik.cipmas.supplier.repository;
/*
 @Author Hamzat Habibllahi
 */
import ng.com.codetrik.cipmas.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SupplierRepoI extends JpaRepository<Supplier, UUID> {

}
