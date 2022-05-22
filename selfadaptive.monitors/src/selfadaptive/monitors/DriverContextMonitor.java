package selfadaptive.monitors;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.driver.DriverSeatOcuppied;
import selfadaptive.properties.driver.FaceStatus;
import selfadaptive.properties.driver.HandsOnTheWheel;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.interfaces.EFaceStatus;

public class DriverContextMonitor {
	protected BundleContext context;
	
	public DriverContextMonitor(BundleContext ctx) {
		context = ctx;
	}
	
	public void modifyHandsOnTheWheel(boolean newValue) {
		HandsOnTheWheel handsOn = OSGiUtils.getService(context, HandsOnTheWheel.class);
		boolean currentHandsOnTheWheel = handsOn.getHandsOnTheWheel();
		if(currentHandsOnTheWheel != newValue && handsOn != null) {
			handsOn.setHandsOnTheWheel(newValue);
			System.out.print("Hands On The Wheel changed to " + newValue);
		}
	}
	
	public void modifyFaceStatus(EFaceStatus newFaceStatus) {
		FaceStatus faceStatus = OSGiUtils.getService(context, FaceStatus.class);
		EFaceStatus currentFaceStatus = faceStatus.getFaceStatus();
		if(currentFaceStatus != newFaceStatus && faceStatus != null) {
			faceStatus.setFaceStatus(newFaceStatus);
			System.out.print("Face Status changed to " + newFaceStatus);
		}
	}
	
	public void modifyDriverSeatOcuppied(boolean newValue) {
		DriverSeatOcuppied driverSeat = OSGiUtils.getService(context, DriverSeatOcuppied.class);
		boolean currentDriverSeat = driverSeat.getDriverSeatOccupied();
		if(currentDriverSeat != newValue && driverSeat != null) {
			driverSeat.setDriverSeatOcuppied(newValue);
			System.out.print("Driver Seat Ocuppied changed to " + newValue);
		}
	}
}
