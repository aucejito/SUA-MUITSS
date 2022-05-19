package selfadaptive.properties.system;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.PropertyKnowledge;

public class ActiveDrivingService extends PropertyKnowledge{

	public ActiveDrivingService(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		addInterface(this.getClass().getName());
	}
	
	public void setActiveDrivingService(String drivingService) {
		modifyKnowledge("drivingservice", drivingService);
	}
	
	public String getActiveDrivingService() {
		return (String) props.get("drivingservice");
	}

}
