# Travelling Salesman via Ant Colony Optimization

This repository contains a **Travelling Salesman Problem (TSP)** simulator based on **Ant Colony Optimization (ACO)**, designed as a course project for *Object Oriented Programming* at IST.  The system is implemented in Java with an emphasis on clean object-oriented design, UML-driven architecture, and discrete stochastic simulation of ant behavior on weighted graphs.  

## Problem description

- The TSP asks for the shortest Hamiltonian cycle in a weighted graph \(G = (N, E, \mu)\), visiting each node exactly once and returning to the start.  
- The project approximates this NP-complete problem by simulating a colony of artificial ants that construct tours and communicate indirectly via pheromone trails.  
- The approach is motivated by swarm intelligence and has applications in planning, logistics, and microchip manufacturing.  

## Ant colony optimization model

The simulator implements a classic ACO scheme tailored to TSP:  

- **Ant movement rules**  
  - Ants start from a *nest* node \(n_1\) and build tours by moving between adjacent nodes without revisiting nodes, except returning to the nest.  
  - At each step, an ant chooses the next node with probability proportional to the pheromone level and inversely proportional to edge weight, controlled by parameters \(\alpha\) and \(\beta\).  
  - If no unvisited neighbors are available, the ant backtracks by removing the cycle it just created and continues.  

- **Timing and events**  
  - Travel time on an edge follows an exponential distribution with mean \(\delta \times a_{ij}\), where \(a_{ij}\) is the edge weight and \(\delta\) is a parameter.  
  - A **Discrete Stochastic Simulation (DSS)** handles two event types: ant moves and pheromone evaporations.  

- **Pheromone dynamics**  
  - Pheromones start at zero on all edges and are increased only when an ant completes a Hamiltonian cycle.  
  - The pheromone increment on each edge of a tour is \(\gamma W / \mu(\text{cycle})\), where \(W = \sum_{e \in E} \mu(e)\) and \(\gamma\) is a parameter.  
  - Pheromone evaporates in discrete steps; each evaporation event reduces pheromone by \(\rho\) on each edge, with inter-evaporation times drawn from an exponential distribution with mean \(\eta\).  

- **Best cycle tracking**  
  - The simulator maintains the best (shortest) Hamiltonian cycle found so far and updates it whenever a better tour is discovered.  

## Architecture and object orientation

The project is structured around an OO design in UML and Java:  

- A clear separation of responsibilities between:  
  - Graph representation (nodes, edges, weights, pheromone levels).  
  - Ant entities (state, current path, movement decisions).  
  - Simulation engine (event scheduling, time progression, statistics).  
  - Input parsing and output formatting.  

- Emphasis on:  
  - Encapsulation of domain concepts (graph, colony, events).  
  - Use of polymorphism and open/closed design to ease extensions and testing.  
  - Javadoc documentation and UML diagrams as deliverables alongside the code.  

## Input, parameters, and running

The program is distributed as a `project.jar` executable and can be invoked in two main modes.  

### 1. Random graph mode

```bash
java -jar project.jar -r n a n1 α β δ η ρ γ ν τ
```

- `n`: number of nodes in the graph.  
- `a`: maximum edge weight; weights are generated in \([0, a]\) ensuring at least one Hamiltonian cycle.  
- `n1`: index of the nest node.  
- `α, β, δ`: parameters for ant movement probabilities and travel time.  
- `η, ρ`: parameters for pheromone evaporation timing and decrement.  
- `γ`: scaling factor for pheromone deposition.  
- `ν`: number of ants in the colony.  
- `τ`: final simulation time.  

The program generates a random weighted graph with at least one Hamiltonian cycle and runs the simulation with the given parameters.  

### 2. File-based graph mode

```bash
java -jar project.jar -f <input_file>
```

- `<input_file>`: text file containing parameters and the adjacency matrix of the weighted graph.  
- The file format is:  
  - First lines: `n`, `n1`, `α β`, `δ η`, `ρ γ`, `ν τ`.  
  - Followed by an `n x n` symmetric adjacency matrix with `aij = aji` and `aii = 0`.  
- The input file can be given with an absolute or relative path (e.g., `./TESTS/input.txt`).  

On startup, the program prints all input parameters and the graph adjacency matrix in a fixed format for traceability.  

## Simulation output

During the run, the simulator periodically reports its state to the terminal:  

- Observations are printed at times `τ/20, 2τ/20, ..., τ` (20 observations in total).  
- Each observation includes:  
  - Current simulation time (`instant`).  
  - Number of move events executed (`mevents`).  
  - Number of evaporation events executed (`eevents`).  
  - Up to five top candidate cycles found so far, printed with their weights.  
  - The best Hamiltonian cycle discovered so far, or `{}` if none has been found yet.  

Cycles are printed in the form `{1,5,4,2,3}:14`, where the sequence describes the tour and the number is its total weight.  

## Educational goals

This project demonstrates how **object-oriented design**, **discrete event simulation**, and **metaheuristic optimization** can be combined to solve a classical NP-complete problem.  It reinforces concepts such as UML modelling, encapsulation and polymorphism, stochastic processes, and the practical behavior of ant colony optimization on weighted graphs.  
