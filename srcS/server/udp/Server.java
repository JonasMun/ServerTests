

public class Server {

	private ServerUDPManager UDPManager;
	
	public Server() {
		UDPManager = new ServerUDPManager();
	}
	
	public static void main(String[] args) {
		System.out.println("Server Initiated.");
		System.out.println("Goodbye.");

	}

}
