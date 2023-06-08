package DSS;

/**
 * Class that represents the event of show updates about the simulation
 */
public class ObservationEvent extends EventTypes {

    EventForObserver Evento;
    double maxTime;

    /**
     * @param time  time of the event
     * @param evento ????
     */
    ObservationEvent(double time, EventForObserver evento) {
        super(time);
        this.Evento = evento;
        this.maxTime = time*20;
    }

    /**
     * Set the time of the next event
     * @param newTime new time of the event
     */
    public void setTime(double newTime) {

        this.timestamp = newTime;
    }

    /**
     * Execute the event
     */
    @Override
    public void execute() {

        Evento.print();
        this.setTime(timestamp+maxTime/20);

    }

}
