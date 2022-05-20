package selfadaptive.properties.road;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.PropertyKnowledge;

public class LongSecurityDistance extends PropertyKnowledge {

	public LongSecurityDistance(BundleContext ctx) {
		super(ctx);
		addInterface(this.getClass().getName());
	}

	public void setLongSecurityDistance(int distance) {
		modifyKnowledge("longsecuritydistance", distance);
	}

	public int getLongSecurityDistance() {
		return (int) getKnowledge("longsecuritydistance");
	}

}
