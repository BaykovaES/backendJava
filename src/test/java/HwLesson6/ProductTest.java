package HwLesson6;

import HWLesson5.dto.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductTest {
    @Test
    void ProductTest() throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();

    db.dao.ProductsMapper productMapper = sqlSession.getMapper(db.dao.ProductsMapper.class);
    db.model.ProductsExample products = new db.model.ProductsExample();

        products.createCriteria().andCategory_idEqualTo(1l);
        List<db.model.Products> list = productMapper.selectByExample(products);
        System.out.println(productMapper.countByExample(products));

        db.model.Products product1 = new db.model.Products();
                product1.setTitle("Cheese");
                product1.setCategory_id(1l);
                product1.setPrice(564);
        productMapper.deleteByPrimaryKey(product1.getId());
        sqlSession.commit();

}
}
