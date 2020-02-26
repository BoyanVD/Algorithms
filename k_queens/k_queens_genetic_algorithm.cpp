#include <iostream>
#include <cmath>
#include <time.h>
using namespace std;

// Configuration constants. You can try to play around with different values and see what will happen.
const int N = 8;
const int boardSize = 8;
const int populationSize = 1000;
const double survivalRate = 0.05;
const double mutationRate = 0.5;

struct Board
{
    int queens[boardSize];

	Board() {
		for (int i=0; i < N; ++i) {
			int randomNum = rand();
            queens[i] = randomNum % N;
		}
	}

    void print_board()
    {
        for (int row = 0; row < N; ++row)
        {
            cout << "|";
            int colIndex = 0;
            for (; colIndex < queens[row]; ++colIndex)
            {
                if ((colIndex + row) % 2 == 0)
                    cout << "#|";
                else
                    cout << " |";
            }

            cout << "Q|";
            ++colIndex;

            for (; colIndex < N ; ++colIndex)
            {
                if ((colIndex + row) % 2 == 0)
                        cout << "#|";
                    else
                        cout << " |";
            }
            cout << endl;
        }
    }

    // fitness - function, that indicates the strenght of each individual
    int count_conflicts()
    {
        int count = 0;
        int columnIndexes[N] = {0};

        for (int i = 0; i < N; ++i)
        {
            if (columnIndexes[queens[i]] != 0)
                ++count;
            ++columnIndexes[queens[i]];
        }

        int primaryDiagonal[2 * N] = {0};
        int secondaryDiagonal[2 * N] = {0};

        for (int i = 0; i < N; ++i)
        {
            if (secondaryDiagonal[queens[i] + i] != 0)
                ++count;
            if (primaryDiagonal[queens[i] + (N - i)] != 0)
                ++count;
            ++secondaryDiagonal[queens[i] + i];
            ++primaryDiagonal[queens[i] + (N - i)];
        }

        return count;
    }

    // mutation - function that helps to improve the solution
    void mutate() 
    {
        int mutationRateCountNum = (int)floor(mutationRate * N);
        for (int i = 0; i < mutationRateCountNum; ++i)
        {
            int randomRow = (rand() % N);
            int randomCol = (rand() % N);
            queens[randomRow] = randomCol;
        }
	}

    // crossover - function, that helps us generate the next generation
    Board crossover(Board secondParent) 
    {
		Board child;
		int randomPosition =(rand() % N);

        if (randomPosition == 0)
            ++randomPosition;

		for (int i = 0; i < randomPosition; ++i) 
			child.queens[i] = queens[i];
			
		for(int i = randomPosition; i < N; ++i)
			child.queens[i]=secondParent.queens[i];
		
		return child;
	}

};

struct Population
{
    Board * population;
    int size;

    Population(int populationSize) 
    {
        size = populationSize;
	    population = new Board[size];
	    for (int i = 0; i < size; ++i)
		    population[i] = Board();
    }

    void swap(Board & board1, Board & board2)
    {
        Board temp;
        temp = board1;
		board1 = board2;
		board2 = temp;
    }

    //function, that sorts the population by each individual fitness function "performance"
    //implements another sort algorithm
    void sort_population(int left, int right)
    {
        int i = left;
        int j = right;
	    int medianConflictValue = population[(left + right) / 2].count_conflicts();

	    while(i <= j) 
        {
		    while(population[i].count_conflicts() < medianConflictValue) 
            {
			    i++;
		    }

		    while(population[j].count_conflicts() > medianConflictValue) 
            {
			    j--;
		    }

		    if(i <= j) 
            {
                swap(population[i], population[j]);
			    i++;
			    j--;
		    }
	    }

	    if(left < j) 
	    	sort_population(left, j);
	    
	    if(right > i) 
	    	sort_population(i, right);
    }

    // the main part of the algorithm, that makes the whole selection, next generation and solution
    int genetic_algorithm() 
    {
    	int numberOfMoves=0;

    	int prevConflictsCount = INT32_MIN;
        int minimumConflictCount = INT32_MIN;
    	while(true) 
        {
    		sort_population(0, size-1);
    		minimumConflictCount = population[0].count_conflicts();
            
    		if(prevConflictsCount != minimumConflictCount)
    			prevConflictsCount = minimumConflictCount;
    		
    		if(minimumConflictCount == 0) {
    			break;
    		}

            int numberOfPopulationMembersToRemove = (int)floor(size * survivalRate);

            if (numberOfPopulationMembersToRemove == 0)
                ++numberOfPopulationMembersToRemove;

	    	int numberOfNextGeneration = floor(1/survivalRate) - 1;
	    	for(int i = numberOfPopulationMembersToRemove, j = 0; i < size; i += numberOfNextGeneration, ++j) {
    			for(int k = 0; k < numberOfNextGeneration; ++k)
	    			population[i+k] = population[j].crossover(population[(j + k) % numberOfPopulationMembersToRemove]);
	    	}

	    	for(int i = 0; i < size; ++i)
	    		population[i].mutate();

	    	++numberOfMoves;
	    }

	    return numberOfMoves;
    }
};

int main() {

    srand(time(0));
	cout << "Number of queens: " << N << endl;
	cout << "Population size: " << populationSize << endl;
	cout << "Survivial rate: " << survivalRate << endl;
	cout << "Mutation rate: " << mutationRate << endl;

    Population pop(populationSize);
	cout << "Number of Moves for solution : " << pop.genetic_algorithm() << endl;
	pop.population[0].print_board();
	
	return 0;
}