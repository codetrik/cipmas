package ng.com.codetrik.cipmas.supplier.controller;
/*
 @Author Hamzat Habibllahi
 */
import ng.com.codetrik.cipmas.supplier.entity.Supplier;
import ng.com.codetrik.cipmas.supplier.service.SupplierServiceI;
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
@RequestMapping(path = "api/v1/suppliers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SupplierC {

    @Autowired
    @Qualifier("SupplierServiceGeneric")
    GenericServiceI supplierServiceGeneric;

    @Autowired
    SupplierServiceI supplierServiceImpl;

    @GetMapping(path = "/{id}")
    public Supplier getSupplierById(@PathVariable("id") UUID id){
        return (Supplier) supplierServiceGeneric.entityWithId(id);
    }

    @GetMapping()
    public List<Supplier> getAllSuppliers(){
        return supplierServiceGeneric.allEntity();
    }

    @PostMapping()
    public Supplier createNewSuppler(@RequestBody @Valid Supplier supplier, BindingResult br){
        return (Supplier) supplierServiceGeneric.saveEntity(supplier);
    }

    @PutMapping()
    public Supplier updateExistingSupplier(@RequestBody @Valid Supplier supplier, BindingResult br){
        return supplierServiceImpl.updateSuppler(supplier);
    }

    @DeleteMapping(path = "/{id}")
    public Status deleteExistingSupplier(@PathVariable("id") UUID id){
        return supplierServiceGeneric.deleteEntity(id);
    }

}
