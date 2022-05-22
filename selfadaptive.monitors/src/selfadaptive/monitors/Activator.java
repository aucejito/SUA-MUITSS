package selfadaptive.monitors;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private RoadContextMonitor roadContextMonitor;
	private ServiceRegistration<RoadContextMonitor> roadContextMonitorServiceRegistration;
	private DriverContextMonitor driverContextMonitor;
	private ServiceRegistration<DriverContextMonitor> driverContexServiceRegistration;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		//Inicialización del monitor de contexto de carretera
		this.roadContextMonitor = new RoadContextMonitor(context);
		this.roadContextMonitorServiceRegistration = context.registerService(RoadContextMonitor.class,
				roadContextMonitor, null);
		
		//Inicialización del monitor sobre el contexto del conductor
		this.driverContextMonitor = new DriverContextMonitor(context);
		this.driverContexServiceRegistration = context.registerService(DriverContextMonitor.class, driverContextMonitor, null);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {

		this.roadContextMonitorServiceRegistration.unregister();
		this.roadContextMonitorServiceRegistration = null;
		this.roadContextMonitor = null;
		
		this.driverContexServiceRegistration.unregister();
		this.roadContextMonitorServiceRegistration = null;
		this.driverContextMonitor = null;

		Activator.context = null;
	}

}
