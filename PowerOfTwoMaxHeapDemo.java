public class PowerOfTwoMaxHeapDemo {
    public static void main(String[] args) {
        //PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(1); // x = 1 -> ordinary binary heap
        
        int[] values = {5,3, 8, 1, 9, 2, 7, 4, 6, 0};              //{5, 3, 8, 1,  9, 2};

        //int[] exponentsToTest = {0, 1, 2, 3};

        int[] exponentsToTest = {0, 1, 2, 3, 30}; //added a very large x
        
        /*for (int v : values) {
            heap.insert(v);
        }*/

        for (int exponent : exponentsToTest) {
            PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(exponent);
            for (int v : values) {
                heap.insert(v);
            }

            //System.out.print("x=" + exponent + "(children per node=" + (1 << exponent) + "): ");
            System.out.print("x=" + exponent + " (children per node=" + (1L << exponent) + "): ");  
            while (!heap.isEmpty()) {
                System.out.print(heap.popMax() + " ");
            }
                System.out.println();
        }

            // Edge case: popping an emoty heap should throw, not crash silently
            PowerOfTwoMaxHeap<Integer> emptyHeap = new PowerOfTwoMaxHeap<>(2);
            try {
                emptyHeap.popMax();
                System.out.println("BUG: expected an exception but none was thrown");
            } catch (java.util.NoSuchElementException e) {
                System.out.println("empty-heap popMax correctly threw:" + e.getMessage());
            }

            //Edge case: duplicates shouldn't confiuse the ordering
            PowerOfTwoMaxHeap<Integer> dupHeap = new PowerOfTwoMaxHeap<>(2);
            int[] dupValues = {5, 5, 5, 1, 5};
            for (int v : dupValues) dupHeap.insert(v);
            System.out.print("duplicates test: ");
            while (!dupHeap.isEmpty()) {
                System.out.print(dupHeap.popMax() + " ");
            }
            System.out.println();
        }
      
    }


        /*System.out.println("size after inserts" + heap.size());
        System.out.println("max should be 9: " + heap.peekMax());

        while (!heap.isEmpty()) {
            System.out.println("popped:" + heap.popMax());
        }*/
    

