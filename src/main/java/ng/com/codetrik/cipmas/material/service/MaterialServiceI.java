package ng.com.codetrik.cipmas.material.service;

import ng.com.codetrik.cipmas.material.entity.Material;

public interface MaterialServiceI {
    Material saveMaterial(Material material);
    Material updateMaterial(Material material);
}
