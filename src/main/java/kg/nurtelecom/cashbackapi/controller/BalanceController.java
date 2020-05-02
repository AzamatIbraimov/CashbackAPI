package kg.nurtelecom.cashbackapi.controller;

import kg.nurtelecom.cashbackapi.apicontroller.OrgBonusTypeRestController;
import kg.nurtelecom.cashbackapi.apicontroller.OrganizationRestController;
import kg.nurtelecom.cashbackapi.service.BalanceHistoryService;
import kg.nurtelecom.cashbackapi.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping("/client/{clientId}/balances")
@Controller
public class BalanceController {
    @Autowired
    private OrganizationRestController organizationRestController;
    @Autowired
    private OrgBonusTypeRestController orgBonusTypeRestController;
    @Autowired
    private BalanceHistoryService balanceHistoryService;
    @Autowired
    private BalanceService balanceService;

    @GetMapping()
    public String getBalanceList(@PathVariable("clientId")Long id, Model model) {

        model.addAttribute("history", balanceHistoryService.findHistoryByClientAndOrgAndBonusTypeId(id, null, null));

        model.addAttribute("totalList", balanceService.findBalanceByClientAndOrgAndBonusTypeId(id,null,null));

        model.addAttribute("orgList", organizationRestController.getAll());

        model.addAttribute("orgBonusTypeList", orgBonusTypeRestController.findAllOrgBonusTypeDto());

        model.addAttribute("clientId",id);

        return "balanceList";
    }

    @GetMapping(value = "/filter")
    public String getBonusListWithFilter(@PathVariable("clientId")Long clientId,
                                         @RequestParam("orgId") Long orgId,
                                         @RequestParam("bonusTypeId") Long bonusTypeId, Model model) {

        model.addAttribute("history", balanceHistoryService.findHistoryByClientAndOrgAndBonusTypeId(clientId, orgId, bonusTypeId));

        model.addAttribute("totalList", balanceService.findBalanceByClientAndOrgAndBonusTypeId(clientId, orgId, bonusTypeId));

        model.addAttribute("orgList", organizationRestController.getAllSorted());

        model.addAttribute("orgBonusTypeList", orgBonusTypeRestController.findAllOrgBonusTypeDto());

        model.addAttribute("clientId",clientId);

        return "balanceList";
    }
}
