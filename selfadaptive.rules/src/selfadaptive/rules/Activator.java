package selfadaptive.rules;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import selfadaptive.rules.ADS.L3_HWCtoL3_TJC;

public class Activator implements BundleActivator {

	private static BundleContext context;

	private L3_HWCtoL3_TJC highwayChaufferToTrafficJam;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		highwayChaufferToTrafficJam = new L3_HWCtoL3_TJC(context);
		context.addServiceListener(highwayChaufferToTrafficJam);

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;

		context.removeServiceListener(highwayChaufferToTrafficJam);
		this.highwayChaufferToTrafficJam = null;
	}

}
