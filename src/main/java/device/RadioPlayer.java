package device;

import java.util.ArrayList;

public class RadioPlayer implements Device {
	
	 ArrayList<String>Playlist=new ArrayList<String>();
	 int Lautstaerke = 0;
	 String playTrack="";
	 
	public RadioPlayer() {
		super();
		Playlist.add("Radio YouFM");
		Playlist.add("Raio Teddy");
		Playlist.add("Radio MegaHits");
		playTrack=Playlist.get(0);
	}
	
	@Override
	public void louder() {
		if(Lautstaerke < 100) {
			Lautstaerke += 1;
		} else {
			Lautstaerke = 100;
		}		
	}
	
	@Override
	public void quiter() {
		if(Lautstaerke > 0) {
			Lautstaerke -= 1;
		} else {
			Lautstaerke = 0;
		}
	}

	@Override
	public int getVolume() {
		return this.Lautstaerke;
	}

	@Override
	public void next() {
		int correntIdex=Playlist.indexOf(playTrack);
		int nextIndex=(correntIdex+1)%Playlist.size();
		playTrack=Playlist.get(nextIndex);
	}

	@Override
	public void prev() {
		int correntIdex=Playlist.indexOf(playTrack);
		int nextIndex=Playlist.size()-1;
		if(correntIdex!=0){nextIndex=(correntIdex-1);}
		playTrack=Playlist.get(nextIndex);
	}

	@Override
	public String getInfoText() {
		return null;
	}

	@Override
	public String[] getOptions() {
		 String[] options = new String[3];
		 for(int i=0;i<options.length;i++) {
			 options[i]=Playlist.get(i);
		 }
		return options;
	}

	@Override
	public void chooseOption(String opt) {	
		this.playTrack=this.Playlist.get(Playlist.indexOf(opt));	
	}

	@Override
	public String play() {
		return ("Radio:0"+(Playlist.indexOf(playTrack)+1)+ " "+this.playTrack);
	}
}
