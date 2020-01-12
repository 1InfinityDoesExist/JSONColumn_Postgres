package com.primaryDI.Beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "SuperClass")
@Table(name = "super_class")
@EntityListeners(AuditingEntityListener.class)
@TypeDefs({ @TypeDef(name = "SubClassType", typeClass = SubClassType.class) })
public class SuperClass implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "x")
	private int x;

	@Column(name = "name")
	private String name;

	@Column(columnDefinition = "jsonb")
	@Type(type = "SubClassType")
	private SubClass subClass;

	public SuperClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SuperClass(int x, String name, SubClass subClass) {
		super();
		this.x = x;
		this.name = name;
		this.subClass = subClass;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SubClass getSubClass() {
		return subClass;
	}

	public void setSubClass(SubClass subClass) {
		this.subClass = subClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subClass == null) ? 0 : subClass.hashCode());
		result = prime * result + x;
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
		SuperClass other = (SuperClass) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subClass == null) {
			if (other.subClass != null)
				return false;
		} else if (!subClass.equals(other.subClass))
			return false;
		if (x != other.x)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SuperClass [x=" + x + ", name=" + name + ", subClass=" + subClass + "]";
	}

}
