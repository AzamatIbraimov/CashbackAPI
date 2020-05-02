package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.entity.OrgPreferenceCategory;
import kg.nurtelecom.cashbackapi.repository.OrgPreferenceCategoryRepository;
import kg.nurtelecom.cashbackapi.service.OrgPreferenceCategoryService;
import kg.nurtelecom.cashbackapi.utils.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgPreferenceCategoryServiceImpl implements OrgPreferenceCategoryService {
    @Autowired
    private OrgPreferenceCategoryRepository orgPreferenceCategoryRepository;

    public OrgPreferenceCategory findById(Long id) {
        return orgPreferenceCategoryRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }

    public List<OrgPreferenceCategory> findAll() {
        return orgPreferenceCategoryRepository.findAll();
    }

    public OrgPreferenceCategory create(OrgPreferenceCategory orgPreferenceCategory) {
        return orgPreferenceCategoryRepository.save(orgPreferenceCategory);
    }

    public String deleteById(Long id) {
        orgPreferenceCategoryRepository.deleteById(id);
        return "OrgPreferenceCategory number " + id + " has been deleted!";
    }

    public OrgPreferenceCategory putById(Long id, OrgPreferenceCategory orgPreferenceCategory) {
        return orgPreferenceCategoryRepository.findById(id)
                .map(newOrgPreferenceCategory -> {
                    newOrgPreferenceCategory.setOrganization(orgPreferenceCategory.getOrganization());
                    newOrgPreferenceCategory.setPreferenceCategory(orgPreferenceCategory.getPreferenceCategory());
                    return orgPreferenceCategoryRepository.save(newOrgPreferenceCategory);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + id));
    }


}
