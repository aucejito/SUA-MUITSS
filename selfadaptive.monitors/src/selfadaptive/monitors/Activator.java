package selfadaptive.monitors;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private RoadContextMonitor roadContextMonitor;
	private ServiceRegistration<RoadContextMonitor> roadContextMonitorServiceRegistration;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		this.roadContextMonitor = new RoadContextMonitor(context);
		this.roadContextMonitorServiceRegistration = context.registerService(RoadContextMonitor.class,
				roadContextMonitor, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {

		this.roadContextMonitorServiceRegistration.unregister();
		this.roadContextMonitorServiceRegistration = null;
		this.roadContextMonitor = null;

		Activator.context = null;
	}

}
