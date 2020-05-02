package kg.nurtelecom.cashbackapi.dao;

import kg.nurtelecom.cashbackapi.enums.OperationType;
import kg.nurtelecom.cashbackapi.model.BalanceModel;
import kg.nurtelecom.cashbackapi.model.HistoryModel;
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
public class BalanceDao {

    private final DataSource dataSource;

    @Autowired
    public BalanceDao(ApplicationContext applicationContext, DataSource dataSource) {
        this.dataSource = dataSource;
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory.containsBean("jdbcDataSource")) {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("jdbcDataSource");
        } else {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("dataSource");
        }
    }

    public List<HistoryModel> getBalanceHistoryByClientId(Long clientId, String last, Long historyId, Integer pageSize){
        List<HistoryModel> result = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();

            String query = "SELECT history.id, balance.client_id, history.created_date, history.operation_type, history.amount, balance.amount AS total, type.name AS bonus_type, org.name " +
                    "FROM balance_history history " +
                    "JOIN balance balance " +
                    "ON balance.id = history.balance_id " +
                    "JOIN org_bonus bonus " +
                    "ON bonus.id = balance.org_bonus_id " +
                    "JOIN org_bonus_type type " +
                    "ON type.id = bonus.org_bonus_type_id " +
                    "JOIN organization org ON org.id = bonus.organization_id " +
                    "WHERE (history.created_date > DATE('" + last + "') OR history.created_date = DATE('" + last + "')  AND history.id > " + historyId + " ) AND balance.client_id = " + clientId + " " +
                    "ORDER BY history.created_date DESC, history.id ASC " +
                    "LIMIT " + pageSize;


            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                HistoryModel dto = new HistoryModel();
                dto.setId(resultSet.getLong("id"));
                dto.setClientId(resultSet.getLong("client_id"));
                dto.setCreatedDate(resultSet.getDate("created_date"));
                dto.setOperationType(resultSet.getString("operation_type").equals("DEBIT")  ? OperationType.DEBIT : OperationType.CREDIT);
                dto.setBonusType(resultSet.getString("bonus_type"));
                dto.setAmount(resultSet.getDouble("amount"));
                dto.setTotal(resultSet.getDouble("total"));
                dto.setOrganizationName(resultSet.getString("name"));
                result.add(dto);
            }

        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
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

    public List<HistoryModel> getBalanceHistoryByClientIdAndOrgIdAndBonusTypeId(Long clientId, Long orgId, Long typeId){
        List<HistoryModel> result = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();

            String query = "SELECT balance.client_id, history.created_date, history.operation_type, history.amount, balance.amount AS total, type.name AS bonus_type, org.name " +
                    "FROM balance_history history " +
                    "JOIN balance balance ON balance.id = history.balance_id " +
                    "JOIN org_bonus bonus ON bonus.id = balance.org_bonus_id " +
                    "JOIN org_bonus_type type ON type.id = bonus.org_bonus_type_id " +
                    "JOIN organization org ON org.id = bonus.organization_id WHERE balance.client_id = " + clientId;

            if (orgId != null){
                query = query + " and org.id=" + orgId;
            }
            if(typeId != null){
                query = query + " and bonus.org_bonus_type_id=" + typeId;
            }

            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                HistoryModel dto = new HistoryModel();
                dto.setClientId(resultSet.getLong("client_id"));
                dto.setCreatedDate(resultSet.getDate("created_date"));
                dto.setOperationType(resultSet.getString("operation_type").equals("DEBIT")  ? OperationType.DEBIT : OperationType.CREDIT);
                dto.setBonusType(resultSet.getString("bonus_type"));
                dto.setAmount(resultSet.getDouble("amount"));
                dto.setTotal(resultSet.getDouble("total"));
                dto.setOrganizationName(resultSet.getString("name"));
                result.add(dto);
            }

        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
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
    public List<HistoryModel> getBalanceByClientIdAndOrgIdAndBonusTypeId(Long clientId, Long orgId, Long typeId){
        List<HistoryModel> result = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();

            String query = "SELECT balance.amount AS total, type.name AS bonus_type, org.name " +
                    "FROM " +
                    "balance balance " +
                    "JOIN org_bonus bonus ON bonus.id = balance.org_bonus_id " +
                    "JOIN org_bonus_type type ON type.id = bonus.org_bonus_type_id " +
                    "JOIN organization org ON org.id = bonus.organization_id WHERE balance.client_id = " + clientId;

            if (orgId != null){
                query = query + " and org.id=" + orgId;
            }
            if(typeId != null){
                query = query + " and bonus.org_bonus_type_id=" + typeId;
            }

            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                HistoryModel dto = new HistoryModel();
                dto.setBonusType(resultSet.getString("bonus_type"));
                dto.setTotal(resultSet.getDouble("total"));
                dto.setOrganizationName(resultSet.getString("name"));
                result.add(dto);
            }

        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
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

    public Long getBalanceIdByClientIdAndOrgIdAndBonusTypeId(Long clientId, Long orgId, Long typeId){
        Long balanceId = null;
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();

            String query = "SELECT balance.id " +
                    "FROM " +
                    "balance balance " +
                    "JOIN org_bonus bonus ON bonus.id = balance.org_bonus_id " +
                    "JOIN org_bonus_type type ON type.id = bonus.org_bonus_type_id " +
                    "JOIN organization org ON org.id = bonus.organization_id WHERE balance.client_id = " + clientId;

            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                balanceId = resultSet.getLong("id");
            }

        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return balanceId;
    }

    public List<BalanceModel> getClientBalanceByClientCodeOrgId(String code, Long orgId) {
       List<BalanceModel> balanceModelList = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        String sql = null;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            // QUERY
            sql = "SELECT c.id, c.first_name, c.last_name, c.image, b.id, b.amount, type.name" +
                    "                    FROM client c" +
                    "                    JOIN balance b on c.id = b.client_id" +
                    "                    JOIN org_bonus bonus ON bonus.id = b.org_bonus_id" +
                    "                    JOIN org_bonus_type type ON type.id = bonus.org_bonus_type_id" +
                    "                    WHERE c.personal_code = '" + code + "' and bonus.organization_id = " + orgId ;

            ResultSet resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                do {
                    BalanceModel balanceModel = new BalanceModel();
                    balanceModel.setClientId(resultSet.getLong("id"));
                    balanceModel.setFirstName(resultSet.getString("first_name"));
                    balanceModel.setLastName(resultSet.getString("last_name"));
                    balanceModel.setImage(resultSet.getString("image"));
                    balanceModel.setBalanceId(resultSet.getLong("id"));
                    balanceModel.setAmount(resultSet.getDouble("amount"));
                    balanceModel.setType(resultSet.getString("name"));
                    balanceModelList.add(balanceModel);
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
                    stmt.close();
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
        return balanceModelList;
    }

    public List<BalanceModel> getClientBalanceIdsByClientIdOrgId(Long clientId, Long orgId) {
       List<BalanceModel> balanceModelList = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        String sql = null;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            // QUERY
            sql = "SELECT b.id " +
                    "                                        FROM client c " +
                    "                                        JOIN balance b on c.id = b.client_id " +
                    "                                        JOIN org_bonus bonus ON bonus.id = b.org_bonus_id " +
                    "                                        JOIN org_bonus_type type ON type.id = bonus.org_bonus_type_id " +
                    "                                        WHERE c.id = " + clientId + "  and bonus.organization_id = " + orgId ;

            ResultSet resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                do {
                    BalanceModel balanceModel = new BalanceModel();
                    balanceModel.setBalanceId(resultSet.getLong("id"));
                    balanceModelList.add(balanceModel);
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
                    stmt.close();
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
        return balanceModelList;
    }

    public boolean updateBalance(Double amount,Long clientId, Long orgBonusId){
        boolean res = false;
        Connection connection = null;
        PreparedStatement stmt = null;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.prepareStatement("UPDATE balance " +
                                                    " SET amount = amount - ? " +
                                                    " WHERE client_id = ? and org_bonus_id = ? ");
            stmt.setDouble(1, amount);
            stmt.setLong(2, clientId);
            stmt.setLong(3, orgBonusId);

            int updated  = stmt.executeUpdate();
            connection.commit();
            if (updated > 0) {
                res = true;
            }
        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
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
        return res;
    }

}
