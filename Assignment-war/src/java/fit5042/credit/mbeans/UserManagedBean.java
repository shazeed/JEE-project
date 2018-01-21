/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.mbeans;

import fit5042.credit.repository.UserRepositoryImplRemote;
import fit5042.credit.repository.entities.Address;
import fit5042.credit.repository.entities.BankingUser;
import fit5042.credit.repository.entities.PublicUser;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.RequestScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shazeed ahsan
 */
@Named(value = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    @EJB
    private UserRepositoryImplRemote userRepo;
    private String status;
    private BankingUser bankUser;
    private PublicUser publicUser;
    private Address userAddress;
    private List<BankingUser> userList;
    private String userId;
    private String firstName;
    private String lastName;
    private String userType;
    private String email;

    /**
     * Creates a new instance of userManagedBean
     */
    public UserManagedBean() {
        status = "";
        userType = "";
        bankUser = new BankingUser();
        this.publicUser = new PublicUser();
        userAddress = new Address();
        this.userList = new ArrayList<>();

    }

    public PublicUser getPublicUser() {
        return publicUser;
    }

    public void setPublicUser(PublicUser publicUser) {
        this.publicUser = publicUser;
    }

    public UserRepositoryImplRemote getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepositoryImplRemote userRepo) {
        this.userRepo = userRepo;
    }

    public BankingUser getBankUser() {
        return bankUser;
    }

    public void setBankUser(BankingUser bankUser) {
        this.bankUser = bankUser;
    }

    public List<BankingUser> getUserList() {
        return this.userList;
    }

    public void setUserList(List<BankingUser> userList) {
        this.userList = userList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void searchUserById(int userId) {
        try {
            this.bankUser = userRepo.searchUserById(userId);

        } catch (Exception ex) {

        }
    }

    @PostConstruct
    public void listAllUsers() {
        try {
            this.userList = userRepo.getAllUsers();
            if (userList.isEmpty()) {
                System.out.println("No users in the Database");
            }
        } catch (Exception ex) {
            System.out.println("Exception occured while getting all the users: " + ex.getMessage());
        }
    }

    public void addNewUser() {
        String stop = "";
        try {
            if (this.userType.equals("public")) {
                PublicUser pubUser = new PublicUser(this.bankUser.getId_number(), this.bankUser.getLastName(), this.bankUser.getFirstName(), this.bankUser.getEmail(), this.bankUser.getPassword(), this.userType);
                pubUser.setAddress(userAddress);
                String newPassword = this.hashThePassword(pubUser.getPassword());
                pubUser.setPassword(newPassword);
                System.out.println(pubUser.toString());
                userRepo.addUser(pubUser);
                pubUser = new PublicUser();
                userAddress = new Address();

            } else {
                bankUser.setAddress(userAddress);
                String newPassword = this.hashThePassword(bankUser.getPassword());
                bankUser.setPassword(newPassword);
                userRepo.addUser(bankUser);
                bankUser = new BankingUser();
                userAddress = new Address();
            }

            status = "User added Successful";
            this.listAllUsers();

        } catch (Exception ex) {
            status = "Error in adding new user" + ex.getMessage();
        }
    }

    public void updateUser() {
        String myParam = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("updateId");
        System.out.println(myParam);
        int updateUserId = Integer.parseInt(myParam);
        try {
            this.bankUser = userRepo.searchUserById(updateUserId);
            System.out.println(this.bankUser.toString());
            FacesContext.getCurrentInstance().getExternalContext().dispatch("updateUser.xhtml");
        } catch (Exception ex) {
            System.out.println("error");
        }

    }

    public void mergeUser() {
        try {
            if (this.userType.equals("public")) {
                PublicUser pubUser = new PublicUser(this.bankUser.getId_number(), this.bankUser.getLastName(), this.bankUser.getFirstName(), this.bankUser.getEmail(), this.bankUser.getPassword(), this.userType);
                pubUser.setAddress(userAddress);
                String newPassword = this.hashThePassword(pubUser.getPassword());
                pubUser.setPassword(newPassword);
                System.out.println(pubUser.toString());
                userRepo.editUser(pubUser);
                pubUser = new PublicUser();
                userAddress = new Address();

            } else {
                bankUser.setAddress(userAddress);
                String newPassword = this.hashThePassword(bankUser.getPassword());
                bankUser.setPassword(newPassword);
                userRepo.editUser(bankUser);
                bankUser = new BankingUser();
                userAddress = new Address();
            }

            status = "User Update Successful";
            FacesContext.getCurrentInstance().getExternalContext().dispatch("index.xhtml");

        } catch (Exception ex) {
            status = "Error in updating new user " + ex.getMessage();
        }
    }

    /**
     * public void viewUserDetails() { String myParam = (String)
     * FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userID");
     * System.out.println(myParam); int uid = Integer.parseInt(myParam); try {
     * this.publicUser = userRepo.searchPublicUserById(uid);
     * System.out.println(publicUser.toString());
     * FacesContext.getCurrentInstance().getExternalContext().redirect("userDetails.xhtml");
     * } catch (Exception ex) {
     *
     * }
     *
     * }
     */
    public void viewUserDetails() {
        this.bankUser = new BankingUser();
        String myParam = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userID");
        System.out.println(myParam);
        int uid = Integer.parseInt(myParam);
        try {
            this.bankUser = userRepo.searchUserById(uid);
            if (this.bankUser.getUserType().equals("public")) {
                this.publicUser = userRepo.searchPublicUserById(uid);
                System.out.println(bankUser.toString());
                FacesContext.getCurrentInstance().getExternalContext().redirect("publicUserDetails.xhtml");
            } else {
                //this.bankUser = userRepo.searchUserById(uid);
                System.out.println(bankUser.toString());
                FacesContext.getCurrentInstance().getExternalContext().redirect("userDetails.xhtml");

            }

        } catch (Exception ex) {

        }

    }

    public void deleteUser() {
        this.bankUser = new BankingUser();
        String myParam = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("deleteId");
        int uid = Integer.parseInt(myParam);
        try {
            this.bankUser = userRepo.searchUserById(uid);
            if (this.bankUser.getUserType().equals("public")) {
                this.userRepo.removePublicUser(uid);
                status = "Public User has been removed";
                System.out.println("Public User has been removed");
                this.listAllUsers();
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
            } else {
                this.userRepo.removeUser(uid);
                status = "User has been removed";
                System.out.println("User has been removed");
                this.listAllUsers();
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<BankingUser> searchUser() {
        List<BankingUser> retVal = new ArrayList<>();
        try {
            retVal = this.userRepo.getSelectedUsers(userId, firstName, lastName, userType, email);
            System.out.println(retVal.toString());
            this.userList = retVal;
            System.out.println(this.userList.toString());

        } catch (Exception ex) {
            System.out.println("catch managebean error");
        }
        return this.userList;
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

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();

        final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        request.getSession(false).invalidate();

        return "/index.xhtml";
    }

}
