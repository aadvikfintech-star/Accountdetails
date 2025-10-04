package com.cooperative.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooperative.entity.SystemConfiguration;

@Service
public interface CommonService<T> {

	public T findById(Class<T> className, Long id);

	public List<Object> findAllForMaster(Class<T> className);

	public Boolean saveOrUpdate(T object) throws Exception;

	public Boolean delete(T object);

	public List<Object> fetchByNamedQuery(String query,String parameter);

	public Object fetchObjectByNamedQuery(String query,String parameter);

	public Boolean merge(Object object);

	public List<Object> findAll(Class<T> className);

	public List<Object> fetchByNativeQuery(String query);

	public List<SystemConfiguration> findByConfigType(String string);

}