package LeitorDeArgs;

import GrafoPack.GrafoInterface;
import java.util.ArrayList;

public interface stratsInterface {

    public void readArgs(String[] args);

    GrafoInterface getGrafo();

    ArrayList<Number> getConstants();
}