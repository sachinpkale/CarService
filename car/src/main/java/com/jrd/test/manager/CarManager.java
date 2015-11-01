package com.jrd.test.manager;

import com.jrd.test.entities.Car;

public interface CarManager {

	Car getCarDetails(String id);

	void addCarDetails(Car car);

	Car updateCarDetails(Car car);

	void deleteCarDetails(String id);

}
