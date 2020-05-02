package kg.nurtelecom.cashbackapi.controller;

import kg.nurtelecom.cashbackapi.entity.Organization;
import kg.nurtelecom.cashbackapi.entity.User;
import kg.nurtelecom.cashbackapi.model.ManagerListDto;
import kg.nurtelecom.cashbackapi.model.OrganizationManagerDto;
import kg.nurtelecom.cashbackapi.service.UserService;
import kg.nurtelecom.cashbackapi.service.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("organization/")
public class OrganizationManagerController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{orgId}/manager/list")
    public String getList(@RequestParam(value = "search" ,required = false) String search,
                          @PageableDefault(7) Pageable pageable,
                          @PathVariable("orgId") Long orgId,
                          Model model) {
        Page<ManagerListDto> managers;
        if(search != null) {
            managers = userService.findAllByOrgIdAndByNameOrDescription(orgId, search.toLowerCase(), pageable);
        } else {
            managers = userService.findAllByOrgIdWithPagination(orgId, pageable);
            search = "";
        }
        model.addAttribute("orgId", orgId);
        model.addAttribute("search", search);
        model.addAttribute("managers", managers);
        return "organizationManagerList";
    }


    @GetMapping("manager/list/forOrgAdmin")
    public String getClientListForOrgAdmin(@PageableDefault Pageable pageable,
                                           @RequestParam(value = "search", required = false) String search,
                                           Model model,
                                           Authentication authentication
    ) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        Organization organization = user.getOrganization();
        Page<ManagerListDto> managers = null;

        if(search != null) {
            managers = userService.findAllByOrgIdAndByNameOrDescription(organization.getId(), search.toLowerCase(), pageable);
        } else {
            managers = userService.findAllByOrgIdWithPagination(organization.getId(), pageable);
            search = "";
        }
        model.addAttribute("orgId", organization.getId());
        model.addAttribute("search", search);
        model.addAttribute("managers", managers);
        return "organizationManagerList";
    }

    @GetMapping("{orgId}/manager/form")
    public String organizationManagerForm(@PathVariable("orgId") Long organizationId, Model model) {
        User manager = new User();
        model.addAttribute("manager", manager);
        model.addAttribute("organizationId", organizationId);
        model.addAttribute("add", true);
        return "organizationManagerForm";
    }

    @GetMapping(value = "{orgId}/manager/{managerId}")
    public String getFilialDetailPage(@PathVariable("managerId") Long managerId, @PathVariable("orgId") Long orgId, Model model) {
        User organizationManager = userService.findById(managerId);
        model.addAttribute("manager", organizationManager);
        model.addAttribute("orgId", orgId);
        model.addAttribute("add", false);
        return "organizationManagerForm";
    }

    @PostMapping(value = "{orgId}/manager/create")
    public String createOrganizationManager(@PathVariable("orgId") Long orgId,
                                            @Param("role") Boolean role,
                                            @Valid @ModelAttribute("manager") OrganizationManagerDto organizationManagerDto,
                                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(organizationManagerDto);
            model.addAttribute("orgId", orgId);
            model.addAttribute("add", true);
            return "organizationManagerForm";
        }
        organizationManagerDto.setOrganizationId(orgId);
        userService.createOrganizationManager(organizationManagerDto,role);
        return "redirect:/organization/" + orgId + "/manager/list";
    }


    @PostMapping("{orgId}/manager/update/{managerId}")
    public String updateOrganizationManager(@PathVariable("orgId") Long orgId,
                                            @Param("role") Boolean role,
                                            @PathVariable("managerId") Long managerId,
                                            @Valid @ModelAttribute("manager") OrganizationManagerDto organizationManagerDto,
                                            BindingResult result,
                                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute(organizationManagerDto);
            model.addAttribute("organizationId", orgId);
            model.addAttribute("add", false);
            return "organizationManagerForm";
        }
        organizationManagerDto.setOrganizationId(orgId);
        userService.updateOrganizationManager(managerId, organizationManagerDto,role);
        return "redirect:/organization/" + orgId + "/manager/list";
    }


    @GetMapping(value = "{orgId}/manager/delete/{managerId}")
    public String deleteFilial(@PathVariable("managerId") Long filId, @PathVariable("orgId") Long orgId) {
        userService.deleteById(filId);
        return "redirect:/organization/" + orgId + "/manager/list";
    }
}
