package selfadaptive.properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import selfadaptive.properties.driver.DriverSeatOcuppied;
import selfadaptive.properties.driver.FaceStatus;
import selfadaptive.properties.driver.HandsOnTheWheel;
import selfadaptive.properties.driver.HumanSensorAvailable;
import selfadaptive.properties.road.LateralSecurityDistance;
import selfadaptive.properties.road.LongSecurityDistance;
import selfadaptive.properties.road.ReferenceSpeed;
import selfadaptive.properties.road.RoadStatus;
import selfadaptive.properties.road.RoadStatusAvailable;
import selfadaptive.properties.road.RoadType;
import selfadaptive.properties.road.RoadTypeAvailable;
import selfadaptive.properties.system.ActiveDrivingService;
import selfadaptive.properties.system.DistanceSensorAvailable;
import selfadaptive.properties.system.LidarSensorAvailable;
import selfadaptive.properties.system.LineSensorAvailable;
import selfadaptive.properties.system.NotificationServiceAvailable;
import sua.autonomouscar.interfaces.ESensorLocation;

public class Activator implements BundleActivator {

	private static BundleContext context;

	// Driver properties
	private DriverSeatOcuppied driverSeatOcuppied;
	private FaceStatus faceStatus;
	private HandsOnTheWheel handsOnTheWheel;
	private HumanSensorAvailable humanSensorAvailable;

	// Road properties
	private LateralSecurityDistance lateralSecurityDistance;
	private LongSecurityDistance longSecurityDistance;
	private ReferenceSpeed referenceSpeed;
	private RoadStatus roadStatus;
	private RoadStatusAvailable roadStatusAvailable;
	private RoadType roadType;
	private RoadTypeAvailable roadTypeAvailable;

	// System properties
	private ActiveDrivingService activeDrivingService;
	private DistanceSensorAvailable frontDistanceSensorAvailable;
	private DistanceSensorAvailable rearDistanceSensorAvailable;
	private LidarSensorAvailable lidarSensorAvailable;
	private LineSensorAvailable rightLineSensorAvailable;
	private LineSensorAvailable leftLineSensorAvailable;
	private NotificationServiceAvailable notificationServiceAvailable;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		// Driver properties
		driverSeatOcuppied = new DriverSeatOcuppied();
		faceStatus = new FaceStatus();
		handsOnTheWheel = new HandsOnTheWheel();
		humanSensorAvailable = new HumanSensorAvailable();

		// Road properties
		lateralSecurityDistance = new LateralSecurityDistance();
		longSecurityDistance = new LongSecurityDistance();
		referenceSpeed = new ReferenceSpeed();
		roadStatus = new RoadStatus();
		roadType = new RoadType();
		roadStatusAvailable = new RoadStatusAvailable();
		roadTypeAvailable = new RoadTypeAvailable();

		// System properties
		activeDrivingService = new ActiveDrivingService();
		frontDistanceSensorAvailable = new DistanceSensorAvailable(context, ESensorLocation.FRONT);
		rearDistanceSensorAvailable = new DistanceSensorAvailable();
		lidarSensorAvailable = new LidarSensorAvailable();
		rightLineSensorAvailable = new LineSensorAvailable();
		leftLineSensorAvailable = new LineSensorAvailable();
		notificationServiceAvailable = new NotificationServiceAvailable();

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
