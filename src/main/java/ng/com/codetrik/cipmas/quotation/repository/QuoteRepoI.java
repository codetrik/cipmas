package ng.com.codetrik.cipmas.quotation.repository;

import ng.com.codetrik.cipmas.quotation.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuoteRepoI extends JpaRepository<Quote, UUID> {

}
