package com.chilternit.persistence;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.chilternit.model.Customer;

@Stateless
public class CustomerDao {

	@PersistenceContext
	private EntityManager em;

	public void persist(Customer customer) {
			em.persist(customer);
	}

	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Customer> getAllCustomers() {
		TypedQuery<Customer> query = em.createQuery(
				"SELECT c FROM Customer c", Customer.class);
		
		return query.getResultList();
	}

	public void persistOrMerge(Customer c) {
		if(c.getId() != null) {
			em.merge(c);
		} else {
			em.persist(c);
		}
		
	}

	public void remove(int id) {
		em.createQuery("DELETE FROM Customer c where id=" + id).executeUpdate();
	}

}
