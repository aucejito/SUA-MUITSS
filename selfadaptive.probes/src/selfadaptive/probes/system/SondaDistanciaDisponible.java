package selfadaptive.probes.system;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import selfadaptive.probes.ProbeCommon;
import sua.autonomouscar.devices.interfaces.IDistanceSensor;
import sua.autonomouscar.infrastructure.OSGiUtils;

public class SondaDistanciaDisponible extends ProbeCommon implements ServiceListener {

	private BundleContext context;
	private String location;

	public SondaDistanciaDisponible(BundleContext ctx, String location) {
		super(ctx);
		context = ctx;
		this.location = location;
		addInterface(this.getClass().getName());
		addInterface(ServiceListener.class.getName());
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		IDistanceSensor distanceSensor = OSGiUtils.getService(context, IDistanceSensor.class,
				String.format("(%s=%s)", IIdentifiable.ID, position.getNormalSensorId()));
	}

}
