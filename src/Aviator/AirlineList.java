package Aviator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds the list of all the Airlines in the database.
 *
 * @author juliofdiaz
 * @version 0.1b
 *
 */
public class AirlineList {
    private ArrayList<Airline> airlines;

    public AirlineList(String fileName) throws FileNotFoundException {
        this();
        Scanner in = new Scanner(new File(fileName));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.charAt(0) != '#') {
                this.airlines.add(new Airline(line));
            }
        }
    }

    private AirlineList(){
        this.airlines = new ArrayList<>();
    }

    /**
     * This method checks is the list contains an Airline with the iata
     * code input.
     * @param airlineIata The iata code of the airline the user wants to
     *                    check.
     * @return True only if the list contains an Airline with the iata code
     * input by the user.
     */
    public Boolean contains(String airlineIata){
        for(Airline airline : this.airlines){
            if(airline.getIata().equals(airlineIata)){
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns an Airline given an String representing the
     * airline's iata code.
     * @param airlineIata The iata code of the airline the user wants to
     *                    get.
     * @return An Airline object with the input iata code or null if not found.
     */
    public Airline get(String airlineIata){
        for(Airline airline: this.airlines){
            if(airline.getIata().equals(airlineIata)){
                return airline;
            }
        }
        return null;
    }

}
