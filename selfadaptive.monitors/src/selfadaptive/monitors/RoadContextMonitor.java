package selfadaptive.monitors;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.road.RoadStatus;
import selfadaptive.properties.road.RoadStatusAvailable;
import selfadaptive.properties.road.RoadType;
import selfadaptive.properties.road.RoadTypeAvailable;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.ERoadStatus;
import sua.autonomouscar.interfaces.ERoadType;

public class RoadContextMonitor {

	protected BundleContext context;

	public RoadContextMonitor(BundleContext ctx) {
		// TODO Auto-generated constructor stub
		context = ctx;
	}

	public void modifyRoadType(ERoadType newRoadType) {
		RoadType roadType = OSGiUtils.getService(context, RoadType.class);
		ERoadType currentRoadType = roadType.getRoadType();
		if (currentRoadType != newRoadType && roadType != null) {
			roadType.setRoadType(newRoadType);
			System.out.print("Road Status changed to " + newRoadType);
		}
	}

	public void modifyRoadStatus(ERoadStatus newRoadStatus) {
		RoadStatus roadStatus = OSGiUtils.getService(context, RoadStatus.class);
		ERoadStatus currentRoadStatus = roadStatus.getRoadStatus();
		if (currentRoadStatus != newRoadStatus && roadStatus != null) {
			roadStatus.setRoadStatus(newRoadStatus);
			System.out.print("Road Status changed to " + newRoadStatus);
		}
	}

	public void modifyRoadTypeAvailable(boolean newAvailableStatus) {
		RoadTypeAvailable roadTypeAvailable = OSGiUtils.getService(context, RoadTypeAvailable.class);
		boolean currentRoadTypeAvailable = roadTypeAvailable.isAvailable();
		if (currentRoadTypeAvailable != newAvailableStatus && roadTypeAvailable != null) {
			roadTypeAvailable.setAvailable(currentRoadTypeAvailable);
		}
	}

	public void modifyRoadStatusAvailable(boolean newAvailableStatus) {
		RoadStatusAvailable roadStatusAvailable = OSGiUtils.getService(context, RoadStatusAvailable.class);
		boolean currentRoadStatusAvailable = roadStatusAvailable.isAvailable();
		if (currentRoadStatusAvailable != newAvailableStatus && roadStatusAvailable != null) {
			roadStatusAvailable.setAvailable(currentRoadStatusAvailable);
		}
	}
}
