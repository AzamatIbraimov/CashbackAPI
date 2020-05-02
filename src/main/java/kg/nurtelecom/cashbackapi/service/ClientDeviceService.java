package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.ClientDevice;
import kg.nurtelecom.cashbackapi.model.ClientDeviceChangeModel;
import kg.nurtelecom.cashbackapi.model.ClientDeviceModel;

import java.util.List;

public interface ClientDeviceService {
    ClientDevice findById(Long id);

    ClientDevice findByPhone(String id);

    List<ClientDevice> findAll();

    ClientDevice create(ClientDevice clientDevice);

    String deleteById(Long id);

    ClientDevice putById(Long id, ClientDevice clientDevice);

    String putDeviceById(Long id, ClientDeviceChangeModel clientDeviceChangeModel);

    String changeDeviceById(Long id, String string);

    ClientDevice createClientDevice(ClientDeviceModel clientDeviceModel);
}
