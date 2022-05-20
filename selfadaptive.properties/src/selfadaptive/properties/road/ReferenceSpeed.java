package selfadaptive.properties.road;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.PropertyKnowledge;

public class ReferenceSpeed extends PropertyKnowledge {

	public ReferenceSpeed(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		addInterface(this.getClass().getName());
	}

	public void setReferenceSpeed(int speed) {
		modifyKnowledge("referencespeed", speed);
	}

	public int getReferenceSpeed() {
		return (int) getKnowledge("referencespeed");
	}

}
