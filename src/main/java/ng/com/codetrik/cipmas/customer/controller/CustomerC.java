package ng.com.codetrik.cipmas.customer.controller;
/*
 @Author Hamzat Habibllahi
 */
import ng.com.codetrik.cipmas.customer.entity.Customer;
import ng.com.codetrik.cipmas.customer.service.CustomerServiceI;
import ng.com.codetrik.cipmas.utility.GenericServiceI;
import ng.com.codetrik.cipmas.utility.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/customers",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerC {
    @Autowired
    CustomerServiceI customerServiceImpl;

    @Autowired
    @Qualifier("CustomerServiceGeneric")
    GenericServiceI customerServiceGeneric;

    @GetMapping(path = "/{id}")
    public Customer getCustomerById(@PathVariable("id") UUID id){
        return (Customer) customerServiceGeneric.entityWithId(id);
    }

   @GetMapping()
   public List<Customer> getAllCustomers(){
        return customerServiceGeneric.allEntity();
    }

/*    @GetMapping()
    public List<Customer> getCustomersBySupplerName(@RequestParam(name = "name")String supplierName){
        return customerServiceImpl.allCustomersBySupplier(supplierName);
    }*/

    @PostMapping()
    public Customer createNewCustomer(@RequestBody @Valid Customer customer, BindingResult br){
           return (Customer) customerServiceGeneric.saveEntity(customer);
    }

    @PutMapping()
    public Customer updateExistingCustomer(@RequestBody @Valid Customer customer, BindingResult br){
        return customerServiceImpl.updateCustomer(customer);
    }

    @DeleteMapping(path = "/{id}")
    public Status deleteExistingCustomer(@PathVariable("id") UUID id){
        return customerServiceGeneric.deleteEntity(id);
    }
}
