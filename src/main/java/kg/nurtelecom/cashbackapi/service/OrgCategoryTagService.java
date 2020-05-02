package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.OrgCategoryTag;
import kg.nurtelecom.cashbackapi.model.OrgCategoryTagModel;

import java.util.List;

public interface OrgCategoryTagService {
    OrgCategoryTag findById(Long id);
    OrgCategoryTagModel getTagById(Long id);

    List<OrgCategoryTag> findAll();

    // OrgCategoryTag createEntity(OrgCategoryTag orgCategoryTag);
    OrgCategoryTag create(OrgCategoryTagModel tagModel, Long orgCatId);

    String deleteById(Long id);

//    OrgCategoryTag putById(Long id, OrgCategoryTag orgCategoryTag);
    OrgCategoryTagModel putById(Long id, OrgCategoryTagModel orgCategoryTag);


    List<OrgCategoryTagModel> getTagsByCategoryId(Long categoryId);
}
