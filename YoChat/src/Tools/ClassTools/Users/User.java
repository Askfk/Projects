package Tools.ClassTools.Users;

import java.io.Serializable;

/**
 * This is the user class, contains all the properties of a user.
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class User implements Serializable {

    private static final long serialVersionUID = 5950169519310163575L;

    protected String Name;
    protected String password;
    protected String NickName;
    protected String Email;
    protected String Gender;
    protected String age;
    protected String nation;
    protected String UID;
    protected Boolean isLogin;
    protected Boolean unreceiveMess = false;

    protected Boolean isMatched = false;

    /**
     *
     * @param Name
     * @param password
     * @param email
     */
    public User(String Name, String password, String email) {
        this.Name = Name;
        this.password = password;
        this.NickName = Name;
        this.Gender = "Unknown";
        this.age = "";
        this.nation = "Mars";
        this.Email = email;
        this.isLogin = false;
    }

    public User(String name, String nickName, String email, String gender, String age, String nation, String UID, boolean islogin) {
        this.Name = name;
        this.NickName = nickName;
        this.Email = email;
        this.Gender = gender;
        this.age = age;
        this.nation = nation;
        this.UID = UID;
        this.isLogin = islogin;
    }

    public void setNickName(String nickName) {
        this.NickName = nickName;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public Boolean getMatched() {
        return isMatched;
    }

    public void setMatched(Boolean matched) {
        isMatched = matched;
    }

    public Boolean getUnreceiveMess() {
        return unreceiveMess;
    }

    public void setUnreceiveMess(Boolean unreceiveMess) {
        this.unreceiveMess = unreceiveMess;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return NickName;
    }

    public String getUID() {
        return UID;
    }

    public String getEmail() {
        return Email;
    }

    public String getGender() {
        return Gender;
    }

    public String getAge() {
        return age;
    }

    public String getNation() {
        return nation;
    }

    public boolean equals(User user){
        return this.getUID().equals(user.getUID());
    }

    @Override
    public int hashCode(){
        return this.Name.hashCode() + new Integer(this.UID).hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }
}
