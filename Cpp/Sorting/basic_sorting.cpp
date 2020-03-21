#include <iostream>

template <class T>
using ComparePredicate = int (*) (T, T);

template <class T>
void swap(T* x1, T* x2) {
    T tmp = *x1;
    *x1 = *x2;
    *x2 = tmp;
}

template <class T>
void bubbleSort(T arr[], size_t size, ComparePredicate<T> pred) {
    for (int i = 0; i < size - 1; ++i) {
        bool has_a_swap = false;
        for (int j = 0; j < size - 1 - i; ++j) {
            if (pred(arr[j], arr[j + 1]) > 0) {
                swap(&arr[j], &arr[j + 1]);
                has_a_swap = true;
            }
        }
        if (!has_a_swap)
            return;
    }

}

template<typename T>
void selectionSort(T arr[], size_t size, ComparePredicate<T> pred) {
    for (int i = 0; i < size - 1; ++i) {
        int minimum_index = i;  
        for (int j = i+1; j < size; j++)  
            if (pred(arr[j], arr[minimum_index]) > 0)  
                minimum_index = j;  
  
        swap(&arr[minimum_index], &arr[i]);  
    }  
}

template<typename T>
void insertionSort(T arr[], size_t size, ComparePredicate<T> pred) {
    for (int i = 1; i < size; i++) {
        T key = arr[i];  
        int j = i - 1;  

        while (j >= 0 && pred(arr[j], key) > 0) {  
            arr[j + 1] = arr[j];  
            j = j - 1;  
        }  
        arr[j + 1] = key;  
    }  
}

template <class T>
int binarySearch(T arr[], T item, int low, int high, ComparePredicate<T> pred) {
    if (high <= low) 
        return (pred(item, arr[low]) > 0) ?  (low + 1) : low; 
  
    int mid = (low + high) / 2; 
  
    if(pred(item, arr[mid]) == 0) 
        return mid + 1; 
  
    if(pred(item, arr[mid]) > 0) 
        return binarySearch(arr, item, mid + 1, high); 
    return binarySearch(arr, item, low, mid - 1); 
} 

template <class T>
void binaryInsertionSort(T arr[], size_t size, ComparePredicate<T> pred) {
    for (int i = 1; i < size; ++i) { 
        int j = i - 1; 
        T key = arr[i]; 
        int location = binarySearch(arr, key, 0, j, pred); 
  
        while (j >= location) { 
            arr[j+1] = arr[j]; 
            --j; 
        } 
        arr[j+1] = key; 
    } 
} 

template <class T>
void countingSort(T arr[], size_t size) {
    T output[size];
    int min_id = 0, max_id = 0;

    for (int i = 0; i < size; ++i) {
        if (arr[i] < arr[min_id])
            min_id = i;
        if (arr[i] > arr[max_id])
            max_id = i;
    }

    int range = arr[max_id] - arr[min_id];
    int count[range + 1];  
    for (int i = 0; i <= range; ++i)
        count[i] = 0;

    for (int i = 0; i < size; ++i)
        ++count[arr[i] - arr[min_id]];

    int output_id = 0;
    for (int i = 0; i <= range; ++i) {
        for (int j = 0; j < count[i]; ++j) {
            output[output_id] = i + arr[min_id];
            ++output_id;
        }
    }

    for (int i = 0; i < size; ++i)
        arr[i] = output[i];
}

int main() {
}