package selfadaptive.rules.ADS;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.properties.road.RoadStatus;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IL3_TrafficJamChauffer;
import sua.autonomouscar.driving.l3.highwaychauffer.L3_HighwayChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.driving.DrivingService;
import sua.autonomouscar.interfaces.ERoadStatus;

//ADS_L3-4
public class L3_TJCtoL3_HWC implements ServiceListener{
	BundleContext context;
	IDrivingService currentDrivingService;
	RoadStatus roadStatus;
	
	public L3_TJCtoL3_HWC(BundleContext ctx) {
		super();
		this.context = ctx;
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class, String.format("(%s=%s)",
				DrivingService.ACTIVE, true));
		roadStatus = OSGiUtils.getService(context, RoadStatus.class, "roadstatus");
		
		if(currentDrivingService instanceof IL3_TrafficJamChauffer &&
				roadStatus.getRoadStatus() == ERoadStatus.FLUID) {
			currentDrivingService.stopDriving();
			
			//Falta comprobar los sensores ?
			
			L3_HighwayChauffer newDrivingService = new L3_HighwayChauffer(context, "l3_highwaychauffer");
			newDrivingService.registerThing();
			newDrivingService.startDriving();
		}
	}
	
	
	
	
}
