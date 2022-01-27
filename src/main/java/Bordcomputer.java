import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner;

public class Bordcomputer {

	//private static String[] deviceName = new String[3];
	private static String[] installedDevices=new String[3];
	//private int deviceNr = 0;
	
	
	//Verwendung von Hilfvariable -> Nachfragen
	Class<?> c;
	Object aktiveDevice=null;
	
	public Bordcomputer() {
		this.readConfig();
		try {
			this.setDevices();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readConfig() {
		try(FileReader reader =  new FileReader("Geraete.config")) {
			Properties properties = new Properties();
			properties.load(reader);

			installedDevices[0] = properties.getProperty("CD");
			installedDevices[1] = properties.getProperty("Radio");
			installedDevices[2] = properties.getProperty("USB");
			System.out.println(installedDevices[0]);
			System.out.println(installedDevices[1]);
			System.out.println(installedDevices[2]);
			//deviceNr = -1;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void setDevices() throws ClassNotFoundException {
		if(installedDevices[0] == null) {
			System.out.println("Kein Device verf�gbar!");

		} else {
			this.c=Class.forName(this.installedDevices[0]);
			try {
				this.aktiveDevice=this.c.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void shutdown() {
		System.out.println("\n Bordcomputer wird heruntergefahren...");
		System.exit(0);
	}
	
	public void play() {
		Method methode;
		try {
			if(installedDevices[0] != null) {
			methode = this.c.getDeclaredMethod("play");
			System.out.println(methode.invoke(this.aktiveDevice));
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(methode.invoke(this.aktiveDevice));		
	}
	
	@SuppressWarnings("deprecation")
	public void changeDevice() {
		//Method methode;
		try {
			this.installedDevices=changeToNext(this.installedDevices);
			if(installedDevices[0] != null) {
				this.c=Class.forName(this.installedDevices[0]);
				this.aktiveDevice=this.c.newInstance();
				System.out.println("Auf "+this.installedDevices[0]+" gewechselt.");
			}
		} catch (SecurityException | IllegalArgumentException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	
	public void next()  {
		try {
			TakeMethodsAndRun("next");
		} catch (SecurityException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void prev()  {
		try {
			TakeMethodsAndRun("prev");
		} catch (SecurityException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//============ Hilf-Funktionen ==============================
	private String[] changeToNext(String[] inputFeld) {
		String[] outputFeld = new String[inputFeld.length];
		for(int i=0;i<inputFeld.length;i++) {
			if(i==0) {outputFeld[inputFeld.length-1]=inputFeld[i];}
			else{outputFeld[i-1]=inputFeld[i];}
		}
		return outputFeld;
	}
	private void TakeMethodsAndRun(String taste)  {
		Method methode;
		try {
			if (installedDevices[0] != null) {
			methode = this.c.getDeclaredMethod(taste);
			methode.invoke(this.aktiveDevice);
			}
			//methode=this.c.getDeclaredMethod("play");
			//System.out.println(methode.invoke(this.aktiveDevice));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	//============================================================
	
	public void louder(int p) {
		Method methode;
		try {
			methode = this.c.getDeclaredMethod("louder");
			for(int i = 0; i < p; i++) {
				methode.invoke(this.aktiveDevice);
				}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void quieter(int p) {
		Method methode;
		try {
			methode = this.c.getDeclaredMethod("quieter");
			for(int i = 0; i < p; i++) {
				methode.invoke(this.aktiveDevice);
				}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showVolume() {
		if (installedDevices[0] != null) {
		for(Method m:this.c.getDeclaredMethods()) {
			if(m.getName()=="getVolume") {
				try {
					System.out.println("Die aktuelle Lautst�rke betr�gt " + m.invoke(this.aktiveDevice));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
			}
		}
	}
	public void showOptions() {
		System.out.println("Folgende Optionen sind verf�gbar:");

		Method methode;
		try {
			methode = this.c.getDeclaredMethod("getOptions");
			String[] erg=(String[]) methode.invoke(this.aktiveDevice);
			for(String s:erg) {	
				System.out.println(s);
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void enterOption(int choice) {
		Method methode;
		try {
			methode = this.c.getDeclaredMethod("getOptions");
			String[] erg=(String[]) methode.invoke(this.aktiveDevice);
			
			for(Method m:this.c.getDeclaredMethods()) {
				if(m.getName()=="chooseOption") {
					m.invoke(this.aktiveDevice,erg[choice-1]);
					}
				}
			methode=this.c.getDeclaredMethod("play");
			System.out.println(methode.invoke(this.aktiveDevice));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args) throws ClassNotFoundException {
		Bordcomputer b = new Bordcomputer();

		Scanner scanner=new Scanner(System.in);
		if(installedDevices[0] != "") {
			System.out.println("Bordcomputer ist betriebsbereit. " + installedDevices[0] + " ist ausgew�hlt");
			
		} else {
			System.out.println("Bordcomputer ist betriebsbereit aber es ist kein Ger�t angeschlossen!");
		}
		
		while(true) {
			System.out.println("\nFolgende Eingaben sind m�glich: \n "
					+ "0 - Bordcomputer ausschalten \n "
					+ "1 - Device wird abgespielt \n "
					+ "2 - Device wird gewechselt \n "
					+ "3 - N�chster Sender/Titel wird gew�hlt \n "
					+ "4 - Voheriger Sender/Titel wird gew�hlt \n "
					+ "5 - Lautst�rke erh�hen \n "
					+ "6 - Lautst�rke verringern \n "
					+ "7 - Aktuelle Lautst�rke anzeigen \n "
					+ "8 - Zeige verf�gbare Musik/Radio-Optionen an \n "
					+ "9 - Option eingeben \n ");
			Integer eingabe=Integer.parseInt(scanner.next());
			switch(eingabe){
	        case 0:
	        	b.shutdown();
	            break;
	        case 1:
	        	b.play();
	            break;
	        case 2:
	        	b.changeDevice();
	        	b.setDevices();
	            break;
	        case 3:
	        	b.next();
	        	break;
	        case 4:
	        	b.prev();
	        	break;
	        case 5:
	        	System.out.println("Um wieviel soll die Lautst�rke erh�ht werden?");
	        	Integer p = Integer.parseInt(scanner.next());
	        	b.louder(p);
	        	break;
	        case 6:
	        	System.out.println("Um wieviel soll die Lautst�rke verringert werden?");
	        	Integer q = Integer.parseInt(scanner.next());
	        	b.quieter(q);
	        	break;
	        case 7:
	        	b.showVolume();
	        	break;
	        case 8:
	        	b.showOptions();
	        	break;
	        case 9:
	        	System.out.println("welche Musik-/SenderNr. soll abgespielt werden?");
	        	Integer nr = Integer.parseInt(scanner.next());
	        	b.enterOption(nr);
	        	break;
	        default:
	            System.out.println("Falsche Eingabe.");
	            break;
	        }
			
		}
		

	}
	
}
