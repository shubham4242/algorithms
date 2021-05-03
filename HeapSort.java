public class HeapSort {
    interface Check {
        public boolean check(int a, int b);
    }

    public class CheckMaxValue implements Check {

        @Override
        public boolean check(int a, int b) {
            return a > b;
        }
    }

    public class CheckMinValue implements Check {

        @Override
        public boolean check(int a, int b) {
            return a < b;
        }
    }

    public void heapSort(int[] elements) {
        heapSort(elements, true);
    }

    public void heapSort(int[] elements, boolean ASC) {
        int numberOfElements = elements.length;
        Check compare = ASC ? new CheckMaxValue() : new CheckMinValue();
        for (int i = numberOfElements / 2 - 1; i >= 0; i--) {
            heapify(elements, elements.length, i, compare);
        }
        for (int i = numberOfElements - 1; i >= 0; i--) {
            swap(elements, i, 0);
            heapify(elements, i, 0, compare);
        }
    }

    public void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public void heapify(int[] elements, int numberOfElements, int currentIndex, Check compare) {
        int largestValue = currentIndex;
        int leftChild = 2 * currentIndex + 1;
        int rightChild = 2 * currentIndex + 2;
        if (leftChild < numberOfElements && compare.check(elements[leftChild], elements[largestValue]))
            largestValue = leftChild;
        if (rightChild < numberOfElements && compare.check(elements[rightChild], elements[largestValue]))
            largestValue = rightChild;
        if (largestValue != currentIndex) {
            swap(elements, currentIndex, largestValue);
            heapify(elements, numberOfElements, largestValue, compare);
        }
    }

}
