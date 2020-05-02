package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.ClientDevice;
import kg.nurtelecom.cashbackapi.model.ClientDeviceChangeModel;
import kg.nurtelecom.cashbackapi.model.ClientDeviceModel;
import kg.nurtelecom.cashbackapi.service.ClientDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/clientDevice")
public class ClientDeviceRestController {
    @Autowired
    private ClientDeviceService clientDeviceService;

    @GetMapping("/{id}")
    public ClientDevice getClientDeviceById(@PathVariable("id") Long id) {
        return clientDeviceService.findById(id);
    }

    @GetMapping("/all")
    public List<ClientDevice> getAll() {
        return clientDeviceService.findAll();
    }

    @PutMapping("/{id}")
    public ClientDevice putClientDevice(@PathVariable("id") Long id, @RequestBody ClientDevice clientDevice) {
        return clientDeviceService.putById(id, clientDevice);
    }

    @PostMapping("/{id}")
    public ResponseEntity putDevice(@PathVariable("id") Long id, @RequestBody ClientDeviceChangeModel clientDeviceChangeModel) {
        return clientDeviceService.putDeviceById(id, clientDeviceChangeModel).equals("ok") ? new ResponseEntity(HttpStatus.ACCEPTED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/phone/{id}")
    public ResponseEntity changeDevice(@PathVariable("id") Long id, @RequestBody String phone) {
        return clientDeviceService.changeDeviceById(id, phone).equals("ok") ? new ResponseEntity(HttpStatus.ACCEPTED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping()
    public ClientDevice postClientDevice(@RequestBody ClientDevice clientDevice) {
        clientDeviceService.create(clientDevice);
        return clientDevice;
    }

    @PostMapping("/device")
    public ClientDevice postDevice(@RequestBody ClientDeviceModel clientDevice) {
        System.out.println("Yo");
        System.out.println(clientDevice.toString());
        return clientDeviceService.createClientDevice(clientDevice);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return clientDeviceService.deleteById(id);
    }

    public ClientDevice findByPhone(String username) {
        return clientDeviceService.findByPhone(username);
    }
}
