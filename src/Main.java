import com.mysql.cj.protocol.Resultset;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static String url = "C:/Kai/Programme/Sports/Fußballdaten/1. Bundesliga/Bundesliga_2017_2018.txt";

    public static void main (String [] args) throws IOException, SQLException, ParseException {

        Textreader read = new Textreader();
        read.readData(url);

        Datenbank data = new Datenbank();
        data.verbinde();


    }
}



