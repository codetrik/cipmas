package ng.com.codetrik.cipmas.material.repository;

import ng.com.codetrik.cipmas.material.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface MaterialRepoI extends JpaRepository<Material, UUID> {

}
