package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.Filial;
import kg.nurtelecom.cashbackapi.entity.OrgImage;
import kg.nurtelecom.cashbackapi.model.*;
import kg.nurtelecom.cashbackapi.service.FilialService;
import kg.nurtelecom.cashbackapi.service.OrgImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/image")
public class OrgImageRestController {

    @Autowired
    private OrgImageService orgImageService;

    @GetMapping("/{id}")
    public OrgImage findById(@PathVariable("id") Long id) {
        return orgImageService.findById(id);
    }

    @PutMapping("/{id}")
    public OrgImage putById(@PathVariable("id") Long id, @RequestBody OrgImage orgImage) {
        return orgImageService.putById(id, orgImage);
    }

    @PostMapping()
    public OrgImage create(@RequestBody OrgImage orgImage) {
        return orgImageService.create(orgImage);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return orgImageService.deleteById(id);
    }

//    @GetMapping("/all")
//    public List<OrgImage> findAll() {
//        return orgImageService.findAll();
//    }

    @GetMapping("/all")
    public Page<OrgImageModel> findAllByOrgIdWithPagination(Long id, Pageable pageable){
        return orgImageService.findAllByOrgIdWithPagination(id, pageable);
    }
}
