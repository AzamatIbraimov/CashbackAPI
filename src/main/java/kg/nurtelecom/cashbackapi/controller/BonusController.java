package kg.nurtelecom.cashbackapi.controller;

import kg.nurtelecom.cashbackapi.apicontroller.OrgBonusTypeRestController;
import kg.nurtelecom.cashbackapi.apicontroller.OrganizationRestController;
import kg.nurtelecom.cashbackapi.entity.OrgBonus;
import kg.nurtelecom.cashbackapi.entity.OrgBonusType;
import kg.nurtelecom.cashbackapi.entity.Organization;
import kg.nurtelecom.cashbackapi.model.BonusModel;
import kg.nurtelecom.cashbackapi.model.OrgBonusTypeModel;
import kg.nurtelecom.cashbackapi.service.OrgBonusService;
import kg.nurtelecom.cashbackapi.service.security.UserPrincipal;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class BonusController {

    private final OrgBonusService orgBonusService;
    private final OrganizationRestController organizationRestController;
    private final OrgBonusTypeRestController orgBonusTypeRestController;

    public BonusController (OrgBonusService orgBonusService, OrganizationRestController organizationRestController, OrgBonusTypeRestController orgBonusTypeRestController){
        this.orgBonusService = orgBonusService;
        this.organizationRestController = organizationRestController;
        this.orgBonusTypeRestController = orgBonusTypeRestController;
    }


    @GetMapping(value = "/bonus/list")
    public String getBonusList(Model model) {

        List<BonusModel> orgBonusList = orgBonusService.findAllOrgBonusList();
        System.out.println(orgBonusList);
        model.addAttribute("orgBonusList", orgBonusList);

        List<Organization> orgList = organizationRestController.getAllSorted();
        model.addAttribute("orgList", orgList);

        List<OrgBonusTypeModel> orgBonusTypeList = orgBonusTypeRestController.findAllOrgBonusTypeDto();
        model.addAttribute("orgBonusTypeList", orgBonusTypeList);

        model.addAttribute("isBonusListByOrg", false);

        return "bonusList";
    }

    @PostMapping(value = "/bonus/list")
    public String getBonusListWithFilter(@Param("orgId") Long orgId, @Param("bonusTypeId") Long bonusTypeId,
                                         @Param("dateFrom") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateFrom, @Param("from") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateTo,
                                         @Param("status") Boolean status, Model model) {
        List<BonusModel> orgBonusList = orgBonusService.findByCriteria(orgId, bonusTypeId, dateFrom, dateTo, status);
        model.addAttribute("orgBonusList", orgBonusList);

        List<Organization> orgList = organizationRestController.getAll();
        model.addAttribute("orgList", orgList);

        List<OrgBonusTypeModel> orgBonusTypeList = orgBonusTypeRestController.findAllOrgBonusTypeDto();
        model.addAttribute("orgBonusTypeList", orgBonusTypeList);

        model.addAttribute("isBonusListByOrg", false);

        return "bonusList";
    }

    @GetMapping("bonus/forOrgAdmin")
    public String getClientListForOrgAdmin(Model model,
                                           Authentication authentication
    ) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        Long organization = user.getOrganization().getId();
        List<BonusModel> orgBonusList = orgBonusService.findAllOrgBonusListByOrgId(organization);
        model.addAttribute("orgBonusList", orgBonusList);
        model.addAttribute("orgId", organization);
        model.addAttribute("isBonusListByOrg",true);
        return "bonusList";
    }

    @GetMapping(value = "/bonus/{bonusId}")
    public String bonusProfile(@PathVariable("bonusId") Long id, Model model) {
        OrgBonus orgBonus = orgBonusService.findById(id);
        model.addAttribute("orgBonus", orgBonus);

        Organization organization = orgBonus.getOrganization();
        model.addAttribute("organization", organization);

        OrgBonusType orgBonusType = orgBonus.getOrgBonusType();
        model.addAttribute("orgBonusType", orgBonusType);


        model.addAttribute("add",false);

        model.addAttribute("isOrganizationPage",false);

        return "bonusForm";
    }

    @PostMapping(value = "/bonus/update/{bonusId}")
    public String updateOrgBonus(@PathVariable("bonusId") Long bonusId, @Valid OrgBonus orgBonus){
        orgBonusService.putById(bonusId, orgBonus);
        return "redirect:/bonus/list";
    }

    @GetMapping(value = "/bonus/delete/{bonusId}")
    public String deleteOrgBonus(@PathVariable("bonusId") Long bonusId){
        orgBonusService.deleteById(bonusId);
        return "redirect:/bonus/list";
    }


