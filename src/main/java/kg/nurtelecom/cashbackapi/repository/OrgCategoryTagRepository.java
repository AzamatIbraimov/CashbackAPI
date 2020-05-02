package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.OrgCategoryTag;
import kg.nurtelecom.cashbackapi.model.OrgCategoryTagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrgCategoryTagRepository extends JpaRepository<OrgCategoryTag, Long> {
    @Query("select new kg.nurtelecom.cashbackapi.model.OrgCategoryTagModel(tag.id, tag.name) from   OrgCategoryTag  tag where tag.id =:id")
    OrgCategoryTagModel getTagById(Long id);
}
