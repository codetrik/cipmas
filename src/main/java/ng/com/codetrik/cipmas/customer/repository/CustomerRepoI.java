package ng.com.codetrik.cipmas.customer.repository;
/*
 @Author Hamzat Habibllahi
 */
import ng.com.codetrik.cipmas.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepoI  extends JpaRepository<Customer, UUID> {

    @Query(value = "select * from codetrik_server.customer where customer.customer_name = :name",nativeQuery = true)
    List<Customer> findBySupplier(@Param(value = "name")String supplierName);
}
