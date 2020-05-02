package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.entity.PreferenceCategory;
import kg.nurtelecom.cashbackapi.repository.PreferenceCategoryRepository;
import kg.nurtelecom.cashbackapi.service.PreferenceCategoryService;
import kg.nurtelecom.cashbackapi.utils.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceCategoryServiceImpl implements PreferenceCategoryService {
    @Autowired
    private PreferenceCategoryRepository preferenceCategoryRepository;

    public PreferenceCategory findById(Long id) {
        return preferenceCategoryRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }

    public List<PreferenceCategory> findAll() {
        return preferenceCategoryRepository.findAll();
    }

    public PreferenceCategory create(PreferenceCategory preferenceCategory) {
        return preferenceCategoryRepository.save(preferenceCategory);
    }

    public String deleteById(Long id) {
        preferenceCategoryRepository.deleteById(id);
        return "PreferenceCategory number " + id + " has been deleted!";
    }

    public PreferenceCategory putById(Long id, PreferenceCategory preferenceCategory) {
        return preferenceCategoryRepository.findById(id)
                .map(newPreferenceCategory -> {
                    newPreferenceCategory.setName(preferenceCategory.getName());
                    newPreferenceCategory.setParent(preferenceCategory.getParent());
                    newPreferenceCategory.setType(preferenceCategory.getType());
                    return preferenceCategoryRepository.save(newPreferenceCategory);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + id));
    }


}
