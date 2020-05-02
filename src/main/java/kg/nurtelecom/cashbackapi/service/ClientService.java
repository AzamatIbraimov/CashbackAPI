package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.Client;
import kg.nurtelecom.cashbackapi.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    Client findById(Long id);

    List<Client> findAll();

    Page<ClientShortModel> getAllClientByPagination(Pageable pageable);

    List<ClientShortModel> getAllClientByOrgId(Long id, String search);

    Client create(Client client);

    Client create(ClientModelMultipartImage client);

    String deleteById(Long id);

    Client putById(Long id, ClientModelMultipartImage clientLongModel);

    Client putClientById(Long id, ClientChangeModel clientChangeModel);

    ClientLongModel findModelById(Long id);

    ClientPersonalCodeModel findCodeByClientId(Long id);

    ClientLongModel findClientByPersonalCode(String code);


    List<ClientPreferenceModel> getClientPreferences(Long id);

    List<ClientPreferenceModel> getClientPreferences(Long clientId, Long orgId);

    Page<ClientShortModel> findAllByNameOrDescription(String search, Pageable pageable);

}
