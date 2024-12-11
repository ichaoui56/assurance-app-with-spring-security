import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class DatabaseTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");
        EntityManagerFactory emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.getTransaction().commit();
            System.out.println("Database operation successful!");
            System.out.println("Tables creation successful!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
