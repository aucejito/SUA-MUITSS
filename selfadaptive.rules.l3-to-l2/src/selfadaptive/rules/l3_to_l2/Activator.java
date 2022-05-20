package selfadaptive.rules.l3_to_l2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected L3toL2 rule;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.rule = new L3toL2();
		context.addServiceListener(rule);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		context.removeServiceListener(rule);
		Activator.context = null;
		
	}

}
