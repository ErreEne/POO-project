package DSS;

public abstract class EventTypes implements Comparable<EventTypes> {

    double timestamp;

    EventTypes(double time) {
        this.timestamp = time;
    }

    public abstract void execute();

    public double getTime() {

        return this.timestamp;

    }

    public abstract void setTime(double newTime);

    @Override
    public int compareTo(EventTypes aux) {
        return this.timestamp > aux.timestamp ? 1 : -1;
    }

}