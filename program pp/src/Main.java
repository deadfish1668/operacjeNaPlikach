import javax.swing.*;
import java.util.*;
import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args)throws FileNotFoundException, IOException, NoSuchElementException{
        Scanner scan = new Scanner(System.in);
        int nr;
        do{
            System.out.println("Aplikacja Szpitalna");
            System.out.println("WYBIERZ OPERACJE: ");
            System.out.println("-----------------");
            System.out.println("1. Wypisz liste pacjentów pozostającyach w szpitalu.");
            System.out.println("2. Wypisz pacjentów NFZ.");
            System.out.println("3. Wypisz pacjentów prywatnych.");
            System.out.println("4. Posortuj pacjentów w kolejności alfabetycznej.");
            System.out.println("5. Dodaj nowego pacjenta.");
            System.out.println("6. Gra.");
            System.out.println("-----------------");
            System.out.println("0. Zamknij program");
            do{
                System.out.print("Wybierz 0-6: ");
                nr = scan.nextInt();
            }
            while( nr < 0 || nr > 6 );
            switch( nr ){
                case 1: m1(); break;
                case 2: m2(); break;
                case 3: m3(); break;
                case 4: m4(); break;
                case 5: m5(); break;
                case 6: m6(); break;
                default: System.out.print("\nKONIEC\n");
            }
        }
        while( nr != 0 );
    }
    static void m1()throws IOException, FileNotFoundException{
        System.out.println("Wybierz przedział wiekowy pacjentów: ");
        System.out.println("Wszyscy - 1");
        System.out.println("Dzieci(0 - 17) - 2");
        System.out.println("Dorośli(18 - 65) - 3");
        System.out.println("Emeryci(65>) - 4");
        System.out.println("Powrót do menu - 0");
        Scanner scan = new Scanner(System.in);
        int nr = scan.nextInt();
        if(nr == 1){
            File plik = new File("dane.txt");
            try {
                Scanner scanner = new Scanner(plik);
                while (scanner.hasNextLine()) {
                    String linia = scanner.nextLine();
                    System.out.println(linia);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(nr == 2) {
            try {
                File plik = new File("dane.txt");
                Scanner scanner = new Scanner(plik);
                while (scanner.hasNextLine()) {
                    String linia = scanner.nextLine();
                    String[] dane = linia.split(" ");
                    String imie = dane[0];
                    String nazwisko = dane[1];
                    int wiek = Integer.parseInt(dane[2]);

                    if (wiek < 18) {
                        System.out.println(imie + " " + nazwisko + ", " + wiek);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        if(nr == 3) {
            try {
                File plik = new File("dane.txt");
                Scanner scanner = new Scanner(plik);
                while (scanner.hasNextLine()) {
                    String linia = scanner.nextLine();
                    String[] dane = linia.split(" ");
                    String imie = dane[0];
                    String nazwisko = dane[1];
                    int wiek = Integer.parseInt(dane[2]);

                    if (wiek >= 18 && wiek < 65 ) {
                        System.out.println(imie + " " + nazwisko + ", " + wiek);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        if(nr == 4) {
            try {
                File plik = new File("dane.txt");
                Scanner scanner = new Scanner(plik);
                while (scanner.hasNextLine()) {
                    String linia = scanner.nextLine();
                    String[] dane = linia.split(" ");
                    String imie = dane[0];
                    String nazwisko = dane[1];
                    int wiek = Integer.parseInt(dane[2]);

                    if ( wiek > 65 ) {
                        System.out.println(imie + " " + nazwisko + ", " + wiek);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
    static void m2(){

        try {
            File plik = new File("dane.txt");
            Scanner scanner = new Scanner(plik);
            while (scanner.hasNextLine()) {
                String linia = scanner.nextLine();
                String[] dane = linia.split(" ");
                String imie = dane[0];
                String nazwisko = dane[1];
                int wiek = Integer.parseInt(dane[2]);
                String typ = dane[3];

                if (typ.equals("NFZ")) {
                    System.out.println(imie + " " + nazwisko + ", " + wiek + " lat, ubezpieczenie NFZ");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    static void m3(){

        try {
            File plik = new File("dane.txt");
            Scanner scanner = new Scanner(plik);
            while (scanner.hasNextLine()) {
                String linia = scanner.nextLine();
                String[] dane = linia.split(" ");
                String imie = dane[0];
                String nazwisko = dane[1];
                int wiek = Integer.parseInt(dane[2]);
                String typ = dane[3];

                if (typ.equals("Prywatnie")) {
                    System.out.println(imie + " " + nazwisko + ", " + wiek + " lat, prywatnie");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    static void m4(){

        List<Osoba> listaOsob = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("dane.txt"))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] dane = linia.split(" ");
                String imie = dane[0];
                String nazwisko = dane[1];
                int wiek = Integer.parseInt(dane[2]);
                String typ = dane[3];
                Osoba osoba = new Osoba(imie, nazwisko, wiek, typ);
                listaOsob.add(osoba);
            }
        } catch (IOException e) {
            System.err.println("Błąd wczytywania danych z pliku: " + e.getMessage());
            System.exit(1);
        }

        Collections.sort(listaOsob, new Comparator<Osoba>() {

            public int compare(Osoba o1, Osoba o2) {
                int porownanieNazwisk = o1.getNazwisko().compareTo(o2.getNazwisko());
                if (porownanieNazwisk != 0) {
                    return porownanieNazwisk;
                } else {
                    return o1.getImie().compareTo(o2.getImie());
                }
            }
        });

        for (Osoba osoba : listaOsob) {
            System.out.println(osoba);
        }
    }

    private static class Osoba {
        private String imie;
        private String nazwisko;
        private int wiek;
        private String typ;

        public Osoba(String imie, String nazwisko, int wiek, String typ) {
            this.imie = imie;
            this.nazwisko = nazwisko;
            this.wiek = wiek;
            this.typ = typ;
        }

        public String getImie() {
            return imie;
        }

        public String getNazwisko() {
            return nazwisko;
        }

        public int getWiek() {
            return wiek;
        }

        public String getTypOpieki() {
            return typ;
        }

        public String toString() {
            return imie + " " + nazwisko + " " + wiek + " " + typ;
        }

    }
    static void m5()throws NoSuchElementException{
        String filename = "dane.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.println("PRZED DODANIEM NOWEJ OSOBY UPEWNIJ SIĘ ŻE PLIK WYNIKOWY NIE JEST OTWARTY");
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {

            file.seek(file.length());
            System.out.print("Imię: ");
            String imie = scanner.nextLine();

            System.out.print("Nazwisko: ");
            String nazwisko = scanner.nextLine();

            System.out.print("Wiek: ");
            int wiek = scanner.nextInt();

            System.out.print("Typ (NFZ/Prywatnie): ");
            String typ = scanner.next();

            String dane = String.format("%s %s %d %s\n", imie, nazwisko, wiek, typ);
            file.writeBytes(dane);

            System.out.println("Twoje dane zostały zapisane do pliku.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisywania danych: " + e.getMessage());
        }

    }

    public static void m6() {
        String[] options = { "Kamień", "Papier", "Nożyce" };
        int playAgain = JOptionPane.YES_OPTION;

        while (playAgain == JOptionPane.YES_OPTION) {
            int userChoice = JOptionPane.showOptionDialog(null, "Wybierz swoją opcję:", "Kamień, Papier, Nożyce",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            int computerChoice = (int) (Math.random() * 3);

            String message = "Wybrałeś: " + options[userChoice] + "\nKomputer wybrał: " + options[computerChoice] + "\n\n";

            if (userChoice == computerChoice) {
                message += "Remis!";
            } else if (userChoice == 0 && computerChoice == 2 || userChoice == 1 && computerChoice == 0
                    || userChoice == 2 && computerChoice == 1) {
                message += "Wygrałeś!";
            } else {
                message += "Przegrałeś!";
            }

            playAgain = JOptionPane.showConfirmDialog(null, message + "\nCzy chcesz zagrać jeszcze raz?", "Wynik",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }

}

