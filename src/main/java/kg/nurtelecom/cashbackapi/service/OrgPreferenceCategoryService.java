package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.OrgPreferenceCategory;

import java.util.List;

public interface OrgPreferenceCategoryService {
    OrgPreferenceCategory findById(Long id);

    List<OrgPreferenceCategory> findAll();

    OrgPreferenceCategory create(OrgPreferenceCategory orgPreferenceCategory);

    String deleteById(Long id);

    OrgPreferenceCategory putById(Long id, OrgPreferenceCategory orgPreferenceCategory);
}
