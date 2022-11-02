import java.io.*;
import java.nio.file.Files;
import javax.swing.border.Border;
import java.awt.Color;
import java.util.Date;
import java.util.Scanner;
import java.awt.*;  
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.*;  
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import org.w3c.dom.Text; 

public class MyNote  implements ActionListener, menuConstants   {
	JFrame f,secf;
	JTextArea space;
	JMenuBar menuBar;
	JMenu File,Edit,Format,View,Help;
	String applicationName=" MyNote ";
	String fileName;  
	File fileRef;  
	FileNameExtensionFilter filter =  new FileNameExtensionFilter("TEXT FILES", "txt", "text");
	   
	Color headColor = Color.decode("#f2f2f2");
	Border empty =    BorderFactory.createMatteBorder(0, 4, 0, 0,Color.WHITE);
	 JFileChooser fileChooser,saveFile;
	 boolean newFileFlag , saved;
	 UndoManager und;
	 //find
	 JTextField t1;
	 
	MyNote(){
		newFileFlag = true;
		saved = false;
		fileName=new String("Untitled");  
		fileRef=new File(fileName);
 		f = new JFrame(fileName + "-" + applicationName);
 		f.setBackground(Color.WHITE);
 		fileChooser = new JFileChooser(); 
 		 filter =  new FileNameExtensionFilter("TEXT FILES", "txt", "text");
 		fileChooser.setFileFilter(filter);
 		  
 		/*********Menu start**********/
        menuBar = new JMenuBar();
        menuBar.setBounds(0,0,2000,30);
        menuBar.setBackground(headColor);
        
        File = new JMenu(" File ");
        Edit = new JMenu(" Edit ");      
        Format = new JMenu(" Format ");       
        View = new JMenu(" View ");        
        Help = new JMenu(" Help ");
 		/*********Menu End**********/
       space = new JTextArea();
       space.setBounds(0,30,2000,2000);
       space.setBorder(empty);
       space.setFont(new Font("Consolas", Font.ROMAN_BASELINE, 15));
       
       fileOpen.addActionListener(this);
       fileNewWindow.addActionListener(this);
       editCopy.addActionListener(this); 
       editCut.addActionListener(this); 
       editPaste.addActionListener(this); 
       editSelectAll.addActionListener(this);
       fileSaveAs.addActionListener(this); 
       fileSave.addActionListener(this); 
       fileNew.addActionListener(this);
       filePrint.addActionListener(this); 
       filePageSetup.addActionListener(this);
       fileExit.addActionListener(this);
       editUndo.addActionListener(this);
       editFind.addActionListener(this);
	 
   File.add(fileNew);File.add(fileNewWindow);File.add(fileOpen);File.add(fileSave);File.add(fileSaveAs);File.add(filePageSetup);File.add(filePrint);File.add(fileExit);  
   Edit.add(editUndo);Edit.add(editCut);Edit.add(editCopy);Edit.add(editPaste);Edit.add(editDelete);Edit.add(editFind);Edit.add(editFindNext);Edit.add(editReplace);Edit.add(editGoTo);Edit.add(editSelectAll);Edit.add(editTimeDate);
   Format.add(formatWordWrap);Format.add(formatFont);
   View.add(viewZoom);View.add(viewStatusBar);viewZoom.add(viewZoomIn);viewZoom.add(viewZoomOut);  
   Help.add(helpHelpTopic);Help.add(helpAboutNotepad);	 
   menuBar.add(File); menuBar.add(Edit); menuBar.add(Format); menuBar.add(View);menuBar.add(Help);
	
		 f.add(menuBar);
		 f.add(space);
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 f.setLayout(null);
		 f.setVisible(true);
		 f.setSize(1000,600);
	}
	//not working
	public void newWindow() {
		secf =  new JFrame(applicationName);
 		secf.setBackground(Color.WHITE);
 		secf.add(menuBar);
        secf.add(space);
        secf.setLayout(null);
        secf.setVisible(true);
        secf.setSize(1000,600);
	}
	/*********/
	 

