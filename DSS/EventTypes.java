/**
 * The DSS package contains classes and interfaces related to Discrete Stochastic Simulation (DSS).
 * DSS is a simulation method that is used to analyze the behavior of a system over time.
 *
 */
package DSS;

/**
 * Class that represents the types of events that can happen
 */
abstract class EventTypes implements Comparable<EventTypes> {

    /**
     * Time of the event
     */
    double timestamp;

    /**
     * @param time time of the event
     */
    EventTypes(double time) {
        this.timestamp = time;
    }

    /**
     * Execute the event
     */
    abstract void execute();

    /**
     * @return the time of the event
     */
    double getTime() {

        return this.timestamp;

    }

    /**
     * @param newTime new time of the event
     */
    abstract void setTime(double newTime);

    int eventTypeIncrase(int eventNumber) {
        return eventNumber + 1;
    }

    /**
     * Compare the timestamp of the object with the timestamp of the object to be
     * compared
     * 
     * @param aux the object to be compared.
     * @return 1 if the timestamp of the object is bigger than the timestamp of the
     *         object to be compared, -1 otherwise
     */
    @Override
    public int compareTo(EventTypes aux) {
        return this.timestamp > aux.timestamp ? 1 : -1;
    }

}