package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class ObjectStreamTest {
	
	public static void main(String args[]){
		Employee harry = new Employee("harry",50000,1989,10,1);
		Manager carl = new Manager("carl",100000,1979,1,1);
		Manager tony = new Manager("tony",120000,1973,12,12);
		carl.setSecretary(harry);
		tony.setSecretary(harry);
		
		Employee staff[] = new Employee[3];
		
		staff[0] = carl;
		staff[1] = harry;
		staff[2] = tony;
		
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/employee.dat"));
			oos.writeObject(staff);
			oos.close();
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/employee.dat"));
			Employee [] inStaff = (Employee[]) ois.readObject();
			ois.close();
			
			inStaff[1].raiseSalary(10);
			
			for(Employee e : inStaff){
				System.out.println(e);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}

class Employee implements Serializable {
	
	private String name;
	private double salary;
	private Date hireDay;

	
	public Employee(){}

	public Employee(String n,double s,int year,int month,int day){
		this.name = n;
		this.salary =s;
		GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
		this.hireDay = calendar.getTime();
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public Date getHireDay() {
		return hireDay;
	}


	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	};
	
	
	public void raiseSalary(double byPercent){
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public String toString(){
		return this.getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
	}
}

class Manager extends Employee {
	
	private Employee secretary;
	
	public Manager(String n,double s,int year,int month,int day) {
		super(n, s, year, month, day);
		secretary = null;
	}
	
	public void setSecretary(Employee e){
		this.secretary = e;
	}
	
	public String toString(){
		return super.toString() + "[secretary=" + secretary + "]";
	}
}