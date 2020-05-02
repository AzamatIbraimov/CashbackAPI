package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.dao.OrgCategoryTagDao;
import kg.nurtelecom.cashbackapi.entity.OrgCategory;
import kg.nurtelecom.cashbackapi.entity.OrgCategoryTag;
import kg.nurtelecom.cashbackapi.model.OrgCategoryTagModel;
import kg.nurtelecom.cashbackapi.repository.OrgCategoryTagRepository;
import kg.nurtelecom.cashbackapi.service.OrgCategoryService;
import kg.nurtelecom.cashbackapi.service.OrgCategoryTagService;
import kg.nurtelecom.cashbackapi.utils.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrgCategoryTagServiceImpl implements OrgCategoryTagService {

    @Autowired
    private OrgCategoryService orgCategoryService;

    @Autowired
    private OrgCategoryTagDao orgCategoryTagDao;
    @Autowired
    private OrgCategoryTagRepository orgCategoryTagRepository;

    public OrgCategoryTag findById(Long id) {
        return orgCategoryTagRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }
    public OrgCategoryTagModel getTagById(Long id) {
        return orgCategoryTagRepository.getTagById(id);
    }

    public List<OrgCategoryTag> findAll() {
        return orgCategoryTagRepository.findAll();
    }

    public OrgCategoryTag create(OrgCategoryTagModel tagModel, Long orgCatId ) {
        OrgCategoryTag orgCategoryTag = new OrgCategoryTag();
        orgCategoryTag.setName(tagModel.getName());
        OrgCategoryTag savedTag = orgCategoryTagRepository.save(orgCategoryTag);
        orgCategoryService.addTag(orgCatId, orgCategoryTag);
        return savedTag;
    }
//    public OrgCategoryTag create(OrgCategoryTag orgCategoryTag) {
//        return orgCategoryTagRepository.save(orgCategoryTag);
//    }

    public String deleteById(Long id) {
        orgCategoryTagRepository.deleteById(id);
        return "OrgCategoryTag number " + id + " has been deleted!";
    }

    @Override
    public OrgCategoryTagModel putById(Long id, OrgCategoryTagModel orgCategoryTag) {
        OrgCategoryTag categoryTag = new OrgCategoryTag();
        categoryTag.setId(id);
        categoryTag.setName(orgCategoryTag.getName());
        orgCategoryTagRepository.save(categoryTag);
        return orgCategoryTag;
    }

    public OrgCategoryTag putById(Long id, OrgCategoryTag orgCategoryTag) {
        return orgCategoryTagRepository.findById(id)
                .map(newOrgCategoryTag -> {
                    newOrgCategoryTag.setName(orgCategoryTag.getName());
                    newOrgCategoryTag.setOrgCategories(orgCategoryTag.getOrgCategories());
                    return orgCategoryTagRepository.save(newOrgCategoryTag);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + id));
    }

    @Override
    public List<OrgCategoryTagModel> getTagsByCategoryId(Long categoryId) {
        return orgCategoryTagDao.getTagsByOrgCategoryId(categoryId);
    }


}
