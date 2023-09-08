package hiber.dao;

import hiber.config.AppConfig;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }



   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override


   public List<User> getUserByModelAndSeries(String model, int series) {
      String hQL = "from User user where user.empCar.model =: model and user.empCar.series =: series";
      TypedQuery<User> typedQuery
              = sessionFactory.getCurrentSession().createQuery(hQL);
      typedQuery.setParameter("model", model).setParameter("series", series);
      return typedQuery.getResultList();
   }

}
