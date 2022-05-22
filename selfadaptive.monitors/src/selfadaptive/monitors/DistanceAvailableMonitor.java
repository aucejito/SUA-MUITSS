package selfadaptive.monitors;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.system.DistanceSensorAvailable;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.ESensorLocation;

public class DistanceAvailableMonitor {
	protected BundleContext context;
	protected ESensorLocation sensorLocation;

	public DistanceAvailableMonitor(BundleContext ctx, ESensorLocation sensorLocation) {
		context = ctx;
		this.sensorLocation = sensorLocation;
	}

	public void modifyDistanceAvailable(Boolean newValue) {
		DistanceSensorAvailable distanceSensorAvailable = OSGiUtils.getService(context, DistanceSensorAvailable.class,
				String.format("(%s=%s)", "location", this.sensorLocation));
		Boolean currentAvailability = distanceSensorAvailable.isAvailable();
		if (currentAvailability != newValue) {
			distanceSensorAvailable.setAvailable(newValue);
			System.out.println(
					"[Monitor - DistanceAvailableMonitor] ----> DistanceSensorAvailable changed to " + newValue);
		}
	}
}
