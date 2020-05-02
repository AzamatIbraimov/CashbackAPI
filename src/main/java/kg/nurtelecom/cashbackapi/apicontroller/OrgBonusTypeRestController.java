package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.OrgBonusType;
import kg.nurtelecom.cashbackapi.model.OrgBonusTypeModel;
import kg.nurtelecom.cashbackapi.service.OrgBonusTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orgBonusType")
public class OrgBonusTypeRestController {
    @Autowired
    private OrgBonusTypeService orgBonusTypeService;

    @GetMapping("/{id}")
    public OrgBonusType getOrgBonusTypeById(@PathVariable("id") Long id) {
        return orgBonusTypeService.findById(id);
    }

    @GetMapping("/all")
    public List<OrgBonusType> getAll() {
        return orgBonusTypeService.findAll();
    }

    @PutMapping("/{id}")
    public OrgBonusType putOrgBonusType(@PathVariable("id") Long id, @RequestBody OrgBonusType orgBonusType) {
        return orgBonusTypeService.putById(id, orgBonusType);
    }

    @PostMapping()
    public OrgBonusType postOrgBonusType(@RequestBody OrgBonusType orgBonusType) {
        orgBonusTypeService.create(orgBonusType);
        return orgBonusType;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return orgBonusTypeService.deleteById(id);
    }

    @GetMapping("/listOrgBonusType")
    public List<OrgBonusTypeModel> findAllOrgBonusTypeDto() {
        return orgBonusTypeService.findAllOrgBonusTypeDto();
    }

}
