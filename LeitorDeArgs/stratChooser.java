package LeitorDeArgs;

import java.util.*;
import GrafoPack.GrafoInterface;

public class stratChooser {

    private stratsInterface FinalStrategy;
    HashMap<String, stratsInterface> strategies;

    public void setStrategy(String stratString, stratsInterface strat) {
        strategies.put(stratString, strat);
    }

    public void readArgs(String[] args) {

        FinalStrategy = strategies.get(args[0]);
        FinalStrategy.readArgs(args);

    }

    public GrafoInterface getGrafo() {
        return FinalStrategy.getGrafo();
    }

    public ArrayList<Number> getConstantes() {
        return FinalStrategy.getConstants();
    }

}