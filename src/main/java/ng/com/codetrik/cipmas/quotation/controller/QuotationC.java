package ng.com.codetrik.cipmas.quotation.controller;
/*
 @Author Hamzat Habibllahi
 */
import ng.com.codetrik.cipmas.quotation.entity.Quotation;
import ng.com.codetrik.cipmas.quotation.service.QuotationServiceI;
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
@RequestMapping(path = "/api/v1/quotations", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class QuotationC {

    @Autowired
    @Qualifier("QuotationServiceGeneric")
    GenericServiceI quotationServiceGeneric;

    @Autowired
    QuotationServiceI quotationServiceI;

    @GetMapping(path = "/{id}")
    public Quotation getQuotationWithId(@PathVariable("id") UUID id){
        return (Quotation) quotationServiceGeneric.entityWithId(id);
    }

    @GetMapping()
    public List<Quotation> getAllQuotation(){
        return  quotationServiceGeneric.allEntity();
    }

    @PostMapping()
    public Quotation createNewQuotation(@RequestBody @Valid Quotation quotation, BindingResult br){
        return quotationServiceI.saveQuotation(quotation);
    }

    @PutMapping()
    public Quotation updateExistingQuotation(@RequestBody @Valid Quotation quotation){
        return quotationServiceI.updateQuotation(quotation);
    }

    @DeleteMapping(path = "/{id}")
    public Status deleteExistingQuotation(@PathVariable("id") UUID id){
        return quotationServiceGeneric.deleteEntity(id);
    }
    
}
