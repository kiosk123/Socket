package mysocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketExecutor implements Runnable{

	private Socket socket;
	private InputStream in;
	private OutputStream out;
	
	public SocketExecutor(Socket socket){
		this.socket = socket;
	}
	
	
	@Override
	public void run() {
		
		try {
			out = socket.getOutputStream();
			in = socket.getInputStream();
			while(true){
				byte[] buffer = new byte[1024];
				int readLen = in.read(buffer);
				if(readLen == -1){
					System.out.println("클라이언트 연결이 종료되었습니다.");
					break;
				}
				
				byte[] readData = new byte[readLen];
				
				System.arraycopy(buffer, 0, readData, 0, readLen);
				out.write(readData);
				out.flush();
				
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}finally{
			if (in != null) {try {in.close();} catch (IOException e) {}}
            if (out != null) {try {out.close();} catch (IOException e) {}}
            if (socket != null) {try {socket.close();} catch (IOException e){}}
		}
	}	
}
