
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerListenerThreadUDP extends Thread{
	//Test variable
	private boolean isRunning = false;
	
    private static DatagramSocket UDPsocket;
    private int port;
    private int MAX_PACKET_SIZE;
    
    private Deserialization NetTools = new Deserialization();
    
    ServerListenerThreadUDP(int port, int packetSize) {
    	this.port = port;
    	MAX_PACKET_SIZE = packetSize;
    }
    
    public void run() {
    	ServerUDPManager ServerQueue = new ServerUDPManager();
    	try {
			UDPsocket = new DatagramSocket(port);
		} catch (SocketException e1) {
			System.err.println("Port in use.");
			e1.printStackTrace();
		}
    	
    	System.out.println("SERVER UDP LISTENER Activated.");
    	isRunning = true;
    	while(true) {
    		byte[] data = new byte[MAX_PACKET_SIZE];
    		DatagramPacket receivePacket = new DatagramPacket(data, data.length);
    		try {
				UDPsocket.receive(receivePacket);
				
				//----------Test Area--------------
				System.out.println("Received Packet");
				byte[] buffer = receivePacket.getData();
				ServerQueue.addToQueueTest(buffer.toString());
				
				
				
				//----------End of Test Area-----------
				
				//-------------Receiving Objects---------
				//AbstractMessage gameMessage = NetTools.deserialize(receivePacket.getData());
				//ServerQueue.addToQueueMessages(gameMessage);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
    public boolean getStatus() {
    	return isRunning;
    }
    
    
}
