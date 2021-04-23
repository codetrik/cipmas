package ng.com.codetrik.cipmas.utility;
/*
 @Author Hamzat Habibllahi
 */

import ng.com.codetrik.cipmas.utility.enums.Status;
import java.util.List;
import java.util.UUID;

public interface GenericServiceI<T extends Object> {
    T saveEntity(T t);
    T entityWithId(UUID id);
    List<T> allEntity();
    Status deleteEntity(UUID id);
}
