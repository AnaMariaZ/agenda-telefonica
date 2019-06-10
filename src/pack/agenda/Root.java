package pack.agenda;
import java.util.Scanner;

public class Root {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MethodContainer callFunction = new MethodContainer();
        callFunction.createTable();

        boolean quit = false;
        int choice;
        arataOptiuniAgendaContacte();
        while (!quit) {
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    //Afiseaza optiuni meniu
                    arataOptiuniAgendaContacte();
                    break;
                case 1:
                    //1 - Creaza contact si adauga in baza de date"
                    callFunction.adougareContact();
                    break;
                case 2:
                    //2 - Modifica nume si prenume pentru un contact, cautand cu adresa mail
                    callFunction.modificareContact();
                    break;
                case 3:
                    //3 - Sterge contact cautand dupa nume
                    callFunction.eliminareContact();
                    break;
                case 4:
                    ///4 - Afiseaza toate contactele din agenda
                    callFunction.afisareaTuturorContactelor();
                    break;
                case 5:
                    ///5 - Afiseaza numele si prenumele contactelor cu o anumita adresa mail
                    callFunction.cautareNumeContact();
                    break;
                case 6:
                    ///Afiseaza numele si prenumele contactelor cu un anumit numar telefon
                    callFunction.cautareNumarTelefonContact();
                    break;
                case 7:
                    //exit
                    quit = true;
                    break;
            }
        }
    }

    /**
     * Prints menu options
     */
    private static void arataOptiuniAgendaContacte() {
        System.out.println("\nPlease select your choice ");
        System.out.println("\t 0 - Afiseaza optiuni meniu");
        System.out.println("\t 1 - Creaza contact si adauga in baza de date");
        System.out.println("\t 2 - Modifica nume si prenume pentru un contact, cautand cu adresa mail");
        System.out.println("\t 3 - Sterge contact cautand dupa nume");
        System.out.println("\t 4 - Afiseaza toate contactele din agenda");
        System.out.println("\t 5 - Afiseaza numele si prenumele contactelor cu o anumita adresa mail");
        System.out.println("\t 6 - Afiseaza numele si prenumele contactelor cu un anumit numar telefon");
        System.out.println("\t 7 - Iesire meniu");
    }
}