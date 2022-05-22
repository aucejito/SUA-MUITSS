package selfadaptive.rules.driver;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.properties.driver.DriverSeatOcuppied;
import selfadaptive.properties.driver.HandsOnTheWheel;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IL3_DrivingService;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.driving.DrivingService;
import sua.autonomouscar.infrastructure.interaction.HapticVibration;
import sua.autonomouscar.infrastructure.interaction.NotificationService;

public class DriverHaptics implements ServiceListener {
	BundleContext context;
	IDrivingService currentDrivingService;
	HandsOnTheWheel handsOnTheWheel;
	DriverSeatOcuppied driverSeat;
	NotificationService notificationService;

	public DriverHaptics(BundleContext context) {
		super();
		this.context = context;
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class,
				String.format("(%s=%s)", DrivingService.ACTIVE, true));
		handsOnTheWheel = OSGiUtils.getService(context, HandsOnTheWheel.class);
		driverSeat = OSGiUtils.getService(context, DriverSeatOcuppied.class);

		if (currentDrivingService instanceof IL3_DrivingService) {
			notificationService = OSGiUtils.getService(context, NotificationService.class);

			HapticVibration steeringWheelVibration = OSGiUtils.getService(context, HapticVibration.class,
					"id=SteeringWheel_HapticVibration");
			HapticVibration driverSeatVibration = OSGiUtils.getService(context, HapticVibration.class,
					"id=DriverSeat_HapticVibration");

			if (handsOnTheWheel.getHandsOnTheWheel()) {
				notificationService.addInteractionMechanism(steeringWheelVibration.getId());
				System.out.println("[Rule - DriverHaptics] ----> Adding Steering Wheel Vibration");
			} else {
				notificationService.removeInteractionMechanism(steeringWheelVibration.getId());
				System.out.println("[Rule - DriverHaptics] ----> Removing Steering Wheel Vibration");
			}
			if (driverSeat.getDriverSeatOccupied()) {
				notificationService.addInteractionMechanism(driverSeatVibration.getId());
				System.out.println("[Rule - DriverHaptics] ----> Adding Driver Seat Vibration");
			} else {
				notificationService.removeInteractionMechanism(driverSeatVibration.getId());
				System.out.println("[Rule - DriverHaptics] ----> Removing Driver Seat Vibration");
			}

		}
	}
}
