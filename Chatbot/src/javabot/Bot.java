package javabot;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Bot extends JFrame implements KeyListener{

	JPanel p=new JPanel();
	JTextArea dialog=new JTextArea(20,50);
	JTextArea input=new JTextArea(1,50);
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	
	//I setup this dual array of strings to make it easy to create input and outputs for the
	//Chat bot
	String[][] chat={
		//greetings
		{"hi","hello","hola","good morning", "good afternoon", "howdy"},
		{"hi","hello","hey", "sup..."},
		//more greetings
		{"how are you?","how r you?","how r u?","how are u?", "how are you doing?", "how are you","how r you","how r u","how are u", "how are you doing"},
		{"good","I'm doing well"},
		//Name
		{"what is your name", "what is your name?", "what are you?", "what are you", "who are you?", "who are you"},
		{"Joe, your personal chat bot","Joe Mama"},
		//Basketball
		{"what do you do?", "what do you like to do?", "what do you do", "what do you like to do"},
		{"I like talking about Basketball","I like Basketball"},
		//Basketball questions
		{"who is your favorite player?", "what do you like about basketball", "who is your favorite player", "what do you like about basketball?", "who is your favorite basketball player", "who is your favorite basketball player?"},
		{"MICHAEL JORDAN","Michael Jordan, because he is insane at the craft!"},
		//Basketball questions part 2
		{"what is your favorite team?", "what is your favorite team", "What team do you like?", "what team do you like", "What team do you like the most?", "what team do you like the most"},
		{"I do not have a preference on team","I don't really have a favorite team"},
		//Basketball players
		{"stephen curry", "lebron james", "michael jordan", "larry bird", "shaq", "kevin durant", "james harden", "giannis antetokounmpo", "kawhi leonard", "anthony davis", "Luka Doncic", "jimmy butler", "damian lillard"},
		{"He is an insane basketball player","He's a terrible player", "I agree he is very underated!", "I like him too!"},
		//Questions for the user
		{"ok", "ok!", "nice", "nice!", "cool", "cool!", "ask me a question"},
		{"Do you like basketball?","Do you like Michael Jordan?", "Who is your favorite player?"},
		//bye
		{"bye", "adios", "bye!", "see you later", "see you later!", "see ya!", "see ya"},
		{"See Ya!"},
		//yes
		{"yes"},
		{"Ok, Cool!","Sure!","whatever"},
		//no
		{"no"},
		{"Ok, lame!","Sure... I guess","whatever"},
		//default
		{"I love you","Sorry, I don't really understand","I don't understand", "I'm confused", "I don't know."}
	};
	
	public static void main(String[] args){
		new Bot();
	}
	
	//generates the world and GUI of the chatbot
	public Bot(){
		super("Basketball ChatBot");
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
	
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(170,0,250));
		add(p);
		
		setVisible(true);
	}
	// makes the text editable in the textbox
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}
	
	//finds match of input with the inputs in the array
	public boolean inArray(String in,String[] str){
		boolean m=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				m=true;
			}
		}
			return m;
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}
	
	
	//essentially what makes the chatbot operate
	//displays your inputs and the responses of the bot
	//iterates through the responses of the bot
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			//-----grab quote-----------
			String quote=input.getText();
			input.setText("");
			addText("-->You:\t"+quote);
			quote.trim();
			byte output=0;
			
			int j=0;//which group we're checking
			//algorithm used to randomly select the outputs of the chatbot
			while(output==0){
				if(inArray(quote.toLowerCase(),chat[j*2])){
					output=2;
					int r=(int)Math.floor(Math.random()*chat[(j*2)+1].length);
					addText("\n-->Joe\t"+chat[(j*2)+1][r]);
				}
				j++;
				if(j*2==chat.length-1 && output==0){
					output=1;
				}
			}
			
			//if the input doesn't match the ones in the array, default values are printed
			if(output==1){
				if(quote.contains("team")) {
					addText("\n-->Joe\t"+ "I don't have a preference on team.");
				}
				else if(quote.contains("kobe") || quote.contains("Kobe")) {
					addText("\n-->Joe\t"+ "Yes, Rest in Peace!");
				}
				else if(quote.contains("hate") && quote.contains("Jordan")) {
					addText("\n-->Joe\t"+ "HOW DARE YOU!!!!");
				}
				else if(quote.contains("soccer")) {
					addText("\n-->Joe\t"+ "I don't care about that sport.");
				}
				else if(quote.contains("football")) {
					addText("\n-->Joe\t"+ "I don't care about that sport.");
				}
				else {
					int r=(int)Math.floor(Math.random()*chat[chat.length-1].length);
					addText("\n-->Joe\t"+chat[chat.length-1][r]);
				}
			}
			addText("\n");
		}
	}
	
	
}
