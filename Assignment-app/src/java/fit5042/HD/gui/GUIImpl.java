/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.HD.gui;

import fit5042.credit.repository.entities.Address;
import fit5042.credit.repository.entities.BankingUser;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author shazeed ahsan
 */
public class GUIImpl extends JFrame implements UserOperationsGUI {
    
    private static final String[] TABLE_COLUMNS = {"ID", "Address", "First Name", "Last Name", "Email", "Password"};
    private static final String TAG_SEPARATOR = ",";
    
    private final JButton closeButton;
    private final JButton addButton;
    private final JButton viewButton;
    //private final JButton searchButton;
    private final JButton updateButton;
    private final JButton deleteButton;
    
    private final JPanel inputPanel;
    private final JPanel buttonPanel;

    private final JLabel userIdLabel;
    private final JLabel firstNameLabel;
    private final JLabel lastNameLabel;
    private final JLabel emailLabel;
    private final JLabel passwordLabel;
    private final JLabel streetNumberLabel;
    private final JLabel streetAddressLabel;
    private final JLabel suburbLabel;
    private final JLabel postcodeLabel;
    private final JLabel stateLabel;
    private final JLabel userTypeLabel;
    private final JLabel contactNumLabel;


    private final JTextField userIdTextField;
    private final JTextField firstNameTextField;
    private final JTextField lastNameTextField;
    private final JTextField emailTextField;
    private final JTextField passwordTextField;
    private final JTextField streetNumberTextField;
    private final JTextField streetAddressTextField;
    private final JTextField suburbTextField;
    private final JTextField postcodeTextField;
    private final JTextField stateTextField;
    private final JTextField contactNumTextField;

    private final JComboBox userTypeComboBox;
    private final JTable userTable;
    
   // private final JComboBox contactPersonComboBox;
    
