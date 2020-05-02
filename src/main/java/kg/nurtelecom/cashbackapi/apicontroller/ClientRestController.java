package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.Client;
import kg.nurtelecom.cashbackapi.model.ClientChangeModel;
import kg.nurtelecom.cashbackapi.model.ClientLongModel;
import kg.nurtelecom.cashbackapi.model.ClientPersonalCodeModel;
import kg.nurtelecom.cashbackapi.model.ClientShortModel;
import kg.nurtelecom.cashbackapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/client")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") Long id) {
        return clientService.findById(id);
    }

    @GetMapping("/code/{id}")
    public ClientPersonalCodeModel getCodeByClientId(@PathVariable("id") Long id) {
        return clientService.findCodeByClientId(id);
    }

    @GetMapping("/all")
    public List<Client> getAll() {
        return clientService.findAll();
    }

    @GetMapping("/allByOrgId/{id}")
    public List<ClientShortModel> getAllByOrgIdDao(@PathVariable("id") Long id) {
//        return clientService.getAllClientById(id);
        return null;
    }

//
    @PutMapping("/{id}")
    public Client putClient(@PathVariable("id") Long id, @RequestBody ClientChangeModel client) {
        return clientService.putClientById(id, client);
    }

    @PostMapping()
    public Client postClient(@RequestBody Client client) {
        clientService.create(client);
        return client;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return clientService.deleteById(id);
    }

//    @GetMapping("/list/{id}")
//    public List<ClientListDto> getClientList(@PathVariable("id") Long id) {
//        return clientService.getClientsByOrganization(id);
//    }

    @GetMapping("getClientByCode/{code}")
    public ClientLongModel getClientByCode(@PathVariable("code")String code){
        return clientService.findClientByPersonalCode(code);
    }
}
