package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.Event;
import kg.nurtelecom.cashbackapi.model.EventFullModel;
import kg.nurtelecom.cashbackapi.model.EventModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select new kg.nurtelecom.cashbackapi.model.EventModel(event.id, event.image, event.name, event.dateFrom, event.dateTo,event.organization.id,event.organization.image,event.organization.name,event.organization.orgCategory.name) FROM Event event WHERE organization_id = :id")
    List<EventModel> findAllEventsByOrgId(@Param("id") Long id);

    @Query("select new kg.nurtelecom.cashbackapi.model.EventModel(event.id, event.image, event.name, event.dateFrom, event.dateTo,event.organization.id,event.organization.image,event.organization.name,event.organization.orgCategory.name) FROM Event event WHERE organization_id = :id and lower(name) like %:search% ORDER BY name ASC")
    Page<EventModel> findAllEventsByOrgIdAndName(Long id, String search, Pageable pageable);

    @Query("select new kg.nurtelecom.cashbackapi.model.EventModel(event.id, event.image, event.name, event.dateFrom, event.dateTo,event.organization.id,event.organization.image,event.organization.name,event.organization.orgCategory.name ) FROM Event event WHERE organization_id = :id")
    Page<EventModel> findAllEventsByOrgIdWithPagination(Long id, Pageable pageable);

    @Query("select new kg.nurtelecom.cashbackapi.model.EventFullModel(event.id, event.image, event.name, event.dateFrom, event.dateTo, event.description,event.organization.id,event.organization.image,event.organization.name,event.organization.orgCategory.name) FROM Event event WHERE organization_id = :id")
    Page<EventFullModel> getAllEventsByOrgId(Long id, Pageable pageable);

//    @Query("select new kg.nurtelecom.cashbackapi.model.EventFullModel(event.id, event.name, event.dateFrom, event.dateTo, event.description) FROM Event event WHERE dateFrom > current_date ORDER BY dateFrom ASC")
    @Query("select new kg.nurtelecom.cashbackapi.model.EventFullModel(event.id, event.image, event.name, event.dateFrom, event.dateTo, event.description,event.organization.id,event.organization.image,event.organization.name,event.organization.orgCategory.name) FROM Event event ORDER BY dateFrom ASC")
    Page<EventFullModel> findAllWhereDateFrom(Pageable pageable);

}