    public GUIImpl(ActionListener actionListener, ListSelectionListener listSelectionListener){
        super("User Operations");
        
        this.closeButton = new JButton("Close");
        this.viewButton = new JButton("View");
        this.addButton = new JButton("Add");
        this.updateButton = new JButton("Update");
        this.deleteButton = new JButton("Delete");
        
        Container container = this.getContentPane();

        // create labels
        this.userIdLabel = new JLabel("User ID:");
        this.firstNameLabel = new JLabel("First Name");
        this.lastNameLabel = new JLabel("Last Name");
        this.emailLabel = new JLabel("Email");
        this.passwordLabel = new JLabel("Password");
        this.userTypeLabel = new JLabel("User Type");
        this.streetNumberLabel = new JLabel("Street Number:");
        this.streetAddressLabel = new JLabel("Street Address:");
        this.suburbLabel = new JLabel("Suburb:");
        this.postcodeLabel = new JLabel("Postcode:");
        this.stateLabel = new JLabel("State:");
        this.contactNumLabel = new JLabel("Contact Number");

        // create text fields
        this.userIdTextField = new JTextField();
        this.firstNameTextField = new JTextField();
        this.lastNameTextField = new JTextField();
        this.emailTextField = new JTextField();
        this.passwordTextField = new JTextField();
        this.streetNumberTextField = new JTextField();
        this.streetAddressTextField = new JTextField();
        this.suburbTextField = new JTextField();
        this.postcodeTextField = new JTextField();
        this.stateTextField = new JTextField();
        this.contactNumTextField = new JTextField();
        
        // create table
        this.userTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        this.userTable.getSelectionModel().addListSelectionListener(listSelectionListener);       
        this.userTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        TableColumnModel propertyTableColumnModel = this.userTable.getColumnModel();
        propertyTableColumnModel.getColumn(0).setPreferredWidth(50);
        propertyTableColumnModel.getColumn(1).setPreferredWidth(300);
        propertyTableColumnModel.getColumn(2).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(3).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(4).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(5).setPreferredWidth(100);
        
        this.userTypeComboBox = new JComboBox();
        
        
        // create panels
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();

        // set layout manager
        container.setLayout(new BorderLayout());
        this.inputPanel.setLayout(new GridLayout(12,2));
        this.buttonPanel.setLayout(new GridLayout(1,4));

        // add action listeners
        this.closeButton.addActionListener(actionListener);
        this.addButton.addActionListener(actionListener);
        this.viewButton.addActionListener(actionListener);
        this.updateButton.addActionListener(actionListener);
        this.deleteButton.addActionListener(actionListener);

        // add components
        this.inputPanel.add(userIdLabel);
        this.inputPanel.add(userIdTextField);
        
        this.inputPanel.add(this.firstNameLabel);
        this.inputPanel.add(this.firstNameTextField);
        
        this.inputPanel.add(this.lastNameLabel);
        this.inputPanel.add(this.lastNameTextField);
        
        this.inputPanel.add(this.emailLabel);
        this.inputPanel.add(this.emailTextField);
        
        this.inputPanel.add(this.passwordLabel);
        this.inputPanel.add(this.passwordTextField);
        
        this.inputPanel.add(this.userTypeLabel);
        this.inputPanel.add(this.userTypeComboBox);

        this.inputPanel.add(this.streetNumberLabel);
        this.inputPanel.add(this.streetNumberTextField);
        
        this.inputPanel.add(this.streetAddressLabel);
        this.inputPanel.add(this.streetAddressTextField);
        
        this.inputPanel.add(this.suburbLabel);
        this.inputPanel.add(this.suburbTextField);
        
        this.inputPanel.add(this.postcodeLabel);
        this.inputPanel.add(this.postcodeTextField);
        
        this.inputPanel.add(this.stateLabel);
        this.inputPanel.add(this.stateTextField);
        
        this.inputPanel.add(this.contactNumLabel);
        this.inputPanel.add(this.contactNumTextField);
              


        // add buttons to panel
        this.buttonPanel.add(this.addButton);
        this.buttonPanel.add(this.updateButton);
        this.buttonPanel.add(this.deleteButton);
        this.buttonPanel.add(this.viewButton);
        this.buttonPanel.add(this.closeButton);
        
        // add panels to content pane
        container.add(inputPanel,BorderLayout.NORTH);
        container.add(new JScrollPane(this.userTable), BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);
       
        // change the default behaviour of the close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(750, 570);       
        this.setVisible(true);
    }
    
    @Override
    public void displayAllUserTypes() {
        this.userTypeComboBox.removeAllItems();
        
        this.userTypeComboBox.addItem("");
        this.userTypeComboBox.addItem("public");
        this.userTypeComboBox.addItem("admin");
    }
    
    @Override
    public String getSelectedContactPerson() {
        if (this.userTypeComboBox.getItemCount() > 0 && this.userTypeComboBox.getSelectedIndex() > 0) {
            return this.userTypeComboBox.getSelectedItem().toString();
        } else {
            return null;
        }
    }
    
    
    @Override
    public int getUserId() {
        String id = this.userIdTextField.getText();
        return id == null || id.isEmpty() ? 0 : Integer.parseInt(id);
    }
    
     public boolean isUserSelected() {
        return (this.userTable.getSelectedRow() >= 0);
    }
    
    @Override
    public int getSelectedUserId() {
        int selectedRowIndex = this.userTable.getSelectedRow();
        
        String propertyId = this.userTable.getValueAt(selectedRowIndex, 0).toString();
        return Integer.parseInt(propertyId); 
    }
    
    @Override
    public BankingUser getUserDetails()
    {
        return new BankingUser(Integer.parseInt(this.userIdTextField.getText()), 
                            this.lastNameTextField.getText(),
                            this.firstNameTextField.getText(),
                            this.emailTextField.getText(),
                            this.passwordTextField.getText(),
                            this.userTypeComboBox.getSelectedItem().toString(),
                            new Address(this.streetNumberTextField.getText(), this.streetAddressTextField.getText(), this.postcodeTextField.getText(), this.stateTextField.getText(), this.suburbTextField.getText()),
                            this.contactNumTextField.getText()
        );		
    }
    
