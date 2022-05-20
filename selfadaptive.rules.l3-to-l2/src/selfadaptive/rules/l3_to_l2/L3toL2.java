package selfadaptive.rules.l3_to_l2;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.l2.acc.L2_AdaptiveCruiseControl;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.driving.DrivingService;
import sua.autonomouscar.infrastructure.driving.L3_DrivingService;

public class L3toL2 implements ServiceListener {
	
	BundleContext context;
	IDrivingService currentDrivingService;
	
	public L3toL2(BundleContext context) {
		this.context = context;
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		// TODO Auto-generated method stub
		//Get services to be able to evaluate conditions (properties)
		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class, String.format("(%s=%s)", DrivingService.ACTIVE, true));
		
		//Evaluate conditions
		LineSensorHealthStatus leftLine
		// currentDrivingService is L3
		if (currentDrivingService instanceof L3_DrivingService
				//Roadtype == ERoadType.Standard || roadtype == offroad
				) {
			currentDrivingService.stopDriving(); //yo pondría unregister que ya hará el stop pertinente
			
			//Check necessary sensors
			
			//Perform the action
			L2_AdaptiveCruiseControl newDrivingService = new L2_AdaptiveCruiseControl(context, "L2_AdaptiveCruiseControl");
			newDrivingService.registerThing();
			
		}
		
	}
	
}
