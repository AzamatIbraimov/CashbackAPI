package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.ClientEvent;

import java.util.List;

public interface ClientEventService {
    ClientEvent findById(Long id);

    List<ClientEvent> findAll();

    ClientEvent create(ClientEvent clientEvent);

    String deleteById(Long id);

    ClientEvent putById(Long id, ClientEvent clientEvent);
}
