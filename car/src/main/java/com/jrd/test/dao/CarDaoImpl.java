package com.jrd.test.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.jrd.test.entities.Car;
import com.jrd.test.entities.CarAttribute;

public class CarDaoImpl implements CarDao {

	@PersistenceContext
	EntityManager entityManager;

	public Car getCarDetails(Long id) {
		return entityManager.find(Car.class, id);
	}

	@Transactional
	public void addCarDetails(Car car) {
		if(car.getAttributes() != null) {
			for(CarAttribute carAttribute : car.getAttributes()) {
				carAttribute.setCar(car);
			}
		}
		entityManager.persist(car);
	}

	@Transactional
	public Car updateCarDetails(Car car) {
		if(car.getAttributes() != null) {
			for(CarAttribute carAttribute : car.getAttributes()) {
				carAttribute.setCar(car);
			}
		}
		return entityManager.merge(car);
	}

	@Transactional
	public void deleteCarDetails(Long id) {
		entityManager.remove(getCarDetails(id));
	}	

}
