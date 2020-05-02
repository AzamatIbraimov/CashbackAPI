package kg.nurtelecom.cashbackapi.dao;

import kg.nurtelecom.cashbackapi.model.EventFullModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDao {

    private final DataSource dataSource;

    @Autowired
    public EventDao(ApplicationContext applicationContext, DataSource dataSource) {
        this.dataSource = dataSource;
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory.containsBean("jdbcDataSource")) {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("jdbcDataSource");
        } else {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("dataSource");
        }
    }

    public List<EventFullModel> getEventByOrgId(Long orgId, Long lastId, String lastDate, Integer limit) {
        List<EventFullModel> result = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            String query = "SELECT " +
                    "e.id, e.name, e.date_from, e.date_to, e.description " +
                    "FROM event e " +
                    "WHERE ((e.date_to > DATE('" + lastDate + "') OR e.date_to = date('" + lastDate + "')) AND e.id > " + lastId + " ) AND e.organization_id =  " + orgId +
                    " ORDER BY e.date_to ASC LIMIT " + limit;

            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                EventFullModel dto = new EventFullModel();
                dto.setId(resultSet.getLong("id"));
                dto.setName(resultSet.getString("name"));
                dto.setDateFrom(resultSet.getDate("date_from"));
                dto.setDateTo(resultSet.getDate("date_to"));
                dto.setDescription(resultSet.getString("description"));
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
