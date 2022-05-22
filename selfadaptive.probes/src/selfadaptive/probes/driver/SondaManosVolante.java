package selfadaptive.probes.driver;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.monitors.DriverContextMonitor;
import selfadaptive.probes.ProbeCommon;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.devices.HandsOnWheelSensor;

public class SondaManosVolante extends ProbeCommon implements ServiceListener {
	BundleContext context;

	public SondaManosVolante(BundleContext bundleContext) {
		super(bundleContext);
		context = bundleContext;
		addInterface(this.getClass().getName());
		addInterface(ServiceListener.class.getName());
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		DriverContextMonitor driverMonitor = OSGiUtils.getService(context, DriverContextMonitor.class);
		HandsOnWheelSensor handsSensor = OSGiUtils.getService(context, HandsOnWheelSensor.class);
		driverMonitor.modifyHandsOnTheWheel(handsSensor.areTheHandsOnTheSteeringWheel());

	}
}
