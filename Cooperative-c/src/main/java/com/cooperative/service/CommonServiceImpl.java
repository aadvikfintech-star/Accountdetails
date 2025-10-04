package com.cooperative.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cooperative.entity.SystemConfiguration;

@Repository
@Transactional
public class CommonServiceImpl<T> implements CommonService<T>{


    @PersistenceContext
	private EntityManager entityManager;

    public T findById(Class<T> className,Long id) {
		return entityManager.find(className, id);
	}

	public List<Object> findAllForMaster(Class<T> className) {
		List<Object> lstData =  entityManager.createQuery("SELECT t FROM " + className.getSimpleName() + " t").getResultList();
		return lstData;
	}
	
	public List<Object> findAll(Class<T> className) {
		List<Object> lstData =  entityManager.createQuery("SELECT t FROM " + className. getSimpleName() + " t ").getResultList();
		return lstData;
	}

	
	public Boolean saveOrUpdate(T object) throws Exception {
		try {
			entityManager.persist(object);
			return true;
		}catch (Exception e) {
			if(e.getMessage().contains("detached entity passed to persist")){
				entityManager.merge(object);
				return true;
			}
			throw e;
		}
	}
	
	public Boolean merge(Object object) {
		try {
			//Transaction tx  = null;
			//tx  = session.getCurrentSession().beginTransaction();
			//session.getCurrentSession().merge(object);
			//tx.commit();
			entityManager.merge(object);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			//tx.rollback();
			//session.getCurrentSession().close();
		}
		return false;
	}
	
	public Boolean delete(T object) {
		try {
			entityManager.remove(object);
			//session.getCurrentSession().delete(object);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Object> fetchByNamedQuery(String namedQuery,String parameter) {
		List<Object> lstObject = new ArrayList<Object>();
		try {
			Query query =  entityManager.createNamedQuery(namedQuery);
			
			if (parameter != null && !parameter.equals("")) {
				String[] indExpres = parameter.split("&");

				for (int i = 0; i < indExpres.length; i++) {
					String[] indParam = indExpres[i].split("=");
					
					if (indParam[2].toLowerCase().equals("string")) 
						query = query.setParameter(indParam[0], indParam[1]);
					else if (indParam[2].toLowerCase().equals("long")) 
						query = query.setParameter(indParam[0],	Long.parseLong(indParam[1]));
					else if (indParam[2].toLowerCase().equals("double")) 
						query = query.setParameter(indParam[0], Double.parseDouble(indParam[1]));
					else if (indParam[2].toLowerCase().equals("float")) 
						query = query.setParameter(indParam[0], Float.parseFloat(indParam[1]));
					else if (indParam[2].toLowerCase().equals("character")) 
						query = query.setParameter(indParam[0], (indParam[1]).charAt(0));
					else if (indParam[2].toLowerCase().equals("int") || indParam[2].toLowerCase().equals("integer")) 
						query = query.setParameter(indParam[0], Integer.parseInt((indParam[1])));
					else if (indParam[2].toLowerCase().equals("date")) 
						query = query.setParameter(indParam[0], new SimpleDateFormat("dd-MM-yyyy").parse(indParam[1]) );
				}
			}
			lstObject = query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lstObject;
	}
	
	public Object fetchObjectByNamedQuery(String namedQuery,String parameter) {
		Object object = null ; 
		try {
			Query query =  entityManager.createNamedQuery(namedQuery);
			if (parameter != null && !parameter.equals("")) {
				String[] indExpres = parameter.split("&");

				for (int i = 0; i < indExpres.length; i++) {
					String[] indParam = indExpres[i].split("=");
					
					if (indParam[2].toLowerCase().equals("string")) 
						query = query.setParameter(indParam[0], indParam[1]);
					else if (indParam[2].toLowerCase().equals("long")) 
						query = query.setParameter(indParam[0],	Long.parseLong(indParam[1]));
					else if (indParam[2].toLowerCase().equals("double")) 
						query = query.setParameter(indParam[0], Double.parseDouble(indParam[1]));
					else if (indParam[2].toLowerCase().equals("float")) 
						query = query.setParameter(indParam[0], Float.parseFloat(indParam[1]));
					else if (indParam[2].toLowerCase().equals("character")) 
						query = query.setParameter(indParam[0], (indParam[1]).charAt(0));
					else if (indParam[2].toLowerCase().equals("int") || indParam[2].toLowerCase().equals("integer")) 
						query = query.setParameter(indParam[0], Integer.parseInt((indParam[1])));
					else if (indParam[2].toLowerCase().equals("date")) 
						query = query.setParameter(indParam[0], new SimpleDateFormat("dd-MM-yyyy").parse(indParam[1]) );
				}
			}
			object = query.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public List<Object> fetchByNativeQuery(String query) {
		List<Object> lstObject = new ArrayList<Object>();
		try {
			Query queryToRun =  entityManager.createNativeQuery(query);
			lstObject = queryToRun.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lstObject;
	}

	public List<SystemConfiguration> findByConfigType(String sys_type) {
		List<SystemConfiguration> lstObject = new ArrayList<SystemConfiguration>();
		try {
			Query query =  entityManager.createNamedQuery("findByConfigType");
			query = query.setParameter("sysType", sys_type);
			lstObject = query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lstObject;
	}
}
