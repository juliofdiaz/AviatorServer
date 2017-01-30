package Aviator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * This class holds information about a commercial route including the
 * route number, the name of the carrier, the city where the flight
 * originates, the destination city of the flight, the time of take off,
 * the time of landing, the duration of the flight, the day offset of
 * arrival, and the days where the flight operate.
 *
 *
 * @author juliofdiaz
 * @version 0.1b
 *
 */
public class Route {
    private Integer number;
    private String carrier;
    private String origin;
    private String destination;
    private LocalTime takeOff;
    private LocalTime landing;
    private LocalTime duration;
    private Integer arrivalOffset;
    private ArrayList<DayOfWeek> days;

    public Route(String line){
        String[] info = line.split(",");
        this.setNumber(Integer.parseInt(info[0]));
        this.setCarrier( info[1] );
        this.setOrigin( info[2] );
        this.setDestination( info[3] );
        this.setTakeOff( getLocalTime(info[4]));
        this.setLanding( getLocalTime(info[5]) );
        this.setDuration( getLocalTime(info[6]));
        this.setArrivalOffset( Integer.parseInt(info[7]) );
        this.setDays( getDaysOfWeek(info[8]) );
    }

    /**
     * This method tests if the current route is on the air according to when
     * the flight took off and the duration of the route.
     *
     * @param outTimeZone A String containing the time zone of the departure
     *                    city. The String has to meet java time zone
     *                    standards.
     * @return True if the flight is on the air.
     *
     */
    public Boolean isOnAir( String outTimeZone ){
        ZoneId outZone = ZoneId.of(outTimeZone);

        Long timeLapsed;
        if(this.arrivalOffset==0){
            timeLapsed = ChronoUnit.MINUTES.between(this.takeOff,LocalTime.now(outZone));
        }else{
            timeLapsed = ChronoUnit.MINUTES.between(this.takeOff,LocalTime.now(outZone))+1440;
        }
        Long duration = ChronoUnit.MINUTES.between(LocalTime.of(0,0),this.duration);

        if( this.days.contains(LocalDate.now(outZone).getDayOfWeek()) && this.arrivalOffset==0){
            if( LocalTime.now(outZone).isAfter(this.takeOff) ) {
                if(timeLapsed<duration){
                    return true;
                }
            }
        }else if( this.days.contains(LocalDate.now(outZone).getDayOfWeek().minus(1)) && this.arrivalOffset==1){
            Long timeLapsed_2 = ChronoUnit.MINUTES.between(this.takeOff,LocalTime.now(outZone))+1440;
            if(timeLapsed_2<duration){
                return true;
            }
        }
        return false;
    }

    /**
     * This method takes a String representing days of the week and returns a
     * list of DaysOfWeek.
     *
     * @param days A String including numbers from 1 to 7 where each number
     *             corresponds to a day of the week (Monday=1, Tuesday=2,
     *             Wednesday=3, Thursday=4, Friday=5, Saturday=6 and Sunday=7).
     * @return A List of containing the DayOfWeek items represented in days.
     *
     */
    private static ArrayList<DayOfWeek> getDaysOfWeek(String days){
        ArrayList<DayOfWeek> result = new ArrayList<>();
        for(int i=0; i<days.length(); i++){
            result.add(DayOfWeek.of(Character.getNumericValue(days.charAt(i))));
        }
        return result;
    }

    /**
     * This method evaluates a String representing a time including hour and minutes
     * and it returns a LocalTime object with that information.
     *
     * @param simpleTime A String made up of 4 integers where the first two
     *                   represent hours and the last two represent minutes. When
     *                   representing hour of day, the 24h system should be used.
     * @return A LocalTime time object set to the time specified in a String.
     */
    private static LocalTime getLocalTime(String simpleTime){
        Integer hour = Integer.parseInt(simpleTime.substring(0,2));
        Integer minute = Integer.parseInt(simpleTime.substring(2,4));
        return LocalTime.of(hour,minute);
    }

    /**
     * This method retrieves the value of the carrier class variable.
     *
     * @return The value of the carrier class variable.
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * This method sets the carrier class variable.
     *
     * @param carrier The new value of the carrier class variable.
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    /**
     * This method retrieves the value of the origin class variable.
     *
     * @return The value of the origin class variable.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * This method sets the origin class variable.
     *
     * @param origin The new value of the origin class variable.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * This method retrieves the value of the destination class variable.
     *
     * @return The value of the destination class variable.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * This method sets the destination class variable.
     *
     * @param destination The new value of the destination class variable.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * This method retrieves the value of the takeOff class variable.
     *
     * @return The value of the takeOff class variable.
     */
    public LocalTime getTakeOff() {
        return takeOff;
    }

    /**
     * This method sets the takeOff class variable.
     *
     * @param takeOff The new value of the takeOff class variable.
     */
    public void setTakeOff(LocalTime takeOff) {
        this.takeOff = takeOff;
    }

    /**
     * This method retrieves the value of the landing class variable.
     *
     * @return The value of the landing class variable.
     */
    public LocalTime getLanding() {
        return landing;
    }

    /**
     * This method sets the landing class variable.
     *
     * @param landing The new value of the landing class variable.
     */
    public void setLanding(LocalTime landing) {
        this.landing = landing;
    }

    /**
     * This method retrieves the value of the days class variable.
     *
     * @return The value of the days class variable.
     */
    public ArrayList<DayOfWeek> getDays() {
        return days;
    }

    /**
     * This method sets the days class variable.
     *
     * @param days The new value of the days class variable.
     */
    public void setDays(ArrayList<DayOfWeek> days) {
        this.days = days;
    }

    /**
     * This method retrieves the value of the number class variable.
     *
     * @return\ The value of the number class variable.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * This method sets the number class variable.
     *
     * @param number The new value of the number class variable.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * This method retrieves the value of the arrivalOffset class
     * variable.
     *
     * @return The value of the arrivalOffset class variable.
     */
    public Integer getArrivalOffset() {
        return arrivalOffset;
    }

    /**
     * This method sets the arrivalOffset class variable.
     *
     * @param arrivalOffset The new value of the arrivalOffset class
     *                      variable.
     */
    public void setArrivalOffset(Integer arrivalOffset) {
        this.arrivalOffset = arrivalOffset;
    }

    /**
     * This method sets the duration class variable.
     *
     * @param duration The new value of the duration class variable.
     */
    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    /**
     * This method retrieves the value of the duration class variable.
     *
     * @return The value of the duration class variable.
     */
    public LocalTime getDuration() {
        return duration;
    }


    /**
     *
     * @return
     */
    public Long getDurationInMinutes(){
        return ChronoUnit.MINUTES.between(LocalTime.of(0,0),
                this.getDuration());
    }

    public Long getTimeLapsed(){
        return null;
    }
}
