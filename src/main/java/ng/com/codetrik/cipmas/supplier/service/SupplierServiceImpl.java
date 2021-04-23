package ng.com.codetrik.cipmas.supplier.service;

import ng.com.codetrik.cipmas.supplier.entity.Supplier;
import ng.com.codetrik.cipmas.supplier.repository.SupplierRepoI;
import ng.com.codetrik.cipmas.utility.GenericServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierServiceI{

    @Autowired
    SupplierRepoI supplierRepo;

    @Autowired
    @Qualifier("SupplierServiceGeneric")
    GenericServiceI<Supplier> SupplierServiceGenericBean;

    @Override
    public Supplier updateSuppler(Supplier supplier) {
        return supplierRepo.findById(supplier.getId()).map(s -> {
            s.setAddress(supplier.getAddress());
            s.setName(supplier.getName());
            return supplierRepo.save(s);
        }).orElseGet(()-> SupplierServiceGenericBean.saveEntity(supplier));
    }

}