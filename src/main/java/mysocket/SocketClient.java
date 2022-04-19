package mysocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
	private Socket socket;
	private InputStream in;
	private OutputStream out;
	
	public SocketClient(){
		try {
			this.socket = new Socket("127.0.0.1",7777);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void execute(){		
		try {
			out = socket.getOutputStream();
			Scanner sc = new Scanner(System.in);
			
			while(true){
				System.out.print("클라이언트 입력 : ");
				String msg = sc.nextLine();				
				if(msg.trim().equalsIgnoreCase("quit")){
					break;
				}				
				out.write(msg.getBytes());
				out.flush();
				in = socket.getInputStream();
				byte[] buffer = new byte[1024];
				int readLen = in.read(buffer);
				byte[] readData = new byte[readLen];
				System.arraycopy(buffer,0, readData, 0, readLen);
				System.out.println("메아리 : "+new String(readData));
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			 if(in!=null){try{in.close();}catch(IOException e){}}
	         if(out!=null){try{out.close();}catch(IOException e){}}
	         if(socket!=null){try{socket.close();}catch(IOException e){}}
		}
	}
	
}
