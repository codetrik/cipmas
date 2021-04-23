package ng.com.codetrik.cipmas.quotation.service;

import ng.com.codetrik.cipmas.quotation.entity.Quotation;

public interface QuotationServiceI {
    Quotation saveQuotation(Quotation quotation);
    Quotation updateQuotation(Quotation quotation);
}
