package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;

import events.IObserver;
import model.Dato;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements IObserver {

	UDPconnection udp;
	Dato dato;
	PImage bowl;
	PImage papitas;
	PImage granizado;
	PImage brownie;
	Gson gson;
	private ArrayList<Dato> datos;
	int y = 50;
	int y2 = 100;
	int y3 = 80;




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

		// imagenes
		bowl = loadImage("data/bowl.png");

		papitas = loadImage("data/papitas.png");

		granizado = loadImage("data/granizado.png");

		brownie = loadImage("data/brownie.png");

	

	
	}

	public void draw() {
		background(234, 225, 200);
		fill(19, 31, 99);
		textSize(20);
		text("BIENVENIDO A WONKA", 50, 30);
		pintar();

	}

	@Override
	public void mousePressed() {


		
		for (int i = 0; i < datos.size(); i++) {
			
			if (dist(mouseX,mouseY, 90, y + (120 * i))<100) {
				

				udp.sendMessage(datos.get(i).getNum() +" listo");
				datos.remove(datos.get(i));

		}
			
		}

	}

	public void pintar() {


		for (int i = 0; i < datos.size(); i++) {

			switch (datos.get(i).getOrden()) {

			case "granizado":

				image(granizado, 50, y + (120 * i), 100, 100);

				 DateFormat dateFormat = new SimpleDateFormat("HH:mm");
				 Date date = new Date();
				
				 fill(19,31,99);
				 textSize(12);
				 text(datos.get(i).getNum(),200,y3+(120*i));
				 text("Pedido a las: " + dateFormat.format(date),200,y2+(120*i));
				

				break;

			case "papitas":

				image(papitas, 50, y + (120 * i), 100, 100);
				
				 DateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
				 Date date2 = new Date();
				
				 fill(19,31,99);
				 textSize(12);
				 text(datos.get(i).getNum(),200,y3+(120*i));
				 text("Pedido a las: " + dateFormat2.format(date2),200,y2+(120*i));
				

				break;

			case "bowl":

				image(bowl, 50, y + (120 * i), 100, 100);
				
				 DateFormat dateFormat3 = new SimpleDateFormat("HH:mm");
				 Date date3 = new Date();
				
				 fill(19,31,99);
				 textSize(12);
				 text(datos.get(i).getNum(),200,y3+(120*i));
				 text("Pedido a las: " + dateFormat3.format(date3),200,y2+(120*i));
				

				break;

			case "brownie":

				image(brownie, 50, y + (120 * i), 100, 100);
				
				 DateFormat dateFormat4 = new SimpleDateFormat("HH:mm");
				 Date date4= new Date();
				
				 fill(19,31,99);
				 textSize(12);
				 text(datos.get(i).getNum(),200,y3+(120*i));
				 text("Pedido a las: " + dateFormat4.format(date4),200,y2+(120*i));
				

				break;

			}

		}

	}

	@Override
	public void recibirMensaje(String mensaje) {

		Gson gson = new Gson();
		Dato dato = gson.fromJson(mensaje, Dato.class);
		
		dato.setNum("Pedido # "+ datos.size());

		if (datos.size() < 4) {
			datos.add(dato);
		}
		

	}

	

}
