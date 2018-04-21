package bean;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.beans.BeanInfo;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.Statement;
import java.beans.XMLEncoder;
import java.util.ArrayList;

import javax.management.IntrospectionException;

public class DamageReport {

	public enum CarType{
		SEDAN,WAGON,SUV
	}
	
	private String rentalRecord;
	private CarType carType;
	private boolean removeMode;
	private ArrayList<Point2D> points = new ArrayList<Point2D>();
	
	private static final int MARK_SIZE = 5;

	public String getRentalRecord() {
		return rentalRecord;
	}

	public void setRentalRecord(String rentalRecord) {
		this.rentalRecord = rentalRecord;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public boolean isRemoveMode() {
		return removeMode;
	}

	public void setRemoveMode(boolean removeMode) {
		this.removeMode = removeMode;
	}
	
	public void click(Point2D p) {
		if(removeMode) {
			for(Point2D center : points){
				Ellipse2D circle = new Ellipse2D.Double(center.getX()-MARK_SIZE, center.getY()-MARK_SIZE,
						2*MARK_SIZE, 2*MARK_SIZE);
				if(circle.contains(p)) {
					points.remove(center);
					return;
				}
			}
		}else {
			points.add(p);
		}
	}
	
	public void drawDamage(Graphics2D g){
		g.setPaint(Color.RED);
		
		for(Point2D center : points) {
			Ellipse2D circle = new Ellipse2D.Double(center.getX()-MARK_SIZE, center.getY()-MARK_SIZE,
					2*MARK_SIZE, 2*MARK_SIZE);
			g.draw(circle);
		}
	}
	
	public void configureEncoder(XMLEncoder encoder) {
		encoder.setPersistenceDelegate(Point2D.Double.class, new DefaultPersistenceDelegate(new String[]{"X","Y"}));
		encoder.setPersistenceDelegate(DamageReport.class, new DefaultPersistenceDelegate(){
			protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder out) {
				super.initialize(type, oldInstance, newInstance, out);
				DamageReport r = (DamageReport)oldInstance;
				
				for(Point2D p : points) {
					out.writeStatement(new Statement(oldInstance,"click",new Object[]{p}));
				}
			}
		});
	}
	
	
	static {
		BeanInfo info;
		try {
				info = Introspector.getBeanInfo(DamageReport.class);
			for(PropertyDescriptor desc : info.getPropertyDescriptors()) {
				if(desc.getName().equals("removeMode")) {
					desc.setValue("transient", Boolean.TRUE);
				}
			}
		} catch (java.beans.IntrospectionException e) {
			e.printStackTrace();
		}
	}
	
}
