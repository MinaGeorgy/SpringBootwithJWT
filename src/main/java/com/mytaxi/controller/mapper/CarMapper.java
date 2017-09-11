package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
/**
 * 
 * @author mina.georgy
 *
 */
public class CarMapper
{
    public static CarDO makeCarDO(CarDTO carDTO)
    {
    	if(carDTO==null)
    		return null;
        return new CarDO(carDTO.getSeatCount(), carDTO.getLicensePlate(),carDTO.getConvertible(),carDTO.getRating(),carDTO.getEngineType());
    }


    public static CarDTO makeCarDTO(CarDO carDO)
    {
    	if(carDO==null)
    		return null;
    	CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
            .setId(carDO.getId())
            .setSeatCount(carDO.getSeatCount())
            .setLicensePlate(carDO.getLicensePlate())
            .setConvertible(carDO.getConvertible())
            .setRating(carDO.getRating())
            .setEngineType(carDO.getEngineType());

        return carDTOBuilder.createCarDTO();
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> carDOs)
    {
        return carDOs.stream()
            .map(CarMapper::makeCarDTO)
            .collect(Collectors.toList());
    }
}
