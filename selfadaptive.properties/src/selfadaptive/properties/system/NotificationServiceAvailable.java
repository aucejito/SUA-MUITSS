package selfadaptive.properties.system;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.AvailablePropertyCommon;

public class NotificationServiceAvailable extends AvailablePropertyCommon {

	public NotificationServiceAvailable(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		addInterface(this.getClass().getName());
	}

}
