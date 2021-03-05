
import java.sql.*;


public class DBConnection {
    private static Connection connection;

    private static final String DB_NAME = "learn";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "**********";
    private static StringBuilder insertQuery = new StringBuilder();

    public static long getInsertQueryLenth() {
        return insertQuery.length();
    }

    public static String getInsertQuery() {
        String insertString = insertQuery.toString();

        insertQuery = new StringBuilder();
        return insertString;
    }

    public static Connection getConnection() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + DB_NAME
                                + "?user=" + DB_USER
                                + "&password=" + DB_PASS
                                + "&useSSL=false&serverTimezone=UTC");
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDay DATE NOT NULL, " +
                        "num_station INT NOT NULL, " +
                        "time DATETIME NOT NULL," +
                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return connection;
    }

    public static void executeMultiInsert(String insertString) throws Exception {

        String sql = "INSERT INTO voter_count(name, birthDay, num_station, time) "
                + "VALUES" + insertString;



        /**
         * поставил условие что бы откинуть запросы с отсутствующими VALUES
         */
        if (sql.length() > 70) {
            try {
                DBConnection.getConnection().createStatement().execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public static void countVoter(String name, String birthDay, String numStation, String time) {
        birthDay = birthDay.replace('.', '-');
        insertQuery.append((insertQuery.length() == 0 ? "" : ",")
                + "('" + name + "', '" + birthDay + "', '" + numStation + "', '" + time + "')");


    }

//    public static void printVoterCounts() throws SQLException {
//        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
//        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
//        while (rs.next()) {
//            System.out.println("\t" + rs.getString("name") + " (" +
//                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
//        }
//    }
}
