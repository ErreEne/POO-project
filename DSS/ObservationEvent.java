package DSS;

public class ObservationEvent extends EventTypes {

    EventForObserver Evento;
    double maxTime;

    ObservationEvent(double time, EventForObserver evento) {
        super(time);
        this.Evento = evento;
        this.maxTime = time*20;
    }

    public void setTime(double newTime) {

        this.timestamp = newTime;

    }

    @Override
    public void execute() {

        Evento.print();
        this.setTime(timestamp+maxTime/20);

    }

}
