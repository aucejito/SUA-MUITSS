package selfadaptive.probes.driver;

public class EstadoConductorDisponible {
	Boolean newDriverStatusAvailableValue;
	
	public EstadoConductorDisponible(Boolean newDriverStatusAvailableValue) {
		super();
		this.newDriverStatusAvailableValue = newDriverStatusAvailableValue;
	}
	
	public Boolean getNewDriverStatusAvailableValue() {
		return newDriverStatusAvailableValue;
	}

	public void setNewDriverStatusAvailableValue(Boolean newDriverStatusAvailableValue) {
		this.newDriverStatusAvailableValue = newDriverStatusAvailableValue;
	}

}
