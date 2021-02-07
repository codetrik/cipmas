package ng.com.codetrik.cipmas.customer.service;

import ng.com.codetrik.cipmas.customer.entity.Customer;
import ng.com.codetrik.cipmas.customer.repository.CustomerRepoI;
import ng.com.codetrik.cipmas.utility.GenericServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
/*
 @Author Hamzat Habibllahi
 */
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServiceI {

    @Autowired
    CustomerRepoI customerRepo;

    @Autowired
    @Qualifier("CustomerServiceGeneric")
    GenericServiceI<Customer> customerServiceGeneric;

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepo.findById(customer.getId()).map(c -> {
            c.setAddress(customer.getAddress());
            c.setName(customer.getName());
            return customerRepo.save(c);
        }).orElseGet(() -> customerServiceGeneric.saveEntity(customer));
    }

    @Override
    public List<Customer> allCustomersBySupplier(String supplerName) {
        return customerRepo.findBySupplier(supplerName);
    }

}
