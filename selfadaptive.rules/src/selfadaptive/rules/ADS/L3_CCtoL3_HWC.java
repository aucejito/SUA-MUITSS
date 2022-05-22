package selfadaptive.rules.ADS;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.properties.road.RoadStatus;
import selfadaptive.properties.road.RoadType;
import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.interfaces.IL3_CityChauffer;
import sua.autonomouscar.driving.l3.highwaychauffer.L3_HighwayChauffer;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.driving.DrivingService;
import sua.autonomouscar.interfaces.ERoadStatus;
import sua.autonomouscar.interfaces.ERoadType;

//ADS_L3-6 Pt2/2
public class L3_CCtoL3_HWC implements ServiceListener {
	BundleContext context;
	IDrivingService currentDrivingService;
	RoadStatus roadStatus;
	RoadType roadType;

	public L3_CCtoL3_HWC(BundleContext context) {
		super();
		this.context = context;
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		currentDrivingService = OSGiUtils.getService(context, IDrivingService.class,
				String.format("(%s=%s)", DrivingService.ACTIVE, true));
		roadStatus = OSGiUtils.getService(context, RoadStatus.class, "roadstatus");
		roadType = OSGiUtils.getService(context, RoadType.class, "roadtype");

		if (currentDrivingService instanceof IL3_CityChauffer && roadType.getRoadType() == ERoadType.HIGHWAY
				&& roadStatus.getRoadStatus() == ERoadStatus.FLUID) {
			currentDrivingService.stopDriving();

			L3_HighwayChauffer newDrivingService = new L3_HighwayChauffer(context, "l3_highwaychauffer");

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

			newDrivingService.setReferenceSpeed(L3_HighwayChauffer.DEFAULT_REFERENCE_SPEED);
			newDrivingService
					.setLongitudinalSecurityDistance(L3_HighwayChauffer.DEFAULT_LONGITUDINAL_SECURITY_DISTANCE);
			newDrivingService.setLateralSecurityDistance(L3_HighwayChauffer.DEFAULT_LATERAL_SECURITY_DISTANCE);

			newDrivingService.setNotificationService("NotificationService");
			newDrivingService.setFallbackPlan("ParkInTheRoadShoulderFallbackPlan");

			System.out.println("[Rule - L3_CCtoL3_HWC] ----> Change to L3_HighwayChauffer");
			newDrivingService.registerThing();
			newDrivingService.startDriving();
		}
	}
}
