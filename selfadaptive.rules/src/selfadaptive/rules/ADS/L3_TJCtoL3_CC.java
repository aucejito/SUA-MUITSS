package selfadaptive.rules.ADS;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.properties.road.RoadType;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IL3_TrafficJamChauffer;
import sua.autonomouscar.driving.l3.citychauffer.L3_CityChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.driving.DrivingService;
import sua.autonomouscar.interfaces.ERoadType;

//ADS_L3-5
public class L3_TJCtoL3_CC implements ServiceListener{

	BundleContext context;
	IDrivingService currentDrivingService;
	RoadType roadType;
	
	public L3_TJCtoL3_CC(BundleContext ctx) {
		super();
		this.context = ctx;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class, String.format("(%s=%s)", DrivingService.ACTIVE, true));
		roadType = OSGiUtils.getService(context, RoadType.class, "roadtype");
		
		if(currentDrivingService instanceof IL3_TrafficJamChauffer && 
				roadType.getRoadType() == ERoadType.CITY) {
			currentDrivingService.stopDriving();
			
			L3_CityChauffer newDrivingService = new L3_CityChauffer(context, "l3_citychauffer");
			newDrivingService.registerThing();
			newDrivingService.startDriving();
		}
	}
	
}
