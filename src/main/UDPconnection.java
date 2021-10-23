package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import events.IObserver;
import model.Dato;

public class UDPconnection extends Thread {
	
	private DatagramSocket socket;
	Gson gson;
	private Dato dato;
	private IObserver observer;
	
//	public void setObserver(IObserver observer) {
//		this.observer = observer;
//	}
	
	public UDPconnection(IObserver observer) {
		this.observer = observer;
	}
	
	public void run() {
		
		
		try {
			
			socket = new DatagramSocket(5000);
			
			recibir();
			
			
				

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void sendMessage(String mensaje) {
		
		new Thread(()->{
			
			
			try {
				InetAddress IP;
				
				IP = InetAddress.getByName("192.168.0.121");
				DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length,IP,9000);
				socket.send(packet);
			
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}).start();

		
	}
	

	private void recibir(){
		while(true) {
			
			byte [] buffer = new byte[100];
			DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
			//System.out.println("Esperando datagrama");
			
			
			try {
			socket.receive(packet);
			
			String mensaje = new String(packet.getData()).trim();
		    
			
			//datos = gson.fromJson(mensaje, Dato.class);
			
			System.out.println("Pedido: "+ mensaje);
			observer.recibirMensaje(mensaje);
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
