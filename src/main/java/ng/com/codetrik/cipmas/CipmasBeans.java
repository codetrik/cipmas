package ng.com.codetrik.cipmas;
/*
 @Author Hamzat Habibllahi
 */
import ng.com.codetrik.cipmas.customer.entity.Customer;
import ng.com.codetrik.cipmas.customer.repository.CustomerRepoI;
import ng.com.codetrik.cipmas.material.entity.Material;
import ng.com.codetrik.cipmas.material.repository.MaterialRepoI;
import ng.com.codetrik.cipmas.quotation.entity.Quotation;
import ng.com.codetrik.cipmas.quotation.entity.Quote;
import ng.com.codetrik.cipmas.quotation.repository.QuotationRepoI;
import ng.com.codetrik.cipmas.quotation.repository.QuoteRepoI;
import ng.com.codetrik.cipmas.supplier.entity.Supplier;
import ng.com.codetrik.cipmas.supplier.repository.SupplierRepoI;
import ng.com.codetrik.cipmas.utility.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CipmasBeans {

    @Autowired
    SupplierRepoI supplierRepoI;

    @Autowired
    CustomerRepoI customerRepoI;

    @Autowired
    QuoteRepoI quoteRepoI;

    @Autowired
    QuotationRepoI quotationRepoI;

    @Autowired
    MaterialRepoI materialRepoI;

    @Bean(name = "SupplierServiceGeneric")
    @Qualifier("SupplierServiceGeneric")
    public GenericServiceImpl<Supplier> supplierServiceGeneric(){
        GenericServiceImpl<Supplier> bean = new GenericServiceImpl<>();
        bean.setRepo(supplierRepoI);
        return bean;
    }

    @Bean(name = "CustomerServiceGeneric")
    @Qualifier("CustomerServiceGeneric")
    public GenericServiceImpl<Customer> customerServiceGeneric(){
        GenericServiceImpl<Customer> bean = new GenericServiceImpl<>();
        bean.setRepo(customerRepoI);
        return bean;
    }

    @Bean(name = "QuotationServiceGeneric")
    @Qualifier("QuotationServiceGeneric")
    public GenericServiceImpl<Quotation> quotationServiceGeneric(){
        GenericServiceImpl<Quotation> bean = new GenericServiceImpl<>();
        bean.setRepo(quotationRepoI);
        return bean;
    }

    @Bean(name = "QuoteServiceGeneric")
    @Qualifier("QuoteServiceGeneric")
    public GenericServiceImpl<Quote> quoteServiceGeneric(){
        GenericServiceImpl<Quote> bean = new GenericServiceImpl<>();
        bean.setRepo(quoteRepoI);
        return bean;
    }

    @Bean(name = "MaterialServiceGeneric")
    @Qualifier("MaterialServiceGeneric")
    public GenericServiceImpl<Material> materialServiceGeneric(){
        GenericServiceImpl<Material> bean = new GenericServiceImpl<>();
        bean.setRepo(materialRepoI);
        return bean;
    }
}
