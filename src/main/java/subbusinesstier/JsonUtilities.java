package aplikacja;

import aplikacja.entities.Film;
import aplikacja.entities.Klient;
import aplikacja.entities.Rezerwacja;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonUtilities {

    public static void saveJson(String clientsPath, String moviesPath, String reservationPath, ArrayList<Klient> clientList, ArrayList<Film> movieList, ArrayList<aplikacja.entities.Rezerwacja> reservationList) {
        JSONArray clientsJsonList = new JSONArray();
        clientList.forEach(klient -> saveClientToJson(klient, clientsJsonList));

        try (FileWriter file = new FileWriter("klienci.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(clientsJsonList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray moviesJsonList = new JSONArray();
        movieList.forEach(movie -> saveMovieToJson(movie, moviesJsonList));

        try (FileWriter file = new FileWriter("filmy.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(moviesJsonList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray reservationJsonList = new JSONArray();
        reservationList.forEach(reservation -> saveReservationToJson(reservation, reservationJsonList));

        try (FileWriter file = new FileWriter("rezerwacje.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(reservationJsonList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveReservationToJson(aplikacja.entities.Rezerwacja reservation, JSONArray reservationJsonList) {
        JSONObject reservationDetails = new JSONObject();
        reservationDetails.put("title", (String) reservation.getTitle());
        reservationDetails.put("login", (String) reservation.getLogin());


        JSONObject klientObject = new JSONObject();
        klientObject.put("rezerwacja", reservationDetails);


        reservationJsonList.add(klientObject);
    }

    private static void saveClientToJson(Klient klient, JSONArray klienciJsonList) {
        JSONObject klientDetails = new JSONObject();
        klientDetails.put("imie", (String) klient.getImie());
        klientDetails.put("nazwisko", (String) klient.getNazwisko());
        klientDetails.put("login", (String) klient.getLogin());
        klientDetails.put("haslo", (String) klient.getHaslo());
        klientDetails.put("wiek", klient.getWiek());
        klientDetails.put("state", klient.getState());

        JSONObject klientObject = new JSONObject();
        klientObject.put("klient1", klientDetails);


        klienciJsonList.add(klientObject);
    }

    private static void saveMovieToJson(Film film, JSONArray moviesJsonList) {
        JSONObject movieDetails = new JSONObject();
        movieDetails.put("tytul", (String) film.getTytul());
        movieDetails.put("gatunek", (String) film.getGatunek());
        movieDetails.put("imie", (String) film.getImie());
        movieDetails.put("nazwisko", (String) film.getNazwisko());
        movieDetails.put("rok produkcji", film.getRokProdukcji());
        movieDetails.put("wytwornia", (String) film.getWytwornia());
        movieDetails.put("ilosc egzemplarzy", film.getIloscEgzemplarzy());

        JSONObject movieObject = new JSONObject();
        movieObject.put("film", movieDetails);


        moviesJsonList.add(movieObject);
    }


    public void readJson(String clientsPath, String moviesPath,String reservationPath, ArrayList<Klient> clientList,ArrayList<Film> movieList,ArrayList<Rezerwacja> reservationList) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(clientsPath)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray klienciJsonList = (JSONArray) obj;


            //Iterate over employee array
            klienciJsonList.forEach(emp -> parseClientObject((JSONObject) emp,clientList));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //JSON parser object to parse read file
        jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(moviesPath)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray moviesJsonList = (JSONArray) obj;


            //Iterate over employee array
            moviesJsonList.forEach(emp -> parseMovieObject((JSONObject) emp,movieList));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //JSON parser object to parse read file
        jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(reservationPath)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray reservationJsonList = (JSONArray) obj;


            //Iterate over employee array
            reservationJsonList.forEach(emp -> parseReservationObject((JSONObject) emp,reservationList));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseReservationObject(JSONObject reservation,ArrayList<Rezerwacja> reservationList) {
        JSONObject reservationObject = (JSONObject) reservation.get("rezerwacja");

        String title = (String) reservationObject.get("tytul");

        String login = (String) reservationObject.get("login");

        aplikacja.entities.Rezerwacja rezerwacja = new Rezerwacja(title, login);
        reservationList.add(rezerwacja);
        System.out.println("Dodano rezerwacje");
    }

    private void parseMovieObject(JSONObject movie,ArrayList<Film>movieList) {

        JSONObject movieObject = (JSONObject) movie.get("film");

        String title = (String) movieObject.get("tytul");

        String genre = (String) movieObject.get("gatunek");

        String firstName = (String) movieObject.get("imie");

        String lastName = (String) movieObject.get("nazwisko");

        long year = (long) movieObject.get("rok produkcji");

        String works = (String) movieObject.get("wytwornia");

        long howMuch = (long) movieObject.get("ilosc egzemplarzy");

        Film film = new Film(title, genre, firstName, lastName, year, works, howMuch);
        movieList.add(film);
        System.out.println("Dodano film");
    }

    private void parseClientObject(JSONObject klienci,ArrayList<Klient> listaKlientow) {
        //Get employee object within list
        JSONObject klientObject = (JSONObject) klienci.get("klient1");

        String imie = (String) klientObject.get("imie");

        String nazwisko = (String) klientObject.get("nazwisko");

        String login = (String) klientObject.get("login");

        String haslo = (String) klientObject.get("haslo");

        long wiek = (long) klientObject.get("wiek");

        Boolean state = (Boolean) klientObject.get("state");

        Klient klient = new Klient(login, haslo, imie, nazwisko, wiek, state);
        listaKlientow.add(klient);
        System.out.println("Dodano klienta");
    }
}