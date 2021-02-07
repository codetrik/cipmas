package ng.com.codetrik.cipmas.customer.service;
/*
 @Author Hamzat Habibllahi
 */
import ng.com.codetrik.cipmas.customer.entity.Customer;
import java.util.List;


public interface CustomerServiceI {
    Customer updateCustomer(Customer customer);
    List<Customer> allCustomersBySupplier(String supplerName);
}
