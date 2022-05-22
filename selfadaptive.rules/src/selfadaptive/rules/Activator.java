package selfadaptive.rules;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import selfadaptive.rules.ADS.L3_CCtoL3_HWC;
import selfadaptive.rules.ADS.L3_CCtoL3_TJC;
import selfadaptive.rules.ADS.L3_HWCtoL3_TJC;
import selfadaptive.rules.ADS.L3_TJCtoL3_CC;
import selfadaptive.rules.ADS.L3_TJCtoL3_HWC;
import selfadaptive.rules.driver.DriverHaptics;

public class Activator implements BundleActivator {

	private static BundleContext context;

	// ADS
	private L3_CCtoL3_HWC cityToHighway;
	private L3_CCtoL3_TJC cityToTrafficJam;
	private L3_HWCtoL3_TJC highwayToTrafficJam;
	private L3_TJCtoL3_CC trafficJamToCity;
	private L3_TJCtoL3_HWC trafficJamToHighway;

	// Driver-related
	private DriverHaptics driverHaptics;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		// ADS
		cityToHighway = new L3_CCtoL3_HWC(context);
		context.addServiceListener(cityToHighway);

		cityToTrafficJam = new L3_CCtoL3_TJC(context);
		context.addServiceListener(cityToTrafficJam);

		highwayToTrafficJam = new L3_HWCtoL3_TJC(context);
		context.addServiceListener(highwayToTrafficJam);

		trafficJamToCity = new L3_TJCtoL3_CC(context);
		context.addServiceListener(trafficJamToCity);

		trafficJamToHighway = new L3_TJCtoL3_HWC(context);
		context.addServiceListener(trafficJamToHighway);

		// Driver-related
		driverHaptics = new DriverHaptics(context);
		context.addServiceListener(driverHaptics);
	}

	public void stop(BundleContext bundleContext) throws Exception {

		// ADS
		context.removeServiceListener(cityToHighway);
		this.cityToHighway = null;

		context.removeServiceListener(cityToTrafficJam);
		this.cityToTrafficJam = null;

		context.removeServiceListener(highwayToTrafficJam);
		this.highwayToTrafficJam = null;

		context.removeServiceListener(trafficJamToCity);
		this.trafficJamToCity = null;

		context.removeServiceListener(trafficJamToHighway);
		this.trafficJamToHighway = null;

		// Driver-related
		context.removeServiceListener(driverHaptics);
		this.driverHaptics = null;

		Activator.context = null;
	}

}
