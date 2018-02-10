
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerUDPManager extends Thread{
	
	private boolean setDefault = true;
	
	private boolean listenerUp = false;
	private boolean broadcasterUp = false;
	
	//Listeners add messages to this queue.
	protected static volatile Queue<AbstractMessage> queueMessages = new LinkedList<AbstractMessage>();
	//Gamelogic gives new game states onto this queue.
	protected static volatile Queue<AbstractMessage> queueGameStates = new LinkedList<AbstractMessage>();
	//Current Game State.
	protected static AbstractMessage gameStates = new AbstractMessage();
	
	
	protected static Queue<String> queueTestStates = new LinkedList<String>();
	
	ServerBroadcasterThreadUDP BSocket;
	ServerListenerThreadUDP SListener;

	int portListener;
	int portBroadcast;
	String groupID;
	String serverIP;
	
	protected ServerUDPManager() {
		portListener = 6666;
		portBroadcast = 6667;
		groupID = "230.0.0.0";
		serverIP = "localhost";
	}
	
	/*
	public static void main(String args[]) {
		ServerUDPManager UDPManager = new ServerUDPManager();
		UDPManager.run();
	}
	*/
	
	public void run() {
		if(setDefault) {
			System.out.println("UDP Default settings.");
		} else {
			System.out.println("Custom settings.");
		}
		startListener(portListener,1024);
		startBroadcastSender(groupID, portBroadcast);
		
		showInformation();
		
		//GameLogicThreadUDP GLThread = new GameLogicThreadUDP();
		
		//GLThread.start();
	}
	
	private void startListener(int serverPort, int MAX_PACKET_SIZE) {
		SListener = new ServerListenerThreadUDP(serverPort, MAX_PACKET_SIZE);
		SListener.start();
		listenerUp = SListener.getStatus();
	}
	
	private void startBroadcastSender(String groupID, int groupPort) {
		BSocket = new ServerBroadcasterThreadUDP(groupID, groupPort);
		BSocket.start();
		broadcasterUp = BSocket.getStatus();
	}
	
	//Used by the Broadcaster
	public void addToQueueStates(AbstractMessage newState) {
		queueGameStates.add(newState);
	}
	//Game Logic uses these packets to update game.
	public void addToQueueMessages(AbstractMessage msg) {
		queueMessages.add(msg);
	}
	
	public void addToQueueTest(String strg) {
		queueTestStates.add(strg);
	}
	
	public boolean listenerRunning() {
		return listenerUp;
	}
	
	public boolean broadcasterRunning() {
		return broadcasterUp;
	}
	
	public void showInformation() {
		System.out.println();
		try {
		System.out.println("Server IP: " + InetAddress.getByName(serverIP));
		} catch (IOException e){
			e.printStackTrace();
		}
		System.out.println("Port Listener: " + portListener);
		System.out.println("Port Broadcaster: " + portBroadcast);
		System.out.println("Group Address/ID: " + groupID);
	}
	
}
