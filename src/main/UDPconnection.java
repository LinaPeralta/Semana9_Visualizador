package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPconnection extends Thread {
	
	private DatagramSocket socket;
	
	public void run() {
		
		
		try {
			
			socket = new DatagramSocket(5000);
			
			while(true) {
				
				byte [] buffer = new byte[100];
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
				System.out.println("Esperando datagrama");
				socket.receive(packet);
				
				
				String mensaje = new String(packet.getData()).trim();
						
				System.out.println("Esperando recibido"+ mensaje);
			}
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void sendMessage(String mensaje) {
		
		InetAddress IP;
		try {
			
			IP = InetAddress.getByName("127.0.2.2");
			DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length,IP,6000);
			socket.send(packet);
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
