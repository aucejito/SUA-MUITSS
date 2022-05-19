package selfadaptive.properties;

import org.osgi.framework.BundleContext;

public abstract class AvailablePropertyCommon extends PropertyKnowledge {
	
	private boolean available = false;

	public AvailablePropertyCommon(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		
	}
	
	public boolean isAvailable() {
		return (boolean) props.get("available");
	}

	public void setAvailable(boolean available) {
		modifyKnowledge("available", available);
	}

	
	

}
