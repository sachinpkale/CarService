package com.jrd.test.dao;

import com.jrd.test.entities.Car;

public interface CarDao {

	Car getCarDetails(Long id);

	void addCarDetails(Car car);

	Car updateCarDetails(Car car);

	void deleteCarDetails(Long id);

}
