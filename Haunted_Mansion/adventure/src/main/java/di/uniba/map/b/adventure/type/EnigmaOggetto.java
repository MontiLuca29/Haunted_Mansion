package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;
import di.uniba.map.b.adventure.type.AdvObject;

public class EnigmaOggetto extends Enigma{
	
	private String stringSuccess;
	private String stringFailure;
	private int solutionId;
		
	public EnigmaOggetto(int id, String question,String success,String failure,int idsolution) {
		super(id,question);
		this.stringSuccess = success;
		this.stringFailure = failure;
		this.solutionId = idsolution;
	};
	
	
	
	public void solve(AdvObject obj){
		if(obj == null) {
			System.out.println(this.getQuestion());
		}else{
			if(obj.getId()== this.solutionId) {
				setSolved();
				System.out.println(this.stringSuccess);
			}else {
				System.out.println(this.stringFailure);
			}
		}
	}
	
}