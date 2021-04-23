package ng.com.codetrik.cipmas.quotation.service;

import ng.com.codetrik.cipmas.quotation.entity.Quote;

public interface QuoteServiceI {
    Quote saveQuote(Quote quote);
    Quote updateQuote(Quote quote);
}
