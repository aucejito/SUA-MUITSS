package selfadaptive.initialconfig;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.driving.interfaces.IDrivingService;
import sua.autonomouscar.driving.l1.assisteddriving.L1_AssistedDriving;
import sua.autonomouscar.infrastructure.OSGiUtils;
import sua.autonomouscar.infrastructure.devices.DistanceSensor;
import sua.autonomouscar.infrastructure.devices.Engine;
import sua.autonomouscar.infrastructure.devices.LineSensor;
import sua.autonomouscar.infrastructure.devices.Steering;
import sua.autonomouscar.infrastructure.interaction.NotificationService;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected IDrivingService drivingFunction = null;
	protected Engine engine;
	protected Steering steering;
	protected LineSensor leftLineSensor;
	protected LineSensor rightLineSensor;
	protected DistanceSensor frontDistanceSensor;
	protected NotificationService notificationService;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		// Posible fallo en la busqueda del servicio por la no utilizacion de la
		// interfaz IEngine
		this.engine = OSGiUtils.getService(bundleContext, Engine.class);
		if (this.engine == null) {
			this.engine = new Engine(bundleContext, "Engine");
			this.engine.registerThing();
		}

		this.steering = OSGiUtils.getService(bundleContext, Steering.class);
		if (this.steering == null) {
			this.steering = new Steering(bundleContext, "Steering");
			this.steering.registerThing();
		}

		// Preguntar profesor por la busqueda del servicio por si cogera el correcto
		// existiendo dos dispositivos LineSensor
		this.leftLineSensor = OSGiUtils.getService(bundleContext, LineSensor.class);
		if (this.leftLineSensor == null) {
			this.leftLineSensor = new LineSensor(bundleContext, "LeftLineSensor");
			this.leftLineSensor.registerThing();
		}

		this.rightLineSensor = OSGiUtils.getService(bundleContext, LineSensor.class);
		if (this.rightLineSensor == null) {
			this.rightLineSensor = new LineSensor(bundleContext, "RightLineSensor");
			this.rightLineSensor.registerThing();
		}

		this.frontDistanceSensor = OSGiUtils.getService(bundleContext, DistanceSensor.class);
		if (this.frontDistanceSensor == null) {
			this.frontDistanceSensor = new DistanceSensor(bundleContext, "FrontDistanceSensor");
			this.frontDistanceSensor.registerThing();
		}

		this.notificationService = OSGiUtils.getService(bundleContext, NotificationService.class);
		if (this.notificationService == null) {
			this.notificationService = new NotificationService(bundleContext, "NotificationService");
			this.notificationService.registerThing();
		}

		// Preguntar al profesor para saber cual esta activo
		this.drivingFunction = OSGiUtils.getService(bundleContext, IDrivingService.class);
		if (this.drivingFunction != null) {
			this.drivingFunction.stopDriving();
		}

		L1_AssistedDriving assistedDriving = OSGiUtils.getService(bundleContext, L1_AssistedDriving.class);
		if (assistedDriving == null) {
			assistedDriving = new L1_AssistedDriving(bundleContext, "L1_AssistedDriving");
			assistedDriving.registerThing();
		}

		// Preguntar al profesor si esto es correcto
		assistedDriving.setLeftLineSensor("LeftLineSensor");
		assistedDriving.setRightLineSensor("RightLineSensor");
		assistedDriving.setFrontDistanceSensor("FrontDistanceSensor");
		assistedDriving.setNotificationService("NotificationService");
		drivingFunction = assistedDriving;
		drivingFunction.startDriving();
	}

	public void stop(BundleContext bundleContext) throws Exception {

		if (this.engine != null) {
			this.engine.unregisterThing();
		}

		if (this.steering != null) {
			this.steering.unregisterThing();
		}

		if (this.leftLineSensor != null) {
			this.leftLineSensor.unregisterThing();
		}

		if (this.rightLineSensor != null) {
			this.rightLineSensor.unregisterThing();
		}

		if (this.frontDistanceSensor != null) {
			this.frontDistanceSensor.unregisterThing();
		}

		if (this.notificationService != null) {
			this.notificationService.unregisterThing();
		}

		if (this.drivingFunction != null) {
			this.drivingFunction.stopDriving();
		}

		Activator.context = null;
	}

}
