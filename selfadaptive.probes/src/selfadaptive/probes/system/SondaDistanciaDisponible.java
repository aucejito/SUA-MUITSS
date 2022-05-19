package selfadaptive.probes.system;

import org.osgi.framework.BundleContext;

public class SondaDistanciaDisponible {

	private BundleContext context;
	private String location;
	
	public SondaDistanciaDisponible(BundleContext ctx, String location) {
		context = ctx;
		this.location = location;
	}
	
	
}
