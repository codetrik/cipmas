package ng.com.codetrik.cipmas.utility;
/*
 @Author Hamzat Habibllahi
 */

import lombok.Data;
import ng.com.codetrik.cipmas.exceptions.SaveUnsuccessfulException;
import ng.com.codetrik.cipmas.utility.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class GenericServiceImpl<T extends Object> implements GenericServiceI<T>{

    private JpaRepository repo;

    @Override
    public T saveEntity(T t) {
        return Optional.of(t).map(s-> (T) repo.save(s)).orElseThrow(()-> new SaveUnsuccessfulException());
    }

    @Override
    public T entityWithId(UUID id) {
        return (T) repo.findById(id).get();
    }

    @Override
    public List<T> allEntity() {
        return repo.findAll();
    }

    @Override
    public Status deleteEntity(UUID id) {
        try{
            repo.deleteById(id);
            return Status.SUCCESSFUL;
        }catch (Exception e){
            return Status.UNSUCCESSFUL;
        }
    }
}
