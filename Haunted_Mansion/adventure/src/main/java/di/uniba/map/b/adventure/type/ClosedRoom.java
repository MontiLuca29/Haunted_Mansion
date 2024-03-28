package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

public class ClosedRoom extends Room{
	
	private Enigma enigmaN;
	private Enigma enigmaS;
	private Enigma enigmaW;
	private Enigma enigmaE;
	
	
	public ClosedRoom(int id, String name, String description) {
        super(id,name,description);
        this.enigmaN = null;
        this.enigmaS = null;
        this.enigmaE = null;
        this.enigmaW = null;
    }
	
	
	public int canNorth(){
		if(this.getNorth() == null) {
			return -1;
		}else{
			if(this.enigmaN.IsSolved()) {
				return 1;
			}else {
				return 0;
			}
		}
	}
	
	public int canSouth(){
		if(this.getSouth() == null) {
			return -1;
		}else{
			if(this.enigmaS.IsSolved()) {
				return 1;
			}else {
				return 0;
			}
		}
	}

public int canEast(){
		if(this.getEast() == null) {
			return -1;
		}else{
			if(this.enigmaE.IsSolved()) {
				return 1;
			}else {
				return 0;
			}
		}
	}

public int canWest(){
		if(this.getWest() == null) {
			return -1;
		}else{
			if(this.enigmaW.IsSolved()) {
				return 1;
			}else {
				return 0;
			}
		}
	}
	
	public Enigma getEnigmaN() {
		return enigmaN;
	}
	
	public void setEnigmaN(Enigma e) {
		this.enigmaN = e;
	}
	
	public Enigma getEnigmaS() {
		return enigmaS;
	}
	
	public void setEnigmaS(Enigma e) {
		this.enigmaS = e;
	}

	public Enigma getEnigmaE() {
		return enigmaE;
	}
	
	public void setEnigmaE(Enigma e) {
		this.enigmaE = e;
	}
	
	public Enigma getEnigmaW() {
		return enigmaW;
	}
	
	public void setEnigmaW(Enigma e) {
		this.enigmaW = e;
	}
}