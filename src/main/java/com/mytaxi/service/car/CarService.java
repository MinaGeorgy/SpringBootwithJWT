package com.mytaxi.service.car;

import java.util.List;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
/**
 * 
 * @author mina.georgy
 *
 */
public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDo) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    CarDO findByPlate(String licensePlate) throws EntityNotFoundException;

    List<CarDO> find(EngineType engineType);

}
