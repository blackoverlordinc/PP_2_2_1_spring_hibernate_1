package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Дуайт", "Шрутт", "user1@mail.ru", new Car("Cadillac Escalade", 4)));
      userService.add(new User("Нельсон", "Бигетти", "user2@mail.ru", new Car("Lamborgini Gallardo", 6)));
      userService.add(new User("Виталий", "Бредун", "user3@mail.ru", new Car("Rolls-Royse Calinnan", 8)));
      userService.add(new User("Александр", "Масляков", "user4@mail.ru", new Car("Zhiguli Semerka", 10)));




      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      List<User> users1 = userService.getUserByModelAndSeries("Rolls-Royse Calinnan", 8);
      System.out.println("-------------------------------------------------------------------");
      System.out.println("Юзер с машиной: ");
      for(User user : users1) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
