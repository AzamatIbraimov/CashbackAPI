package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.Organization;
import kg.nurtelecom.cashbackapi.model.OrganizationFullModel;
import kg.nurtelecom.cashbackapi.model.OrganizationModel;
import kg.nurtelecom.cashbackapi.model.OrganizationModelMultipartImage;
import kg.nurtelecom.cashbackapi.model.OrganizationShortModel;
import kg.nurtelecom.cashbackapi.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/organization")
public class OrganizationRestController {
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private FilialRestController filialRestController;
    @Autowired
    private OrgBonusRestController orgBonusRestController;
    @Autowired
    private EventRestController eventRestController;

    @GetMapping("/list/{id}")
    public List<OrganizationModel> getAllOrganizationByUserId(@PathVariable("id") Long id) {
        return organizationService.getOrganizationListByClientId(id);
    }

    @GetMapping("/next/{id}")
    public List<OrganizationModel> nextOrganizationListByClientIdAndCategoryId(@PathVariable("id") Long id,
                                                                               @RequestParam(value = "cat_i", defaultValue="1", required = false) Long categoryId,
                                                                               @RequestParam(value = "last_i", defaultValue = "1", required = false) Long lastId,
                                                                               @RequestParam(value = "limit", defaultValue = "4", required = false) Integer limit) {
        return organizationService.nextOrganizationListByClientIdAndCategoryId(id, categoryId, lastId , limit);
    }

    @GetMapping(
            value = "/list",
            params = { "search", "page", "size" })
    public Page<OrganizationModel> getOrganizationList(
            @RequestParam(value = "search", required = false) String search, @RequestParam("page") Integer page, @RequestParam("size") Integer size
            ) {
        Page<OrganizationModel> organizations;
        if (search != null) {
            organizations = organizationService.findRestOrganizationList(search.toLowerCase(), page, size);
        } else {
            organizations = organizationService.findRestOrganizationList("", page,size);
        }
        return organizations;
    }

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable("id") Long id) {
        return organizationService.findById(id);
    }

    @GetMapping("/info/{id}")
    public OrganizationModel getOrganizationInfoById(@PathVariable("id") Long id) {
        return organizationService.getOrganizationById(id); //orgBonusRestController.findAllOrgBonusList(id, 0L, 0, 7), eventRestController.getAllEventsByOrgId(id,0L,"2020-03-03 00:00:00", 5), filialRestController.findAllFilialsByOrgId(id, 0L, 5.0,5)
    }

    @GetMapping(value = "/changeStatus/{id}")
    public void changeBonusStatus(@PathVariable("id") Long id){
        organizationService.changeStatus(id);
    }


    @GetMapping("/all")
    public List<Organization> getAll() {
        return organizationService.findAll();
    }

    @GetMapping(value="/category/{id}", params = {"page", "size"})
    public Page<OrganizationShortModel> getAllShort(@PathVariable("id") Long id,
                                                    @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return organizationService.findOrgsById(id, page, size);
    }

    @GetMapping("/allSorted")
    public List<Organization> getAllSorted() {
        return organizationService.findAllSorted();
    }

    @PutMapping("/{id}")
    public Organization putOrganization(@PathVariable("id") Long id, @RequestBody OrganizationModelMultipartImage organization) {
        return organizationService.putById(id, organization);
    }

    @PostMapping()
    public Organization postOrganization(@RequestBody OrganizationModelMultipartImage organization) {
        return organizationService.create(organization);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return organizationService.deleteById(id);
    }

    @GetMapping("/list")
    public List<OrganizationModel> getAllOrgs() {
        return organizationService.findAllOrganizationList();
    }
}
