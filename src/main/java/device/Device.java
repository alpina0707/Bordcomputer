package device;

public interface Device {

	void louder();
	void quiter();
	int getVolume();
	void next();
	void prev();
	String getInfoText();
	String[] getOptions();
	void chooseOption(String opt);
	String play();
	}
