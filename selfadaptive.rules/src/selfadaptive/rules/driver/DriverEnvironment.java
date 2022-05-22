package selfadaptive.rules.driver;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.properties.driver.DriverSeatOcuppied;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IL3_DrivingService;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.driving.DrivingService;
import sua.autonomouscar.infrastructure.interaction.HapticVibration;
import sua.autonomouscar.infrastructure.interaction.NotificationService;
import sua.autonomouscar.infrastructure.interaction.VisualIcon;
import sua.autonomouscar.infrastructure.interaction.VisualText;

public class DriverEnvironment implements ServiceListener {
	BundleContext context;
	IDrivingService currentDrivingService;
	DriverSeatOcuppied driverSeat;
	NotificationService notificationService;

	public DriverEnvironment(BundleContext context) {
		super();
		this.context = context;
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class,
				String.format("(%s=%s)", DrivingService.ACTIVE, true));
		driverSeat = OSGiUtils.getService(context, DriverSeatOcuppied.class);

		if (currentDrivingService instanceof IL3_DrivingService) {

			notificationService = OSGiUtils.getService(context, NotificationService.class);
			HapticVibration driverSeatVibration = OSGiUtils.getService(context, HapticVibration.class,
					"(id=DriverSeat_HapticVibration)");
			VisualText visualText = OSGiUtils.getService(context, VisualText.class, "(id=DriverDisplay_VisualText)");
			VisualIcon visualIcon = OSGiUtils.getService(context, VisualIcon.class, "(id=DriverDisplay_VisualIcon)");

			if (driverSeat.getDriverSeatOccupied()) {
				System.out.println("[Rule - DriverEnvironment] ----> Driver Environment changed to active");
				notificationService.addInteractionMechanism(driverSeatVibration.getId());
				notificationService.addInteractionMechanism(visualIcon.getId());
				notificationService.addInteractionMechanism(visualText.getId());

			} else {
				System.out.println("[Rule - DriverEnvironment] ----> Driver Environment changed to disabled");
				notificationService.removeInteractionMechanism(driverSeatVibration.getId());
				notificationService.removeInteractionMechanism(visualIcon.getId());
				notificationService.removeInteractionMechanism(visualText.getId());

			}
		}
	}

}
