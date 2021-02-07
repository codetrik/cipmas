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
        var bean =  new GenericServiceImpl<Supplier>();
        bean.setRepo(supplierRepoI);
        return bean;
    }

    @Bean(name = "CustomerServiceGeneric")
    @Qualifier("CustomerServiceGeneric")
    public GenericServiceImpl<Customer> customerServiceGeneric(){
        var bean = new GenericServiceImpl<Customer>();
        bean.setRepo(customerRepoI);
        return bean;
    }

    @Bean(name = "QuotationServiceGeneric")
    @Qualifier("QuotationServiceGeneric")
    public GenericServiceImpl<Quotation> quotationServiceGeneric(){
        var bean = new GenericServiceImpl<Quotation>();
        bean.setRepo(quotationRepoI);
        return bean;
    }

    @Bean(name = "QuoteServiceGeneric")
    @Qualifier("QuoteServiceGeneric")
    public GenericServiceImpl<Quote> quoteServiceGeneric(){
        var bean = new GenericServiceImpl<Quote>();
        bean.setRepo(quoteRepoI);
        return bean;
    }

    @Bean(name = "MaterialServiceGeneric")
    @Qualifier("MaterialServiceGeneric")
    public GenericServiceImpl<Material> materialServiceGeneric(){
        var bean = new GenericServiceImpl<Material>();
        bean.setRepo(materialRepoI);
        return bean;
    }
}
