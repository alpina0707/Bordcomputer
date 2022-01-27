package device;

//CD Klasse
interface Device {
	
	//private String deviceName;
	//public Device(String Name ); //{deviceName = Name;}
	
	
	public void lauder();
	public void quieter();
	public int getVolume();
	public String getInfoText();
	public void next();
	public void prev();
	
	public String[] getOptions() throws SecurityException, ClassNotFoundException;
	public void chooseOption(int opt) throws RuntimeException, ReflectiveOperationException;
	public String play();
	
}
