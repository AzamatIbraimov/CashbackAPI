package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.OrgCategory;
import kg.nurtelecom.cashbackapi.model.OrgCategoryMultipartImageModel;
import kg.nurtelecom.cashbackapi.model.OrgCategoryModel;
import kg.nurtelecom.cashbackapi.service.OrgCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orgCategory")
public class OrgCategoryRestController {
    @Autowired
    private OrgCategoryService orgCategoryService;

    @GetMapping("/{id}")
    public OrgCategory getOrgCategoryById(@PathVariable("id") Long id) {
        return orgCategoryService.findById(id);
    }

//    @GetMapping("/all")
//    public List<OrgCategory> getAll() {
//        return orgCategoryService.findAll();
//    }
    @GetMapping("/all")
    public List<OrgCategoryModel> getAll() {
        return orgCategoryService.listAll();
    }


    @PutMapping("/{id}")
    public OrgCategory putOrgCategory(@PathVariable("id") Long id, @RequestBody OrgCategoryMultipartImageModel orgCategory) {
        return orgCategoryService.putById(id, orgCategory);
    }

    @PostMapping()
    public OrgCategory postOrgCategory(@RequestBody OrgCategoryMultipartImageModel orgCategory) {
        return orgCategoryService.create(orgCategory);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return orgCategoryService.deleteById(id);
    }

}
