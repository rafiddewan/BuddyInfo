
public class BuddyInfo 
{

	private String name, phoneNum, address; 
	
	public BuddyInfo(String name, String phoneNum, String address) 
	{
		this.name = name;
		this.phoneNum = phoneNum;
		this.address = address;
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
}
