package Aviator;

/**
 * This class holds information about an airport including the name,
 * the icao code, the iata code, the city where the airport is located,
 * the country where the airport is located and the time zone associated
 * with the location of the airport.
 *
 * @author juliofdiaz
 * @version 0.1b
 *
 */
public class Airport {
    private String name;
    private String icao;
    private String iata;
    private String city;
    private String country;
    private String timeZone;

    public Airport(String line){
        String[] info = line.split(",");
        this.setName(info[0]);
        this.setIcao(info[1]);
        this.setIata(info[2]);
        this.setCity(info[3]);
        this.setCountry(info[4]);
        this.setTimeZone(info[5]);
    }

    public Airport(){
        this.name = "N/A";
        this.icao = "N/A";
        this.iata = "N/A";
        this.city = "N/A";
        this.country = "N/A";
        this.timeZone = "America/Lima";
    }

    /**
     * This method retrieved the value of the name class variable.
     *
     * @return The value of the name class variable.
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name class variable.
     *
     * @param name The new value of the name class variable.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method retrieves the value of the icao class variable
     *
     * @return The value of the icao class variable.
     */
    public String getIcao() {
        return icao;
    }

    /**
     * This method sets the iata class variable.
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
     * This method retrieves the value of the city class variable.
     *
     * @return The value of the city class variable.
     */
    public String getCity() {
        return city;
    }

    /**
     * This method sets the city class variable.
     *
     * @param city The new value of the city class variable
     */
    public void setCity(String city) {
        this.city = city;
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
     * This method retrieves the value of the timeZone class variable.
     *
     * @return The value of the timeZone class variable.
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * This method sets the timeZone class variable.
     *
     * @param timeZone The new value of the timeZone class variable.
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * This method overrides the default toString() method and it prints
     * all the information known about the instance of the Airline.
     *
     * @return A String with the information about the Airport.
     */
    public String toString(){
        return "[name:"+this.name+", icao:"+this.icao+
                ", iata:"+this.iata+", city"+this.city+
                ", country:"+this.country+ "]";
    }

    /**
     * This method retrieves information about an airport based on its iata
     * code and it returns an Airport object with that information.
     *
     * @param location The iata code of the airport.
     * @param airports The list of all airport in the database.
     * @return An Aiport object corresponding to the iata code.
     */
    public static Airport getAirport(String location, AirportList airports){
        if(airports.contains(location)){
            return airports.get(location);
        }else {
            //some error
            return new Airport();
        }
    }
}
