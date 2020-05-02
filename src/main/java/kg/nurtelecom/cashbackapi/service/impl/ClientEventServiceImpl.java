package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.entity.ClientEvent;
import kg.nurtelecom.cashbackapi.repository.ClientEventRepository;
import kg.nurtelecom.cashbackapi.service.ClientEventService;
import kg.nurtelecom.cashbackapi.utils.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientEventServiceImpl implements ClientEventService {
    @Autowired
    private ClientEventRepository clientEventRepository;

    public ClientEvent findById(Long id) {
        return clientEventRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }

    public List<ClientEvent> findAll() {
        return clientEventRepository.findAll();
    }

    public ClientEvent create(ClientEvent clientEvent) {
        return clientEventRepository.save(clientEvent);
    }

    public String deleteById(Long id) {
        clientEventRepository.deleteById(id);
        return "ClientEvent number " + id + " has been deleted!";
    }

    public ClientEvent putById(Long id, ClientEvent clientEvent) {
        return clientEventRepository.findById(id)
                .map(newClientEvent -> {
                    newClientEvent.setId(clientEvent.getId());
                    newClientEvent.setClient(clientEvent.getClient());
                    newClientEvent.setEvent(clientEvent.getEvent());
                    newClientEvent.setNotificationDate(clientEvent.getNotificationDate());
                    return clientEventRepository.save(newClientEvent);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + id));
    }


}
