package selfadaptive.probes.road;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.monitors.RoadContextMonitor;
import selfadaptive.probes.ProbeCommon;
import sua.autonomouscar.devices.interfaces.IRoadSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;

public class SondaTipoVia extends ProbeCommon implements ServiceListener {

	BundleContext context;

	public SondaTipoVia(BundleContext ctx) {
		super(ctx);
		context = ctx;
		addInterface(this.getClass().getName());
		addInterface(ServiceListener.class.getName());
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		RoadContextMonitor roadMonitor = OSGiUtils.getService(context, RoadContextMonitor.class);
		IRoadSensor roadSensor = OSGiUtils.getService(context, IRoadSensor.class);
		roadMonitor.modifyRoadType(roadSensor.getRoadType());
	}
}
