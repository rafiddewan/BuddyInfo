import java.util.ArrayList;

public class AddressBook 
{

	private ArrayList<BuddyInfo> buddys;

	public AddressBook() 
	{
		this.buddys = new ArrayList<BuddyInfo>();
	}
	
	public boolean addBuddy(BuddyInfo thisBuddy) 
	{
		if(thisBuddy != null) {
			buddys.add(thisBuddy);
		}
		return false;
	}
	
	public boolean removeBuddy(int index) 
	{
		if(index >= 0 && index < buddys.size())
		{
			buddys.remove(index);
			return true;
		}
		return false;
		
	}
	
	public static void main(String[] args) {
		
		BuddyInfo buddy = new BuddyInfo("Brad", "Carleton", "1234");
		AddressBook addressBook =  new AddressBook();
		addressBook.addBuddy(buddy);
		addressBook.removeBuddy(0);
	}
}
