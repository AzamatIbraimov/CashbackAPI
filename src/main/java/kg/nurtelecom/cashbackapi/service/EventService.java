package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.Event;
import kg.nurtelecom.cashbackapi.model.EventFullModel;
import kg.nurtelecom.cashbackapi.model.EventModel;
import kg.nurtelecom.cashbackapi.model.EventModelMultipartImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventService {
    Event findById(Long id);

    List<Event> findAll();

    Event create(EventModelMultipartImage event);

    String deleteById(Long id);

    Event putById(Long id, EventModelMultipartImage event);

    List<EventModel> findAllEventsByOrgId(@Param("id") Long id);

    List<EventFullModel> getAllEventsByOrgId(Long orgId, Long lastId, String lastDate, Integer limit);

    Page<EventModel> findAllEventsByOrgIdAndName(Long id, String search, Pageable pageable);

    Page<EventFullModel> findRestAllEvents(Integer page, Integer size);

    Page<EventModel> findAllEventsByOrgIdWithPagination(Long id, Pageable pageable);

    Page<EventFullModel> findAllEventsByIdWithPagination(Long id, int page, int size);
}
