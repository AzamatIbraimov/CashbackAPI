package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.OrgBonusValue;
import kg.nurtelecom.cashbackapi.service.OrgBonusValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orgBonusValue")
public class OrgBonusValueRestController {
    @Autowired
    private OrgBonusValueService orgBonusValueService;

    @GetMapping("/{id}")
    public OrgBonusValue getOrgBonusValueById(@PathVariable("id") Long id) {
        return orgBonusValueService.findById(id);
    }

    @GetMapping("/all")
    public List<OrgBonusValue> getAll() {
        return orgBonusValueService.findAll();
    }

    @PutMapping("/{id}")
    public OrgBonusValue putOrgBonusValue(@PathVariable("id") Long id, @RequestBody OrgBonusValue orgBonusValue) {
        return orgBonusValueService.putById(id, orgBonusValue);
    }

    @PostMapping()
    public OrgBonusValue postOrgBonusValue(@RequestBody OrgBonusValue orgBonusValue) {
        orgBonusValueService.create(orgBonusValue);
        return orgBonusValue;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return orgBonusValueService.deleteById(id);
    }

}
