package Aviator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds the list of all the Airports in the database.
 *
 * @author juliofdiaz
 * @version 0.1b
 *
 */
public class AirportList {
    private ArrayList<Airport> airports;

    public AirportList(String fileName) throws FileNotFoundException {
        this();
        Scanner in = new Scanner(new File(fileName));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.charAt(0) != '#') {
                this.airports.add(new Airport(line));
            }
        }
    }

    private AirportList(){
        this.airports = new ArrayList<>();
    }

    /**
     * This method checks is the list contains an Airport with the iata
     * code input.
     * @param airportIata The iata code of the airport the user wants to
     *                    check.
     * @return True only if the list contains an Airport with the iata code
     * input by the user.
     */
    public Boolean contains(String airportIata){
        for(Airport airport : this.airports){
            if(airport.getIata().equals(airportIata)){
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns an Airport given a String representing the
     * airports's iata code.
     * @param airportIata The iata code of the airport the user wants to
     *                    get.
     * @return An Airport object with the input iata code or null if not found.
     */
    public Airport get(String airportIata){
        for(Airport airport : this.airports){
            if(airport.getIata().equals(airportIata)){
                return airport;
            }
        }
        return null;
    }

}
