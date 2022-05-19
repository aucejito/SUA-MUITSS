package selfadaptive.properties.driver;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.PropertyKnowledge;

public class HandsOnTheWheel extends PropertyKnowledge{

	public HandsOnTheWheel(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		addInterface(this.getClass().getName());
	}
	
	public void setHandsOnTheWheel(boolean status) {
		modifyKnowledge("handsonthewheel", status);
	}

	public boolean getHandsOnTheWheel() {
		return (boolean) props.get("handsonthewheel");
	}
}
