package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.OrgPreferenceCategory;
import kg.nurtelecom.cashbackapi.service.OrgPreferenceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orgPreferenceCategory")
public class OrgPreferenceCategoryRestController {
    @Autowired
    private OrgPreferenceCategoryService orgPreferenceCategoryService;

    @GetMapping("/{id}")
    public OrgPreferenceCategory getOrgPreferenceCategoryById(@PathVariable("id") Long id) {
        return orgPreferenceCategoryService.findById(id);
    }

    @GetMapping("/all")
    public List<OrgPreferenceCategory> getAll() {
        return orgPreferenceCategoryService.findAll();
    }

    @PutMapping("/{id}")
    public OrgPreferenceCategory putOrgPreferenceCategory(@PathVariable("id") Long id, @RequestBody OrgPreferenceCategory orgPreferenceCategory) {
        return orgPreferenceCategoryService.putById(id, orgPreferenceCategory);
    }

    @PostMapping()
    public OrgPreferenceCategory postOrgPreferenceCategory(@RequestBody OrgPreferenceCategory orgPreferenceCategory) {
        orgPreferenceCategoryService.create(orgPreferenceCategory);
        return orgPreferenceCategory;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return orgPreferenceCategoryService.deleteById(id);
    }

}
