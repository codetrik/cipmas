package ng.com.codetrik.cipmas.quotation.controller;
/*
 @Author Hamzat Habibllahi
 */
import ng.com.codetrik.cipmas.quotation.entity.Quote;
import ng.com.codetrik.cipmas.quotation.service.QuoteServiceI;
import ng.com.codetrik.cipmas.utility.GenericServiceI;
import ng.com.codetrik.cipmas.utility.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/quotes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class QuoteC {
    @Autowired
    @Qualifier("QuoteServiceGeneric")
    GenericServiceI quoteServiceGeneric;

    @Autowired
    QuoteServiceI quoteService;

    @GetMapping(path = "/{id}")
    public Quote getQuoteWithId(@PathVariable("id") UUID id){
        return (Quote) quoteServiceGeneric.entityWithId(id);
    }

    @GetMapping()
    public List<Quote> getAllQuotes(){
        return quoteServiceGeneric.allEntity();
    }

    @PostMapping()
    public Quote createNewQuote(@RequestBody  Quote quote){
        return quoteService.saveQuote(quote);
    }

    @PutMapping()
    public Quote updateExistingQuote(@RequestBody Quote quote){
        return quoteService.updateQuote(quote);
    }

    @DeleteMapping(path = "/{id}")
    public Status deleteExistingQuote(@PathVariable("id") UUID id){
        return quoteServiceGeneric.deleteEntity(id);
    }
}
