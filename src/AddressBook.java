import java.io.BufferedReader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.DefaultListModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AddressBook extends DefaultListModel implements Serializable
{

	public AddressBook() 
	{
		super();
	}
	
	public void addBuddy(BuddyInfo thisBuddy) {
		if (thisBuddy != null) {
			super.addElement(thisBuddy);
		}
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
	public static AddressBook importBuddiesFromFile(String filename) throws IOException {
		String curr = "";
		AddressBook book = new AddressBook();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			while ((curr = reader.readLine()) != null) {
				book.addBuddy(BuddyInfo.importBuddy(curr));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return book;
	}

	public AddressBook readObject(String filename) throws ClassNotFoundException, IOException {
		ObjectInputStream istream;
		istream = new ObjectInputStream(new FileInputStream(filename));
		Object o = istream.readObject();
		istream.close();
		return (AddressBook) o;
	}

	public void writeObject(String filename) throws IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename));
		outputStream.writeObject(this);
		outputStream.close();
	}

	public boolean save(String filename) {
		try {
			FileWriter writer = new FileWriter(new File(filename), false);
			for(int i = 0; i < this.getSize(); i++) {
				writer.write(this.getElementAt(i).toString() + "\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String toXML() {
		String s = "<AddressBook> \n";
		for(int i = 0; i < this.getSize(); i++) {
			BuddyInfo b = (BuddyInfo) this.getElementAt(i);
			s += b.toXML() + "\n";
		}
		s += "</AddressBook>";
		return s;

	}

	public void exportToXMLFile(String filename) {
		FileWriter writer;
		try {
			writer = new FileWriter(new File(filename), false);
			writer.write(this.toXML());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public AddressBook importFromXMLFile(String filename) throws SAXException,ParserConfigurationException  {
		SAXParserFactory sax = SAXParserFactory.newInstance();
		SAXParser parser;
		MyHandler handler = new MyHandler();
		parser = sax.newSAXParser();
		try {
			parser.parse(new File(filename), handler);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return handler.getAddressBook();
	}

	static class MyHandler extends DefaultHandler {
		private AddressBook addy;
		private StringBuilder data;
		private String name;
		private String address;
		private String phone;
		private boolean nameState;
		private boolean phoneState;
		private boolean addressState;


		public MyHandler() {
			this.addy = new AddressBook();
			this.data = null;
			this.name = "";
			this.address = "";
			this.phone = "";
			nameState = false;
			phoneState = false;
			addressState = false;
		}

		public AddressBook getAddressBook() {
			return this.addy;
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			switch (qName) {
				case "name":
					nameState = true;
					break;
				case "phone":
					phoneState = true;
					break;
				case "address":
					addressState = true;
					break;
			}
			data = new StringBuilder();
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (nameState) {
				name = new String(data.toString());
				nameState = false;
			} else if (phoneState) {
				phone = new String(data.toString());
				phoneState = false;
			} else if (addressState) {
				address = new String(data.toString());
				addressState = false;
			}

			if (name != "" && phone != "" && address != "") {
				addy.addBuddy(new BuddyInfo(name, address, phone));
				this.name = "";
				this.address = "";
				this.phone = "";
			}
		}

		@Override
		public void characters(char ch[], int start, int length) throws SAXException {
			data.append(new String(ch, start, length));
		}

	}

}
