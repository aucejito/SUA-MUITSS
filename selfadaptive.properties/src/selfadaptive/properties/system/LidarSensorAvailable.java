package selfadaptive.properties.system;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.AvailablePropertyCommon;

public class LidarSensorAvailable extends AvailablePropertyCommon {

	public LidarSensorAvailable(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		addInterface(this.getClass().getName());
	}

}
