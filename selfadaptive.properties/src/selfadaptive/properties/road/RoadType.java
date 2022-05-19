package selfadaptive.properties.road;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.PropertyKnowledge;
import sua.autonomouscar.interfaces.ERoadType;

public class RoadType extends PropertyKnowledge{

	public RoadType(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		
		addInterface(this.getClass().getName());
	}
	
	public void setRoadType(ERoadType roadType) {
		modifyKnowledge("roadtype", roadType);
	}
	
	public ERoadType getRoadType() {
		return (ERoadType) props.get("roadtype");
	}

}
