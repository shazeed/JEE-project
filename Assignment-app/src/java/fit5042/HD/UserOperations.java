/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.HD;

import fit5042.HD.gui.GUIImpl;
import fit5042.HD.gui.UserOperationsGUI;
import fit5042.credit.repository.UserRepositoryImplRemote;
import fit5042.credit.repository.entities.BankingUser;
import fit5042.credit.repository.entities.PublicUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.util.List;
import javax.ejb.EJB;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author shazeed ahsan
 */
public class UserOperations implements ActionListener, ListSelectionListener {

    /**
     * @param args the command line arguments
     */
    @EJB
    private static UserRepositoryImplRemote userRepo;

    private String name;
    private UserOperationsGUI gui;

    public UserOperations(String name) throws Exception {
        this.name = name;
    }

    public void initView() {
        this.gui = new GUIImpl(this, this);
        this.displayAllUsers();
        this.displayAllUserTypes();
    }

    public void displayAllUserTypes() {
        this.gui.displayAllUserTypes();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == gui.getViewButton()) {
            this.displayAllUsers();
        } else if (event.getSource() == gui.getAddButton()) {
            this.addUser();
            this.displayAllUsers();
        } else if (event.getSource() == gui.getUpdateButton()) {
            this.updateUser();
        } else if (event.getSource() == gui.getDeleteButton()) {
            this.deleteUser();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if ((event.getSource() == this.gui.getUserTable().getSelectionModel())
                && (!event.getValueIsAdjusting())) {
            try {
                if (this.gui.isUserSelected()) {
                    int userId = this.gui.getSelectedUserId();

                    BankingUser bankUser = userRepo.searchUserById(userId);
                    this.gui.displaySelectedUserDetails(bankUser);
                }
            } catch (Exception e) {
                gui.displayMessageInDialog(e.getMessage());
            }
        }
    }

    private void addUser() {
        BankingUser bankuser = gui.getUserDetails();
        try {
            if (bankuser.getUserType().equals("public")) {
                PublicUser pubUser = new PublicUser(bankuser.getId_number(), bankuser.getLastName(), bankuser.getFirstName(), bankuser.getEmail(), bankuser.getPassword(), bankuser.getUserType());
                pubUser.setAddress(bankuser.getAddress());
                String newPassword = this.hashThePassword(pubUser.getPassword());
                pubUser.setPassword(newPassword);
                System.out.println(pubUser.toString());
                this.userRepo.addUser(pubUser);
            } else {
                String newPassword = this.hashThePassword(bankuser.getPassword());
                bankuser.setPassword(newPassword);
                System.out.println(bankuser.toString());
                this.userRepo.addUser(bankuser);
            }
            this.displayAllUsers();
            this.gui.clearInput();
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to add user: " + ex.getMessage());
        } finally {
            this.gui.clearInput();
        }
    }
    
    public void deleteUser() {
        try {
            int userId = this.gui.getUserId();
            userRepo.removeUser(userId);
            this.displayAllUsers();
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to update user: " + ex.getMessage());
        }  finally {
            this.gui.clearInput();
        }
    }
    
    public void updateUser() {
        BankingUser bankuser = gui.getUserDetails();
        try {
            if (bankuser.getUserType().equals("public")) {
                PublicUser pubUser = new PublicUser(bankuser.getId_number(), bankuser.getLastName(), bankuser.getFirstName(), bankuser.getEmail(), bankuser.getPassword(), bankuser.getUserType());
                pubUser.setAddress(bankuser.getAddress());
                String newPassword = this.hashThePassword(pubUser.getPassword());
                pubUser.setPassword(newPassword);
                System.out.println(pubUser.toString());
                this.userRepo.editUser(pubUser);
            } else {
                String newPassword = this.hashThePassword(bankuser.getPassword());
                bankuser.setPassword(newPassword);
                System.out.println(bankuser.toString());
                this.userRepo.editUser(bankuser);
            }
            this.displayAllUsers();
            this.gui.clearInput();
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to update user: " + ex.getMessage());
        } finally {
            this.gui.clearInput();
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void displayAllUsers() {
        try {
            List<BankingUser> bankUsers = this.userRepo.getAllUsers();
            if (bankUsers != null) {
                this.gui.displayUserDetails(bankUsers);
            }
        } catch (Exception ex) {
        }
    }

    public String hashThePassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Hex format : " + sb.toString());

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        System.out.println("Hex format : " + hexString.toString());
        return hexString.toString();

    }

    public static void main(String[] args) {

        try {
            final UserOperations userOps = new UserOperations("Monash Real Estate Agency");
            //JDK 1.7
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    agency.initView();
//                }
//            });
            userOps.initView();

//            //JDK 1.8
//            SwingUtilities.invokeLater(()-> {
//                agency.initView();
//            });
        } catch (Exception ex) {
            System.out.println("Failed to run application: " + ex.getMessage());
        }
        // TODO code application logic here
    }

}
