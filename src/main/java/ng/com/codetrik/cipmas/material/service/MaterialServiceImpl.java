package ng.com.codetrik.cipmas.material.service;

import ng.com.codetrik.cipmas.material.entity.Material;
import ng.com.codetrik.cipmas.material.repository.MaterialRepoI;
import ng.com.codetrik.cipmas.supplier.repository.SupplierRepoI;
import ng.com.codetrik.cipmas.utility.GenericServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialServiceI{

    @Autowired
    MaterialRepoI materialRepoI;

    @Autowired
    SupplierRepoI supplierRepoI;

    @Autowired
    @Qualifier("MaterialServiceGeneric")
    GenericServiceI materialServiceGeneric;

    @Override
    public Material saveMaterial(Material material) {
        material.setSupplier(supplierRepoI.findById(material.getSupplierId()).get());
        return (Material) materialServiceGeneric.saveEntity(material);
    }

    @Override
    public Material updateMaterial(Material material) {
        return materialRepoI.findById(material.getId()).map((m)->{
            m.setSupplier(supplierRepoI.findById(material.getSupplierId()).get());
            m.setGauge(material.getGauge());
            m.setGenericName(material.getGenericName());
            m.setProductName(material.getProductName());
            m.setUnit(material.getUnit());
            m.setUnitPrice(material.getUnitPrice());
            m.setDealerPrice(material.isDealerPrice());
            return materialRepoI.save(m);
        }).orElseGet(()->saveMaterial(material));
    }
}
