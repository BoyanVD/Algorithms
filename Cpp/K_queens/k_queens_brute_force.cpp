#include <iostream>
using namespace std;
#define QUEEN_NUM 1
#define EMPTY_NUM 0
#define NUMBER_QUEENS 4

const int k = NUMBER_QUEENS;

bool are_indexes_in_array(int r, int c)
{
    return r >= 0 && r < k && c >= 0 && c < k;
}

bool check_diagonal_hits(int r, int c, int arr[k][k])
{
    int curr_r = (r + c >= k - 1) ? (k - 1) : r + c;
    int curr_c = (r + c < k - 1) ? 0 : (r + c) - (k - 1);

    while (are_indexes_in_array(curr_r, curr_c))
    {
        if (arr[curr_r][curr_c] != 0)
            return true;

        --curr_r;
        ++curr_c;
    }

    curr_r = (c >= r) ? 0 : (r - c);
    curr_c = (c >= r) ? (c - r) : 0;

    while (are_indexes_in_array(curr_r, curr_c))
    {
        if (arr[curr_r][curr_c] != 0)
            return true;

        ++curr_r;
        ++curr_c;
    }
}

bool check_horizontal_hits(int r, int c, int arr[k][k])
{
    for (int i = 0; i < k; ++i)
    {
        if(arr[r][i] != 0)
            return true;
    }
}

bool check_vertical_hits(int r, int c, int arr[k][k])
{
    for (int i = 0; i < k; ++i)
    {
        if (arr[i][c] != 0)
            return true;
    }
}

bool check_if_it_hits(int r, int c, int arr[k][k])
{
    return (arr[r][c] != 0)
        || check_diagonal_hits(r, c, arr)
        || check_horizontal_hits(r, c, arr)
        || check_vertical_hits(r, c, arr);
}

bool can_we_put_here(int r, int c, int arr[k][k])
{
    return !check_if_it_hits(r, c, arr);
}

void put_queen(int r, int c, int arr[k][k])
{
    arr[r][c] = QUEEN_NUM;
}

void remove_queen(int r, int c, int arr[k][k])
{
    arr[r][c] = EMPTY_NUM;
}

void print_array(int arr[k][k])
{
    for (int i = 0; i < k; ++i)
    {
        for (int j = 0; j < k; ++j)
        {
            cout << arr[i][j] << " ";
        }
        cout << endl;
    }
}

void put_the_k_queens(int board[k][k], int numberOfQueens)
{   
    if(numberOfQueens == 0)
    {
        print_array(board);
        cout << endl;
        return;
    }
    
    for (int i = 0; i < k; ++i)
    {
        for (int j = 0; j < k; ++j)
        {
            if (can_we_put_here(i, j, board))
            {
                put_queen(i, j, board);
                put_the_k_queens(board, numberOfQueens - 1);
                remove_queen(i, j, board);
            }
        }
    }
}

int main()
{
    cout << "The program will run with " << NUMBER_QUEENS << " number of queens !" << endl;
    int board[k][k] = {0};
    int numberOfQueens = k;
    put_the_k_queens(board, numberOfQueens);
}