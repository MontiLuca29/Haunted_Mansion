package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;
import di.uniba.map.b.adventure.type.AdvObject;

public abstract class Enigma{
	private final int id;
	private boolean solved;
	private String question;
	
	public Enigma(int id,String question){
		this.id = id;
		this.solved = false;
		this.question = question;
	}
	
	public abstract void solve(AdvObject obj);
	
	public String getQuestion(){
		return question;
	}
	
	protected void setSolved(){
		this.solved = true;
	}

	public boolean IsSolved() {
		return solved;
	};
	
@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
	        }
		if (obj == null) {
			return false;
	        }
		if (getClass() != obj.getClass()) {
			return false;
			}
		final Enigma other = (Enigma) obj;
			if (this.id != other.id) {
				return false;
	        }
	        	return true;
	      }
}
