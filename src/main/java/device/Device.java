package device;

public interface Device {

	void louder();
	void quieter();
	int getVolume();
	void next();
	void prev();
	String getInfoText();
	String[] getItemList();
	String play();
	}
