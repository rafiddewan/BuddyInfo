import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddressBookView extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JList<BuddyInfo> buddyInfoList;
    private AddressBook addressBookModel;
    private JMenu addressBook, buddyInfo;
    private JMenuItem createAddressBook, createBuddy, removeBuddy;

    public AddressBookView(){

        //Create Menu bar and Menu items
        this.menuBar = new JMenuBar();
        this.addressBook = new JMenu("Address Book");
        this.buddyInfo = new JMenu("Buddy Info");
        this.menuBar.add(addressBook);
        this.menuBar.add(buddyInfo);
        this.createAddressBook = new JMenuItem("New Address Book");
        this.createAddressBook.addActionListener(this);
        this.createBuddy = new JMenuItem("New Buddy");
        this.createBuddy.addActionListener(this);
        this.removeBuddy = new JMenuItem("Remove Buddy");
        this.removeBuddy.addActionListener(this);

        //adding menu items to menus
        this.addressBook.add(createAddressBook);
        this.buddyInfo.add(createBuddy);
        this.buddyInfo.add(removeBuddy);

        //adding JLIst
        this.buddyInfoList = new JList<BuddyInfo>();
        this.buddyInfoList.setVisible(true);
        //adding to frame
        this.setJMenuBar(this.menuBar);
        this.add(buddyInfoList);
        this.setSize(500,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createAddressBook){
            if(addressBookModel == null) {
                addressBookModel = new AddressBook();
                buddyInfoList.setModel(addressBookModel);
                buddyInfoList.setVisible(true);
            }
            else{
                JOptionPane pane = new JOptionPane();
                pane.setMessage("CANNOT ADD A NEW ADDRESS BOOK");
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
    }

    public static void main(String[] args){
        AddressBookView view = new AddressBookView();
    }
}