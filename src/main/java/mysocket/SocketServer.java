package mysocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	
	private ServerSocket serverSocket;
	
	public SocketServer() {
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("Server 소켓 시작");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void execute(){
		Socket socket = null;
		try {
			while(true){
				System.out.println("Server 소켓 대기중...");
				socket = serverSocket.accept();
				SocketExecutor socketExecutor = new SocketExecutor(socket);
				new Thread(socketExecutor).start();
			}			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}finally{
			if (serverSocket != null) {try {serverSocket.close();} catch (IOException e) {}}
		}
	}
}
