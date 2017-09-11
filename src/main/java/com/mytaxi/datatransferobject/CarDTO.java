package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainvalue.EngineType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
	@JsonIgnore
	private Long id;

	@NotNull(message = "license plate can not be null!")
	private String licensePlate;

	@NotNull(message = "seat count can not be null!")
	private String seatCount;

	private Boolean convertible;

	private String rating;

	private EngineType engineType;

	private CarDTO() {
	}

	private CarDTO(Long id,String seatCount, String licensePlate, Boolean convertible, String rating, EngineType engineType) {
		this.id=id;
		this.licensePlate = licensePlate;
		this.seatCount = seatCount;
		this.convertible = convertible;
		this.rating = rating;
		this.engineType = engineType;

	}

	public static CarDTOBuilder newBuilder() {
		return new CarDTOBuilder();
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public String getSeatCount() {
		return seatCount;
	}

	public Boolean getConvertible() {
		return convertible;
	}

	public String getRating() {
		return rating;
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public static class CarDTOBuilder {
		private Long id;
		private String licensePlate;
		private String seatCount;
		private Boolean convertible;
		private String rating;
		private EngineType engineType;


		public CarDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public CarDTOBuilder setLicensePlate(String licensePlate) {
			this.licensePlate = licensePlate;
			return this;
		}

		public CarDTOBuilder setConvertible(Boolean convertible) {
			this.convertible = convertible;
			return this;
		}
		public CarDTOBuilder setSeatCount(String seatCount) {
			this.seatCount = seatCount;
			return this;
		}

		public CarDTOBuilder setRating(String rating) {
			this.rating = rating;
			return this;
		}
		
		public CarDTOBuilder setEngineType(EngineType engineType) {
			this.engineType = engineType;
			return this;
		}

		public CarDTO createCarDTO() {
			return new CarDTO(id,seatCount,licensePlate,convertible,rating,engineType);
		}

	}
}
