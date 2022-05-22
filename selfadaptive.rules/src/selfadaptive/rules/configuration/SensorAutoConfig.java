package selfadaptive.rules.configuration;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.properties.system.DistanceSensorAvailable;
import selfadaptive.properties.system.LidarSensorAvailable;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IL2_DrivingService;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.driving.DrivingService;

public class SensorAutoConfig implements ServiceListener {

	private BundleContext context;
	private LidarSensorAvailable rearLidarSensorAvailable;
	private LidarSensorAvailable frontLidarSensorAvailable;
	private DistanceSensorAvailable rearDistanceSensorAvailable;
	private DistanceSensorAvailable frontDistanceSensorAvailable;
	private IDrivingService currentDrivingService;

	public SensorAutoConfig(BundleContext context) {
		// TODO Auto-generated constructor stub
		super();
		this.context = context;
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		rearDistanceSensorAvailable = OSGiUtils.getService(context, DistanceSensorAvailable.class, "(id=87)");
		frontDistanceSensorAvailable = OSGiUtils.getService(context, DistanceSensorAvailable.class, "(id=86)");

		rearLidarSensorAvailable = OSGiUtils.getService(context, LidarSensorAvailable.class, "(id=90)");
		frontLidarSensorAvailable = OSGiUtils.getService(context, LidarSensorAvailable.class, "(id=91)");

		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class,
				String.format("(%s=%s)", DrivingService.ACTIVE, true));

		if (rearDistanceSensorAvailable.getAvailability() && frontDistanceSensorAvailable.getAvailability()
				&& currentDrivingService instanceof IL2_DrivingService) {
			System.out.println("[Rule - SensorAutoConfig] ----> Change to DistanceSensor");

			((IL2_DrivingService) currentDrivingService).setFrontDistanceSensor("FrontDistanceSensor");
			((IL2_DrivingService) currentDrivingService).setRearDistanceSensor("RearDistanceSensor");
		} else {
			System.out.println("[Rule - SensorAutoConfig] ----> Change to LIDAR");

			((IL2_DrivingService) currentDrivingService).setFrontDistanceSensor("LIDAR-FrontDistanceSensor");
			((IL2_DrivingService) currentDrivingService).setRearDistanceSensor("LIDAR-RearDistanceSensor");
		}
	}

}
