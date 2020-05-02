package kg.nurtelecom.cashbackapi.dao;

import kg.nurtelecom.cashbackapi.model.ClientPersonalCodeModel;
import kg.nurtelecom.cashbackapi.model.ClientShortModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ClientDao {

    private final DataSource dataSource;

    @Autowired
    public ClientDao(ApplicationContext applicationContext, DataSource dataSource) {
        this.dataSource = dataSource;
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory.containsBean("jdbcDataSource")) {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("jdbcDataSource");
        } else {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("dataSource");
        }
    }


    public List<ClientShortModel> getClientsByOrgId(Long orgId, String search) {
        List<ClientShortModel> clientList = new ArrayList();
        Connection connection = null;
        Statement stmt = null;
        String sql = null;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            // QUERY
            if(search == null) {
                sql = "select c.id, c.image, c.first_name, c.last_name, c.patronymic from client_organization c_o join organization org on c_o.organization_id = org.id join client c on c_o.client_id=c.id where org.id = " + orgId;
            } else {
//                sql = "select c.id, c.first_name, c.last_name, c.patronymic from client_organization c_o join organization org on c_o.organization_id = org.id join client c on c_o.client_id=c.id where org.id = " + orgId;
            }
            //pagination (LIMIT ? OFFSET ?)
            ResultSet resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                do {
                    ClientShortModel client = new ClientShortModel();
                    client.setId(resultSet.getLong("id"));
                    client.setImage(resultSet.getString("image"));
                    client.setFirstName(resultSet.getString("first_name"));
                    client.setLastName(resultSet.getString("last_name"));
                    client.setPatronymic(resultSet.getString("patronymic"));
                    clientList.add(client);
                } while (resultSet.next());
            } else {
                System.out.println("result set is empty");
            }
            resultSet.close();
        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    connection.close();
            } catch (SQLException se) {
                System.out.println(se);
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return clientList;
    }


    public ClientPersonalCodeModel getCodeByClientId(Long clientId) {
        ClientPersonalCodeModel result = new ClientPersonalCodeModel();
        Connection connection = null;
        Statement stmt = null;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            // QUERY
            String sql = "SELECT c.id, c.personal_code FROM client c WHERE c.id = " + clientId;
            ResultSet resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                    result.setId(resultSet.getLong("id"));
                    result.setPersonalCode(resultSet.getString("personal_code"));
            } else {
                result = new ClientPersonalCodeModel();
                System.out.println("result set is empty");
            }
            resultSet.close();
        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    connection.close();
            } catch (SQLException se) {
                System.out.println(se);
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

    public Page<ClientShortModel> getClientsByOrgIdByPagination(Long orgId, Pageable pageable) {
        List<ClientShortModel> clientList = new ArrayList();
        Connection connection = null;
        Statement stmt = null;
        int total = 0;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            String rowCountSql = "SELECT count(1) AS row_count " +
                    "from client_organization c_o " +
                    "join organization org on c_o.organization_id = org.id " +
                    "join client c on c_o.client_id=c.id where org.id = " + orgId;
            ResultSet rs = stmt.executeQuery(rowCountSql);

//            total = rs.getInt(0);
            if (rs.next()){
                total = rs.getInt("row_count");
            }
            // QUERY
            String sql = "select c.id,  c.image, c.first_name, c.last_name, c.patronymic from client_organization c_o " +
                    "join organization org on c_o.organization_id = org.id " +
                    "join client c on c_o.client_id=c.id where org.id = " + orgId +
                    "LIMIT " + pageable.getPageSize() +
                    "OFFSET " + pageable.getOffset();
            //pagination (LIMIT ? OFFSET ?)
            ResultSet resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                do {
                    ClientShortModel client = new ClientShortModel();
                    client.setId(resultSet.getLong("id"));
                    client.setImage(resultSet.getString("image"));
                    client.setFirstName(resultSet.getString("first_name"));
                    client.setLastName(resultSet.getString("last_name"));
                    client.setPatronymic(resultSet.getString("patronymic"));
                    clientList.add(client);
                } while (resultSet.next());
            } else {
                System.out.println("result set is empty");
            }
            resultSet.close();
        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    connection.close();
            } catch (SQLException se) {
                System.out.println(se);
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return new PageImpl<>(clientList, pageable, total);
    }

}

