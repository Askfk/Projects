package Server.ServerDB;

import Tools.ClassTools.Infors.Info;
import Tools.ClassTools.Users.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class helps to connect database
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class JDBConnection {
    private Connection connect;

//    private static final String driver = "com.mysql.cj.jdbc.Driver";
//    private static final String url = "jdbc:mysql://127.0.0.1:3306/MySQL";
//    private static final String user = "root";
//    private static final String password = "QQ.963225";

    private String url;
    private String user;
    private String password;
    private String driver;

    public JDBConnection(){
        initialize();
    }

    /**
     * Initialize the jdbcConnection
     */
    void initialize(){
        try
        {
            try (FileInputStream input = new FileInputStream(new File("res/db.properties"))) {
                Properties props = new Properties();
                props.load(input);
                user = props.getProperty("user");
                password = props.getProperty("password");
                url = props.getProperty("url");
                driver = props.getProperty("driver");
            }
//            Properties properties = new Properties();
//            InputStream inputStream = JDBConnection.class.getResourceAsStream("/db.properties");
//            properties.load(inputStream);
//
//            url = properties.getProperty("url");
//            user = properties.getProperty("user");
//            password = properties.getProperty("password");
//            driver = properties.getProperty("driver");
//
//            inputStream.close();

            Class.forName(driver);     //Load MYSQL JDBC driver
            tell("Successfully load Mysql Driver!");
        }
        catch (Exception e) {
            error("Error loading Mysql Driver!, Error code: -1.");
            e.printStackTrace();
        }

        tell("start connecting");

        try{
            connect = DriverManager.getConnection(url, user, password);
            //The URL connection is   jdbc:mysql//address of the database server/name of the database  ，the last two is user and pw.

            tell("Successfully connect Mysql server!");

            // create tables if not exists
            Statement stmt = connect.createStatement();
            String sql = "CREATE TABLE if not exists userTable (" +
                    "    user_id BIGSERIAL PRIMARY KEY," +
                    "    user_name varchar(45) NOT NULL," +
                    "    user_pw varchar(255) NOT NULL," +
                    "    user_email varchar(45) NOT NULL," +
                    "    user_nick varchar(45)," +
                    "    user_login BOOLEAN NOT NULL," +
                    "    user_gender varchar(30) default 'Female'," +
                    "    user_age varchar(30) default '0'," +
                    "    user_nation varchar(30) default 'Mars'," +
                    "    user_ip varchar(45) default 'off-line'," +
                    "    friendsUID varchar(255) default NUll," +
                    "    matchUID varchar(255) default NULL," +
                    "    group_id varchar(255) default NULL" +
                    ");";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE if not exists groupTable (" +
                    "    group_id BIGSERIAL PRIMARY KEY," +
                    "    group_name VARCHAR(45) NOT NULL," +
                    "    group_members VARCHAR(100) NOT NULL," +
                    "    remark VARCHAR(255) default NULL" +
                    ");";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE if not exists messagePrivate (" +
                    "    message_id BIGSERIAL PRIMARY KEY," +
                    "    pair_id VARCHAR(45) NOT NULL," +
                    "    fromID VARCHAR(45) NOT NULL," +
                    "    toID VARCHAR(45) NOT NULL," +
                    "    message VARCHAR(255) NOT NULL," +
                    "    mess_date VARCHAR(45) NOT NULL," +
                    "    remark VARCHAR(255) default NULL" +
                    ");";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE if not exists messageGroup (" +
                    "    message_id BIGSERIAL PRIMARY KEY," +
                    "    fromID VARCHAR(45) NOT NULL," +
                    "    groupID VARCHAR(45) NOT NULL," +
                    "    message VARCHAR(255) NOT NULL," +
                    "    mess_date VARCHAR(45) NOT NULL," +
                    "    remark VARCHAR(255) default NULL" +
                    ");";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException e){
            System.out.println("Connect to Mysql server failed. Error Code: -2");
            e.printStackTrace();
        }
    }

    /**
     * Change user online status
     * @param onlineStatus
     * @param UID
     * @return
     */
    public boolean changeOnlineStatus(boolean onlineStatus, String UID){
        PreparedStatement preparedStatement;
        try {
            String sql = "update userTable set user_login=? where user_id=" + "\'" + UID + "\'";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setBoolean(1, onlineStatus);

            preparedStatement.executeUpdate();
            //System.out.println("here 2 jdbc");
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Change user personal infos
     */
    public boolean changeInfo(User user){
        PreparedStatement preparedStatement;
        try {
            String sql = "update userTable set user_nick=?, user_gender =?, user_age=?, user_nation=? where user_id=" + "\'" + user.getUID() + "\'";
            preparedStatement = connect.prepareStatement(sql);
//            System.out.println(user.getNickName() + user.getGender() + user.getAge() + user.getNation());
            preparedStatement.setString(1, user.getNickName());
            preparedStatement.setString(2, user.getGender());
            preparedStatement.setString(3, user.getAge());
            preparedStatement.setString(4, user.getNation());

            preparedStatement.executeUpdate();
            //System.out.println("here 2 jdbc");
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * cooperate with sign up, add new user
     * @param user
     * @return
     */
    public boolean addUser(User user) {
        try{
            PreparedStatement preparedStatement;
            String sql = "insert into userTable(user_name, user_pw, user_email, user_nick, user_login) values(?,?,?,?,?)";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setBoolean(5, user.isLogin());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            error("Error code: -4");
            e.printStackTrace();
            //preparedStatement.close();
            return false;
        }
    }

    /**
     * Login
     * @param infos
     * @param address
     * @return
     */
    public User Login(String[] infos, InetAddress address){
        try{
            Statement stmt = connect.createStatement();
            String query = "select user_pw from userTable where user_name=" + "\'" + infos[0] + "\'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            if(infos[1].equals(rs.getString("user_pw"))){
                String st = "update userTable set user_login=true, user_ip=" + "\'" +address.toString().substring(1) + "\'" + " where user_name=" + "\'" + infos[0] + "\'";
                stmt.executeUpdate(st);
                String query_ = "select * from userTable where user_name=" + "\'" + infos[0] + "\'";
                ResultSet resultSet = stmt.executeQuery(query_);
                resultSet.next();
                String name = resultSet.getString("user_name");
                String nickname = resultSet.getString("user_nick");
                String email = resultSet.getString("user_email");
                String gender = resultSet.getString("user_gender");
                String age = resultSet.getString("user_age");
                String nation = resultSet.getString("user_nation");
                String UID = resultSet.getString("user_id");

                User normalUser = new User(name, nickname, email, gender, age, nation, UID, true);

                stmt.close();
                rs.close();
                resultSet.close();

                return normalUser;
            }
            else{
                stmt.close();
                rs.close();
                System.err.println("Instruction error code: -1, password incorrect.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            error("Error Code -5");
            return null;
        }
    }

    /**
     * Create group chat
     */
    public boolean createGroupChat(String groupMembersId){
        String[] ids = groupMembersId.split(":");
        try {
            PreparedStatement preparedStatement;
            Statement stmt = connect.createStatement();
            String sql = "insert into groupTable(group_name, group_members) values(?,?)";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, ids[0]);
            preparedStatement.setString(2, ids[1]);
            preparedStatement.executeUpdate();

            String query = "select group_id from groupTable where group_name=" + "\'" + ids[0] + "\' " + "order by group_id desc limit 1";
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();
            String groupId = resultSet.getString("group_id");
            String[] userIds = ids[1].split("/");
            for(String id : userIds){
                joinGroup(id, groupId);
            }

            preparedStatement.close();
            stmt.close();
            resultSet.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Add group id into user table.
     * @param userId user's id
     * @param groupId group id
     */
    void joinGroup(String userId, String groupId){
        try {
            Statement stmt = connect.createStatement();
            String query = "select group_id from userTable where user_id=" + "\'" + userId + "\'";
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();
            String group = resultSet.getString("group_id");
            if(group == null) {
                group = groupId + "/";
            }
            else
                group += groupId + "/";
            String sql = "update userTable set group_id=" + "\'" + group + "\'" + " where user_id=" + "\'" + userId + "\'";
            stmt.execute(sql);

            stmt.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * get group members with given group id
     * @param groupId
     * @return
     */
    public String getGroupMembers(String groupId){
        try {
            Statement stmt = connect.createStatement();
            String query = "select group_members from groupTable where group_id="  + "\'" + groupId + "\'";
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();

            String members = resultSet.getString("group_members");

            resultSet.close();
            stmt.close();
            return members;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get all group information that contains the given user id
     * @param UID
     * @return
     */
    public Map<String, ArrayList<User>> getGroupStatus(String UID){
        Map<String, ArrayList<User>> groupStatus = new HashMap<>();
        try {
            Statement stmt = connect.createStatement();
            String query = "select group_id from userTable where user_id="  + "\'" + UID + "\'";
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();
            String groups = resultSet.getString("group_id");
            if(groups != null){
                String[] groupIds = groups.split("/");
                for(String id : groupIds){
                    ArrayList<User> users = new ArrayList<>();
                    query = "select group_name, group_members from groupTable where group_id="  + "\'" + id + "\'";
                    resultSet = stmt.executeQuery(query);
                    resultSet.next();
                    String user = resultSet.getString("group_members");
                    String name = resultSet.getString("group_name");
                    String[] ids = user.split("/");
                    for(String uid : ids){
                        users.add(getUserById(uid));
                    }
                    groupStatus.put(id + "/" + name, users);
                }
            }
            stmt.close();
            resultSet.close();

            return groupStatus;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get user info by user id
     * @param Id
     * @return
     */
    public User getUserById(String Id){
        try{
            Statement stmt = connect.createStatement();
            String query = "select * from userTable where user_id="  + "\'" + Id + "\'";
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();
            String name = resultSet.getString("user_name");
            String nickname = resultSet.getString("user_nick");
            String email = resultSet.getString("user_email");
            String gender = resultSet.getString("user_gender");
            String age = resultSet.getString("user_age");
            String nation = resultSet.getString("user_nation");
            String UID = resultSet.getString("user_id");
            String ip = resultSet.getString("user_ip");

            User normalUser;
            if(ip.equals("off-line")){
                normalUser = new User(name, nickname, email, gender, age, nation, UID, false);
            }
            else
                normalUser = new User(name, nickname, email, gender, age, nation, UID, true);



            stmt.close();
            resultSet.close();

            return normalUser;

        } catch (SQLException e) {
            e.printStackTrace();
            error("Error code -10");
            return null;
        }
    }

    /**
     * get user infos by email
     * @param email
     * @return
     */
    public User getUserByEmail(String email){
        try{
            Statement stmt = connect.createStatement();
            String query = "select * from userTable where user_email="  + "\'" + email + "\'";
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();
            String name = resultSet.getString("user_name");
            String nickname = resultSet.getString("user_nick");
            String Email = resultSet.getString("user_email");
            String gender = resultSet.getString("user_gender");
            String age = resultSet.getString("user_age");
            String nation = resultSet.getString("user_nation");
            String UID = resultSet.getString("user_id");
            String ip = resultSet.getString("user_ip");

            User normalUser;
            if(ip.equals("off-line")){
                normalUser = new User(name, nickname, email, gender, age, nation, UID, false);
            }
            else
                normalUser = new User(name, nickname, email, gender, age, nation, UID, true);

            stmt.close();
            resultSet.close();

            return normalUser;

        } catch (SQLException e) {
            e.printStackTrace();
            error("Error code -10");
            return null;
        }
    }

    /**
     * get user infos by ip
     * @param IP
     * @return
     */
    public User getUserIP(String IP){
        try{
            Statement stmt = connect.createStatement();
            String query = "select * from userTable where user_ip="  + "\'" + IP + "\'";
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();
            String name = resultSet.getString("user_name");
            String nickname = resultSet.getString("user_nick");
            String email = resultSet.getString("user_email");
            String gender = resultSet.getString("user_gender");
            String age = resultSet.getString("user_age");
            String nation = resultSet.getString("user_nation");
            String UID = resultSet.getString("user_id");

            User normalUser = new User(name, nickname, email, gender, age, nation, UID, true);

            stmt.close();
            resultSet.close();

            return normalUser;

        } catch (SQLException e) {
            e.printStackTrace();
            error("Error code -10");
            return null;
        }
    }

    /**
     * get all friends' infos by given user id
     * @param Id
     * @return
     */
    public ArrayList<User> getFriendsStatus(String Id){
        try{
            Statement stmt = connect.createStatement();
            String query = "select friendsUID from userTable where user_id="   + "\'" + Id + "\'";
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();
            String friendsUID = resultSet.getString("friendsUID");
            String[] ids = new String[0];
            if(friendsUID != null && !friendsUID.equals(""))
                ids = friendsUID.split("/");

            query = "select matchUID from userTable where user_id=" + "\'" + Id + "\'";
            resultSet = stmt.executeQuery(query);
            resultSet.next();
            String matchUID = resultSet.getString("matchUID");
            String[] matchids = new String[0];
            if(matchUID != null){
                matchids = matchUID.split("/");
            }
            ArrayList<User> friends = new ArrayList<User>();
            if(ids.length >= 1){
                for(String id : ids){
                    friends.add(getUserById(id));
                }
            }

            if(matchids.length >= 1){
                for(String id : matchids){
                    User matchUser = getUserById(id);
//                    System.out.println(matchUser);
                    matchUser.setMatched(true);
                    friends.add(matchUser);
                }
            }

            return friends;


        } catch (SQLException e) {
            e.printStackTrace();
            error("Error code -11");
            return null;
        }
    }

    /**
     * set friends of each other
     * @param id1
     * @param id2
     */
    public void addFriends(String id1, String id2){
        try{
            Statement stmt = connect.createStatement();
            String query = "select friendsUID from userTable where user_id=" + "\'" + id1 + "\'";
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();
            String friendsUID = resultSet.getString("friendsUID");
            if(friendsUID == null){
                friendsUID = "";
            }
            friendsUID += id2 + "/";
            String sql = "update userTable set friendsUID=" + "\'" + friendsUID + "\'" + " where user_id=" + "\'" + id1 + "\'";
            stmt.execute(sql);

            query =  "select friendsUID from userTable where user_id=" + "\'" + id2 + "\'";
            resultSet = stmt.executeQuery(query);
            resultSet.next();
            friendsUID = resultSet.getString("friendsUID");
            if(friendsUID == null){
                friendsUID = "";
            }
            friendsUID += id1 + "/";
            sql = "update userTable set friendsUID=" + "\'" + friendsUID + "\'" + " where user_id=" + "\'" + id2 + "\'";
            stmt.execute(sql);

            stmt.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * logout
     * @param UID
     * @return
     */
    public boolean logout(String UID){
        try {
            Statement stmt = connect.createStatement();
            String st = "update userTable set user_login=false, user_ip = 'off-line' where user_id=" + "\'" + UID + "\'";
            stmt.executeUpdate(st);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            error("Error code: -6");
            return false;
        }
    }

    /**
     * make id1 and id2 be match friends
     * @param id1
     * @param id2
     */
    public void writeMatchPair(String id1, String id2){
        try{
            Statement stmt = connect.createStatement();

            String query = "select friendsUID from userTable where user_id=" + "\'" + id1 + "\'";
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();
            String friendsUID = resultSet.getString("friendsUID");
            if(friendsUID != null){
                for(String id : friendsUID.split("/")){
                    if (id.equals(id2)){
                        return;
                    }
                }
            }

            // If not friends, then check whether if is match friend.
            query = "select matchUID from userTable where user_id=" + "\'" + id1 + "\'";
            resultSet = stmt.executeQuery(query);
            resultSet.next();
            String matchUID = resultSet.getString("matchUID");
            if(matchUID != null){
                for(String id : matchUID.split("/")){
                    if (id.equals(id2)){
                        return;
                    }
                }
            }
            else
                matchUID = "";

            // if not match friend, then match.
            matchUID += id2 + "/";
            String sql = "update userTable set matchUID=" + "\'" + matchUID + "\'" + " where user_id=" + "\'" + id1 + "\'";
            stmt.execute(sql);

            query =  "select matchUID from userTable where user_id=" + "\'" + id2 + "\'";
            resultSet = stmt.executeQuery(query);
            resultSet.next();
            matchUID = resultSet.getString("matchUID");
            if(matchUID == null)
                matchUID = "";
            matchUID += id1 + "/";
            sql = "update userTable set matchUID=" + "\'" + matchUID + "\'" + " where user_id=" + "\'" + id2 + "\'";
            stmt.execute(sql);

            stmt.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * record the chat info
     * @param info
     * @return
     */
    public boolean writeInfo(Info info){
        Boolean isPrivate = info.isPrivate;
        try {
            PreparedStatement preparedStatement;
            String sql;
            if(isPrivate){
                sql = "insert into messagePrivate(pair_id, fromID, toID, message, mess_date) values(?,?,?,?,?)";
                preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setString(1, info.fromID + info.toID);
                preparedStatement.setString(2, info.fromID);
                preparedStatement.setString(3, info.toID);
                preparedStatement.setString(4, info.message);
                preparedStatement.setString(5, info.time);
            }
            else {
                sql = "insert into messageGroup(fromID, groupID, message, mess_date) values(?,?,?,?)";
                preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setString(1, info.fromID);
                preparedStatement.setString(2, info.toID);
                preparedStatement.setString(3, info.message);
                preparedStatement.setString(4, info.time);
            }

            preparedStatement.executeUpdate();

            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get private chat info
     * @param id1
     * @param id2
     * @param isPrivate
     * @return
     */
    public ArrayList<Info> getInfo(String id1, String id2, boolean isPrivate){
        ArrayList<Info> chatHistory = new ArrayList<Info>();
        try{
            Statement stmt = connect.createStatement();
            if(isPrivate){
                String query = "select * from messagePrivate where pair_id=" + "\'" + id1+id2 + "\'" + " or pair_id=" + "\'" + id2+id1 + "\'";
                ResultSet resultSet = stmt.executeQuery(query);
                while(resultSet.next()){
                    String fromId = resultSet.getString("fromID");
                    String toID = resultSet.getString("toID");
                    String message = resultSet.getString("message");
                    String time = resultSet.getString("mess_date");
                    Info info = new Info(fromId, toID, message, time, true);
                    chatHistory.add(info);
                }
            }
            else {
                String query = "select * from messageGroup where groupID=" + "\'" + id2 + "\'";
                ResultSet resultSet = stmt.executeQuery(query);
                while(resultSet.next()){
                    String fromID = resultSet.getString("fromID");
                    String toID = resultSet.getString("groupID");
                    String message = resultSet.getString("message");
                    String time = resultSet.getString("mess_date");
                    Info info = new Info(fromID, toID, message, time, false);
                    chatHistory.add(info);
                }
            }

            return chatHistory;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * finish the db connection, close all the connections
     * @return
     */
    public boolean finish(){
        try {
            connect.close();
            return true;
        } catch (SQLException e) {
            error("Error occurs when close the connection to the Sql server. Error Code: -3");
            e.printStackTrace();
            return false;
        }
    }

    void tell(Object s){
//        System.out.println(s);
    }

    void error(String error){
        System.err.println(error);
    }

}
