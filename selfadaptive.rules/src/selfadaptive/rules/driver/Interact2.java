package selfadaptive.rules.driver;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.properties.driver.DriverSeatOcuppied;
import selfadaptive.properties.driver.HandsOnTheWheel;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IL3_CityChauffer;
import sua.autonomouscar.driving.interfaces.IL3_HighwayChauffer;
import sua.autonomouscar.driving.interfaces.IL3_TrafficJamChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.driving.DrivingService;
import sua.autonomouscar.infrastructure.interaction.HapticVibration;
import sua.autonomouscar.infrastructure.interaction.NotificationService;

public class Interact2 implements ServiceListener{
	BundleContext context;
	IDrivingService currentDrivingService;
	HandsOnTheWheel handsOnTheWheel;
	DriverSeatOcuppied driverSeat;
	NotificationService notificationService;
	
	public Interact2(BundleContext context) {
		super();
		this.context = context;
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class,
				String.format("(%s=%s)", DrivingService.ACTIVE, true));
		handsOnTheWheel = OSGiUtils.getService(context, HandsOnTheWheel.class);
		driverSeat = OSGiUtils.getService(context, DriverSeatOcuppied.class);
		
		
		if (currentDrivingService instanceof IL3_CityChauffer || currentDrivingService instanceof IL3_HighwayChauffer
				|| currentDrivingService instanceof IL3_TrafficJamChauffer) {
			notificationService = OSGiUtils.getService(context, NotificationService.class);
			HapticVibration steeringWheelVibration = new HapticVibration(context, "SteeringWheel");
			if(handsOnTheWheel.getHandsOnTheWheel()) {
				//notificationService.addInteractionMechanism("SteeringWheel");
				//HapticVibration steeringWheelVibration = OSGiUtils.getService(context, HapticVibration.class,"id=SteeringWheel_HapticVibration");
				steeringWheelVibration.registerThing();
				notificationService.addInteractionMechanism(steeringWheelVibration.getId());
			}else {
				notificationService.removeInteractionMechanism("SteeringWheel");
				steeringWheelVibration.unregisterThing();
			}
			if(driverSeat.getDriverSeatOccupied()) {
				notificationService.addInteractionMechanism("");
			}
			
		}
	}
}
