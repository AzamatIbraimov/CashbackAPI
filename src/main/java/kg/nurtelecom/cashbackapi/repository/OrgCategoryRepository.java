package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.OrgCategory;
import kg.nurtelecom.cashbackapi.model.OrgCategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgCategoryRepository extends JpaRepository<OrgCategory, Long> {
    @Query("select new kg.nurtelecom.cashbackapi.model.OrgCategoryModel(orgCategory.id,orgCategory.image,orgCategory.name, orgCategory.description) FROM OrgCategory orgCategory")
    List<OrgCategoryModel> getOrgCategoryList();

    @Query("select new kg.nurtelecom.cashbackapi.model.OrgCategoryModel(orgCategory.id,orgCategory.image,orgCategory.name, orgCategory.description) FROM OrgCategory orgCategory where orgCategory.id = :id")
    OrgCategoryModel getOrgCategoryById(Long id);

    @Query("select new kg.nurtelecom.cashbackapi.model.OrgCategoryModel(orgCategory.id,orgCategory.image,orgCategory.name, orgCategory.description) FROM OrgCategory orgCategory")
    Page<OrgCategoryModel> getOrgCategoryListByPagination(Pageable pageable);
}

