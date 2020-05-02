package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.ClientPreferenceValue;
import kg.nurtelecom.cashbackapi.model.ClientPreferenceModel;
import kg.nurtelecom.cashbackapi.service.ClientPreferenceValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/clientPreferenceValue")
public class ClientPreferenceValueRestController {
    @Autowired
    private ClientPreferenceValueService clientPreferenceValueService;

    @GetMapping("/{id}")
    public ClientPreferenceValue getClientPreferenceValueById(@PathVariable("id") Long id) {
        return clientPreferenceValueService.findById(id);
    }

    @GetMapping("/all")
    public List<ClientPreferenceValue> getAll() {
        return clientPreferenceValueService.findAll();
    }

    @PutMapping("/{id}")
    public ClientPreferenceValue putClientPreferenceValue(@PathVariable("id") Long id, @RequestBody ClientPreferenceValue clientPreferenceValue) {
        return clientPreferenceValueService.putById(id, clientPreferenceValue);
    }

    @PostMapping()
    public ClientPreferenceValue postClientPreferenceValue(@RequestBody ClientPreferenceValue clientPreferenceValue) {
        clientPreferenceValueService.create(clientPreferenceValue);
        return clientPreferenceValue;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return clientPreferenceValueService.deleteById(id);
    }


    @GetMapping("client/{id}")
    public List<ClientPreferenceModel> getClientPreferenceValueModelById(@PathVariable("id") Long id) {
        return clientPreferenceValueService.getClientPreference(id);
    }

}
