package selfadaptive.probes;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import selfadaptive.probes.road.SondaEstadoVia;
import selfadaptive.probes.road.SondaTipoVia;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private SondaTipoVia sondaTipoVia;
	private SondaEstadoVia sondaEstadoVia;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		sondaTipoVia = new SondaTipoVia(context);
		context.registerService(
				sondaTipoVia.implInterfacesList.toArray(new String[sondaTipoVia.implInterfacesList.size()]),
				this.sondaTipoVia, null);
		context.addServiceListener(sondaTipoVia);

		sondaEstadoVia = new SondaEstadoVia(context);
		context.registerService(
				sondaEstadoVia.implInterfacesList.toArray(new String[sondaEstadoVia.implInterfacesList.size()]),
				this.sondaEstadoVia, null);
		context.addServiceListener(sondaEstadoVia);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		context.removeServiceListener(sondaTipoVia);
		this.sondaTipoVia = null;
		context.removeServiceListener(sondaEstadoVia);
		this.sondaEstadoVia = null;

		Activator.context = null;
	}

}
