package Aviator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds a list of all the Routes in the database.
 *
 * @author juliofdiaz
 * @version 0.1b
 *
 */
public class RouteList {
    private ArrayList<Route> routes;

    public RouteList(String fileName) throws FileNotFoundException {
        this();
        Scanner in = new Scanner(new File(fileName));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.charAt(0)!='#') {
                this.routes.add(new Route(line));
            }
        }
    }

    private RouteList(){
        this.routes = new ArrayList<>();
    }

    /**
     * This method retrieves the routes in this instance.
     *
     * @return The list of routes.
     */
    public ArrayList<Route> getRoutes(){
        return this.routes;
    }
}
