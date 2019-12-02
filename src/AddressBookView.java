import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddressBookView extends JFrame implements ActionListener {

    private JList<BuddyInfo> buddyInfoList;
    private AddressBook addressBookModel;
    private JMenuItem createAddressBook, createBuddy, removeBuddy, saveBuddy;

    public AddressBookView(){

        //Create Menu bar and Menu items
        JMenuBar menuBar = new JMenuBar();
        JMenu addressBook = new JMenu("Address Book");
        JMenu buddyInfo = new JMenu("Buddy Info");
        menuBar.add(addressBook);
        menuBar.add(buddyInfo);
        this.createAddressBook = new JMenuItem("New Address Book");
        this.createAddressBook.addActionListener(this);
        this.createBuddy = new JMenuItem("New Buddy");
        this.createBuddy.addActionListener(this);
        this.removeBuddy = new JMenuItem("Remove Buddy");
        this.removeBuddy.addActionListener(this);
        this.saveBuddy = new JMenuItem("Save Buddy");
        this.saveBuddy.addActionListener(this);
        //adding menu items to menus
        addressBook.add(createAddressBook);
        buddyInfo.add(createBuddy);
        buddyInfo.add(removeBuddy);
        addressBook.add(saveBuddy);


        //adding JLIst
        this.buddyInfoList = new JList<BuddyInfo>();
        this.buddyInfoList.setVisible(true);
        //adding to frame
        this.add(menuBar, BorderLayout.NORTH);
        this.add(buddyInfoList, BorderLayout.CENTER);
        this.setSize(500,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.createAddressBook) {
            if(this.addressBookModel == null) {
                this.addressBookModel = new AddressBook();
                try {
                    addressBookModel = addressBookModel.importFromXMLFile("buddies.txt");
                } catch (SAXException | ParserConfigurationException e1) {
                    addressBookModel = new AddressBook();
                }
                buddyInfoList.setModel(addressBookModel);
                buddyInfoList.setVisible(true);
                this.setTitle("Address Book 1");
            } else {
                JOptionPane.showMessageDialog(this, "Address Book already created");
            } 
        }        
        else if(e.getSource() == removeBuddy){
            JOptionPane prompt = new JOptionPane();
            String num = prompt.showInputDialog("Enter a number");
            int index = Integer.parseInt(num);
            if(addressBookModel == null) prompt.setMessage(JOptionPane.ERROR_MESSAGE);
            else if(addressBookModel.getSize() == 0) prompt.setMessage(JOptionPane.ERROR_MESSAGE);
            else addressBookModel.remove(index);
        }
        else if(e.getSource() == createBuddy){
            JOptionPane prompt = new JOptionPane();
            String name = prompt.showInputDialog("Enter a name: ");
            String address = prompt.showInputDialog("Enter a address: ");
            String phone = prompt.showInputDialog("Enter Phone Number: ");
            BuddyInfo tempBuddy = new BuddyInfo(name, address, phone);
            addressBookModel.addBuddy(tempBuddy);
        }
        else if(e.getSource() == saveBuddy) {
            addressBookModel.exportToXMLFile("buddies.txt");
        }
    }

    public static void main(String[] args){
        AddressBookView view = new AddressBookView();
    }
}