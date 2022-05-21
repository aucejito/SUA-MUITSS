package selfadaptive.properties;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public abstract class PropertyKnowledge {

	protected BundleContext context = null;
	protected Dictionary<String, Object> props;
	protected List<String> implInterfacesList;
	private ServiceRegistration<?> serviceRegistration;

	public PropertyKnowledge(BundleContext ctx) {
		context = ctx;
		implInterfacesList = new ArrayList<String>();
		props = new Hashtable<String, Object>();
	}

	protected void addInterface(String name) {
		implInterfacesList.add(name);
	}

	protected void modifyKnowledge(String prop, Object newValue) {
		props.put(prop, newValue);
		serviceRegistration.setProperties(props);
	}

	protected Object getKnowledge(String prop) {
		return props.get(prop);
	}

	public ServiceRegistration<?> getServiceRegistration() {
		return serviceRegistration;
	}

	public void setServiceRegistration(ServiceRegistration<?> serviceRegistration) {
		this.serviceRegistration = serviceRegistration;
	}

}
