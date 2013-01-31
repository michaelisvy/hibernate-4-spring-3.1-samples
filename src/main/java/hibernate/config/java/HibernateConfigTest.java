package hibernate.config.java;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=HibernateConfig.class)
public class HibernateConfigTest {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void retrieveAccount() {
		Session session = sessionFactory.openSession(); // not part of a transaction, so we need to open a session manually
		Query query = session.createQuery("from Account a where a.id=:id").setInteger("id", 1);
		Account a = (Account) query.uniqueResult();
		session.close();
		Assert.assertEquals(a.getCashBalance(), 500.0, 0.01);
	}
	
	@Test @Transactional
	public void updateAccount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Account a where a.id=:id").setInteger("id", 1);
		Account a = (Account) query.uniqueResult();
		a.setName("foo");
	}
	
}
