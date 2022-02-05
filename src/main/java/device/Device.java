package device;

public interface Device {

	void louder();
	void quieter();
	int getVolume();
	void next();
	void prev();
	String getInfoText();

	//returns an array with Method-Names  to bee listed
	String[] getOptions();
	//returns an array with contents to bee played
	String[] getItemList();

	String play();
	}
