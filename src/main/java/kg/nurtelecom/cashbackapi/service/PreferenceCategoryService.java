package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.PreferenceCategory;

import java.util.List;

public interface PreferenceCategoryService {
    PreferenceCategory findById(Long id);

    List<PreferenceCategory> findAll();

    PreferenceCategory create(PreferenceCategory preferenceCategory);

    String deleteById(Long id);

    PreferenceCategory putById(Long id, PreferenceCategory preferenceCategory);
}
