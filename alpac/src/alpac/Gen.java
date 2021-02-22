package alpac;


import java.util.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaExport.Action;
import org.hibernate.tool.schema.TargetType;


//https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/A-version-5-Hibernate-SchemaExport-example-with-the-ServiceRegistry-and-Metadata

public class Gen {

  public static void main(String[] args) {
    Map<String, String> settings = new HashMap<>();
    settings.put("connection.driver_class", 
                 "com.mysql.jdbc.Driver");
        
    settings.put("dialect", 
                 "org.hibernate.dialect.MySQLDialect");
    settings.put("hibernate.connection.url", 
                 "jdbc:mysql://localhost:3306/trade");
        
    settings.put("hibernate.connection.username", "root");
    settings.put("hibernate.connection.password", "rootpwd");
    settings.put("hibernate.show_sql", "true");
    settings.put("hibernate.format_sql", "true");

    ServiceRegistry serviceRegistry = 
    new StandardServiceRegistryBuilder().applySettings(settings).build();

    MetadataSources metadata = 
      new MetadataSources(serviceRegistry);
    metadata.addAnnotatedClass(OrderStatus.class);
	//metadata.addAnnotatedClass(Quote.class);
	//metadata.addAnnotatedClass(MktOrder.class);
	//metadata.addAnnotatedClass(Account.class);
	//metadata.addAnnotatedClass(Position.class);
	//metadata.addAnnotatedClass(Trade.class);

    

    EnumSet<TargetType> enumSet = EnumSet.of(TargetType.DATABASE);
    SchemaExport schemaExport = new SchemaExport();
    schemaExport.setHaltOnError(true);
    schemaExport.setFormat(true);
    schemaExport.setDelimiter(";");
    
    schemaExport.execute(enumSet, Action.BOTH, metadata.buildMetadata());
    
    System.out.println("Did It work..");
        
  }
}