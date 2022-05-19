package selfadaptive.properties.system;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.AvailablePropertyCommon;
import sua.autonomouscar.interfaces.ESensorLocation;

public class LineSensorAvailable extends AvailablePropertyCommon{

	public LineSensorAvailable(BundleContext ctx, ESensorLocation location) {
		super(ctx);
		// TODO Auto-generated constructor stub
		
		props.put("location", location);
		addInterface(this.getClass().getName());
		
	}
	
}
