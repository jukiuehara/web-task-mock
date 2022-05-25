package service;

import java.sql.Connection;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {

      public List<Product> search(String key){
          try (Connection con = DbUtil.getConnection()) {
        	  ProductDao pd = new ProductDao(con);
              return pd.fintdByProductKey(key);
          } catch (Exception e) {
              e.printStackTrace();
          }
          return null;
}
      public void insert(int pid,int cid,String name, int price,String description) {
  		Product p = new Product();
  		Connection con = DbUtil.getConnection();
  		ProductDao pd = new ProductDao(con);
  		
		p.setProduct_id(pid);
		p.setCategory_id(cid);
		p.setName(name);
		p.setPrice(price);
		p.setDescription(description);
		
		pd.insert(p);
      }
      


}