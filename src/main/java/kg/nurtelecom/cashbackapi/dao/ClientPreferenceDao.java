package kg.nurtelecom.cashbackapi.dao;

import kg.nurtelecom.cashbackapi.model.ClientPreferenceModel;
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

@Repository
public class ClientPreferenceDao {

    private final DataSource dataSource;

    @Autowired
    public ClientPreferenceDao(ApplicationContext applicationContext, DataSource dataSource) {
        this.dataSource = dataSource;
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory.containsBean("jdbcDataSource")) {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("jdbcDataSource");
        } else {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("dataSource");
        }
    }

    public List<ClientPreferenceModel> getClientPreferences(Long clientId) {
        List<ClientPreferenceModel> result = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        int total = 0;

        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            String rowCountSql = "SELECT count (1) AS row_count " +
                    "from client_preference_value client_preference " +
                    "join preference_category category " +
                    "on client_preference.organization_id = category.parent_id " +
                    "where client_preference.client_id = " + clientId;
            ResultSet rs = stmt.executeQuery(rowCountSql);
            if (rs.next()) {
                total = rs.getInt("row_count");
            }


            String query = "select client_preference.id as id,client_preference.value as value,category.id as catId,category.name as name from client_preference_value client_preference join preference_category category on client_preference.organization_id = category.parent_id where client_preference.client_id = " + clientId + "  ORDER BY id ASC";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                ClientPreferenceModel dto = new ClientPreferenceModel();
                dto.setId(Long.valueOf(resultSet.getString("id")));
                dto.setOrganizationName(resultSet.getString("name"));
                dto.setValue(resultSet.getInt("value"));
                result.add(dto);
            }
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

    public List<ClientPreferenceModel> getClientPreferences(Long clientId, Long orgId) {
        List<ClientPreferenceModel> result = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        int total = 0;

        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();

            String query = "select client_preference.id as id,client_preference.value as value,category.id as catId,category.name as name " +
                    "from client_preference_value client_preference " +
                    "    join preference_category category on client_preference.organization_id = category.parent_id " +
                    "where client_preference.client_id = " + clientId + " and client_preference.organization_id = " + orgId + "  ORDER BY id ASC";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                ClientPreferenceModel dto = new ClientPreferenceModel();
                dto.setId(Long.valueOf(resultSet.getString("id")));
                dto.setOrganizationName(resultSet.getString("name"));
                dto.setValue(resultSet.getInt("value"));
                result.add(dto);
            }
            resultSet.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
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

