

public class Server {

	private static ServerUDPManager UDPManager;
	
	public Server() {
		UDPManager = new ServerUDPManager();
	}
	
	public static void main(String[] args) {
		System.out.println("Server Initiated.");
		System.out.println("Goodbye.");
		
		System.out.println("Starting UDP Manager.");
		UDPManager.run();
		
		if(runningListener() && runningBroadcaster()) {
			System.out.println("UDP Manager up and running.");
		} else {
			System.out.println("Unable to start UDP Manager.");
			return;
		}
		

	}
	
	private static boolean runningListener() {
		return UDPManager.listenerRunning();
	}
	
	private static boolean runningBroadcaster() {
		return UDPManager.broadcasterRunning();
	}

}
