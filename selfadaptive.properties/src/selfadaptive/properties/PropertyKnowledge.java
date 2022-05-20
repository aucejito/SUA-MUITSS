package selfadaptive.properties;

import java.util.ArrayList;
import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public abstract class PropertyKnowledge {

	protected BundleContext context = null;
	protected Dictionary<String, Object> props;
	protected ArrayList<String> implInterfacesList;
	private ServiceRegistration<?> serviceRegistration;

	public PropertyKnowledge(BundleContext ctx) {
		context = ctx;
	}

	protected void addInterface(String name) {
		implInterfacesList.add(name);
	}

	protected void modifyKnowledge(String prop, Object newValue) {
		props.put(prop, newValue);
		serviceRegistration.setProperties(props);
	}

	public ServiceRegistration<?> getServiceRegistration() {
		return serviceRegistration;
	}

	public void setServiceRegistration(ServiceRegistration<?> serviceRegistration) {
		this.serviceRegistration = serviceRegistration;
	}

}
