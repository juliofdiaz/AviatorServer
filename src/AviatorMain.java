import Aviator.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static Aviator.Airline.getAirline;
import static Aviator.Airport.getAirport;

// The Java class will be hosted at the URI path "/aviator"
@Path("/aviator")
public class AviatorMain {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.APPLICATION_JSON)
    //@Produces("text/plain")
    public String getClichedMessage() throws FileNotFoundException {
        // Return some cliched textual content
        return message();
        //return json.toString(2);
    }

    private static String message() throws FileNotFoundException {

        String AIRLINE_FILE = "/Users/juliofdiaz/Documents/airlines.tsv";
        String AIRPORT_FILE = "/Users/juliofdiaz/Documents/airports.tsv";
        String ROUTE_FILE = "/Users/juliofdiaz/Documents/routes.tsv";

        AirlineList airlines = new AirlineList(AIRLINE_FILE);
        AirportList airports = new AirportList(AIRPORT_FILE);
        RouteList routes = new RouteList(ROUTE_FILE);


        JSONObject routeList = new JSONObject();

        JSONArray array = getRouteArray(routes,airports,airlines);
        routeList.put("routes", array);

        return routeList.toString();
    }

    /**
     *
     * @param routeList
     * @param airportList
     * @param airlineList
     * @return
     */
    private static JSONArray getRouteArray(RouteList routeList,
                                           AirportList airportList,AirlineList airlineList){
        JSONArray jsonArray = new JSONArray();
        for(Route route : routeList.getRoutes()){
            route.setAirline(airlineList);
            route.setAirportOut(airportList);
            route.setAirportIn(airportList);

            Airline airline = route.getCarrier();
            Airport out = route.getOrigin();
            Airport in = route.getDestination();

            Long timeLapsed = route.getTimeLapsed();
            Long duration = route.getDurationInMinutes();
            if(route.isOnAir(out.getTimeZone())){
                JSONObject routeJson = setRouteJson(route,airline,
                        in,out,duration,timeLapsed);
                jsonArray.put(routeJson);
            }
        }
        return jsonArray;
    }

    /**
     * This method retrieves information from a Route object and its respective
     * Airline object and origin and destination Airport objects. This info
     * is then stored in a JSONObject.
     *
     * @param route Route object whose information will be stored in a
     *              JSONObject.
     * @param airline Airline object whose information will be stored
     *                in a JSONObject.
     * @param airportIn Origin Airport object whose information will be
     *                  stored in a JSONObject.
     * @param airportOut Destination Airport object whore information
     *                   will be stored in a JSONObject.
     * @param duration Duration in minutes of the flight.
     * @param timeLapsed Time in minutes lapsed since the plane took off.
     * @return JSONObject with the pertinent information of the route.
     */
    private static JSONObject setRouteJson(Route route,Airline airline,
                                           Airport airportIn,Airport airportOut,
                                           Long duration, Long timeLapsed){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", route.getNumber());
        jsonObject.put("takeOff", route.getTakeOff());
        jsonObject.put("landing", route.getLanding());
        jsonObject.put("duration", duration);
        jsonObject.put("progress", timeLapsed);


        JSONObject carrierJson = setAirlineJson(airline);
        jsonObject.put("carrier", carrierJson);

        JSONObject originJson = setAirportJson(airportOut);
        jsonObject.put("origin",originJson);

        JSONObject destinationJson = setAirportJson(airportIn);
        jsonObject.put("destination",destinationJson);

        return jsonObject;
    }

    /**
     * This method retrieves information from an Airport object and it
     * stores the information in an JSONObject.
     *
     * @param airport Airport object whose information will be saved in
     *                a JSONObject
     * @return A JSONObject holding the information about the Airport.
     */
    private static JSONObject setAirportJson(Airport airport){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", airport.getName());
        jsonObject.put("icao", airport.getIcao());
        jsonObject.put("iata", airport.getIata());
        jsonObject.put("city", airport.getCity());
        jsonObject.put("country", airport.getCountry());
        jsonObject.put("timeZone", airport.getTimeZone());
        return jsonObject;
    }

    /**
     * This method retrieves information from an Airline object and it stores
     * the information in an JSONObject.
     *
     * @param airline Airline object whose information will be saved in
     *                a JSONObject.
     * @return A JSONObject holding the information about the Airline.
     */
    private static JSONObject setAirlineJson(Airline airline){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", airline.getName());
        jsonObject.put("icao", airline.getIcao());
        jsonObject.put("iata", airline.getIata());
        jsonObject.put("country", airline.getCountry());
        return jsonObject;
    }

}