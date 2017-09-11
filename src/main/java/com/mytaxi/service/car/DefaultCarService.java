package com.mytaxi.service.car;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * @author mina.georgy
 * for Managed all car services .
 * <p/>
 */
@Service
public class DefaultCarService implements CarService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

    private final CarRepository carRepository;


    public DefaultCarService(final CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }


    /**
     * Selects a car by id.
     *
     * @param carId
     * @return CarDO
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    /**
     * Creates a new Car.
     *
     * @param CarDO
     * @return CarDO
     * @throws ConstraintsViolationException if a car already exists with the given licensePlate, ... .
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
        CarDO carDODB;
        try
        {
        	carDODB = carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return carDODB;
    }


    /**
     * Deletes an existing car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = findCarChecked(carId);
        carDO.setDeleted(true);
        carRepository.save(carDO);
    }
   

    /**
     * Find Car by plate number.
     * @return CarDO
     * @param licensePlate
     */
    @Override
    public CarDO findByPlate(String licensePlate)
    {
        return carRepository.findByLicensePlate(licensePlate);
    }


    /**
     * Check Car existence.
     * @return CarDO
     * @param licensePlate
     */
    private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
    	CarDO carDO = carRepository.findOne(carId);
        if (carDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + carId);
        }
        return carDO;
    }
    /**
     * Find all by engine type.
     * @return CarDO
     * @param licensePlate
     */
	@Override
	public List<CarDO> find(EngineType engineType) {
		
		return carRepository.findByEngineTypeAndDeleted(engineType,false);
	}

}
