package com.mytaxi.service.driver;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.QDriverDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.querydsl.core.BooleanBuilder;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;
    
    private final CarRepository carRepository;


    public DefaultDriverService(final DriverRepository driverRepository,final CarRepository carRepository)
    {
        this.driverRepository = driverRepository;
        this.carRepository=carRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }

    /**
     * Select Car for driver .
     *
     * @param onlineStatus
     */
    @Override
    @Transactional
    public synchronized DriverDO selectCar(Long driverId,Long carId) throws CarAlreadyInUseException
    {
    	//retrieve the driver from DB.
    	DriverDO driverDODB = driverRepository.findOne(driverId);
    	//check if the selected car already assigned.
    	findCarChecked(carId);
    	//retrieve the car from DB.
    	CarDO carDO = carRepository.findOne(carId);
    	//update the driver with new car
    	driverDODB.setCarDO(carDO);
    	//save in DB
    	driverRepository.save(driverDODB);
        return driverDODB;
    }
    
    
    /**
     * unassigned car for driver.
     *
     * @param driverId
     */
    @Override
    @Transactional
    public DriverDO deselectCar(Long driverId)
    {
    	//retrieve driver from DB
    	DriverDO driverDODB = driverRepository.findOne(driverId);
    	//set car object with null
    	driverDODB.setCarDO(null);
    	//update the driver in DB
    	driverRepository.save(driverDODB);
        return driverDODB;
    }
    
    /**
     * Check Driver Existence in DB.
     * @return DriverDO
     * @param driverId
     */
    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = driverRepository.findOne(driverId);
        if (driverDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + driverId);
        }
        return driverDO;
    }
    /**
     * Check if Car is Already selected by driver before in DB.
     * @return DriverDO
     * @param driverId
     */
    private DriverDO findCarChecked(Long carId) throws CarAlreadyInUseException
    {
        DriverDO driverDO = driverRepository.findByCarDO_Id(carId);
        if (driverDO != null)
        {
            throw new CarAlreadyInUseException("Car Already In Use With Id: " + driverDO.getCarDO().getId());
        }
        return driverDO;
    }

    
    /**
     * Dynamic Search for car characteristics.
     *
     * @param seatCount
     * @param convertiable
     * @param engineType
     * @param licensePlate
     */
    @SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<DriverDO> search(String seatCount, Boolean convertiable, EngineType engineType, String licensePlate) 
    {
		QDriverDO qDiverDO = QDriverDO.driverDO;
		BooleanBuilder where = new BooleanBuilder();
		if(seatCount != null)
		{
			where.and(qDiverDO.carDO.seatCount.eq(seatCount));
		}
		if(convertiable != null)
		{
			where.and(qDiverDO.carDO.convertible.eq(convertiable));
		}
		if(engineType != null)
		{
			where.and(qDiverDO.carDO.engineType.eq(engineType));
		}
		if(licensePlate != null)
		{
			where.and( qDiverDO.carDO.licensePlate.eq(licensePlate));
		}

		return (List<DriverDO>) driverRepository.findAll(where);
	}

    /**
     * Check Authentication By user name 
     * @return UserDetails
     * @param username
     * @throws UsernameNotFoundException
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DriverDO doDriverDO=driverRepository.findByUsername(username);
		if(doDriverDO==null)
			throw new UsernameNotFoundException("Username doesn't exists.");
		UserDetails detailsService=DriverMapper.makeDriverDTO(doDriverDO);
		return detailsService;
	}


	@Override
	public DriverDO getUsername(String username) 
	{
		DriverDO doDriverDO=driverRepository.findByUsername(username);
		if(doDriverDO==null)
			throw new UsernameNotFoundException("Username doesn't exists.");
		
		return doDriverDO;
	}
	



}
