package selfadaptive.rules.ADS;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.properties.road.RoadStatus;
import selfadaptive.properties.road.RoadType;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IL3_CityChauffer;
import sua.autonomouscar.driving.l3.trafficjamchauffer.L3_TrafficJamChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.driving.DrivingService;
import sua.autonomouscar.interfaces.ERoadStatus;
import sua.autonomouscar.interfaces.ERoadType;

//ADS_L3-6 Pt1/2
public class L3_CCtoL3_TJC implements ServiceListener {

	BundleContext context;
	IDrivingService currentDrivingService;
	RoadStatus roadStatus;
	RoadType roadType;

	public L3_CCtoL3_TJC(BundleContext context) {
		super();
		this.context = context;
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class,
				String.format("(%s=%s)", DrivingService.ACTIVE, true));
		roadStatus = OSGiUtils.getService(context, RoadStatus.class);
		roadType = OSGiUtils.getService(context, RoadType.class);

		if (currentDrivingService instanceof IL3_CityChauffer && roadType.getRoadType() == ERoadType.HIGHWAY
				&& (roadStatus.getRoadStatus() == ERoadStatus.JAM
						|| roadStatus.getRoadStatus() == ERoadStatus.COLLAPSED)) {
			currentDrivingService.stopDriving();

			L3_TrafficJamChauffer newDrivingService = new L3_TrafficJamChauffer(context, "l3_trafficjamchauffer");
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

			newDrivingService.setReferenceSpeed(L3_TrafficJamChauffer.DEFAULT_REFERENCE_SPEED);
			newDrivingService
					.setLongitudinalSecurityDistance(L3_TrafficJamChauffer.DEFAULT_LONGITUDINAL_SECURITY_DISTANCE);
			newDrivingService.setLateralSecurityDistance(L3_TrafficJamChauffer.DEFAULT_LATERAL_SECURITY_DISTANCE);

			newDrivingService.setNotificationService("NotificationService");
			newDrivingService.setFallbackPlan("ParkInTheRoadShoulderFallbackPlan");

			System.out.println("[Rule - L3_CCtoL3_TJC] ----> Change to L3_TrafficJamChauffer");
			newDrivingService.registerThing();
			newDrivingService.startDriving();
		}
	}

}
