package com.primaryDI.Beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@TypeDefs({ @TypeDef(name = "SecondSubClassType", typeClass = SecondSubClassType.class) })
public class SubClass implements Serializable {

	private String surName;
	private Long y;

	@Column(columnDefinition = "jsonb")
	@Type(type = "SecondSubClassType")
	private SecondSubClass secondSubClass;

	public SubClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubClass(String surName, Long y, SecondSubClass secondSubClass) {
		super();
		this.surName = surName;
		this.y = y;
		this.secondSubClass = secondSubClass;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Long getY() {
		return y;
	}

	public void setY(Long y) {
		this.y = y;
	}

	public SecondSubClass getSecondSubClass() {
		return secondSubClass;
	}

	public void setSecondSubClass(SecondSubClass secondSubClass) {
		this.secondSubClass = secondSubClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((secondSubClass == null) ? 0 : secondSubClass.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubClass other = (SubClass) obj;
		if (secondSubClass == null) {
			if (other.secondSubClass != null)
				return false;
		} else if (!secondSubClass.equals(other.secondSubClass))
			return false;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubClass [surName=" + surName + ", y=" + y + ", secondSubClass=" + secondSubClass + "]";
	}

}
