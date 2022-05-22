package selfadaptive.properties.driver;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.PropertyKnowledge;

public class HandsOnTheWheel extends PropertyKnowledge {

	public HandsOnTheWheel(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		addInterface(this.getClass().getName());
	}

	public void setHandsOnTheWheel(Boolean status) {
		modifyKnowledge("hands-on-wheel", status);
	}

	public Boolean getHandsOnTheWheel() {
		return (Boolean) props.get("hands-on-wheel");
	}
}
