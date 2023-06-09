package ACO;

/**
 * Class that represents the pheromone
 */
public class Miguel implements MiguelInter {
    float pheromone;
    int totalWeights;
    float gamma;
    float ro;

    /**
     * @param numberOfNodes total number of nodes
     * @param totalWeights  total number of weights
     * @param gamma         value for calculating the level of pheromone
     * @param ro            value for decreasing pheromones
     */
    public Miguel(int numberOfNodes, int totalWeights, float gamma, float ro) {
        this.pheromone = 0;
        this.totalWeights = totalWeights;
        this.gamma = gamma;
        this.ro = ro;
    }

    /**
     * Set the pheromone level
     * 
     * @param sumOfWeights sum of all weights
     */
    public void setPheromone(int sumOfWeights) {
        this.pheromone += gamma * totalWeights / sumOfWeights;
    }

    /**
     * @return the pheromone level
     */
    public float getPheromone() {
        return pheromone;
    }

    /**
     * Decrease the pheromone level
     */
    public void evaporationOfPheromone() {
        if (pheromone > 0) {
            this.pheromone -= ro;
        } else {
            this.pheromone = 0;
        }

        if (this.pheromone < 0)
            this.pheromone = 0;
    }
}