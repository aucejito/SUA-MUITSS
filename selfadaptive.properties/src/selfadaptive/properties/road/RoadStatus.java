package selfadaptive.properties.road;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.PropertyKnowledge;
import sua.autonomouscar.interfaces.ERoadStatus;

public class RoadStatus extends PropertyKnowledge{

	public RoadStatus(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		addInterface(this.getClass().getName());
	}
	
	public void setRoadStatus(ERoadStatus roadStatus) {
		modifyKnowledge("roadstatus", roadStatus);
	}
	
	public ERoadStatus getRoadStatus() {
		return (ERoadStatus) props.get("roadstatus");
	}
}
