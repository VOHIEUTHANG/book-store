package hibernate;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import app.entity.Author;
import app.entity.Cart;
import app.entity.Category;
import app.entity.Comment;
import app.entity.DeliveryAddress;
import app.entity.Order;
import app.entity.OrderItem;
import app.entity.Product;
import app.entity.ProductImage;
import app.entity.Role;
import app.entity.User;

public class hibernateUtils {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
				settings.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=BookStore");
				settings.put(Environment.USER, "devostack");
				settings.put(Environment.PASS, "0123123123");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
				settings.put(Environment.SHOW_SQL, "false");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Author.class);
				configuration.addAnnotatedClass(Cart.class);
				configuration.addAnnotatedClass(Category.class);
				configuration.addAnnotatedClass(Comment.class);
				configuration.addAnnotatedClass(DeliveryAddress.class);
				configuration.addAnnotatedClass(Order.class);
				configuration.addAnnotatedClass(OrderItem.class);
				configuration.addAnnotatedClass(Product.class);
				configuration.addAnnotatedClass(ProductImage.class);
				configuration.addAnnotatedClass(Role.class);
				configuration.addAnnotatedClass(User.class);
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
