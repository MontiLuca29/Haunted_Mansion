package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnigmaChiuso extends Enigma{
	
	private String answerA;
	private String answerB;
	private String answerC;
	private char answer;
	
	public EnigmaChiuso(int id,String question,String answerA,String answerB,String answerC,char answer) {
		super(id,question);
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answer = answer;
	}
	
	public void solve(AdvObject obj){
		String question;
		char risp;
		Scanner scan = new Scanner(System.in);
		question = this.getQuestion();
		System.out.println(question);
		System.out.println(this.answerA);
		System.out.println(this.answerB);
		System.out.println(this.answerC);
		risp = scan.next().charAt(0);
		if (risp == this.answer) {
			setSolved();
		}else{
			System.out.println("Risposta errata!");
		};
	}
}