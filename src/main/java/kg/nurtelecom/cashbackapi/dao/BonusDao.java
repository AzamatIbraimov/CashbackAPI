package kg.nurtelecom.cashbackapi.dao;

import kg.nurtelecom.cashbackapi.model.BonusShortModel;
import kg.nurtelecom.cashbackapi.model.BonusValueModel;
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
public class BonusDao {

    private final DataSource dataSource;

    @Autowired
    public BonusDao(ApplicationContext applicationContext, DataSource dataSource) {
        this.dataSource = dataSource;
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory.containsBean("jdbcDataSource")) {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("jdbcDataSource");
        } else {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("dataSource");
        }
    }

    public List<BonusShortModel> getBonusByOrgId(Long orgId, Integer lastValidity, Long lastId, Integer limit){
        List<BonusShortModel> result = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();

            String query = "SELECT " +
                    "b.id, type.name, b.valid_from, b.valid_to, b.validity " +
                    "FROM org_bonus b " +
                    "JOIN org_bonus_type type " +
                    "ON type.id = b.org_bonus_type_id " +
                    "WHERE (b.validity > " + lastValidity + " OR b.validity = " + lastValidity + " AND b.id > " + lastId + ") AND b.status = true AND b.organization_id = " + orgId +
                   " ORDER BY b.validity ASC, b.id ASC LIMIT " + limit;

            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                BonusShortModel dto = new BonusShortModel();
                dto.setId(resultSet.getLong("id"));
                dto.setOrgBonusType(resultSet.getString("name"));
                dto.setValidFrom(resultSet.getDate("valid_from"));
                dto.setValidTo(resultSet.getDate("valid_to"));
                dto.setValidity(resultSet.getInt("validity"));
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

    public BonusValueModel getBonusAndValueByOrgIdAndTypeId(Long orgId, Long typeId){
        BonusValueModel result = new BonusValueModel();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();

            String query = "SELECT" +
                    "                    b.id, o.name, b_value.value, b_value.max, b_value.min" +
                    "                    FROM org_bonus b" +
                    "                    JOIN organization o ON o.id = b.organization_id"  +
                    "                    JOIN org_bonus_type type ON type.id = b.org_bonus_type_id" +
                    "                    JOIN org_bonus_value b_value ON b.id = b_value.id" +
                    "                    WHERE b.organization_id = "+ orgId +" and type.id =" + typeId;

            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                result.setBonusId(resultSet.getLong("id"));
                result.setName(resultSet.getString("name"));
                result.setMin(resultSet.getInt("min"));
                result.setMax(resultSet.getInt("max"));
                result.setValue(resultSet.getInt("value"));
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


