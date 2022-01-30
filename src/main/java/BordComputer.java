import device.Device;

import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Scanner;

public class BordComputer {

	private static String[] deviceNames;
	private static Device[] installedDevices;
	private Device activeDevice=null;

	public BordComputer() {
		readConfig();
		activeDevice =installedDevices[0];
	}

	//BordComputers specifics methods
	private void readConfig() {
		try(FileReader reader = new FileReader("Geraete.config")) {
			Properties properties = new Properties();
			properties.load(reader);
			String[] values= properties.values().toArray(new String[0]);

			//defining a new size for deviceNames[] and installedDevices[]
			deviceNames=new String[values.length];
			installedDevices=new Device[values.length];
			//saving this deviceNames
			for(int i=0;i<properties.size();i++){
				deviceNames[i]=values[i];
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		//loading all instances
		setDevices();
		activeDevice =installedDevices[0];
	}

	public void changeDevice() {
		Device[] tempDevices=new Device[installedDevices.length];
		tempDevices[0]=installedDevices[1];
		tempDevices[1]=installedDevices[2];
		tempDevices[2]=installedDevices[0];
		//replaces the lists
		installedDevices=tempDevices;
		//setting activeDevice
		activeDevice =installedDevices[0];
		System.out.println(activeDevice.getInfoText()+" ist activate");
	}

	public void setDevices(){
		try {
			Class<?> c;
			for(int i=0;i<deviceNames.length;i++) {
				c= Class.forName(this.deviceNames[i]);
				installedDevices[i] = (Device) c.getConstructor().newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void shutdown() {
		System.out.println("\n Bordcomputer wird heruntergefahren...");
		System.exit(0);
	}

	// Devices Methods
	public void showItemList(){
		System.out.println("All Items in this medium:");
		for (String item:activeDevice.getItemList()) {
			System.out.println(item);
		}
	}

	public void play(){
		System.out.println(activeDevice.play());
	}
	public void prev(){
		activeDevice.prev();
		System.out.println(activeDevice.play());
	}
	public void next(){
		activeDevice.next();
		System.out.println(activeDevice.play());
	}

	//Device - Volumes
	public void showVolume(){
		System.out.println("Volume: "+activeDevice.getVolume());
	}
	public void louder(){
		activeDevice.louder();
		showVolume();
	}
	public void quieter(){
		activeDevice.quieter();
		showVolume();
	}

	//===================== Main =======================================
	public static void main (String[] args) throws ClassNotFoundException {
		BordComputer b = new BordComputer();

		Scanner scanner=new Scanner(System.in);
		if(b.activeDevice!=null) {
			System.out.println("BordComputer is running. " + b.activeDevice.getClass().getSimpleName() + " ist activate.");
			
		} else {
			System.out.println("BordComputer is running but no device set!");
		}
		
		while(true) {
			System.out.println("\nFollowing entries are possible: \n "
					+ "0 - BordComputer shutdown \n "
					+ "1 - Play device-item \n "
					+ "2 - change device \n "
					+ "3 - Next sender/music item \n "
					+ "4 - Prev sender/music item \n "
					+ "5 - increase volume \n "
					+ "6 - decrease volume \n "
					+ "7 - show volume \n "
					+ "8 - show itemList of this device \n ");
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
	            break;
	        case 3:
	        	b.next();
	        	break;
	        case 4:
	        	b.prev();
	        	break;
	        case 5:
	        	b.louder();
	        	break;
	        case 6:
	        	b.quieter();
	        	break;
	        case 7:
	        	b.showVolume();
	        	break;
	        case 8:
	        	b.showItemList();
	        	break;
	        default:
	            System.out.println("Wrong input.");
	            break;
	        }
		}
	}
	
}
