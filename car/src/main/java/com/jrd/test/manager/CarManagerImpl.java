package com.jrd.test.manager;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.jrd.test.dao.CarDao;
import com.jrd.test.entities.Car;

public class CarManagerImpl implements CarManager {

	CarDao dao;
	CacheManager cacheManager;
	
	@Cacheable(value="car_details", key="#id")
	public Car getCarDetails(String id) {
		System.out.println("Getting data for: " + id);
		Car car = dao.getCarDetails(Long.parseLong(id));
		return car;
	}

	public void addCarDetails(Car car) {
		dao.addCarDetails(car);
		Cache cache = cacheManager.getCache("car_details");
		ValueWrapper value = cache.get(car.getId().toString());
		if(value != null) {
			cache.put(car.getId().toString(), car);
		}
	}

	@CachePut(value="car_details", key="#car.id.toString()")
	public Car updateCarDetails(Car car) {
		car = dao.updateCarDetails(car);
		return car;
	}

	@CacheEvict(value="car_details", key="#id")
	public void deleteCarDetails(String id) {
		dao.deleteCarDetails(Long.parseLong(id));
	}
	
	public CarDao getDao() {
		return dao;
	}

	public void setDao(CarDao dao) {
		this.dao = dao;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
}
