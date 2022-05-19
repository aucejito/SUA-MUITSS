package selfadaptive.properties.driver;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.PropertyKnowledge;

public class DriverSeatOcuppied extends PropertyKnowledge{

	public DriverSeatOcuppied(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		addInterface(this.getClass().getName());
	}
	
	public void setDriverSeatOcuppied(boolean occupied) {
		modifyKnowledge("driverseat", occupied);
	}

	public boolean getDriverSeatOccupied() {
		return (boolean) props.get("driverseat");
	}
	
}
