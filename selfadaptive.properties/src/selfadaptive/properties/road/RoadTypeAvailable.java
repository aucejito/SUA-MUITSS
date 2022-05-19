package selfadaptive.properties.road;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.AvailablePropertyCommon;

public class RoadTypeAvailable extends AvailablePropertyCommon {

	public RoadTypeAvailable(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		addInterface(this.getClass().getName());
	}

}
