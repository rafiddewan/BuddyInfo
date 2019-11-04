import javax.swing.*;
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
}
