package kg.nurtelecom.cashbackapi.dao;

import kg.nurtelecom.cashbackapi.model.OrganizationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrganizationDao {

    private final DataSource dataSource;

    @Autowired
    public OrganizationDao(ApplicationContext applicationContext, DataSource dataSource) {
        this.dataSource = dataSource;
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory.containsBean("jdbcDataSource")) {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("jdbcDataSource");
        } else {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("dataSource");
        }
    }

    public List<OrganizationModel> getOrgByClientId(Long clientId) {
        List<OrganizationModel> result = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            String query = "SELECT org.id, org.image, org.status, org.name, org.catid, org.catname " +
                    "FROM " +
                    "(SELECT org.id as id, org.image, org.status as status, org.name as name, cat.id as catid, cat.name AS catname , ROW_NUMBER() OVER (PARTITION BY cat.id  ORDER BY cat.id ASC ) AS rn " +
                    "    FROM client_organization client_org " +
                    "JOIN organization org ON client_org.organization_id = org.id " +
                    "JOIN client client ON client_org.client_id = client.id " +
                    "JOIN org_category cat ON org.category_id = cat.id " +
                    "WHERE client.id = "+ clientId + " AND org.status = TRUE ) org " +
                    "WHERE rn <= 4 " +
                    "ORDER BY org.id, org.catid";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                OrganizationModel dto = new OrganizationModel();
                dto.setId(resultSet.getLong("id"));
                dto.setImage(resultSet.getString("image"));
                dto.setStatus(resultSet.getBoolean("status"));
                dto.setName(resultSet.getString("name"));
                dto.setCategoryId(resultSet.getLong("catid"));
                dto.setCategoryName(resultSet.getString("catname"));
                result.add(dto);
            };
            result.stream().filter(d-> d.getStatus()).collect(Collectors.toList());

            resultSet.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    connection.close();
            } catch (SQLException se) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return result;
    }

    public List<OrganizationModel> getOrgByClientIdAndCategoryId(Long clientId, Long categoryId, Long lastId, Integer limit) {
        List<OrganizationModel> result = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            String query = "SELECT org.id, org.image, org.status, org.name, cat.id AS catid, cat.name AS catname " +
                    "FROM client_organization client_org " +
                    "JOIN organization org ON client_org.organization_id = org.id " +
                    "JOIN client client ON client_org.client_id = client.id " +
                    "JOIN org_category cat ON org.category_id = cat.id " +
                    "WHERE (org.id > " + lastId + " OR org.id = " + lastId + ") AND cat.id = " + categoryId + " AND client.id = " + clientId + " LIMIT " + limit;
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                OrganizationModel dto = new OrganizationModel();
                dto.setId(resultSet.getLong("id"));
                dto.setImage(resultSet.getString("image"));
                dto.setStatus(resultSet.getBoolean("status"));
                dto.setName(resultSet.getString("name"));
                dto.setCategoryId(resultSet.getLong("catid"));
                dto.setCategoryName(resultSet.getString("catname"));
                result.add(dto);
            };
            result.stream().filter(d-> d.getStatus()).collect(Collectors.toList());

            resultSet.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    connection.close();
            } catch (SQLException se) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return result;
    }
}
