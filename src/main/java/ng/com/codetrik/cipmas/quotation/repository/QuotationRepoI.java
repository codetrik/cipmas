package ng.com.codetrik.cipmas.quotation.repository;

import ng.com.codetrik.cipmas.quotation.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface QuotationRepoI extends JpaRepository<Quotation, UUID> {

}
