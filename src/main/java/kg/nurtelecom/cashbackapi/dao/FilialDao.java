package kg.nurtelecom.cashbackapi.dao;

import kg.nurtelecom.cashbackapi.model.FilialShortModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FilialDao {

    private final DataSource dataSource;

    @Autowired
    public FilialDao(ApplicationContext applicationContext, DataSource dataSource) {
        this.dataSource = dataSource;
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory.containsBean("jdbcDataSource")) {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("jdbcDataSource");
        } else {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("dataSource");
        }
    }


    public List<FilialShortModel> getFilialByOrgId(Long orgId, Long lastId, Double lastAverage, Integer limit){
        List<FilialShortModel> result = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            String query = "SELECT f.id, f.name, f.address, f.description, f.average_rate, f.longitude, f.latitude " +
                    "FROM filial f " +
                    "WHERE ((f.average_rate < " + lastAverage + " OR f.average_rate = " + lastAverage + ") AND f.id > " + lastId + ") AND f.status = true AND f.organization_id = " + orgId +
                    " ORDER BY f.average_rate DESC, f.id ASC LIMIT " + limit;

            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                FilialShortModel dto = new FilialShortModel();
                dto.setId(resultSet.getLong("id"));
                dto.setName(resultSet.getString("name"));
                dto.setAddress(resultSet.getString("address"));
                dto.setDescription(resultSet.getString("description"));
                dto.setAverageRate(resultSet.getDouble("average_rate"));
                result.add(dto);
            }

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
