package com.primaryDI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.primaryDI.Beans.SecondSubClass;
import com.primaryDI.Beans.SubClass;
import com.primaryDI.Beans.SuperClass;

@SpringBootApplication
public class PrimaryQualifierDiApplication {
	final static private Logger logger = LoggerFactory.getLogger(PrimaryQualifierDiApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(PrimaryQualifierDiApplication.class, args);

		SecondSubClass secondSubClass = new SecondSubClass();
		secondSubClass.setAge(10l);
		secondSubClass.setFirstName("Rocky");
		secondSubClass.setLastName("Bhai");

		SubClass subClass = new SubClass();
		subClass.setSurName("Patel");
		subClass.setY(20l);
		subClass.setSecondSubClass(secondSubClass);

		SuperClass superClass = new SuperClass();
		superClass.setName("Patel");
		superClass.setX(10);
		superClass.setSubClass(subClass);
		// Serialize

		System.out.println(superClass);

		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			logger.info("*****************Above SuperClass******************");
			oos.writeObject(superClass);

			logger.info("******************Below SuperClass****************");
			oos.flush();
			oos.close();
			ByteArrayInputStream bias = new ByteArrayInputStream(bos.toByteArray());
			Object obj = new ObjectInputStream(bias).readObject();

			SuperClass superCls = (SuperClass) obj;
			logger.info("Class Info is :- " + superCls.getSubClass().getSecondSubClass().getFirstName());
		} catch (ClassNotFoundException | IOException ex) {
			throw new HibernateException(ex.getMessage());
		}

	}

}
