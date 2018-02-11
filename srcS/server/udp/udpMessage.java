package server.udp;

public class udpMessage extends AbstractMessage{

	private String type;
	
	public udpMessage(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
