package DSS;

public class NextEvent<QueueObject> implements Comparable<NextEvent<QueueObject>> {

    QueueObject GeralObject;
    double TimeStampEvento;

    NextEvent(QueueObject o) {

        this.GeralObject = o;
        this.TimeStampEvento = 0;

    }

    public void SetTimeStamp(int newTime) {

        this.TimeStampEvento = newTime;

    }

    @Override
    public int compareTo(NextEvent<QueueObject> aux) {
        return this.TimeStampEvento > aux.TimeStampEvento ? 1 : -1;
    }

}