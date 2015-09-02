package party.vermontbeast.mcl.methods;

import com.mysql.jdbc.PreparedStatement;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;



public class SQLManager {

    public static JavaPlugin plugin;

    public SQLManager(JavaPlugin plugin) {
        SQLManager.plugin = plugin;
    }

    public enum RESULT {
        SUCCESS, TRUE, FALSE, ERROR
    }

    public static party.vermontbeast.mcl.methods.mysql.MySQL MYSQL = new party.vermontbeast.mcl.methods.mysql.MySQL(plugin, "HOST", "3306", "DATABASE", "USERNAME", "PASSWORD");
    public static Connection c = null;

    public void createTable() {
        try {
            try {
                c = MYSQL.openConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            PreparedStatement ps = (PreparedStatement) c.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Global (UUID varchar(36) NOT NULL, name varchar(32) NOT NULL)");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[ERROR] Could not check if table exists, stopping server.");
            e.printStackTrace();
            Bukkit.getServer().shutdown();
        }
    }

    public static void checkConnection() {
        try {
            if(!MYSQL.checkConnection()) {
                c = MYSQL.openConnection();
            }
        }  catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RESULT updatePlayerName(UUID p, String newName) {
        checkConnection();
        try {
            PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE `Global` SET name=?, WHERE UUIDD = ?");
            ps.setString(1,  newName);
            ps.setString(2, p.toString());
            ps.executeUpdate();
            return RESULT.SUCCESS;
        } catch(SQLException e) {
            e.printStackTrace();
            return RESULT.ERROR;
        }
    }

    public String getName(UUID uuid) throws SQLException {
        checkConnection();
        PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT name FROM Global WHERE UUID = ?");
        ps.setString(1, uuid.toString());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("name");
        } else {
            return null;
        }
    }

    public UUID getUUID(String name) throws SQLException {
        checkConnection();
        PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT UUID FROM Global WHERE name = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return UUID.fromString(rs.getString("UUID"));
        } else {
            return null;
        }
    }

    public RESULT checkExists(UUID uuid) {
        checkConnection();
        try {
            PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT UUID FROM Global WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return RESULT.TRUE;
            }else {
                return RESULT.FALSE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return RESULT.ERROR;
        }
    }

    public RESULT addPlayer(UUID uuid, String name) {
        checkConnection();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
        if(checkExists(uuid) == RESULT.FALSE) {
            PreparedStatement ps;
            try {
                ps = (PreparedStatement) c.prepareStatement("INSERT INTO `Global` VALUES(?,?)");
                ps.setString(1, uuid.toString());
                ps.setString(2, name);
                ps.executeUpdate();
                return RESULT.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                return RESULT.ERROR;
            }
        }else {
            return RESULT.ERROR;
        }
    }

	/*
	 * Votes
	 */

    public static int getVotes(UUID uuid) throws SQLException {
        checkConnection();
        PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT Votes FROM Global WHERE UUID = ?");
        ps.setString(1, uuid.toString());
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return rs.getInt("Votes");
        }else{
            return -1;
        }
    }


    public static RESULT addVotes(UUID uuid, int amount) {
        int gold = -1;
        try {
            gold = SQLManager.getVotes(uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(gold == -1) {
            return RESULT.ERROR;
        }
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) c.prepareStatement("UPDATE `Global` SET Votes =? WHERE UUID = ?");
            ps.setInt(1, amount + gold);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            return RESULT.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return RESULT.ERROR;
        }
    }

    public static RESULT setVotes(UUID uuid, int amount) {
        checkConnection();
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) c.prepareStatement("UPDATE `Global` SET Votes = ? WHERE UUID = ?");
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            return RESULT.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return RESULT.ERROR;
        }
    }
}