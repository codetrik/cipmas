package ng.com.codetrik.cipmas.material.controller;
/*
 @Author Hamzat Habibllahi
 */
import ng.com.codetrik.cipmas.material.entity.Material;
import ng.com.codetrik.cipmas.material.repository.MaterialRepoI;
import ng.com.codetrik.cipmas.material.service.MaterialServiceI;
import ng.com.codetrik.cipmas.utility.GenericServiceI;
import ng.com.codetrik.cipmas.utility.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/materials")
public class MaterialC {

    @Autowired
    @Qualifier("MaterialServiceGeneric")
    GenericServiceI materialServiceGeneric;

    @Autowired
    MaterialServiceI materialServiceI;

    @GetMapping()
    public List<Material> getAllMaterials() {
        return materialServiceGeneric.allEntity();
    }

    @GetMapping(path = "/{id}")
    public Material getMaterialWithId(@PathVariable("id") UUID id){
        return (Material) materialServiceGeneric.entityWithId(id);
    }

    @PostMapping()
    public Material createNewMaterial(@RequestBody @Valid Material material, BindingResult br){
        return materialServiceI.saveMaterial(material);
    }

    @PutMapping()
    public Material updateExistingMaterial(@RequestBody @Valid Material material, BindingResult result){
        return materialServiceI.updateMaterial(material);
    }

    @DeleteMapping(path = "/{id}")
    public Status deleteExistingMaterial(@PathVariable("id")UUID id){
        return materialServiceGeneric.deleteEntity(id);
    }
}
