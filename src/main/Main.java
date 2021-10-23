package main;

import java.util.ArrayList;

import com.google.gson.Gson;

import events.IObserver;
import model.Dato;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements IObserver{
	
	UDPconnection udp;
	Dato dato;
	PImage bowl;
	PImage papitas;
	PImage granizado;
	PImage brownie;
	Gson gson;
	private ArrayList<Dato> datos;
	
	
	public static void main(String[] args) {

		PApplet.main("main.Main");

	}

	public void settings() {
		size(700, 550);
	}

	public void setup() {
		udp = new UDPconnection(this);
		udp.start();
		
		datos = new ArrayList<>();
		
		//imagenes
		bowl =loadImage("data/bowl.png");
		
		papitas =loadImage("data/papitas.png");
		
		granizado =loadImage("data/granizado.png");
		
		brownie =loadImage("data/brownie.png");

	}

	public void draw() {
		background(234,225,200);
		fill(19,31,99);
		text("BIENVENIDO A WONKA",50,30);
		textSize(20);
		pintar();
		
	}
	
	@Override
	public void mousePressed() {
		
		//udp.sendMessage("Pedido listo");
		
		
		
	}
	
	public void pintar() {
		
		int y=50;
		
		for (int i = 0; i < datos.size(); i++) {
			
			
			switch(datos.get(i).getOrden()) {
			
			case "granizado":
				
				image(granizado,50,y+(120*i),100,100);

				break;
				
			case "papitas":

				image(papitas,50,y+(120*i),100,100);

				break;
				
			case "bowl":

				image(bowl, 50,y+(120*i),100,100);

				break;
				
			case "brownie":

				image(brownie, 50,y+(120*i),100,100);

				break;

			
			}
			
		}
		

	}

	@Override
	public void recibirMensaje(String mensaje) {
		
		System.out.println("ME LLEGO EL MENSAJE GORDA");
		Gson gson =  new Gson();
		Dato dato = gson.fromJson(mensaje, Dato.class);
		//dato.setOrden(orden);
		
		if(datos.size()<4) {
			datos.add(dato);
		}
		
	}

}
