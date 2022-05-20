package selfadaptive.properties.road;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.PropertyKnowledge;

public class LateralSecurityDistance extends PropertyKnowledge {

	public LateralSecurityDistance(BundleContext ctx) {
		super(ctx);
		addInterface(this.getClass().getName());
	}

	public void setLateralSecurityDistance(int distance) {
		modifyKnowledge("lateralsecuritydistance", distance);
	}

	public int getLateralSecurityDistance() {
		return (int) getKnowledge("lateralsecuritydistance");
	}

}