// ******************************************************************************************************


    @GetMapping(value = "/organization/{orgId}/bonus/list")
    public String getBonusListByOrg(@PathVariable("orgId") Long orgId, Model model) {
        List<BonusModel> orgBonusList = orgBonusService.findAllOrgBonusListByOrgId(orgId);
        model.addAttribute("orgBonusList", orgBonusList);

        List<OrgBonusTypeModel> orgBonusTypeList = orgBonusTypeRestController.findAllOrgBonusTypeDto();
        model.addAttribute("orgBonusTypeList", orgBonusTypeList);

        model.addAttribute("orgId", orgId);
        model.addAttribute("isBonusListByOrg", true);

        return "bonusList";
    }

    @PostMapping(value = "/organization/{orgId}/bonus/list")
    public String getBonusListByOrgWithFilter(@PathVariable("orgId") Long orgId, @Param("bonusTypeId") Long bonusTypeId,
                                              @Param("dateFrom") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateFrom, @Param("from") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateTo,
                                              @Param("status") Boolean status, Model model) {
        List<BonusModel> orgBonusList = orgBonusService.findByCriteria(orgId, bonusTypeId, dateFrom, dateTo, status);
        model.addAttribute("orgBonusList", orgBonusList);

        List<Organization> orgList = organizationRestController.getAll();
        model.addAttribute("orgList", orgList);

        List<OrgBonusTypeModel> orgBonusTypeList = orgBonusTypeRestController.findAllOrgBonusTypeDto();
        model.addAttribute("orgBonusTypeList", orgBonusTypeList);

        model.addAttribute("isBonusListByOrg", true);

        return "bonusList";
    }

    @GetMapping(value = "/organization/{orgId}/bonus/{bonusId}")
    public String bonusProfileByOrg(@PathVariable("bonusId") Long id, Model model) {
        OrgBonus orgBonus = orgBonusService.findById(id);
        model.addAttribute("orgBonus", orgBonus);

        Organization organization = orgBonus.getOrganization();
        model.addAttribute("organization", organization);

        OrgBonusType orgBonusType = orgBonus.getOrgBonusType();
        model.addAttribute("orgBonusType", orgBonusType);

        model.addAttribute("add",false);

        model.addAttribute("isOrganizationPage",true);

        return "bonusForm";
    }

    @PostMapping(value = "/organization/{orgId}/bonus/update/{bonusId}")
    public String updateOrgBonusByOrg(@PathVariable("orgId") Long orgId, @PathVariable("bonusId") Long bonusId, @Valid OrgBonus orgBonus){
        orgBonusService.putById(bonusId, orgBonus);
        return "redirect:/organization/"+ orgId +"/bonus/list";
    }

    @GetMapping(value = "/organization/{orgId}/bonus/delete/{bonusId}")
    public String deleteOrgBonusByOrg(@PathVariable("orgId") Long orgId, @PathVariable("bonusId") Long bonusId){
        orgBonusService.deleteById(bonusId);
        return "redirect:/organization/"+ orgId +"/bonus/list";
    }

    @GetMapping(value = "/organization/{orgId}/bonus/form")
    public String bonusForm(@PathVariable("orgId") Long orgId, Model model) {
        Organization organization = organizationRestController.getOrganizationById(orgId);
        model.addAttribute("organization", organization);

        OrgBonus orgBonus = new OrgBonus();
        model.addAttribute("orgBonus", orgBonus);

        List<OrgBonusType> orgBonusTypes = orgBonusTypeRestController.getAll();
        model.addAttribute("orgBonusTypes", orgBonusTypes);

        model.addAttribute("add", true);
        model.addAttribute("isOrganizationPage",true);
        return "bonusForm";
    }

    @PostMapping(value = "/organization/{orgId}/bonus/create")
    public String createOrgBonus(@PathVariable("orgId") Long orgId, @Valid OrgBonus orgBonus){
        Organization organization = organizationRestController.getOrganizationById(orgId);
        orgBonus.setOrganization(organization);
        orgBonusService.create(orgBonus);
        return "redirect:/bonus/forOrgAdmin";
    }
}