	@Override
	public  void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == editCopy) 
		    space.copy();	
		if(e.getSource()==editPaste)  
			space.paste();  
		if(e.getSource()==editCut)
		    space.cut();  
		if(e.getSource()==editSelectAll)  
			space.selectAll();
		if(e.getSource() == fileOpen) {
			fileChooser = new JFileChooser(); 	
			fileChooser.setFileFilter(filter);
		    fileChooser.setPreferredSize(new Dimension(800,400));
			int open = fileChooser.showOpenDialog(File);
		    if(open==JFileChooser.APPROVE_OPTION) {
			    File sf = fileChooser.getSelectedFile();
			    String openName = sf.getName();
			    f.setTitle(openName);
			    String filePath = sf.getPath();
			    
				 try {
			         BufferedReader br = new BufferedReader(new FileReader(filePath));
					 String s1 = "", s2 = "";
					 
					 while((s1 = br.readLine()) != null) {
						 s2 += s1 + "\n";
					 }
					 
					 space.setText(s2);
					 br.close();
					 saved = true;
				 }catch(Exception ev){
					 ev.printStackTrace();
					
					 
				 }
			   } 
		     }
	    if(e.getSource() == fileSaveAs) { 
	        saveas();
	    	  }
	    /**************not working*****************/
	    if(e.getSource() == fileSave) {
	    	 
	    	     BufferedWriter Writer; 	  
	    	     FileWriter fout;
	    	     String frameTitle; 
	    		  
	    			 try {
	    				
	    				 frameTitle = f.getTitle();
	    				 System.out.println(frameTitle);
		    			 fileRef = new File(frameTitle);
	    				 fout = new FileWriter(fileRef);
	    				 fout.write(space.getText());
						
						fout.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		 
	    		 
	    	    
		
	    	}
	    /**************************************/
	    if(e.getSource() == fileNew) { 
	        New();
	    	  }
	    if(e.getSource() == filePrint) { 
	        Print();
	    	  }
	    if(e.getSource() == filePageSetup) { 
	    	PageSetup();
	    	  }
	    if(e.getSource() == fileExit) { 
	    	f.dispatchEvent(new WindowEvent(f,WindowEvent.WINDOW_CLOSING));
	    	  }
	    if(e.getSource()==editUndo)  {
	    	 und = new UndoManager();
	    	 
	    	 String tex = space.getText();
	    	  
			
	    	  }
	    if(e.getSource()==editFind) {
	    	find();
	    }  
		   
		
		 
		 
		
		
	}
	
	public void saveas() {
		 saveFile = new JFileChooser(); 	  
		 saveFile.setPreferredSize(new Dimension(800,400));
		 saveFile.setFileFilter(filter);
         int sf = saveFile.showSaveDialog(File);
                   
         String res =  space.getText();
        
         if(sf ==JFileChooser.APPROVE_OPTION) {
             File files = saveFile.getSelectedFile();
             
             
     	     BufferedWriter Writer;
             if(files.exists()) {
                 JOptionPane.showMessageDialog(null, "a File already exists with that name");
                 return;
         	 }
            
            
		     try {   
		    	 
	        	   Writer = new BufferedWriter(new FileWriter(files));
	               Writer.write(res);
	               
	               Writer.close();
	               newFileFlag = false;
	               saved = true;
	               fileName=new String(files.getName());
	               f.setTitle(fileName + "-" + applicationName);
	              
		         }catch(IOException evs) {
		    	     evs.printStackTrace();
		    	    }
                }
        else if(sf == JFileChooser.CANCEL_OPTION)
            JOptionPane.showMessageDialog(null, "File save has been canceled");
        
	}
	public void New() {
		 
		if(!saved) {
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Do you want to save changes to untitled?");
			label.setPreferredSize(new Dimension(250,10));
			panel.add(label);
			panel.setBackground(Color.WHITE);
			panel.setPreferredSize(new Dimension(500,25));
			Object[] options1 = { "save", "Don't save","Cancel" };
			
			try {
				 UIManager UI=new UIManager();
				 UIManager.put("OptionPane.background", Color.white);
			int result = JOptionPane.showOptionDialog(null, panel, "Warning",  JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.PLAIN_MESSAGE,null, options1, options1[0]);
			if(result == JOptionPane.YES_OPTION) {
				saveas();
				
			}
			else if(result == JOptionPane.NO_OPTION) {
				f.dispatchEvent(new WindowEvent(f,WindowEvent.WINDOW_CLOSING));
			}
		
			
		
			}catch(Exception err){
				 err.printStackTrace();
			}
			
             
		}
		else {
			File newFile = new File("Untitled");
			fileName = newFile.getName();
			try {
				newFile.createNewFile();
				f.setTitle(fileName + "-" + applicationName);
				space.setText("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		
	} 
	public void Print() {
		PrinterJob pj = PrinterJob.getPrinterJob();
		
		    if (pj.printDialog()) {
		        try {pj.print();}
		        catch (PrinterException exc) {
		            System.out.println(exc);
		         }
		     } 
	}
	public void PageSetup() {
		PrinterJob pj = PrinterJob.getPrinterJob();
		PageFormat pf = pj.pageDialog(pj.defaultPage());
		
		    
	}
	public void undo() 
			 throws CannotUndoException {
		


	 }
	  public void keyPressed(KeyEvent e) {  
	       int x = e.getKeyLocation();
	       int currentCaretPosition = space.getCaretPosition();
	       space.replaceRange(null, x, currentCaretPosition);
	    }  
	 public void find() {
		 Scanner sc = new Scanner(System.in);
		 JPanel panel = new JPanel();
		 String gtex = space.getSelectedText();
			JTextField textField = new JTextField(gtex,10);
			panel.add(textField);
			panel.setBackground(Color.WHITE);
			panel.setPreferredSize(new Dimension(300,50));
			
			Object[] options1 = { "Find next","cancel" };
			try {
				UIManager UI=new UIManager();
				 UIManager.put("OptionPane.background", Color.white);
			int result = JOptionPane.showOptionDialog(null, panel, "Find",  JOptionPane.OK_CANCEL_OPTION, 
					JOptionPane.PLAIN_MESSAGE,null, options1, options1[0]);
			
			if(result == JOptionPane.OK_OPTION) {
				String findText = textField.getText();
				String allText = space.getText();
				int end =0;
				int start = allText.indexOf(findText,end);
				for(int i=0;i<start;i++) {
					end++;
				 end = start + findText.length();
				 start = allText.indexOf(findText,end);
				if(start == -1) {
					JOptionPane.showMessageDialog(null, '"' + findText + '"' + "Not found");
				}
				else {
					space.select(start, end);
					end++;
				}
				}
			}
			
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		  
	 }
	 


	public static void main(String[] args) {
		
	     new MyNote();
		 
		
	}
	
}

interface menuConstants {
	final JMenuItem fileNew=new JMenuItem("New");  
	final JMenuItem fileNewWindow=new JMenuItem("New Window");  
	final JMenuItem fileOpen= new JMenuItem("Open...");
	final JMenuItem fileSave= new JMenuItem("Save");   
	final JMenuItem fileSaveAs=  new JMenuItem( "Save As..."); 
	final JMenuItem filePageSetup=  new JMenuItem("Page Setup...");  
	final JMenuItem filePrint=new JMenuItem("Print");  
	final JMenuItem fileExit=new JMenuItem("Exit");                                                
	final JMenuItem editUndo=new JMenuItem("Undo"); 
	final JMenuItem editCut=new JMenuItem("Cut");
	final JMenuItem editCopy=new JMenuItem("Copy"); 	        
	final JMenuItem editPaste=new JMenuItem("Paste");  
	final JMenuItem editDelete=new JMenuItem("Delete");  
	final JMenuItem editFind=new JMenuItem("Find...");  
	final JMenuItem editFindNext=new JMenuItem("Find Next");  
	final JMenuItem editReplace=new JMenuItem("Replace");  
	final JMenuItem editGoTo=new JMenuItem("Go To...");  
	final JMenuItem editSelectAll=new JMenuItem("Select All");  
	final JMenuItem editTimeDate=new JMenuItem("Time/Date");  	       
	final JCheckBoxMenuItem formatWordWrap=new JCheckBoxMenuItem("Word Wrap");  
	final JMenuItem formatFont=new JMenuItem("Font...");  
	final JMenuItem formatForeground=new JMenuItem("Set Text color...");  
	final JMenuItem formatBackground=new JMenuItem("Set Pad color...");  
	final JMenu viewZoom = new JMenu("Zoom");
	final JMenuItem viewZoomIn = new JMenuItem("Zoom in");
	final JMenuItem viewZoomOut = new JMenuItem("Zoom out");	
	final   JCheckBoxMenuItem viewStatusBar=new   JCheckBoxMenuItem("Status Bar");  	      
	final JMenuItem helpHelpTopic=new JMenuItem("Help Topic");  
	final JMenuItem helpAboutNotepad=new JMenuItem("About Mynote");       
	final JMenuItem aboutText=new JMenuItem("Your Javapad");
	     
}
