package selfadaptive.rules.ADS;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.properties.road.RoadStatus;
import selfadaptive.properties.road.RoadType;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IL3_CityChauffer;
import sua.autonomouscar.driving.l3.trafficjamchauffer.L3_TrafficJamChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.driving.DrivingService;
import sua.autonomouscar.interfaces.ERoadStatus;
import sua.autonomouscar.interfaces.ERoadType;
//ADS_L3-6 Pt1/2
public class L3_CCtoL3_TJC implements ServiceListener{

	BundleContext context;
	IDrivingService currentDrivingService;
	RoadStatus roadStatus;
	RoadType roadType;
	
	public L3_CCtoL3_TJC(BundleContext context) {
		super();
		this.context = context;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class, String.format("(%s=%s)", DrivingService.ACTIVE, true));
		roadStatus = OSGiUtils.getService(context, RoadStatus.class, "roadstatus");
		roadType = OSGiUtils.getService(context, RoadType.class, "roadtype");
		
		if(currentDrivingService instanceof IL3_CityChauffer && 
				roadType.getRoadType() == ERoadType.HIGHWAY &&
				(roadStatus.getRoadStatus() == ERoadStatus.JAM || roadStatus.getRoadStatus() == ERoadStatus.COLLAPSED)) {
			currentDrivingService.stopDriving();
			
			L3_TrafficJamChauffer newDrivingService = new L3_TrafficJamChauffer(context, "l3_trafficjamchauffer");
			newDrivingService.registerThing();
			newDrivingService.startDriving();
		}
	}

}
