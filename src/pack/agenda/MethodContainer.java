package pack.agenda;

import java.sql.*;
import java.util.Scanner;

public class MethodContainer {

    public void createTable(){
        try{
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\azoitanu\\IdeaProjects\\Agenda telefonica\\agendaTelefonica.db");
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS contacts " +
                "(nume TEXT, prenume TEXT, numarTelefonFix TEXT, numarTelefonMobil TEXT, email TEXT)");

        statement.close();
        conn.close();
    } catch (SQLException e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }
}


    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\azoitanu\\IdeaProjects\\Agenda telefonica\\agendaTelefonica.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

        public void adougareContact () {
        String sql = "INSERT INTO contacts(nume, prenume, numarTelefonFix, numarTelefonMobil, email) VALUES(?,?,?,?,?)";

            Scanner keyboard = new Scanner(System.in);
            System.out.print("Introduceti nume: ");
            String nume = keyboard.nextLine();

            System.out.print("Introduceti prenume: ");
            String prenume = keyboard.nextLine();

            System.out.print("Introduceti numar telefon fix: ");
            String numarTelefonFix = keyboard.nextLine();

            System.out.print("Introduceti numar telefon mobil: ");
            String numarTelefonMobil = keyboard.nextLine();

            System.out.print("Introduceti adresa mail: ");
            String email = keyboard.nextLine();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nume);
            pstmt.setString(2, prenume);
            pstmt.setString(3, numarTelefonFix);
            pstmt.setString(4, numarTelefonMobil);
            pstmt.setString(5, email);


            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificareContact(){
        String sql = "UPDATE contacts SET nume = ? , " + "prenume = ? " + "WHERE email = ?";

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Schimbati numele si prenumele cu adresa mail:  ");
        String email = keyboard.nextLine();

        System.out.print("Introduceti nume dorit: ");
        String nume = keyboard.nextLine();

        System.out.print("Introduceti prenume dorit: ");
        String prenume = keyboard.nextLine();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, nume);
            pstmt.setString(2, prenume);
            pstmt.setString(3, email);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminareContact(){
        String sql = "DELETE FROM contacts WHERE nume = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            Scanner keyboard = new Scanner(System.in);

            System.out.print("Stergeti din baza de date contactul care are numele:  ");
            String nume = keyboard.nextLine();
            // set the corresponding param
            pstmt.setString(1, nume);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void afisareaTuturorContactelor(){
        String sql = "SELECT nume, prenume, numarTelefonFix, numarTelefonMobil, email FROM contacts";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("nume") +  "\t" +
                        rs.getString("prenume") + "\t" +
                        rs.getString("numarTelefonFix") + "\t" +
                        rs.getString("numarTelefonMobil") + "\t" +
                        rs.getString("email") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void cautareNumeContact(){
        String sql = "SELECT nume, prenume "
                + "FROM contacts WHERE email = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            Scanner keyboard = new Scanner(System.in);

            System.out.print("Afisati numele contactelor cu adresa mail:  ");
            String email = keyboard.nextLine();

            // set the value
            pstmt.setString(1, email);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                System.out.println(
                        rs.getString("nume") + "\t" + rs.getString("prenume") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cautareNumarTelefonContact(){
        String sql = "SELECT nume, prenume "
                + "FROM contacts WHERE numarTelefonMobil = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            Scanner keyboard = new Scanner(System.in);

            System.out.print("Afisati numele contactelor cu numarul de telefon mobil:  ");
            String numarTelefonMobil= keyboard.nextLine();

            // set the value
            pstmt.setString(1, numarTelefonMobil);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                System.out.println(
                        rs.getString("nume") + "\t" + rs.getString("prenume") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
