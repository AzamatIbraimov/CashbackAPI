package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.ClientPreferenceValue;
import kg.nurtelecom.cashbackapi.model.ClientPreferenceModel;

import java.util.List;

public interface ClientPreferenceValueService {
    ClientPreferenceValue findById(Long id);

    List<ClientPreferenceValue> findAll();

    ClientPreferenceValue create(ClientPreferenceValue clientPreferenceValue);

    String deleteById(Long id);

    ClientPreferenceValue putById(Long id, ClientPreferenceValue clientPreferenceValue);

    List<ClientPreferenceModel> getClientPreference(Long clientId);

    List<ClientPreferenceModel> getClientPreference(Long clientId, Long orgId);
}
