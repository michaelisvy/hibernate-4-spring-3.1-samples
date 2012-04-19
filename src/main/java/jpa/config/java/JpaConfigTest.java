package jpa.config.java;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JpaConfig.class)
@DirtiesContext
public class JpaConfigTest {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Test
	public void retrieveAccount() {
		Query query = entityManager.createQuery("from Account a where a.id=:id").setParameter("id", 1L);
		Account a = (Account) query.getSingleResult();
		Assert.assertEquals(a.getCashBalance(), 500.0, 0.01);
	}
	
	@Test @Transactional
	public void updateAccount() {
		Query query = entityManager.createQuery("from Account a where a.id=:id").setParameter("id", 1L);
		Account a = (Account) query.getSingleResult();
		a.setName("foo");
	}

}
