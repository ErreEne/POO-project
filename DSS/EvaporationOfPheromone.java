package DSS;

import ACO.Miguel;
import ACO.MiguelInter;
import java.util.ArrayList;

public class EvaporationOfPheromone implements FeromonasInterface {

    public void execute() {
        // Chamar coisas em Miguel?? que evaporem e depois gerar o timestamp do próximo
        // evento
        Miguel.evaporationOfPheromone(); // precisa de ser static?
    };

    public void GenerateQueue(ArrayList<MiguelInter> array) {
    }

}