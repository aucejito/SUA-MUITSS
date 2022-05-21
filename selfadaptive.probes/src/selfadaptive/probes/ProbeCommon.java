package selfadaptive.probes;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public abstract class ProbeCommon {
	protected BundleContext context = null;
	protected List<String> implInterfacesList;
	private ServiceRegistration<?> serviceRegistration;
	
	public ProbeCommon(BundleContext bundleContext) {
		this.context = bundleContext;
		implInterfacesList = new ArrayList<String>();
		implInterfacesList.add(this.getClass().getName());
	}
	
	protected void addInterface(String name) {
		implInterfacesList.add(name);
	}
	
	public ServiceRegistration<?> getServiceRegistration() {
		return serviceRegistration;
	}

	public void setServiceRegistration(ServiceRegistration<?> serviceRegistration) {
		this.serviceRegistration = serviceRegistration;
	}
}
