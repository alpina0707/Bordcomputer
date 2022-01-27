package device;

import java.lang.reflect.Method;
//old one
public class USBDevice implements Device {
	
	//**************Variables*************************
		private int trackNumber ;
		private String deviceName;
		private String albumName;
		private int numberOfTracks ;
		private boolean on;
		private int volume;
		private Class<?> cls;
		
		//**************Functions*************************
		public USBDevice(String AlbumName,int tracksNumber) {
			this.deviceName = "USB";
			this.albumName = AlbumName;
			this.trackNumber = 0;
			this.numberOfTracks = tracksNumber;
			this.on = true;
			this.volume=0;
		}
		//***************************************
		public void next() {
			trackNumber =(trackNumber + 1)% (numberOfTracks);
		}
		//***************************************
		public void prev() {
			/*trackNumber--;
			if(trackNumber < 1)
				trackNumber = 1;*/
			trackNumber =(trackNumber - 1)% (numberOfTracks);
		}
		//***************************************
		public String play() { 
			on = true;
			return "Track "+ trackNumber + "plays now.";
		}
		//***************************************
		public void lauder() { 
			
			 this.volume += 1;
		
		}
		//***************************************
		public void quieter() {
			
			this.volume -= 1;
		}
		//***************************************
		public int getVolume() {
			
			return this.volume;
		}
		
		//***************************************
		public String getInfoText() {
			return "The Device is "+this.deviceName+" \nThe Name of the Album is :"+this.albumName+ 
					"\nIt has " +this.numberOfTracks+" Tracks";
		}
		//***************************************
		public String[] getOptions() throws SecurityException, ClassNotFoundException {
			
			Method[] methods = cls.forName("USBDevice").getDeclaredMethods();
			String[] sm = new String[methods.length];
			int i = 0;
			 for (Method m:methods) {
				 sm[i] = m.getName(); 
				 i++;
			 }       
			return sm ;
		}
		//***************************************
		
		public void setCDName(String name) {this.albumName = name;}
		public String getCDName() { return albumName;}
		public void setTrackNumber(int n) { trackNumber = n ;}
		public int getTrackNumber() { return trackNumber;}
		
		//***************************************
		public void chooseOption(int opt) throws RuntimeException, ReflectiveOperationException {
			if(opt > 0 && opt<13 ) {
				Method[] methods = cls.forName("CDDevice").getDeclaredMethods();
				String[] sm = new String[methods.length];
				switch(opt) {
				case 1:
					next();
					break;
				case 2:
					prev();
				break;
				case 3:
					getVolume();
				break;
				case 4:String[] op=getOptions();
				break;
				case 5:System.out.println(play());
				break;
				case 6:System.out.println(getVolume());
				break;
				case 7:String[] s=getOptions();
				break;
				case 8:System.out.println(getInfoText());
				break;
				case 9:setCDName("new Name");
				break;
				case 10:System.out.println(getCDName());
				break;
				case 11:setTrackNumber(3);
				break;
				case 12:System.out.println(getTrackNumber());
				break;
				}
			}
			
			
		}
		//end of interfce methods
		//***************************************

	}

