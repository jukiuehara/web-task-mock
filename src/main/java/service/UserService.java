package service;

import java.sql.Connection;

import dao.UserDao;
import entity.User;
import util.DbUtil;

public class UserService {

      public User login(String id,String pass){
          try (Connection con = DbUtil.getConnection()) {
        	  UserDao ud = new UserDao(con);
              return ud.fintdByProductIdPass(id,pass);
          } catch (Exception e) {
              e.printStackTrace();
          }
          return null;
}
}