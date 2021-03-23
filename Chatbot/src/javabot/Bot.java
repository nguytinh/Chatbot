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
	String[][] chatBot={
		//standard greetings
		{"hi","hello","hola","good morning", "good afternoon", "howdy"},
		{"hi","hello","hey", "sup..."},
		//question greetings
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
		{"stephen curry", "lebron james", "michael jordan", "kobe bryant", "larry bird", "shaq", "kevin durant", "james harden", "giannis antetokounmpo", "kawhi leonard", "anthony davis", "Luka Doncic", "jimmy butler", "damian lillard"},
		{"He is an insane basketball player","He's a terrible player", "I agree he is very underated!", "I like him too!"},
		//Questions for the user
		{"ok", "ok!", "nice", "nice!", "cool", "cool!", "ask me a question"},
		{"Do you like basketball?","Do you like Michael Jordan?", "Who is your favorite player?"},
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
		super("ChatBot");
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
	
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(170,0,200));
		add(p);
		
		setVisible(true);
	}
	//makes the text in the textbox editable by the user
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}
	//sees if the inputted text matches the text in the array
	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
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
			
			byte response=0;
		
			int j=0;//which group we're checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\n-->Joe\t"+chatBot[(j*2)+1][r]);
				}
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}
			
			//-----default--------------
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->Michael\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
	}
	
}
