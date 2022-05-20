package selfadaptive.rules.ADS;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;

import selfadaptive.properties.road.RoadStatus;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IL3_HighwayChauffer;
import sua.autonomouscar.driving.l3.trafficjamchauffer.L3_TrafficJamChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.driving.DrivingService;
import sua.autonomouscar.interfaces.ERoadStatus;

public class L3_HWCtoL3_TJC {

	BundleContext context;
	IDrivingService currentDrivingService;
	RoadStatus roadStatus;
	
	public L3_HWCtoL3_TJC(BundleContext context) {
		super();
		this.context = context;
	}

	public void serviceChanged(ServiceEvent event) {
		// TODO Auto-generated method stub
		//Get services to be able to evaluate conditions (properties)
		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class, String.format("(%s=%s)", DrivingService.ACTIVE, true));
		roadStatus = OSGiUtils.getService(context, RoadStatus.class, "roadstatus");
		
		//Evaluate conditions
		// currentDrivingService is L3
		if (currentDrivingService instanceof IL3_HighwayChauffer
				&& roadStatus.getRoadStatus() == ERoadStatus.JAM) {
			currentDrivingService.stopDriving();
			
			//Check necessary sensors
			
			//Perform the action
			L3_TrafficJamChauffer newDrivingService = new L3_TrafficJamChauffer(context, "l3_trafficjamchauffer");
			newDrivingService.registerThing();
			newDrivingService.startDriving();
			
		}
		
	}
}
