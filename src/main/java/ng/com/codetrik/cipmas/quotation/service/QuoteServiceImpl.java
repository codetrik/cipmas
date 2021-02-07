package ng.com.codetrik.cipmas.quotation.service;

import ng.com.codetrik.cipmas.material.entity.Material;
import ng.com.codetrik.cipmas.material.repository.MaterialRepoI;
import ng.com.codetrik.cipmas.quotation.entity.Quotation;
import ng.com.codetrik.cipmas.quotation.entity.Quote;
import ng.com.codetrik.cipmas.quotation.repository.QuotationRepoI;
import ng.com.codetrik.cipmas.quotation.repository.QuoteRepoI;
import ng.com.codetrik.cipmas.utility.GenericServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuoteServiceImpl implements QuoteServiceI {

    @Autowired
    MaterialRepoI materialRepoI;

    @Autowired
    QuotationRepoI quotationRepoI;

    @Autowired
    QuoteRepoI quoteRepoI;

    @Autowired
    @Qualifier("QuoteServiceGeneric")
    GenericServiceI quoteServiceGeneric;

    @Override
    @Transactional
    public Quote saveQuote(Quote quote) {
        Quotation quotation = quotationRepoI.findById(quote.getQuotationId()).get();
        quote.setQuotation(quotation);
        Material material = materialRepoI.findById(quote.getMaterialId()).get();
        quote.setMaterial(material);
        if(material.isDealerPrice())
            quote.setPrice(
                    ((material.getUnitPrice()*0.1) + material.getUnitPrice())*quote.getQuantity()
            );
        else
            quote.setPrice(material.getUnitPrice()*quote.getQuantity());

        return (Quote) quoteServiceGeneric.saveEntity(quote);
    }

    @Override
    public Quote updateQuote(Quote quote){
        return quoteRepoI.findById(quote.getId()).map((q)->{
           q.setQuantity(quote.getQuantity());

           if (quote.getMaterialId()!=null)
               q.setMaterial(materialRepoI.findById(quote.getMaterialId()).get());

           if (quote.getQuotationId()!=null)
               q.setQuotation(quotationRepoI.findById(quote.getQuotationId()).get());

           if(q.getMaterial().isDealerPrice())
               q.setPrice(
                        ((q.getMaterial().getUnitPrice()*0.1) + q.getMaterial().getUnitPrice())*quote.getQuantity()
                );
           else
                q.setPrice(q.getMaterial().getUnitPrice()*quote.getQuantity());

           return quoteRepoI.save(q);
        }).orElseGet(()-> saveQuote(quote));
    }
}
