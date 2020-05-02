package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.entity.ClientDevice;
import kg.nurtelecom.cashbackapi.model.ClientDeviceChangeModel;
import kg.nurtelecom.cashbackapi.model.ClientDeviceModel;
import kg.nurtelecom.cashbackapi.repository.ClientDeviceRepository;
import kg.nurtelecom.cashbackapi.service.ClientDeviceService;
import kg.nurtelecom.cashbackapi.service.ClientService;
import kg.nurtelecom.cashbackapi.utils.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientDeviceServiceImpl implements ClientDeviceService {
    @Autowired
    private ClientDeviceRepository clientDeviceRepository;

    @Autowired
    private ClientService clientService;

    public ClientDevice findById(Long id) {
        return clientDeviceRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }

    @Override
    public ClientDevice findByPhone(String phone) {
        return clientDeviceRepository.findByPhoneNumber(phone);
    }

    public List<ClientDevice> findAll() {
        return clientDeviceRepository.findAll();
    }

    public ClientDevice create(ClientDevice clientDevice) {
        return clientDeviceRepository.save(clientDevice);
    }

    public ClientDevice createClientDevice(ClientDeviceModel clientDeviceModel) {
        ClientDevice clientDevice =
                new ClientDevice(0L,
                        clientService.findById(clientDeviceModel.getClientId()),
                        clientDeviceModel.getPhoneNumber(),
                        clientDeviceModel.getPassword(),
                        clientDeviceModel.getImei(),
                        new Date(), true);
        System.out.println(clientDevice.toString());
        return clientDeviceRepository.save(clientDevice);
    }

    public String deleteById(Long id) {
        clientDeviceRepository.deleteById(id);
        return "ClientDevice number " + id + " has been deleted!";
    }

    public ClientDevice putById(Long id, ClientDevice clientDevice) {
        return clientDeviceRepository.findById(id)
                .map(newClientDevice -> {
                    newClientDevice.setId(clientDevice.getId());
                    newClientDevice.setClient(clientDevice.getClient());
                    newClientDevice.setImei(clientDevice.getImei());
                    newClientDevice.setLastEnterDate(clientDevice.getLastEnterDate());
                    newClientDevice.setPassword(clientDevice.getPassword());
                    newClientDevice.setPhoneNumber(clientDevice.getPhoneNumber());
                    newClientDevice.setStatus(clientDevice.getStatus());
                    return clientDeviceRepository.save(newClientDevice);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + id));
    }

    @Override
    public String putDeviceById(Long id, ClientDeviceChangeModel dto) {
        Optional<ClientDevice> old = clientDeviceRepository.findById(id);
        if (old.isPresent() && old.get().getPassword().equals(dto.getCurrentPassword())) {
            old.get().setPassword(dto.getNewPassword() != null && dto.getCurrentPassword().equals(old.get().getPassword()) ? dto.getNewPassword() : old.get().getPassword());
            clientDeviceRepository.save(old.get());
            return "ok";
        }
        return "fail";
    }

    @Override
    public String changeDeviceById(Long id, String value) {
        Optional<ClientDevice> old = clientDeviceRepository.findById(id);
        if (old.isPresent()) {
            old.get().setPhoneNumber(value != null ? value : old.get().getPhoneNumber());
            clientDeviceRepository.save(old.get());
            return "ok";
        }
        return "fail";
    }

}
