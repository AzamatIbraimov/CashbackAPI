package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.OrgCategory;
import kg.nurtelecom.cashbackapi.entity.OrgCategoryTag;
//<<<<<<< Updated upstream
import kg.nurtelecom.cashbackapi.model.OrgCategoryMultipartImageModel;
//=======
//>>>>>>> Stashed changes
import kg.nurtelecom.cashbackapi.model.OrgCategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrgCategoryService {
    OrgCategory findById(Long id);

    List<OrgCategory> findAll();

    Page<OrgCategoryModel> findAll(Pageable pageable);

    OrgCategory create(OrgCategoryMultipartImageModel orgCategoryModel);

    String deleteById(Long id);

    OrgCategory putById(Long id, OrgCategoryMultipartImageModel orgCategoryModel);

    OrgCategoryModel findOrgCategoryById(Long id);

    List<OrgCategoryModel> listAll();

    void addTag(Long catId, OrgCategoryTag tag);
}
