package com.primaryDI.Beans;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Objects;

public class AddressTypes implements UserType, ParameterizedType {
	private static String LIST_TYPE = "LIST";
	private static int[] SQL_TYPES = new int[] { Types.JAVA_OBJECT };
	private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static TypeReference LIST_TYPE_REF = new TypeReference<List<?>>() {
	};

	private static JavaType valueType = null;
	private static Class<?> classType = null;

	@Override
	public void setParameterValues(Properties parameters) {
		// TODO Auto-generated method stub
		String type = parameters.getProperty("type");
		if (type.equals(LIST_TYPE)) {
			if (parameters.getProperty("element") != null) {
				try {
					valueType = OBJECT_MAPPER.getTypeFactory().constructCollectionLikeType(List.class,
							Class.forName(parameters.getProperty("element")));

				} catch (final ClassNotFoundException ex) {
					throw new IllegalArgumentException("Type" + type + " is not a valid Type");
				}
			} else {
				valueType = OBJECT_MAPPER.getTypeFactory().constructType(LIST_TYPE_REF);
			}
		}
		classType = List.class;

	}

	@Override
	public int[] sqlTypes() {
		// TODO Auto-generated method stub
		return SQL_TYPES;
	}

	@Override
	public Class returnedClass() {
		// TODO Auto-generated method stub
		return classType;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		// TODO Auto-generated method stub
		return Objects.equal(x, y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		// TODO Auto-generated method stub
		return Objects.hashCode(x);
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		String value = rs.getString(names[0]);
		Object result = null;
		if (valueType == null) {
			throw new HibernateException("Value Type is not set");
		}
		if (valueType != null && !valueType.equals("")) {
			try {
				result = OBJECT_MAPPER.readValue(value, valueType);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		final StringWriter sw = new StringWriter();
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
		}
		try {
			OBJECT_MAPPER.writeValue(sw, value);
			st.setObject(index, sw.toString(), Types.OTHER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		// TODO Auto-generated method stub
		if (value == null) {
			return null;
		} else if (valueType.isCollectionLikeType()) {

			Object newValue;
			try {
				newValue = value.getClass().newInstance();
				Collection newValueCollection = (Collection) newValue;
				newValueCollection.addAll((Collection) value);
				return newValueCollection;
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				throw new HibernateException("Failed to deep copy the collection-like value object" + e.getMessage());
			}

		}
		return value;
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		// TODO Auto-generated method stub
		return (Serializable) deepCopy(value);
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		// TODO Auto-generated method stub
		return deepCopy(cached);
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		// TODO Auto-generated method stub
		return deepCopy(original);
	}

}
