import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuddyInfoTest {
	
	private BuddyInfo buddyInfo = null;
	private String name = null;
	private String phonenum = null;
	private String address = null;
	private AddressBook buddies = null;
	
	@Before
	public void setUp() throws Exception {
		name = "Nick";
		phonenum = "6134201337";
		address = "420 JavaIsBetterThanCCrescent";
		buddyInfo = new BuddyInfo(name, phonenum, address);
		buddies = new AddressBook();
		buddies.addBuddy(buddyInfo);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetName() {
		assertEquals("Nick should be your buddy", name, buddyInfo.getName());
	}

	@Test
	public void testSetName() {
		name = "Joe";
		buddyInfo.setName(name);
		assertEquals("Nick's name should be changed to Joe", name, buddyInfo.getName());
	}

	@Test
	public void testGetPhoneNum() {
		assertEquals("Joe's phone number has been changed to 6132499966", phonenum, buddyInfo.getPhoneNum());
	}

	@Test
	public void testSetPhoneNum() {
		phonenum = "6132499966";
		buddyInfo.setPhoneNum(phonenum);
		assertEquals("Joe's phone number has been changed to 6132499966", phonenum, buddyInfo.getPhoneNum());
	}

	@Test
	public void testGetAddress() {
		assertEquals("Joe's address has been changed to 6132499966", address, buddyInfo.getAddress());
	}

	@Test
	public void testSetAddress() {
		address = "18 asdnasdionasdoi street";
		buddyInfo.setAddress(address);
		assertEquals("Joe's phone number has been changed to 6132499966", address, buddyInfo.getAddress());
	}

	@Test
	public void testEqualsObject() {
		BuddyInfo checkBuddy = new BuddyInfo(name, phonenum, address);
		assertTrue("This doesn't seem to be Joe's info.", buddyInfo.equals(checkBuddy));
	}

	@Test
	public void testToString() {
		String buddyInfoTestString = "BuddyInfo{" +
				"name='" + name + '\'' +
				", phoneNum='" + phonenum + '\'' +
				", address='" + address + '\'' +
				'}';
		assertEquals("Joe's phone number has been changed to 6132499966", buddyInfoTestString, buddyInfo.toString());	
	}
	
	@Test
	public void copycatBuddy() {
		BuddyInfo copiedBuddy = new BuddyInfo(buddyInfo);
		assertEquals("Joe is your buddy", buddyInfo.getName(), copiedBuddy.getName());
		assertEquals("Joe's phone number has been changed to 6132499966", buddyInfo.getPhoneNum(), copiedBuddy.getPhoneNum());
		assertEquals("Joe's address has been changed to 6132499966", buddyInfo.getAddress(), copiedBuddy.getAddress());
		assertEquals(buddyInfo, copiedBuddy);
	}
	
	@Test
	public void testSize() {
		assertEquals(1, buddies.getSize());
	}

}
