package kg.nurtelecom.cashbackapi.controller;

import kg.nurtelecom.cashbackapi.apicontroller.FilialRestController;
import kg.nurtelecom.cashbackapi.model.FilialRateModel;
import kg.nurtelecom.cashbackapi.service.FilialRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("organization/filial/{id}/rate")
public class FilialRateController {
    @Autowired
    private FilialRestController filialRestController;
    @Autowired
    private FilialRateService filialRateService;

    @GetMapping(value = "/list")
    public String getFilialRate(@PathVariable("id") Long filId, Model model) {
        List<FilialRateModel> filialRateDtoList = filialRateService.findFilialRatesByFilialId(filId);
        model.addAttribute("filial", filialRestController.getFilialById(filId));
        model.addAttribute("rates", filialRateDtoList);
        return "filialRate";
    }

    @PostMapping("/delete/{r_id}")
    public String deleteRate(@PathVariable("r_id") Long id, @PathVariable("id") Long filId) {
        filialRateService.deleteById(id);
        return "redirect:/organization/filial/" + filId + "/rate/list";
    }
}
