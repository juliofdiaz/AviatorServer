package Aviator;

import java.io.FileNotFoundException;
import java.time.LocalTime;

/**
 * This main interface.
 *
 * @author juliofdiaz
 * @version 0.1b
 *
 */
public class AviatorTester {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Aviation 0.1");

        String AIRLINE_FILE = "/Users/juliofdiaz/Documents/airlines.tsv";
        String AIRPORT_FILE = "/Users/juliofdiaz/Documents/airports.tsv";
        String ROUTE_FILE = "/Users/juliofdiaz/Documents/routes.tsv";

        AirlineList airlines = new AirlineList(AIRLINE_FILE);
        AirportList airports = new AirportList(AIRPORT_FILE);
        RouteList routes = new RouteList(ROUTE_FILE);


        for(Route route : routes.getRoutes()){
            route.setAirline(airlines);
            route.setAirportOut(airports);
            route.setAirportIn(airports);

            Airport out = route.getOrigin();
            if(route.isOnAir(out.getTimeZone())){
                System.out.println( routeString(route) );
            }


        }

    }

    /**
     *
     * 
     * @param route The Route to be displayed.
     * @return A String representing information about the Route.
     */
    private static String routeString(Route route){
        return route.getCarrier().getName()+"\n"+
                route.getCarrier().getIata()+" "+route.getNumber() +
                "\t"+route.getOrigin().getCity()+"("+route.getOrigin().getIata() +
                ") - "+route.getDestination().getCity()+"("+route.getDestination().getIata()+
                ")"+"\n"+route.getTakeOff()+" "+
                graphProgress(route.getTimeLapsed(),route.getDurationInMinutes())+
                " "+route.getLanding();
    }

    /**
     * This method represents the progress of the flight since it took-off
     * in a simple String.
     *
     * @param lapsed The time lapsed since the flight left.
     * @param duration The duration of the flight.
     * @return A String representing the progress of the flight.
     */
    private static String graphProgress(Long lapsed, long duration){
        Double progressDouble = ((lapsed+0.0)/(duration+0.0)*20)+0.5;
        Integer progressInteger = progressDouble.intValue();

        String result = "";
        for(int i=0;i<20;i++){
            if(i==progressInteger-1) {
                result = result + "*";
            }else{
                result = result + "-";
            }
        }
        return result;
    }

}
