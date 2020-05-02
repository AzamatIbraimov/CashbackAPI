package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.BalanceHistory;
import kg.nurtelecom.cashbackapi.model.BalanceHistoryLongModel;
import kg.nurtelecom.cashbackapi.model.BalanceHistoryModel;
import kg.nurtelecom.cashbackapi.model.HistoryModel;
import kg.nurtelecom.cashbackapi.service.BalanceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/balanceHistory")
public class BalanceHistoryRestController {
    @Autowired
    private BalanceHistoryService balanceHistoryService;

    @GetMapping("/{id}")
    public BalanceHistory getBalanceHistoryById(@PathVariable("id") Long id) {
        return balanceHistoryService.findById(id);
    }
    @GetMapping(value="/client/{id}", params={"page", "size" })
    public Page<HistoryModel> getBalanceHistoryByClientId(@PathVariable("id") Long clientId,
                                                          @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return balanceHistoryService.getHistoryByClientIdPageable(clientId, page, size);
    }

    @GetMapping("/client/v1/{id}")
    public List<HistoryModel> getBalanceHistoryByClientId(@PathVariable("id") Long clientId,
                                                          @RequestParam(value = "last_d", defaultValue="2000-01-01 00:00:00", required = false) String last,
                                                          @RequestParam(value = "last_i", defaultValue = "0", required = false) Long hid,
                                                          @RequestParam(value = "limit", defaultValue = "7", required = false) Integer pageSize) {
        return balanceHistoryService.findHistoryByClientId(clientId, last, hid, pageSize);
    }

    @GetMapping("/user/{id}")
    public List<BalanceHistoryLongModel> getBalanceHistoryByUserId(@PathVariable("id") Long id,
                                                                   @RequestParam(value ="dateFrom", required = false)String dateFrom,
                                                                   @RequestParam(value ="dateFrom", required = false)String dateTo) {
        return balanceHistoryService.getCashierOperationHistory(id, dateFrom, dateTo);
    }

    @GetMapping("/all")
    public List<BalanceHistory> getAll() {
        return balanceHistoryService.findAll();
    }

    @PutMapping("/{id}")
    public BalanceHistory putBalanceHistory(@PathVariable("id") Long id, @RequestBody BalanceHistory balanceHistory) {
        return balanceHistoryService.putById(id, balanceHistory);
    }

//    @PostMapping()
//    public BalanceHistory postBalanceHistory(@RequestBody BalanceHistory balanceHistory) {
//        balanceHistoryService.create(balanceHistory);
//        return balanceHistory;
//    }
    @PostMapping()
    public BalanceHistory postBalanceHistory(@RequestBody BalanceHistoryModel balanceHistory) {
        return balanceHistoryService.create(balanceHistory);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return balanceHistoryService.deleteById(id);
    }

}