    @Override
    public void displayMessageInDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    @Override
    public void displayUserDetails(BankingUser bankUser) {  
        this.clearPropertyTable();
        this.clearInput();
        
        ((DefaultTableModel)this.userTable.getModel()).addRow(new Object[]{bankUser.getId_number(), 
                                                                               bankUser.getAddress(),
                                                                               bankUser.getFirstName(),
                                                                               bankUser.getLastName(), 
                                                                               bankUser.getEmail(), 
                                                                               bankUser.getPassword()
                                                                               });
        
    }
    
    @Override
    public void displayUserDetails(Set<BankingUser> bankUsers) {    
        this.clearPropertyTable();
        this.clearInput();
        
        for (BankingUser bankUser : bankUsers) {
            ((DefaultTableModel)this.userTable.getModel()).addRow(new Object[]{bankUser.getId_number(), 
                                                                               bankUser.getAddress(),
                                                                               bankUser.getFirstName(),
                                                                               bankUser.getLastName(), 
                                                                               bankUser.getEmail(), 
                                                                               bankUser.getPassword()});
        }
    }
    
    @Override
    public void displayUserDetails(List<BankingUser> bankUsers) {    
        this.clearPropertyTable();
        this.clearInput();
        
        for (BankingUser bankUser : bankUsers) {
            ((DefaultTableModel)this.userTable.getModel()).addRow(new Object[]{bankUser.getId_number(), 
                                                                               bankUser.getAddress(),
                                                                               bankUser.getFirstName(),
                                                                               bankUser.getLastName(), 
                                                                               bankUser.getEmail(), 
                                                                               bankUser.getPassword()});
        }
    }
    
    @Override
    public void displaySelectedUserDetails(BankingUser bankUser) {
        this.userIdTextField.setText(String.valueOf(bankUser.getId_number()));           
        Address address = bankUser.getAddress();
        this.streetNumberTextField.setText(address.getStreetNumber());
        this.streetAddressTextField.setText(address.getStreetAddress());
        this.suburbTextField.setText(address.getSuburb());
        this.postcodeTextField.setText(address.getPostcode());
        this.stateTextField.setText(address.getState());  
        this.firstNameTextField.setText(String.valueOf(bankUser.getFirstName()));
        this.lastNameTextField.setText(String.valueOf(bankUser.getLastName()));
        this.emailTextField.setText(String.valueOf(bankUser.getEmail()));
        this.passwordTextField.setText(String.valueOf(bankUser.getPassword()));
        this.userTypeComboBox.setSelectedItem(bankUser.getUserType());      
    }
    
    @Override
    public void clearPropertyTable() {     
        int numberOfRow = this.userTable.getModel().getRowCount();
        
        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.userTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index --) {
                tableModel.removeRow(index);
            }            
        }
    }
    
    @Override
    public void clearInput() {
        this.clearTextFields();
        this.clearComboBoxes();
    }
    
    @Override
    public void clearTextFields() {
        this.userIdTextField.setText("");
        this.streetNumberTextField.setText("");
        this.streetAddressTextField.setText("");
        this.suburbTextField.setText("");
        this.postcodeTextField.setText("");
        this.stateTextField.setText(""); 
        this.emailTextField.setText("");
        this.passwordTextField.setText("");
        this.contactNumTextField.setText("");
        this.firstNameTextField.setText("");
        this.lastNameTextField.setText("");
        
        
        
          
    }
    
    @Override
    public void clearComboBoxes() {
        if (this.userTypeComboBox.getItemCount() > 0) {
            this.userTypeComboBox.setSelectedIndex(0);
        } 
    }
    
    @Override
    public JButton getUpdateButton() {
        return updateButton;
    }

    @Override
    public JButton getDeleteButton() {
        return deleteButton;
    }

    @Override
    public JTable getUserTable() {
        return userTable;
    }

    @Override
    public JButton getViewButton() {
        return viewButton;
    }

    @Override
    public JButton getAddButton() {
        return addButton;
    }

    @Override
    public JButton getCloseButton() {
        return closeButton;
    }
    
    
}
