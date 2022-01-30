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
	public void quieter() {
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
		return "Your Radio.de App";
	}

	@Override
	public String[] getItemList() {
		String[] items = new String[Playlist.size()];
		for(int i=0;i<items.length;i++) {
			items[i]=Playlist.get(i);
		}
		return items;
	}

	@Override
	public String play() {
		return ("Radio:0"+(Playlist.indexOf(playTrack)+1)+ " "+this.playTrack);
	}
}
