package com.lac.xml.tools;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.lac.po.ClassRoom;
import com.lac.po.Student;

public class JaxbTest {

	@Test
	public void test01() {
		try {
			JAXBContext context = JAXBContext.newInstance(Student.class);
			Marshaller mar = context.createMarshaller();
			Student s = new Student(1, "dp", 12, new ClassRoom(1, "class1",
					"grade1"));
			mar.marshal(s, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test02() {
		try {
			JAXBContext context = JAXBContext.newInstance(Student.class);
			Unmarshaller unmar = context.createUnmarshaller();
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>12</age><classRoom><grade>grade1</grade><id>1</id><name>class1</name></classRoom><id>1</id><name>dp</name></student>";
			Student s = (Student) unmar.unmarshal(new StringReader(xml));
			System.out.format("this student name is %s, age is %d,student No is %d, ClassRoom is %s", s.getName(),s.getAge(),s.getId(),s.getClassRoom().getName());
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
