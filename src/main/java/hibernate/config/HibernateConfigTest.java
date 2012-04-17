package hibernate.config;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="config-spring-hibernate.xml")
@DirtiesContext
public class HibernateConfigTest {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void retrieveAccount() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Account a where a.id=:id").setInteger("id", 1);
		Account a = (Account) query.uniqueResult();
		Assert.assertEquals(a.getCashBalance(), 500.0, 0.01);
	}
	
}
