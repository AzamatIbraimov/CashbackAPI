package kg.nurtelecom.cashbackapi.controller;

import kg.nurtelecom.cashbackapi.apicontroller.BalanceHistoryRestController;
import kg.nurtelecom.cashbackapi.apicontroller.BalanceRestController;
import kg.nurtelecom.cashbackapi.apicontroller.OrgBonusTypeRestController;
import kg.nurtelecom.cashbackapi.apicontroller.UserRestController;
import kg.nurtelecom.cashbackapi.entity.User;
import kg.nurtelecom.cashbackapi.enums.OperationType;
import kg.nurtelecom.cashbackapi.model.*;
import kg.nurtelecom.cashbackapi.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/cashier")
@Controller
public class CashierController {
    @Autowired
    private OrgBonusTypeRestController orgBonusTypeRestController;
    @Autowired
    private BalanceHistoryRestController balanceHistoryRestController;
    @Autowired
    private BalanceRestController balanceRestController;
    @Autowired
    private UserRestController userRestController;
    @Autowired
    private CashierService cashierService;

    private Long orgId;
    private Long userId;

    @GetMapping
    public String operationPage(Model model){
        getUserDetails();

        List<OrgBonusTypeModel> orgBonusTypeList = orgBonusTypeRestController.findAllOrgBonusTypeDto();
        model.addAttribute("orgBonusTypeList", orgBonusTypeList);
        model.addAttribute("orgId", orgId);
        model.addAttribute("confirmBalance", new BalanceConfirmModel());
        return "cashierOperation";
    }

    @GetMapping("/history")
    public String getHistory(Model model){
        if (userId == null)
            getUserDetails();
        List<BalanceHistoryLongModel> list = balanceHistoryRestController.getBalanceHistoryByUserId(userId, null, null);
        model.addAttribute("history", list);
        return "cashierHistory";
    }
    @GetMapping("/history/filter")
    public String getHistoryWithFilter(@RequestParam("dateFrom") String dateFrom,
                                       @RequestParam("dateTo") String dateTo, Model model){
        if (userId == null)
            getUserDetails();

        List<BalanceHistoryLongModel> list;
        if (dateFrom.isEmpty() || dateTo.isEmpty()){
            list = balanceHistoryRestController.getBalanceHistoryByUserId(userId, null, null);
        } else {
            list = balanceHistoryRestController.getBalanceHistoryByUserId(userId, dateFrom, dateTo);
        }
        model.addAttribute("history", list);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        return "cashierHistory";
    }

    @GetMapping("/create")
    public String confirm(@Valid @ModelAttribute("confirmBalance") BalanceConfirmModel balanceConfirm,
                           Model model){
        BonusValueModel bonusValueModel = cashierService.getBonusValueByOrgIdAndTypeId(orgId, balanceConfirm.getTypeId());
        Double sum = cashierService.getSumOfAmount(balanceConfirm.getClientId(), orgId);
        if (sum < 10000)
            bonusValueModel.setValue(bonusValueModel.getMin());
        else if(sum > 20000)
            bonusValueModel.setValue(bonusValueModel.getMax());

        model.addAttribute("organization", bonusValueModel);
        model.addAttribute("confirmBalance",balanceConfirm);
        System.out.println("Operation-----------------------"+ balanceConfirm);
        return "cashierConfirmPage";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("confirmBalance") BalanceConfirmModel balanceConfirm, Model model){
        System.out.println("Operation-----------------------"+ balanceConfirm);
        BonusValueModel bonusValueModel = cashierService.getBonusValueByOrgIdAndTypeId(orgId, balanceConfirm.getTypeId());
        if (balanceConfirm.getPoint() > 0){
            boolean res = cashierService.updateBalance(balanceConfirm.getPoint() , balanceConfirm.getClientId(), bonusValueModel.getBonusId());
            if (!res){
                model.addAttribute("confirmBalance",balanceConfirm);
                return "cashierConfirmPage";
            }
            cashierService.insertBalanceHistory(orgId, userId, balanceConfirm, bonusValueModel, OperationType.CREDIT);
        }
        cashierService.addBonusToBalance(orgId, userId, balanceConfirm,bonusValueModel.getValue());
        return "redirect:/cashier";
    }

    private void getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRestController.getUserByUserName(username);
        userId = user.getId();
        orgId = user.getOrganization().getId();
    }

    @ResponseBody()
    @RequestMapping(value = "/getClientByCodeAndOrgId/{code}/{orgId}", method = RequestMethod.GET,
            produces = "application/json")
    public  List<BalanceModel> getClientData(@PathVariable String code, @PathVariable Long orgId, Model model){
        List<BalanceModel> balanceModels = balanceRestController.getBalanceByClientCodeAndOrgId(code, orgId);
        balanceModels.removeIf(el -> el.getAmount() == 0);
        return balanceModels;
    }

    @ResponseBody()
    @RequestMapping(value = "/getClientPreferences/{clientId}/{orgId}", method = RequestMethod.GET,
            produces = "application/json")
    public  List<ClientPreferenceModel> getClientPreferences(@PathVariable Long clientId,@PathVariable Long orgId, Model model){
        return cashierService.getClientPreferences(clientId, orgId);
    }

    @ResponseBody()
    @RequestMapping(value = "/check", method = RequestMethod.GET,
            produces = "application/json")
    public  List<String> check(@RequestParam Long clientId, @RequestParam Long tId, @RequestParam Double point){
        List<HistoryModel> balance = cashierService.findBalanceByClientAndOrgAndBonusTypeId(clientId, orgId, tId);
        List<String> result = new ArrayList<>();
        result.add(((balance.get(0).getTotal() - point) >= 0) ? "SUCCESS" : "FAIL");
        return result;
    }


}
