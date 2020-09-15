import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Textreader {

    String land;
    String Liga;
    String jahr;
    String Saison;
    String Spieltag;
    String Datum;
    String Verein_Heim;
    String Tore_Heim;
    String Tore_Auswaerts;
    String Verein_Auswaerts;
    String homeGoalsHalfTime;
    String awayGoalsHalfTime;
    int counter;

    public void readData (String url) throws IOException, ParseException, SQLException {

        Datenbank data = new Datenbank();

        FileReader file = new FileReader(url);
        BufferedReader reader = new BufferedReader(file);
        counter = 1;

        String line = reader.readLine();
        land = line;
        line = reader.readLine();

        Liga = line;
        line = reader.readLine();

        if (line.contains("Saison")){
            Saison = line.substring(7,16);
        }
        while (line !=null)
        {
            if (line.contains("Jahr")){
                jahr = line.substring(5,9);
                line = reader.readLine();
                counter = 1;
                System.out.println(jahr);
            }
            if (line.contains("Spieltag")){
                if(line.length()==12) {
                    Spieltag = line.substring(0, 2);
                    line = reader.readLine();
                    counter = 1;
                }
                else {
                    Spieltag = line.substring(0,1);
                    line = reader.readLine();
                    counter = 1;
                }
            }
           switch(counter) {
               case 1:
                   Datum = line.substring(0, 5)+"."+jahr;
                   counter = 2;
                   break;
               case 2:
                   Verein_Heim = line;
                   counter =3;
                   break;
               case 3:
                   if(line.substring(3,4).contains("-")){
                       Tore_Heim = line.substring(0,2);
                       Tore_Auswaerts = line.substring(5,6);
                       counter = 4;
                   }
                   else {
                       if(line.length()==6){
                           Tore_Heim = line.substring(0,1);
                           Tore_Auswaerts = line.substring(4,6);
                           counter = 4;
                       }
                       else {
                           Tore_Heim = line.substring(0, 1);
                           Tore_Auswaerts = line.substring(4, 5);
                           counter = 4;
                       }
                   }
                   break;
               case 4:
                   Verein_Auswaerts = line;
                   counter = 5;
                   break;
               case 5:
                   homeGoalsHalfTime = line.substring(1, 2);
                   awayGoalsHalfTime = line.substring(5, 6);
                   counter = 1;

                   java.util.Date date = new SimpleDateFormat("dd.MM.yyyy").parse(Datum);
                   java.sql.Date sqldate = new java.sql.Date(date.getTime());

                   int Tore_Heimint  = Integer.parseInt(this.Tore_Heim);
                   int Tore_Auswaertsint = Integer.parseInt(this.Tore_Auswaerts);
                   int Spieltagint = Integer.parseInt(this.Spieltag);
                   int Tore_Heim_Halbzeit = Integer.parseInt(this.homeGoalsHalfTime);
                   int Tore_Auswaerts_Halbzeit = Integer.parseInt(this.awayGoalsHalfTime);

                   data.einlesen(sqldate,Verein_Heim,Verein_Auswaerts,Tore_Heimint,Tore_Auswaertsint,Spieltagint,Saison,Tore_Heim_Halbzeit,Tore_Auswaerts_Halbzeit,land,Liga);
                // System.out.println("LAND: " + land+ " || "+"SAISON: "+Saison+" || "+"SPIELTAG: "+Spieltag +" || "+"DATUM: "+Datum+" || "+ "VEREIN_HEIM: "+Verein_Heim+ " TH ("+Tore_Heim+ "-"+ Tore_Auswaerts+") TA "+Verein_Auswaerts+ " || "+ "((("+homeGoalsHalfTime+ " -- "+awayGoalsHalfTime+")))");
                   break;
               default:
                   System.out.println("ETWAS IST SCHIEF GELAUFEN");
           }
            line = reader.readLine();
        }
        }
    }

