package getIP;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class testIP {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		
		System.out.println(InetAddress.getLocalHost().getHostAddress());	
		System.out.println(InetAddress.getLocalHost().getHostName());	

	}

}
