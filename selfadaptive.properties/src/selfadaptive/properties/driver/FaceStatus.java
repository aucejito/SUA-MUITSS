package selfadaptive.properties.driver;

import org.osgi.framework.BundleContext;

import selfadaptive.properties.PropertyKnowledge;
import sua.autonomouscar.interfaces.EFaceStatus;

public class FaceStatus extends PropertyKnowledge{

	public FaceStatus(BundleContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
		addInterface(this.getClass().getName());
	}
	
	public void setFaceStatus(EFaceStatus faceStatus) {
		modifyKnowledge("facestatus", faceStatus);
	}
	
	public EFaceStatus getFaceStatus() {
		return (EFaceStatus) props.get("facestatus");
	}
}
