package com.lac.spring.po;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Spittle {

	private Long id;
	
	@NotNull(message="cannot be null")
	@Size(min=5, max=16,message="{message.size}")
	private String message;
	
	@NotNull(message="time cannot be null")
	private Date time;
	@NotNull
	private String latitude;
	@NotNull
	private String longitude;

	public Spittle(){}
	
	public Spittle(String message, Date time) {
		this(message, time, null, null);
	}

	public Spittle(String message, Date time, String longitude, String latitude) {
		this.message = message;
		this.time = time;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Spittle(long id, String message, Date time, String longitude, String latitude) {
		this.id = id;
		this.message = message;
		this.time = time;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Date getTime() {
		return time;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
