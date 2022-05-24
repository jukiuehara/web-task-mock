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
      public List<Product> search2(String key){
    	  try (Connection con = DbUtil.getConnection()) {
    		  ProductDao pd = new ProductDao(con);
    		  return pd.fintdByProductKey2(key);
    	  } catch (Exception e) {
    		  e.printStackTrace();
    	  }
    	  return null;
      }

}