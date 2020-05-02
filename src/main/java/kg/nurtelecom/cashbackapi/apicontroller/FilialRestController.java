package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.Filial;
import kg.nurtelecom.cashbackapi.model.FilialModel;
import kg.nurtelecom.cashbackapi.model.FilialModelMultipartImage;
import kg.nurtelecom.cashbackapi.model.FilialShortModel;
import kg.nurtelecom.cashbackapi.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/filial")
public class FilialRestController {
    @Autowired
    private FilialService filialService;

    @GetMapping("/{id}")
    public Filial getFilialById(@PathVariable("id") Long id) {
        return filialService.findById(id);
    }

    @PutMapping("/{id}")
    public Filial putFilial(@PathVariable("id") Long id, @RequestBody FilialModelMultipartImage filial) {
        return filialService.putById(id, filial);
    }

    @PostMapping()
    public Filial postFilial(@RequestBody FilialModelMultipartImage filial) {
        return filialService.create(filial);
    }


    @GetMapping(value = "/changeStatus/{id}")
    public void changeBonusStatus(@PathVariable("id") Long id){
        filialService.changeStatus(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return filialService.deleteById(id);
    }

    @GetMapping("/allByOrgId/{id}")
    public List<FilialShortModel> findAllFilialsByOrgId(@PathVariable("id") Long id,
                                                        @RequestParam(value = "last_i", defaultValue="0", required = false) Long lastId,
                                                        @RequestParam(value = "last_a", defaultValue = "5", required = false) Double lastAverage,
                                                        @RequestParam(value = "limit", defaultValue = "5", required = false) Integer pageSize) {
        return filialService.getAllFilialsByOrgId(id, lastId, lastAverage, pageSize);
    }

    @GetMapping(value = "/org/{id}", params = {"page", "size" })
    public Page<FilialModel> findAllByOrgId(@PathVariable("id") Long id,
                                            @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return filialService.getAllByOrgIdPagebale(id, page, size);
    }

    @GetMapping("/all")
    public List<FilialModel> findAllByOrgId(@Param("id") Long id) {
        return filialService.findAllByOrgId(id);
    }

}
