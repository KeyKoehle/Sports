import java.sql.*;
import java.util.Date;

public class Datenbank {


    String url = "jdbc:mysql://localhost:3306/bundesliga?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String password = "1234";

    public void verbinde() throws SQLException {

        Connection myConnection = DriverManager.getConnection(url, user, password);
        Statement myStatement = myConnection.createStatement();
        String sql = "select * from alle_spiele";
        ResultSet rs = myStatement.executeQuery(sql);

    }

    public void einlesen (Date Datum, String Verein_Heim, String Verein_Auswaerts, int Tore_Heim, int Tore_Auswaerts, int Spieltag, String Saison, int Tore_Heim_Halbzeit, int Tore_Auswaerts_Halbzeit, String Land, String Liga ) throws SQLException {

        Connection myConnection = DriverManager.getConnection(url, user, password);
        Statement myStatement = myConnection.createStatement();
        String sql = "INSERT INTO alle_spiele (datum, Verein_Heim, Verein_Auswaerts, Tore_Heim, Tore_Auswaerts, Spieltag, Saison, Tore_Heim_Halbzeit, Tore_Auswaerts_Halbzeit, Land, Liga) " +
                     "VALUES ('"+Datum+"','"+Verein_Heim+"','"+Verein_Auswaerts+"','"+Tore_Heim+"','"+Tore_Auswaerts+"','"+Spieltag+"','"+Saison+"','"+Tore_Heim_Halbzeit+"','"+Tore_Auswaerts_Halbzeit+"','"+Land+"','"+Liga+"')";
        myStatement.execute(sql);

    }
}
