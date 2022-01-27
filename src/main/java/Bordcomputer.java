import device.CDDevice;

import java.io.FileReader;
 import java.util.Properties;
 import java.util.Scanner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//uebung 1
 
public class Bordcomputer {
	
	
	private String[]  className = new String[3];
	private int deviceNr = 0;
	private int option;
	private Class<?> cls;
	private static CDDevice CDobj;
	
	//*********************************
	private void readConfig() {
		 try(FileReader reader =  new FileReader("Geraete")) {
		        Properties properties = new Properties();
		        properties.load(reader);
		       
		        className[deviceNr++] = properties.getProperty("CD");
		        className[deviceNr++] = properties.getProperty("Radio");
		        className[deviceNr++] = properties.getProperty("USB");
			    System.out.println(className[0]);
			    System.out.println(className[1]);
			    System.out.println(className[2]);
		       deviceNr = -1;
		       }catch (Exception e) {;
		       e.printStackTrace();
		       }
		
	}
	//*********************************
	public void changeDevice() {
		deviceNr = (deviceNr +1 )% 3;
		
		try {
			setDevice();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		
	}
	//*********************************
	private void setDevice() throws ClassNotFoundException{
		cls= Class.forName(className[deviceNr]);
		System.out.println("class Name is "+cls.getSimpleName());
		
	}
	//*********************************
	public void shutdown() {
		System.exit(0);
	}
	//*********************************
	public void showVolume() throws NoSuchMethodException, SecurityException, ReflectiveOperationException, IllegalArgumentException, InvocationTargetException {
		Method getVolumCall = cls.getDeclaredMethod("getVolume");
	    System.out.println("The Sound level is "+getVolumCall.invoke(CDobj));
	}
	//*********************************
	public void showOptions() throws SecurityException, IllegalArgumentException, ReflectiveOperationException {
		Method getOptionMethodCall = cls.getDeclaredMethod("getOptions");
		String[] methods =(String[]) getOptionMethodCall.invoke(CDobj);
		for(int i = 0 ; i< methods.length ; i++) {
			System.out.println( "Option["+(i+1)+"] : "+methods[i]);
		}
		
	}
	//*********************************
	public void interOption(int p) throws ReflectiveOperationException, RuntimeException {
		Method chooseOptiontMethodCall = cls.getDeclaredMethod("chooseOption",int.class);
		chooseOptiontMethodCall.invoke(CDobj,8);
	}
	//*********************************
		public void next() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Method nextMethodCall = cls.getDeclaredMethod("next");
			nextMethodCall.invoke(CDobj);
		}
		//***************************************
		public void prev() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Method prevMethodCall = cls.getDeclaredMethod("prev");
			prevMethodCall.invoke(CDobj);
		}
		//***************************************
		public void play() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { 
			Method playMethodCall = cls.getDeclaredMethod("play");
			System.out.println(playMethodCall.invoke(CDobj));
		}
		//***************************************
		public void lauder(int s) throws ReflectiveOperationException, SecurityException {
			
			Method getVolumCall = cls.getDeclaredMethod("getVolume");
			Method lauderMethodCall = cls.getDeclaredMethod("lauder");
			for(int i = 1;i <= s ; i++) {
				if(((int) getVolumCall.invoke(CDobj)) > 7) {
					System.out.println( "Too much. The highst Sound Level is 7  ");
					break;
					}
				else lauderMethodCall.invoke(CDobj);
				}
			}
		//***************************************
        public void quieter(int s) throws ReflectiveOperationException, SecurityException {
			
			Method getVolumCall = cls.getDeclaredMethod("getVolume");
			Method quieterMethodCall = cls.getDeclaredMethod("quieter");
			for(int i = 1;i <= s ; i++) {
				if(((int) getVolumCall.invoke(CDobj)) < 1) {
					System.out.println( "Too little. The lowest Sound Level is 1  ");
					break;
					}
				else quieterMethodCall.invoke(CDobj);
				}
			}
		//***************************************
	public static void main(String[] args) throws SecurityException, IllegalArgumentException, ReflectiveOperationException {
		// RD , CD oder USB
		Bordcomputer ob=new Bordcomputer();
		CDobj = new CDDevice("Never Gone",3);
		ob.readConfig();
		ob.changeDevice();
		ob.showOptions();
		ob.lauder(2);
		ob.showVolume();
		ob.quieter(1);
		ob.showVolume();
		ob.interOption(8);
		//******************+++
		
		Scanner in = new Scanner(System.in);
		
		/*do {
			System.out.println( "\n1-Lauder \n2-Quieter \n3-Show Volume"
					+ "\n4-Next \n5-Prev\n9-Number Of Tracks \n10-Shutdown");
			o = in.nextInt();
			switch(o) {
			case 1:
				
				break;
			case 2:
				Method QuieterMethodCall = cls.getDeclaredMethod("quieter", int.class);
				stars =(String) QuieterMethodCall.invoke(CDobj, 1);
				System.out.println( stars);
			break;
			case 3:
				showVolume();
			break;
			
			
			case 10:shutdown();
			break;
			}

		}while(o != 10 );*/
		//**********************
		
	}

}
