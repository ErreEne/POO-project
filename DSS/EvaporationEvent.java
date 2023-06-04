package DSS;

import ACO.MiguelInter;

import java.util.*;

public class EvaporationEvent implements EventTypes<Void, Void> {

    HashMap<Integer, Hashtable<Integer, MiguelInter>> pheromones;
    double time;

    @Override
    public Void execute(Void a) {

        /// Loop through the pheromones HashMap and modify MiguelInter objects
        for (Map.Entry<Integer, Hashtable<Integer, MiguelInter>> outerEntry : pheromones.entrySet()) {
            Integer outerKey = outerEntry.getKey();
            Hashtable<Integer, MiguelInter> innerHashtable = outerEntry.getValue();

            for (Map.Entry<Integer, MiguelInter> innerEntry : innerHashtable.entrySet()) {
                Integer innerKey = innerEntry.getKey();
                MiguelInter miguelInter = innerEntry.getValue();

                // Modify the MiguelInter object
                miguelInter.evaporationOfPheromone();

                // Update the modified object back to the innerHashtable
                innerHashtable.put(innerKey, miguelInter);
            }

            // Update the modified innerHashtable back to the pheromones HashMap
            pheromones.put(outerKey, innerHashtable);
        }
        return null;
    }

}