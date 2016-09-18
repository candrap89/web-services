package tool;

import java.sql.*;

public class KoneksiORACLE {

    private String driverName = "oracle.jdbc.driver.OracleDriver";
   // private String host = "10.35.75.5";
    private String host = "alcatraz";
    private String dbName = "orcl";
    private String dbUser = "system";
    private String dbPassword = "oracle";
    private Connection conn = null;
    private boolean connect;
    private Statement statement;

    public KoneksiORACLE() {
        init();
    }
    public String getDriverName() {
        return driverName;
    }
    public String getHost() {
        return host;
    }
    public String getDbName() {
        return dbName;
    }
    public String getDbUser() {
        return dbUser;
    }
    public String getDbPassword() {
        return dbPassword;
    }
    public Connection getConn() {
        return conn;
    }
    public void setConnect(boolean connect) {
        this.connect = connect;
    }
    public boolean getConnect() {
        return connect;
    }
    public Statement getStatement() {
        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      //   statement = conn.createStatement();

        } catch (SQLException ex) {
            System.out.println("Pesan Error = " + ex.getMessage());
           //ex.printStackTrace();
        }
        return statement;
    }
    public void init() {
       // String currentUrl = "jdbc:mysql://" + getHost() + "/" + getDbName();
        //String currentUrl = "jdbc:oracle:oci8:@alcatraz:1521:orcl" ;//+ getDbName();
         String currentUrl = "jdbc:oracle:thin:@"+host+":1521:orcl" ;//+ getDbName();
        try {
            Class.forName(getDriverName());
            conn = DriverManager.getConnection(currentUrl, getDbUser(), getDbPassword());
            setConnect(true);
        } catch (ClassNotFoundException cnfe) {
            conn = null;
            setConnect(false);
            System.err.println("Pesan Error class = " + cnfe.getMessage());
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            conn = null;
            setConnect(false);
            System.out.println("Pesan Error sqle= " + sqle.getMessage());
            sqle.printStackTrace();
        }
    }
    public void close() throws SQLException {
        conn.close();
    }
}
