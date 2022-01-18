package subbusinesstier;

import aplikacja.JsonUtilities;
import aplikacja.entities.Klient;
import aplikacja.entities.Rezerwacja;
import aplikacja.entities.Film;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Fasada {
    public ArrayList<Klient> listaKlientow = new ArrayList<Klient>();
    public ArrayList<Film> movieList = new ArrayList<Film>();
    public ArrayList<Rezerwacja> reservationList = new ArrayList<Rezerwacja>();
    public Klient aktualnyKlient;
    JsonUtilities jsonUtilities= new JsonUtilities();
    public Fasada() {
    }

    public static void main(String[] args) throws IOException {
        Fasada fasada = new Fasada();
        String moviesPath = "filmy.json";
        String clientsPath = "klienci.json";
        String reservationPath = "rezerwacje.json";
        fasada.jsonUtilities.readJson(clientsPath, moviesPath, reservationPath, fasada.listaKlientow, fasada.movieList,fasada.reservationList);
        boolean endLoop = false;
        while (true) {
            Scanner scan = new Scanner(System.in);
            int menuChoice = 3;
            System.out.println("\n1. Logowanie\n");
            System.out.println("2. Wyslij prosbe o zalozenie konta\n");
            System.out.println("3. Zamknij program\n");

            int menu1Choice = scan.nextInt();
            if(menu1Choice == 1)
            {
                scan = new Scanner(System.in);
                System.out.println("\n1. Wpisz login\n");
                String login = scan.nextLine();
                System.out.println("2. Wpisz haslo\n");
                String password = scan.nextLine();
                if(login.equals("admin") && password.equals("admin"))
                {
                    menuChoice = 2;
                }
                else {
                    boolean isFound=false;

                    for (int i = 0; i < fasada.listaKlientow.size(); i++) {
                        if ((fasada.listaKlientow.get(i).getLogin().equals(login) && fasada.listaKlientow.get(i).getHaslo().equals(password)) && fasada.listaKlientow.get(i).getState()){
                            menuChoice = 1;
                            fasada.aktualnyKlient = fasada.listaKlientow.get(i);
                            isFound = true;
                        }
                    }

                    if(!isFound)
                    {
                        System.out.println("Niepoprawne dane logowania");
                        menuChoice = 3;
                    }
                }
            }
            else if(menu1Choice == 2){
                fasada.sendRequestForNewAccount();
                menuChoice = 3;
            }
            else if(menu1Choice == 3){
                exit(0);
            }
            else
            {
                menuChoice = 3;
            }

            switch (menuChoice) {
                case 1:
                    do {
                        System.out.println("\nWypozyczalnia filmow\n");
                        System.out.println("1. Pokaz filmy\n");
                        System.out.println("2. Wypozycz film\n");
                        System.out.println("3. Zwroc film\n");
                        System.out.println("4. Zamknij\n");
                        System.out.println("5. Wyloguj\n");
                        scan = new Scanner(System.in);
                        menuChoice = scan.nextInt();
                        switch (menuChoice) {
                            case 1:
                                fasada.printMovies(fasada.movieList);
                                break;
                            case 2:
                                fasada.aktualnyKlient.rentMovie(fasada.movieList, fasada.reservationList);
                                break;
                            case 3:
                                fasada.aktualnyKlient.returnMovie(fasada.movieList);
                                break;
                            case 4:
                                JsonUtilities.saveJson(clientsPath, moviesPath, reservationPath, fasada.listaKlientow, fasada.movieList, fasada.reservationList);
                                exit(0);
                                break;
                            case 5:
                                JsonUtilities.saveJson(clientsPath, moviesPath, reservationPath, fasada.listaKlientow, fasada.movieList, fasada.reservationList);
                                endLoop = true;
                                break;
                        }
                    }while (!endLoop);
                    break;
                case 2:
                    do {
                        System.out.println("\nWypozyczalnia filmow\n");
                        System.out.println("1. Wyswietl klientow\n");
                        System.out.println("2. Weryfikacja danych\n");
                        System.out.println("3. Edycja danych klienta\n");
                        System.out.println("4. Pokaz filmy\n");
                        System.out.println("5. Dodaj film\n");
                        System.out.println("6. Edytuj film\n");
                        System.out.println("7. Usun film\n");
                        System.out.println("8. Zapisz do pliku\n");
                        System.out.println("9. Zamknij\n");
                        System.out.println("10. Wyloguj\n");
                        scan = new Scanner(System.in);
                        menuChoice = scan.nextInt();
                        switch (menuChoice) {
                            case 1:
                                fasada.showKlienci(fasada.listaKlientow);
                                break;
                            case 2:
                                fasada.verifyClients(fasada.listaKlientow);
                                break;
                            case 3:
                                fasada.editClients(fasada.listaKlientow);
                                break;
                            case 4:
                                fasada.printMovies(fasada.movieList);
                                break;
                            case 5:
                                fasada.addMovie(fasada.movieList);
                                break;
                            case 6:
                                fasada.editMovie(fasada.movieList);
                                break;
                            case 7:
                                fasada.removeMovie(fasada.movieList);
                                break;
                            case 8:
                                JsonUtilities.saveJson(clientsPath, moviesPath, reservationPath, fasada.listaKlientow, fasada.movieList, fasada.reservationList);
                                break;
                            case 9:
                                JsonUtilities.saveJson(clientsPath, moviesPath, reservationPath, fasada.listaKlientow, fasada.movieList, fasada.reservationList);
                                exit(0);
                                break;
                            case 10:
                                JsonUtilities.saveJson(clientsPath, moviesPath, reservationPath, fasada.listaKlientow, fasada.movieList, fasada.reservationList);
                                endLoop = true;
                                break;
                        }
                    }while (!endLoop);
                    break;
                case 3:
                    break;
            }

        }
    }



    private void showKlienci(ArrayList<Klient> listaKlientow) {
        for (int i = 0; i < listaKlientow.size(); i++) {
            Klient klient = listaKlientow.get(i);
            printKlientDetails(klient);
        }
    }

    private void printKlientDetails(Klient klient) {
        System.out.print(klient.getImie() + " ");
        System.out.print(klient.getNazwisko() + " ");
        System.out.print(klient.getLogin() + " ");
        System.out.print(klient.getHaslo() + " ");
        System.out.print(klient.getWiek() + " ");
        System.out.print(klient.getState() + "\n");
    }

    private void printMovies(ArrayList<Film> movieList) {
        System.out.println(movieList.size());
        for (int i = 0; i < movieList.size(); i++) {
            Film film = movieList.get(i);
            printMovieDetails(film);

        }
    }

    private void printMovieDetails(Film film) {
        System.out.print(film.getTytul() + " ");
        System.out.print(film.getGatunek() + " ");
        System.out.print(film.getImie() + " ");
        System.out.print(film.getNazwisko() + " ");
        System.out.print(film.getRokProdukcji() + " ");
        System.out.print(film.getWytwornia() + "\n");
    }



    private void sendRequestForNewAccount() {
        Scanner scan = new Scanner(System.in);
        String nazwisko = scan.nextLine();
        String imie = scan.nextLine();
        String login = scan.nextLine();
        String haslo = scan.nextLine();
        long wiek = scan.nextInt();
        Klient klient = new Klient(login, haslo, imie, nazwisko, wiek, false);
        listaKlientow.add(klient);
        System.out.println("Wysylano prosbe o zalozenie konta.");
    }

    private void verifyClients(ArrayList<Klient> listaKlientow) throws IOException {
        char endVerify = 'T';
        char isVerified;
        Scanner scan = new Scanner(System.in);
        int i = 0;
        do {
            if (!listaKlientow.get(i).getState()) {
                printKlientDetails(listaKlientow.get(i));
                System.out.println("Czy potwierdzasz weryfikacje? (T/N)");
                isVerified = scan.next().charAt(0);
                if (isVerified == 'T' || isVerified == 't') {
                    listaKlientow.get(i).setState(true);
                } else {
                    listaKlientow.remove(i);
                }
                System.out.println("Kontynuowac? (T/N)");
                endVerify = scan.next().charAt(0);
            } else {
                endVerify = 'T';
            }
            i++;

        } while ((endVerify == 'T' || endVerify == 't') && i < listaKlientow.size() - 1);
        System.out.println("Wszyscy klienci zostali zweryfikowani");
        char ch = (char) System.in.read();
    }

    private void editClients(ArrayList<Klient> listaKlientow) {
        String login;
        char endEdit = 'T';
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("Podaj login klienta, ktorego dane chcesz edytować: ");
            login = scan.nextLine();

            boolean isFound = false;
            for (int i = 0; i < listaKlientow.size(); i++) {
                if (listaKlientow.get(i).getLogin().equals(login)) {
                    Scanner scanEdit = new Scanner(System.in);
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Co chcesz edytowac: ");

                    System.out.println("1. Nazwisko");
                    System.out.println("2. Imie");
                    System.out.println("3. Login");
                    System.out.println("4. Haslo");
                    System.out.println("5. Wiek");
                    int whatEdit = scanEdit.nextInt();

                    switch (whatEdit) {
                        case 1:
                            System.out.println("Podaj nowe nazwisko: ");
                            String nazwisko = scanner.nextLine();
                            listaKlientow.get(i).setNazwisko(nazwisko);
                            break;
                        case 2:
                            System.out.println("Podaj nowe imie: ");
                            String imie = scanner.nextLine();
                            listaKlientow.get(i).setImie(imie);
                            break;
                        case 3:
                            System.out.println("Podaj nowy login: ");
                            String newLogin = scanner.nextLine();
                            listaKlientow.get(i).setLogin(newLogin);
                            break;
                        case 4:
                            System.out.println("Podaj nowe haslo: ");
                            String haslo = scanner.nextLine();
                            listaKlientow.get(i).setHaslo(haslo);
                            break;
                        case 5:
                            System.out.println("Podaj nowy wiek: ");
                            long wiek = scanner.nextLong();
                            listaKlientow.get(i).setWiek(wiek);
                            break;
                    }
                    isFound = true;
                    break;
                }
            }

            if (!isFound)
                System.out.println("Nie znaleziono klienta o takim loginie.");

            System.out.println("Kontynuowac? (T/N)");
            endEdit = scan.next().charAt(0);

        } while (endEdit == 'T' || endEdit == 't');
    }

    private void editMovie(ArrayList<Film> movieList) {
        String title;
        char endEdit = 'T';
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("Podaj tytul filmu, ktory chcesz edytować: ");
            title = scan.nextLine();

            boolean isFound = false;
            for (int i = 0; i < movieList.size(); i++) {
                System.out.println(movieList.get(i).getTytul() + " " + title);
                if (movieList.get(i).getTytul().equals(title)) {
                    Scanner scanEdit = new Scanner(System.in);
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Co chcesz edytowac: ");

                    System.out.println("1. Tytul");
                    System.out.println("2. Gatunek");
                    System.out.println("3. Imie rezysera");
                    System.out.println("4. Nazwisko rezysera");
                    System.out.println("5. Rok produkcji");
                    System.out.println("6. Nazwe wytworni");
                    int whatEdit = scanEdit.nextInt();

                    switch (whatEdit) {
                        case 1:
                            System.out.println("Podaj nowy tytul: ");
                            String newTitle = scanner.nextLine();
                            movieList.get(i).setTytul(newTitle);
                            break;
                        case 2:
                            System.out.println("Podaj nowy gatunek: ");
                            String genre = scanner.nextLine();
                            movieList.get(i).setGatunek(genre);
                            break;
                        case 3:
                            System.out.println("Podaj nowe imie rezysera: ");
                            String name = scanner.nextLine();
                            movieList.get(i).setImie(name);
                            break;
                        case 4:
                            System.out.println("Podaj nowe nazwisko rezysera: ");
                            String surname = scanner.nextLine();
                            movieList.get(i).setNazwisko(surname);
                            break;
                        case 5:
                            System.out.println("Podaj nowy rok produkcji: ");
                            long year = scanner.nextLong();
                            movieList.get(i).setRokProdukcji(year);
                            break;
                        case 6:
                            System.out.println("Podaj nowa wytwornie: ");
                            String works = scanner.nextLine();
                            movieList.get(i).setWytwornia(works);
                            break;
                    }
                    isFound = true;
                    break;
                }
            }

            if (!isFound)
                System.out.println("Nie znaleziono filmu o takim tytule.");

            System.out.println("Kontynuowac? (T/N)");
            endEdit = scan.next().charAt(0);

        } while (endEdit == 'T' || endEdit == 't');
    }

    private void addMovie(ArrayList<Film> movieList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj tytul: ");
        String title = scanner.nextLine();
        System.out.println("Podaj gatunek: ");
        String genre = scanner.nextLine();
        System.out.println("Podaj imie rezysera: ");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko rezysera: ");
        String lastName = scanner.nextLine();
        System.out.println("Podaj wytwornie: ");
        String works = scanner.nextLine();
        System.out.println("Podaj rok produkcji: ");
        long year = scanner.nextLong();
        System.out.println("Podaj ilosc egzemplarzy: ");
        long howMuch = scanner.nextLong();

        Film film = new Film(title, genre, firstName, lastName, year, works, howMuch);
        movieList.add(film);
        System.out.println("Dodano film");
    }

    private void removeMovie(ArrayList<Film> movieList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj tytul filmu, ktory chcesz usunac: ");
        String title = scanner.nextLine();

        boolean isFound = false;
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getTytul().equals(title)) {
                movieList.remove(i);
                isFound = true;
            }
        }

        if (!isFound)
            System.out.println("Nie ma takiego filmu.");
    }

}