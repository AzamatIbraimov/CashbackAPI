package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.Balance;
import kg.nurtelecom.cashbackapi.model.BalanceConfirmModel;
import kg.nurtelecom.cashbackapi.model.BalanceModel;
import kg.nurtelecom.cashbackapi.model.HistoryModel;
import kg.nurtelecom.cashbackapi.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/balance")
public class BalanceRestController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping("/{id}")
    public Balance getBalanceById(@PathVariable("id") Long id) {
        return balanceService.findById(id);
    }

    @GetMapping("/all")
    public List<Balance> getAll() {
        return balanceService.findAll();
    }

    @PutMapping("/{id}")
    public Balance putBalance(@PathVariable("id") Long id, @RequestBody Balance balance) {
        return balanceService.putById(id, balance);
    }

    @PostMapping("/test")
    public String check(@RequestBody BalanceConfirmModel balanceConfirm) {
//        balanceService.create(balance);
        System.out.println("Test-----------------------------------------");
        return "adi";
    }

    @PostMapping()
    public Balance postBalance(@RequestBody Balance balance) {
        balanceService.create(balance);
        return balance;
    }


    @GetMapping("/getClientByCodeAndOrgId/{code}/{orgId}")
    public List<BalanceModel> getBalanceByClientCodeAndOrgId(@PathVariable("code")String code,
                                                          @PathVariable("orgId")Long orgId){
        return balanceService.getClientBalanceByClientCodeOrgId(code,orgId);
    }
    @GetMapping("/getClientByCodeAndOrgId/{clientId}/{orgId}")
    public List<BalanceModel> getBalanceByClientIdAndOrgId(@PathVariable("clientId")Long clientId,
                                                             @PathVariable("orgId")Long orgId){
        return balanceService.getClientBalanceIdsByClientIdOrgId(clientId,orgId);
    }
    @GetMapping("/getClientByCodeAndOrgId/{clientId}/{orgId}/{typeId}")
    public List<HistoryModel> findBalanceByClientAndOrgAndBonusTypeId(@PathVariable("clientId")Long clientId,
                                                             @PathVariable("orgId")Long orgId,
                                                             @PathVariable("orgId")Long typeId){
        return balanceService.findBalanceByClientAndOrgAndBonusTypeId(clientId, orgId, typeId);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return balanceService.deleteById(id);
    }

}
