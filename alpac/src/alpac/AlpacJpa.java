package alpac;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;


public class AlpacJpa {
	
	public AlpacJpa() {			
		System.out.println("AlpacJpa() DeFault Constructor===");
	}
	
	public static Session getCurrentSession() {
		
		Map<String, String> settings = new HashMap<>();
		settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
		settings.put("dialect", "org.hibernate.dialect.MySQL8Dialect");

		settings.put("hibernate.connection.url", "jdbc:mysql://localhost/trade");
		settings.put("hibernate.connection.username", "root");
		settings.put("hibernate.connection.password", "rootpwd");
		settings.put("hibernate.connection.poolsize", "1");
		settings.put("hibernate.connection.autocommit", "true");
		settings.put("hibernate.current_session_context_class", "thread");
		settings.put("hibernate.show_sql", "true");
		settings.put("hibernate.format_sql", "true");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				                              .applySettings(settings)
				                              .build();

		MetadataSources metadataSources = new MetadataSources(serviceRegistry);
		metadataSources.addAnnotatedClass(QuoteOLD.class);
		metadataSources.addAnnotatedClass(MktOrder.class);
		metadataSources.addAnnotatedClass(Account.class);
		metadataSources.addAnnotatedClass(Position.class);
		metadataSources.addAnnotatedClass(Trade.class);
		metadataSources.addAnnotatedClass(OrderStatus.class);

		Metadata metadata = metadataSources.buildMetadata();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
		Session session = sessionFactory.getCurrentSession();
		return session;
	}
	
	public static Session beginTransaction() {
		Session hibernateSession = getCurrentSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public static void commitTransaction(Session s) {
		s.getTransaction().commit();
	}

	public void rollbackTransaction(Session s) {
		s.getTransaction().rollback();
	}

	public void closeSession(Session s) {
		s.close();
	}

	public static void updateCurrent(String inTblName) {
		
		Session sess = beginTransaction();
		EntityManager em = sess.getEntityManagerFactory().createEntityManager();
				
		StoredProcedureQuery query = em
			    .createStoredProcedureQuery("updCurrentRows")
			    .registerStoredProcedureParameter(
			        "tblName", String.class, ParameterMode.IN)
			    .setParameter("tblName", inTblName);
		
		query.execute();	
		System.out.println(inTblName+" updProc Complete***");
		sess.getTransaction().commit();
		
	}
}
