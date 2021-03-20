import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class editor implements ActionListener{

	JTextPane area = new JTextPane();	
	//file	
		JMenuItem new_file = new JMenuItem("new file");									
		JMenuItem open_file = new JMenuItem("open file");
		JMenuItem save_file = new JMenuItem("save");
	//edit 
	JMenuItem paste = new JMenuItem("paste");									
	JMenuItem cut = new JMenuItem("cut");
	JMenuItem copy = new JMenuItem("copy");

	JButton plus = new JButton("+");
	JButton minus = new JButton("-");

	int font_size = 16;
	public static void main(String[] args){
		new editor(1000 , 700);
	}
	public editor(int width , int height){
		JFrame frame = new JFrame("TEXT EDITOR");			
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//text area
			area.setBounds(0 , 0 , 1000 , 700);
			Font font = new Font("Serif Plain", Font.BOLD, font_size);
			area.setFont(font);
			frame.add(area);
		//text area

		//jmenu 
			JMenuBar mb = new JMenuBar();  	
			JMenu file  = new JMenu("file");
			JMenu edit  = new JMenu("edit");
			JMenu help  = new JMenu("help");
			// file	
												
				file.add(new_file);
				file.add(open_file);
				file.add(save_file);
				save_file.addActionListener(this);
				open_file.addActionListener(this);
				new_file.addActionListener(this);
			//file	
			//edit
				edit.add(paste);
				edit.add(cut);
				edit.add(copy);
				cut.addActionListener(this);
				paste.addActionListener(this);
				copy.addActionListener(this);
			//edit
			mb.add(file);	
			mb.add(edit);	
			mb.add(help);	
			mb.add(plus);
			mb.add(minus);
			frame.setJMenuBar(mb); 
		//jmenu
		//buttons 
			plus.addActionListener(this);
			minus.addActionListener(this);
		//buttons
		frame.setJMenuBar(mb);	
		frame.setVisible(true);
	}	
	public void actionPerformed(ActionEvent e){
		//file
		if(e.getSource() == new_file){
			area.setText("");
		}
		if(e.getSource() == save_file){

				int response;
				File file; 

				JFileChooser chooser = new JFileChooser(".");

				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setDialogTitle("save a File");
				response = chooser.showSaveDialog(null);	

				if(response == JFileChooser.APPROVE_OPTION){

					String text = area.getText();
					file = chooser.getSelectedFile();
					try{	
						FileWriter fw = new FileWriter(file.getPath());
						fw.write(text);
						fw.flush();
						fw.close();

					}catch(Exception e2){
						e2.printStackTrace();
					}
				}
		}
		if(e.getSource() == open_file){

			area.setText(" ");

			File file; 
			Scanner filein;		
			int response;
			JFileChooser chooser = new JFileChooser(".");

			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			response = chooser.showOpenDialog(null);

			if(response == JFileChooser.APPROVE_OPTION){
				file = chooser.getSelectedFile();
							
				try{
				FileReader reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader);
				area.read(br , null);

				}catch(Exception exe){
					exe.printStackTrace();
				}
			}

		}

		//edit 
		if(e.getSource() == cut){
			area.cut();
		}

		if(e.getSource() == copy){
			area.copy();
		}

		if(e.getSource() == paste){
			System.out.println("hellowrol");
			area.copy();
		}

		//buttons
		if(e.getSource() == plus){
			font_size += 2;
			Font font = new Font("Serif Plain", Font.BOLD, font_size);
			area.setFont(font);	
		}

		if(e.getSource() == minus){
			font_size -= 2;
			Font font = new Font("Serif Italic", Font.BOLD, font_size);
			area.setFont(font);	
		}
	}
}