package Aviator;

/**
 * This class holds information about an airport including name, icao
 * code, iata code, and the country affiliation.
 *
 * @author juliofdiaz
 * @version 0.1b
 *
 */
public class Airline {
    private String name;
    private String icao;
    private String iata;
    private String country;

    public Airline(String line){
        String[] info = line.split(",");
        this.setName(info[0]);
        this.setIcao(info[1]);
        this.setIata(info[2]);
        this.setCountry(info[3]);
    }

    public Airline(String iataString, Boolean isOnlyIata){
        this();
        if(isOnlyIata) {
            this.iata = iataString;
        }
    }

    public Airline(){
        this.name="N/A";
        this.icao="N/A";
        this.iata="N/A";
        this.country="N/A";
    }

    /**
     * This method retrieves the value of the name class variable.
     *
     * @return The value of the name class variable.
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name class variable.
     *
     * @param name The new value od the name class variable.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method retrieves the value of the icao class variable.
     *
     * @return The value of the icao class variable.
     */
    public String getIcao() {
        return icao;
    }

    /**
     * This method sets the icao class variable.
     *
     * @param icao The new value of the icao class variable.
     */
    public void setIcao(String icao) {
        this.icao = icao;
    }

    /**
     * This method retrieves the value of the iata class variable.
     *
     * @return The value of the iata class variable.
     */
    public String getIata() {
        return iata;
    }

    /**
     * This method sets the iata class variable.
     *
     * @param iata The new value of the iata class variable.
     */
    public void setIata(String iata) {
        this.iata = iata;
    }

    /**
     * This method retrieves the value of the country class variable.
     *
     * @return The value of the country class variable.
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method sets the country class variable.
     *
     * @param country The new value of the country class variable.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This method overrides the default toString() method and it
     * prints all the information known about the Airline.
     *
     * @return A String with the Airline information.
     */
    public String toString(){
        return "[name:"+this.name+", icao:"+this.icao+
                ", iata:"+this.iata+", country:"+this.country+
                "]";
    }

    /**
     * This method retrieves information about an airline based on its iata
     * code and it returns an Airline object with that information.
     *
     * @param carrier The iata code of the airline.
     * @param airlines The list of all airlines in the database.
     * @return An Airline object corresponding to the iata code.
     */
    public static Airline getAirline(String carrier, AirlineList airlines){
        if(airlines.contains(carrier)){
            return airlines.get(carrier);
        }else {
            //some error
            return new Airline();
        }
    }

}
