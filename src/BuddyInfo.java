import java.io.Serializable;

public class BuddyInfo implements Serializable
{

	private String name, phoneNum, address; 
	
	public BuddyInfo(String name, String phoneNum, String address) 
	{
		this.name = name;
		this.phoneNum = phoneNum;
		this.address = address;
	}
	
	public BuddyInfo(BuddyInfo buddyInfo) {
		this(buddyInfo.getName(), buddyInfo.getPhoneNum(), buddyInfo.getAddress());
	}


	public String getName() 
	{
		return name;
	}


	public void setName(String name) 
	{
		this.name = name;
	}


	public String getPhoneNum() 
	{
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) 
	{
		this.phoneNum = phoneNum;
	}


	public String getAddress() 
	{
		return address;
	}


	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public boolean equals(Object obj) 
	{
		if (obj== this) return true;
		if (obj== null) return false;
		if (this.getClass() != obj.getClass()) return false;	
		BuddyInfo buddy = (BuddyInfo) obj;
		return buddy.getName() == this.getName() && buddy.getPhoneNum() == this.getPhoneNum() && buddy.getAddress() == this.getAddress();
	}

	public String toXML() {
		String s = "<BuddyInfo>\n";
		s += "<name>" + this.name + "</name> \n";
		s += "<address>" + this.address + "</address> \n";
		s += "<phone>" + this.phoneNum + "</phone>\n";
		s += "</BuddyInfo>";
		return s;
	}


	public static BuddyInfo importBuddy(String info) {
		String[] s = info.split("#");
		BuddyInfo b = new BuddyInfo(s[0], s[1], s[2]);
		return b;
	}

	@Override
	public String toString() {
		return this.name + "#" + this.address + "#" + this.phoneNum;
	}
}
