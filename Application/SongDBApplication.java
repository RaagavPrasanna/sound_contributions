package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SongDBApplication {
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }
    public Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@pdbora19c.dawsoncollege.qc.ca:1521/pdbora19c.dawsoncollege.qc.ca";
        Connection connection = DriverManager.getConnection(url, this.username, this.password);
        System.out.println("Connected to the database");
        return connection;
    }

    public void login(String username, String password) throws SQLException {
        this.username = username;
        this.password = password; 
        getConnection();
    }

    public String getArtistsByRecId(String inpRecID) throws SQLException{
        String retStr = "";
        boolean returnsQuery = false;
        String qStr = "SELECT re.recording_id, a.artist_name, r.role_name FROM RECORDING_ARTIST_ROLE rar"
        +" INNER JOIN ARTIST_ROLE ar"
        +" ON rar.ARTIST_ROLE_KEY = ar.ARTIST_ROLE_KEY"
        +" INNER JOIN ARTIST a"
        +" ON a.ARTIST_KEY = ar.ARTIST_KEY"
        +" INNER JOIN ROLE r"
        +" ON r.ROLE_KEY = ar.ROLE_KEY"
        +" INNER JOIN RECORDING re"
        +" ON re.recording_key = rar.recording_key"
        +" WHERE re.recording_id = ?";
    
        PreparedStatement query = getConnection().prepareStatement(qStr);
        query.setString(1, inpRecID);
        ResultSet results = query.executeQuery();
        while(results.next()) {
            retStr += "RECORDING_ID: " +results.getString("RECORDING_ID") +", ARTIST NAME: " 
                +results.getString("artist_name") + ", ROLE NAME: " +results.getString("role_name") + "\n";
            returnsQuery = true;
        }
    
        if(!returnsQuery) {
            throw new IllegalArgumentException("Artists data not found for RECORDING_ID: " +inpRecID);
        }
        return retStr;
    }
    public String getArtistsByCompName(String inpCompID) throws SQLException {
        String retStr = "";
        boolean returnsQuery = false;
        String qStr = "SELECT c.compilation_name, a.artist_name, r.role_name FROM COMPILATION_ARTIST_ROLE cr"
        +" INNER JOIN ARTIST_ROLE ar"
        +" ON ar.ARTIST_ROLE_KEY = cr.ARTIST_ROLE_KEY"
        +" INNER JOIN ARTIST a"
        +" ON a.ARTIST_KEY = ar.ARTIST_KEY"
        +" INNER JOIN ROLE r"
        +" ON r.ROLE_KEY = ar.ROLE_KEY"
        +" INNER JOIN COMPILATION c"
        +" ON c.compilation_key = cr.compilation_key"
        +" WHERE c.compilation_name = ?";
    
        PreparedStatement query = getConnection().prepareStatement(qStr);
        query.setString(1, inpCompID);
        ResultSet results = query.executeQuery();
        while(results.next()) {
            retStr += "COMPILATION_NAME: " +results.getString("COMPILATION_NAME") +", ARTIST NAME: " +results.getString("artist_name") + ", ROLE NAME: " +results.getString("role_name") + "\n";
            returnsQuery = true;
        }
    
        if(!returnsQuery) {
            throw new IllegalArgumentException("Artists data not found for COMPILATION_NAME: " +inpCompID);
        }
        return retStr;
    }
    
    public String getRecordingDetails(String inpRecId) throws SQLException{
        String retStr = "";
        boolean returnsQuery = false;
        String qStr = "SELECT r.recording_id, r.RECORDING_DATE, r.RECORDING_DURATION"
        +" FROM RECORDING r"
        +" WHERE r.recording_id = ?";
    
        PreparedStatement query = getConnection().prepareStatement(qStr);
        query.setString(1, inpRecId);
        ResultSet results = query.executeQuery();
        while(results.next()) {
            retStr += "RECORDING_ID: " +results.getString("RECORDING_ID") +", RECORDING DATE: " +results.getString("RECORDING_DATE") +", RECORDING_DURATION: " 
                +results.getString("RECORDING_DURATION");
            returnsQuery = true;
        }
    
        if(!returnsQuery) {
            throw new IllegalArgumentException("Recording details data not found for RECORDING_ID: " +inpRecId);
        }
        return retStr;
    }
    
    public String getCompilationDetails(String inpCompId) throws SQLException{
        String retStr = "";
        boolean returnsQuery = false;
        String qStr = "SELECT c.COMPILATION_NAME,c.COMPILATION_DATE, c.COMPILATION_DURATION"
        +" FROM COMPILATION c"
        +" WHERE c.compilation_name = ?";
    
        PreparedStatement query = getConnection().prepareStatement(qStr);
        query.setString(1, inpCompId);
        ResultSet results = query.executeQuery();
        while(results.next()) {
            retStr += "COMPILATION NAME: " +results.getString("COMPILATION_NAME") +", COMPILATION DATE: " +results.getString("COMPILATION_DATE") +", COMPILATION DURATION: " 
                +results.getString("COMPILATION_DURATION");
            returnsQuery = true;
        }
    
        if(!returnsQuery) {
            throw new IllegalArgumentException("Compilation details data not found for COMPILATION_NAME: " +inpCompId);
        }
        return retStr;
    }
    
    public String getContributorRoles(String inpArtistKey) throws SQLException{
        String retStr = "";
        boolean returnsQuery = false;
        String qStr = "SELECT a.artist_name, r.role_name FROM ARTIST a"
        +" INNER JOIN artist_role ar"
        +" ON ar.artist_key = a.artist_key"
        +" INNER JOIN ROLE r"
        +" ON r.role_key = ar.role_key"
        +" WHERE a.artist_name = ?";
    
        PreparedStatement query = getConnection().prepareStatement(qStr);
        query.setString(1, inpArtistKey);
        ResultSet results = query.executeQuery();
        while (results.next()) {
            retStr += "ARTIST NAME: " +results.getString("ARTIST_NAME") +", ROLE: " +results.getString("ROLE_NAME") +"\n";
            returnsQuery = true;
        }
        if(!returnsQuery) {
            throw new IllegalArgumentException("Artist and roles details not found for: " +inpArtistKey);
        }
        return retStr;
    } 
    
        public String getImplcsByArt(String inpArtName) throws SQLException{
        String retStr = "";
        boolean returnsQuery = false;
        String qStr = "SELECT a.artist_name, c.compilation_name,r.recording_id, rl.role_name"  
        +" FROM ARTIST a"
        +" INNER JOIN ARTIST_ROLE ar"
        +" ON ar.artist_key = a.artist_key"
        +" INNER JOIN RECORDING_ARTIST_ROLE rar"
        +" ON rar.artist_role_key = ar.artist_role_key"
        +" INNER JOIN RECORDING r"
        +" ON rar.recording_key = r.recording_key"
        +" INNER JOIN ROLE rl"
        +" ON ar.role_key = rl.role_key"
        +" INNER JOIN COMPILATION_COMPONENT cc"
        +" ON cc.recording_key = r.recording_key"
        +" INNER JOIN COMPILATION c"
        +" ON cc.parent_compilation_key = c.compilation_key"
        +" WHERE a.artist_name = ?";
    
        PreparedStatement query = getConnection().prepareStatement(qStr);
        query.setString(1, inpArtName);
        ResultSet results = query.executeQuery();
        while (results.next()) {
            retStr += "ARTIST_NAME: " +results.getString("ARTIST_NAME") +", COMPILATION_NAME: " +results.getString("COMPILATION_NAME") 
                +", RECORDING_ID: " +results.getString("RECORDING_ID") +", ROLE: " +results.getString("ROLE_NAME") +"\n";
            returnsQuery = true;
        }
        if(!returnsQuery) {
            throw new IllegalArgumentException("Artist and implications details not found for: " +inpArtName);
        }
        return retStr;
    }

    //update database
    public int updateValue(String oldValue, String newValue, String table, String column) throws SQLException {
        Statement stmt = getConnection().createStatement();
        String query = "UPDATE " + table + " SET " + column + " = '" + newValue + "', last_updated_datetime = CURRENT_TIMESTAMP, last_updated_user = '" + this.getUsername() + "' WHERE " + column + " = '" + oldValue + "'";
        
        int count = stmt.executeUpdate(query);
        return count;
    }
    public void insertRecording(String recId, String duration, String date) throws SQLException{
        Statement stmt = getConnection().createStatement();
        String query = "INSERT INTO recording (RECORDING_ID, RECORDING_DURATION, RECORDING_DATE, CREATED_USER, CREATED_DATETIME)"
        +" VALUES('" +recId +"', " +duration +", '" +date +"', '" +getUsername()  +"', " +"CURRENT_TIMESTAMP)";
        stmt.executeUpdate(query);
    }
    public void insertContributorName(String name) throws SQLException{
        Statement stmt = getConnection().createStatement();
        String query = "INSERT INTO ARTIST (ARTIST_NAME, CREATED_USER, CREATED_DATETIME)"
        +" VALUES('" +name +"', '" +getUsername() +"', " +"CURRENT_TIMESTAMP)";
        stmt.executeUpdate(query);
    }
    public void insertContributorRole(String role) throws SQLException{
        Statement stmt = getConnection().createStatement();
        String query = "INSERT INTO ROLE (ROLE_NAME, CREATED_USER, CREATED_DATETIME)"
        +" VALUES('" +role +"', '" +getUsername() +"', " +"CURRENT_TIMESTAMP)";
        stmt.executeUpdate(query);
    }
    public void insertArtistRole(String name, String role) throws SQLException{
        Statement stmt = getConnection().createStatement();
        String query = "INSERT INTO ARTIST_ROLE (ARTIST_KEY, ROLE_KEY, CREATED_USER, CREATED_DATETIME)"
        +" VALUES("
        +" (SELECT ARTIST_KEY FROM ARTIST"
            +" WHERE artist_name='" +name +"'),"
        +" (SELECT ROLE_KEY FROM ROLE"
            +" WHERE role_name ='" +role +"'),'"
        +getUsername() +"', "
        +" CURRENT_TIMESTAMP"
        +")";
        stmt.executeUpdate(query);
    }

    public void insertRecordingArtistRole(String name, String role, String recId) throws SQLException{
        Statement stmt = getConnection().createStatement();
        String query = " INSERT INTO RECORDING_ARTIST_ROLE (recording_key, artist_role_key, created_user, created_datetime)"
            +" VALUES("
                +" (SELECT RECORDING_KEY FROM RECORDING"
                    +" WHERE recording_id = '" +recId +"'),"
                +" (SELECT ARTIST_ROLE_KEY FROM ARTIST_ROLE"
                    +" WHERE artist_key ="
                        +" (SELECT artist_key FROM ARTIST"
                            +" WHERE artist_name= '" +name +"')"
                    +" AND"
                    +" role_key ="
                        +" (SELECT role_key FROM ROLE"
                            +" WHERE role_name = '"  +role +"')), '"
                +getUsername() +"', "
                +" CURRENT_TIMESTAMP"
            +")";
        stmt.executeUpdate(query);
    }

    public boolean recIdExists(String inpRecId) throws SQLException{
        boolean exists = false;
        String qStr = "SELECT * FROM RECORDING"
        +" WHERE recording_id = ?";
    
        PreparedStatement query = getConnection().prepareStatement(qStr);
        query.setString(1, inpRecId);
        ResultSet results = query.executeQuery();
        while(results.next()) {
            exists = true;
        }
        return exists;
    }
} 