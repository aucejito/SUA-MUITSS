package selfadaptive.properties.driver;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.AvailablePropertyCommon;

public class HumanSensorAvailable extends AvailablePropertyCommon  {

	public HumanSensorAvailable(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		
		addInterface(this.getClass().getName());
	}

}
