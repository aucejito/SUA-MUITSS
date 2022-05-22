package selfadaptive.probes.driver;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.monitors.DriverContextMonitor;
import selfadaptive.probes.ProbeCommon;
import sua.autonomouscar.devices.interfaces.ISeatSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;

public class SondaAsiento extends ProbeCommon implements ServiceListener {
	BundleContext context;

	public SondaAsiento(BundleContext bundleContext) {
		super(bundleContext);
		context = bundleContext;
		addInterface(this.getClass().getName());
		addInterface(ServiceListener.class.getName());
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		DriverContextMonitor driverMonitor = OSGiUtils.getService(context, DriverContextMonitor.class);
		ISeatSensor seatSensor = OSGiUtils.getService(context, ISeatSensor.class);
		// driverMonitor.modifyHandsOnTheWheel(seatSensor.isSeatOccuppied());

	}
}
