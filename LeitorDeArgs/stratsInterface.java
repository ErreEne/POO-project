package LeitorDeArgs;

import GrafoPack.GrafoInterface;
import java.util.ArrayList;

public interface stratsInterface {

    void readArgs(String[] args);

    GrafoInterface getGrafo();

    ArrayList<Number> getConstants();
}