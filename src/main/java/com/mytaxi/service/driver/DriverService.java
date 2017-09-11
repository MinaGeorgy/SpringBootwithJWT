package com.mytaxi.service.driver;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface DriverService extends UserDetailsService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;
    
    DriverDO selectCar(Long driverId,Long carId) throws CarAlreadyInUseException;

    List<DriverDO> find(OnlineStatus onlineStatus);
    
    DriverDO deselectCar(Long driverId);
    
    List<DriverDO> search(String seatCount, Boolean convertiable, EngineType engineType, String licensePlate);
    
    DriverDO getUsername(String username);

}
