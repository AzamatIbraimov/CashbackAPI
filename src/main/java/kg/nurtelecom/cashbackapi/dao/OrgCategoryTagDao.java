package kg.nurtelecom.cashbackapi.dao;

import kg.nurtelecom.cashbackapi.model.OrgCategoryTagModel;
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
public class OrgCategoryTagDao {
    private final DataSource dataSource;

    @Autowired
    public OrgCategoryTagDao(ApplicationContext applicationContext, DataSource dataSource) {
        this.dataSource = dataSource;
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory.containsBean("jdbcDataSource")) {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("jdbcDataSource");
        } else {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("dataSource");
        }
    }


    public List<OrgCategoryTagModel> getTagsByOrgCategoryId(Long orgCategoryId) {
        List<OrgCategoryTagModel> tagList = new ArrayList();
        Connection connection = null;
        Statement stmt = null;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            // QUERY
            String sql = "SELECT tags.id, tags.name FROM tag tags JOIN org_category_tag cat_tag ON cat_tag.tag_id = tags.id" +
             " JOIN org_category category ON category.id = cat_tag.org_category_id" +
             " WHERE category.id = " + orgCategoryId;
            ResultSet resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                do {
                    OrgCategoryTagModel tagModel = new OrgCategoryTagModel();
                    tagModel.setId(resultSet.getLong("id"));
                    tagModel.setName(resultSet.getString("name"));
                    tagList.add(tagModel);
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
                se.printStackTrace();
                System.out.println(se);
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return tagList;
    }




}
