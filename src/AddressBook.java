import javax.swing.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AddressBook extends DefaultListModel
{

	public AddressBook() 
	{
		super();
	}
	
	public void addBuddy(BuddyInfo thisBuddy)
	{
		try{
			super.addElement(thisBuddy);
		}
		catch (NullPointerException e){
			System.out.println("Cannot pass null value");
		};
	}
	
	public void removeBuddy(int index)
	{
		if(index >= 0 && index < super.size()) super.remove(index);
	}
	
	public static void main(String[] args) {
		
		BuddyInfo buddy = new BuddyInfo("Brad", "Carleton", "1234");
		AddressBook addressBook =  new AddressBook();
		addressBook.addBuddy(buddy);
		addressBook.removeBuddy(0);
	}
	
	public int size() {
		return super.getSize();
	}
	
	public void clear() {
		super.clear();
	}
	
	public void save(String filename) {
		try {
			FileOutputStream ostream = new FileOutputStream(filename);
		    ObjectOutputStream p = new ObjectOutputStream(ostream); 
		    
		    for(int i = 0 ; i < super.getSize(); i++) {
		    	BuddyInfo buddy = (BuddyInfo) super.get(i);
		    	p.writeChars(buddy.toString() + "\n");
		    }
		}
		catch(FileNotFoundException e) { e.printStackTrace(); }		
		catch(IOException e) {e.printStackTrace(); }
	}
}
