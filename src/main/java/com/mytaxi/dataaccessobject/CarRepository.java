package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;

/**
 * @author mina.georgy
 * Database Access Object for Car table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long>
{

    CarDO findByLicensePlate(String licensePlate);
    
    List<CarDO> findByEngineTypeAndDeleted(EngineType engineType,Boolean isDeleted);
}
