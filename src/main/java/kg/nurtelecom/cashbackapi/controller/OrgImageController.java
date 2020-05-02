package kg.nurtelecom.cashbackapi.controller;


import kg.nurtelecom.cashbackapi.model.OrgImageModel;
import kg.nurtelecom.cashbackapi.model.OrgMultipartImageModel;
import kg.nurtelecom.cashbackapi.service.OrgImageService;
import kg.nurtelecom.cashbackapi.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileWriter;
import java.io.IOException;

@Controller
@RequestMapping("organization/")
public class OrgImageController {

    @Autowired
    private OrgImageService orgImageService;

    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value = "{orgId}/image/list")
    public String getList(@PageableDefault(12) Pageable pageable,
                          @PathVariable("orgId") Long orgId,
                          Model model) {
        Page<OrgImageModel> orgImageModels = orgImageService.findAllByOrgIdWithPagination(orgId, pageable);
        model.addAttribute("orgId", orgId);
        model.addAttribute("orgImageModels", orgImageModels);
        return "orgImageList";
    }

    @PostMapping("{orgId}/image/delete/{id}")
    public String deleteOrgCategory(@PathVariable("orgId") Long orgId, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            orgImageService.deleteById(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("has_exception", true);
            redirectAttributes.addFlashAttribute("exception_text", "Couldn't delete on table org_category violates foreign key constraint " +
                    "on table \"organization\"");
            return "redirect:/organization/ "+ orgId + "/image/list";
        }
        return "redirect:/organization/ "+ orgId + "/image/list";
    }

    @PostMapping("{orgId}/image/changeLogo/{imageId}")
    public String changeOrgLogo(@PathVariable("orgId") Long orgId, @PathVariable("imageId") Long imageId, RedirectAttributes redirectAttributes) {
        try {
            organizationService.changeLogo(orgId, imageId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("has_exception", true);
            redirectAttributes.addFlashAttribute("exception_text", "Couldn't delete on table org_category violates foreign key constraint " +
                    "on table \"organization\"");
            return "redirect:/organization/ "+ orgId + "/image/list";
        }
        return "redirect:/organization/ "+ orgId + "/image/list";
    }
}
