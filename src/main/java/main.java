import java.sql.Connection;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class main {
	public static void main(String[] args) {
		Product p = new Product();
		Connection con = DbUtil.getConnection();
		ProductDao pd = new ProductDao(con);

		p.setProduct_id(10);
		p.setCategory_id(10);
		p.setName("juki");
		p.setPrice(10);
		p.setDescription("juki");
		pd.insert(p);

	}
}
