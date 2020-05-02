package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.dao.OrgCategoryTagDao;
import kg.nurtelecom.cashbackapi.entity.OrgCategoryTag;
import kg.nurtelecom.cashbackapi.model.OrgCategoryTagModel;
import kg.nurtelecom.cashbackapi.service.OrgCategoryService;
import kg.nurtelecom.cashbackapi.service.OrgCategoryTagService;
import kg.nurtelecom.cashbackapi.utils.TagWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orgCategoryTag")
public class OrgCategoryTagRestController {

    @Autowired
    OrgCategoryTagDao orgCategoryTagDao;

    @Autowired
    private OrgCategoryTagService orgCategoryTagService;

    @GetMapping("/all/categoryId")
    public List<OrgCategoryTagModel> getAllByCategoryId(@PathVariable Long categoryId) {
        return orgCategoryTagDao.getTagsByOrgCategoryId(categoryId);
    }

    /**
     * Gets list of tags = "tags": ["tag1", "tag2"] using wrapper object
     * @param tagWrapper
     * @param orgId
     * @return
     */
    @PostMapping(value = "/add/new/{orgId}", consumes = "application/json")
    @ResponseBody
    public String addOrgCategoryTag(@RequestBody TagWrapper tagWrapper, @PathVariable Long orgId) {
        String status = "{'status': 'success'}";
        for (String tag: tagWrapper.tags ) {
            OrgCategoryTagModel orgTag = new OrgCategoryTagModel(tag);
            orgCategoryTagService.create(orgTag, orgId);
        }
        System.out.println(status);
        return status;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return orgCategoryTagService.deleteById(id);
    }

}
