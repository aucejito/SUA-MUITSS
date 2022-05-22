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
public class L3_TJCtoL3_CC implements ServiceListener {

	BundleContext context;
	IDrivingService currentDrivingService;
	RoadType roadType;

	public L3_TJCtoL3_CC(BundleContext ctx) {
		super();
		this.context = ctx;
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class,
				String.format("(%s=%s)", DrivingService.ACTIVE, true));
		roadType = OSGiUtils.getService(context, RoadType.class, "roadtype");

		if (currentDrivingService instanceof IL3_TrafficJamChauffer && roadType.getRoadType() == ERoadType.CITY) {
			currentDrivingService.stopDriving();

			L3_CityChauffer newDrivingService = new L3_CityChauffer(context, "l3_citychauffer");
			newDrivingService.setHumanSensors("HumanSensors");
			newDrivingService.setRoadSensor("RoadSensor");
			newDrivingService.setEngine("Engine");
			newDrivingService.setSteering("Steering");
			newDrivingService.setFrontDistanceSensor("FrontDistanceSensor");
			newDrivingService.setRearDistanceSensor("RearDistanceSensor");
			newDrivingService.setRightDistanceSensor("RightDistanceSensor");
			newDrivingService.setLeftDistanceSensor("LeftDistanceSensor");
			newDrivingService.setRightLineSensor("RightLineSensor");
			newDrivingService.setLeftLineSensor("LeftLineSensor");

			newDrivingService.setReferenceSpeed(L3_CityChauffer.DEFAULT_REFERENCE_SPEED);
			newDrivingService.setLongitudinalSecurityDistance(L3_CityChauffer.DEFAULT_LONGITUDINAL_SECURITY_DISTANCE);
			newDrivingService.setLateralSecurityDistance(L3_CityChauffer.DEFAULT_LATERAL_SECURITY_DISTANCE);

			newDrivingService.setNotificationService("NotificationService");
			newDrivingService.setFallbackPlan("ParkInTheRoadShoulderFallbackPlan");

			System.out.println("[Rule - L3_TJCtoL3_CC] ----> Change to L3_CityChauffer");
			newDrivingService.registerThing();
			newDrivingService.startDriving();
		}
	}

}
