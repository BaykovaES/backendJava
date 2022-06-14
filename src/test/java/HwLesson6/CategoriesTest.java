package HwLesson6;


import HWLesson5.RetrofitUtils;
import HWLesson5.api.ProductService;
import HWLesson5.dto.Product;
import db.model.Categories;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CategoriesTest {



    @Test
    void CategoryTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        db.dao.CategoriesMapper categoriesMapper = sqlSession.getMapper(db.dao.CategoriesMapper.class);
        db.model.CategoriesExample categories = new db.model.CategoriesExample();

        categories.getOrderByClause();
        List<db.model.Categories> list = categoriesMapper.selectByExample(categories);
        System.out.println(categoriesMapper.countByExample(categories));

        db.model.Categories categories1 = new db.model.Categories();
        categories1.setTitle("Material");
        categoriesMapper.deleteByPrimaryKey(categories1.getId());
        sqlSession.commit();

    }
}





