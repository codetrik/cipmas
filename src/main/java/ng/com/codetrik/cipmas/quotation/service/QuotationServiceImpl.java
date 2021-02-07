package ng.com.codetrik.cipmas.quotation.service;

import ng.com.codetrik.cipmas.customer.repository.CustomerRepoI;
import ng.com.codetrik.cipmas.quotation.entity.Quotation;
import ng.com.codetrik.cipmas.quotation.repository.QuotationRepoI;
import ng.com.codetrik.cipmas.utility.GenericServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class QuotationServiceImpl implements QuotationServiceI{

    @Autowired
    QuotationRepoI quotationRepoI;

    @Autowired
    CustomerRepoI customerRepoI;

    @Autowired
    @Qualifier("QuotationServiceGeneric")
    GenericServiceI quotationServiceGeneric;

    @Override
    public Quotation saveQuotation(Quotation quotation) {
        quotation.setCustomer(customerRepoI.findById(quotation.getCustomerId()).get());
        return (Quotation) quotationServiceGeneric.saveEntity(quotation);
    }

    @Override
    public Quotation updateQuotation(Quotation quotation) {
        return quotationRepoI.findById(quotation.getId()).map((q)->{
            q.setFulfil(quotation.isFulfil());
            if(quotation.getCustomerId() != null)
                q.setCustomer(customerRepoI.findById(quotation.getCustomerId()).get());

            return quotationRepoI.save(q);
        }).orElseGet(()->saveQuotation(quotation));
    }
}
