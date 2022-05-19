package selfadaptive.properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

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
		driverSeatOcuppied = new DriverSeatOcuppied(context);
		driverSeatOcuppied.registerServiceKnowledge();
		serviceRegistrationKnowledge(driverSeatOcuppied);

		faceStatus = new FaceStatus(context);
		serviceRegistrationKnowledge(faceStatus);

		handsOnTheWheel = new HandsOnTheWheel(context);
		serviceRegistrationKnowledge(handsOnTheWheel);

		humanSensorAvailable = new HumanSensorAvailable(context);
		serviceRegistrationKnowledge(humanSensorAvailable);

		// Road properties
		lateralSecurityDistance = new LateralSecurityDistance();
		// TODO Service Registration

		longSecurityDistance = new LongSecurityDistance();
		// TODO Service Registration

		referenceSpeed = new ReferenceSpeed();
		// TODO Service Registration

		roadStatus = new RoadStatus(context);
		serviceRegistrationKnowledge(roadStatus);

		roadType = new RoadType(context);
		serviceRegistrationKnowledge(roadType);

		roadStatusAvailable = new RoadStatusAvailable(context);
		serviceRegistrationKnowledge(roadStatusAvailable);

		roadTypeAvailable = new RoadTypeAvailable(context);
		serviceRegistrationKnowledge(roadTypeAvailable);

		// System properties
		activeDrivingService = new ActiveDrivingService(bundleContext);
		serviceRegistrationKnowledge(activeDrivingService);

		frontDistanceSensorAvailable = new DistanceSensorAvailable(context, ESensorLocation.FRONT);
		serviceRegistrationKnowledge(frontDistanceSensorAvailable);

		rearDistanceSensorAvailable = new DistanceSensorAvailable(context, ESensorLocation.REAR);
		serviceRegistrationKnowledge(rearDistanceSensorAvailable);

		lidarSensorAvailable = new LidarSensorAvailable(context);
		serviceRegistrationKnowledge(lidarSensorAvailable);

		rightLineSensorAvailable = new LineSensorAvailable(context, ESensorLocation.RIGHT);
		serviceRegistrationKnowledge(rightLineSensorAvailable);

		leftLineSensorAvailable = new LineSensorAvailable(context, ESensorLocation.LEFT);
		serviceRegistrationKnowledge(leftLineSensorAvailable);

		notificationServiceAvailable = new NotificationServiceAvailable(context);
		serviceRegistrationKnowledge(notificationServiceAvailable);

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

	public void serviceRegistrationKnowledge(PropertyKnowledge property) {
		ServiceRegistration<?> service = this.context.registerService((String[]) property.implInterfacesList.toArray(),
				property, property.props);
		property.setServiceRegistration(service);
	}

}
