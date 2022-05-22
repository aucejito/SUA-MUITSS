package selfadaptive.properties.system;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.AvailablePropertyCommon;
import sua.autonomouscar.interfaces.ESensorLocation;

public class DistanceSensorAvailable extends AvailablePropertyCommon {

	public DistanceSensorAvailable(BundleContext ctx, ESensorLocation location) {
		super(ctx);
		props.put("location", location);
		addInterface(this.getClass().getName());
	}

	public void setAvailability(boolean available) {
		modifyKnowledge("available", available);
	}

	public boolean getAvailability() {
		return (boolean) props.get("available");
	}
}
