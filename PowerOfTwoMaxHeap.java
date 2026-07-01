import java.util.Arrays;
import java.util.NoSuchElementException;

public final class PowerOfTwoMaxHeap<T extends Comparable<? super T>> {
    
    private final int branchFactorExponent; // the "x" - how many powers of two 
    private final long childrenPerNode;     // 2^branchFactorExponent, precomputed
    private Object[] elements;
    private int size;

    public PowerOfTwoMaxHeap(int branchFactorExponent) {
        if (branchFactorExponent < 0 || branchFactorExponent > 30) {
            throw new IllegalArgumentException ("branchFactorExponent out of range");
            }
            this.branchFactorExponent = branchFactorExponent;
            this.childrenPerNode = 1L << branchFactorExponent;
            this.elements = new Object[16];
            this.size = 0;
    }  
    
    public void insert(T value) {
        if (value == null) throw new NullPointerException("no nulls");
        ensureCapacity(size + 1);
        elements[size] = value;
        siftUp(size);
        size++;
    }
    
    private void siftUp(int index) {
        Object value = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> branchFactorExponent;
            Object parentValue = elements[parentIndex];
            if (compare(parentValue, value) >=0) break;
            elements[index] = parentValue;
            index = parentIndex;
        }
        elements[index] = value;
    }

    private void ensureCapacity(int minCapacity) {
        if (elements.length < minCapacity) {
            elements = Arrays.copyOf(elements, Math.max(minCapacity, elements.length * 2));
        }
    }
    
    @SuppressWarnings("unchecked")
    private int compare(Object a, Object b) {
        return ((T) a).compareTo((T) b);
    }

    @SuppressWarnings("unchecked")
    public T popMax() {
        if (size == 0) throw new NoSuchElementException("heap is empty");
        T max = (T) elements[0];
        size--;
        if (size >0) {
            elements[0] = elements[size];
            elements[size] = null;
            siftDown(0);
        } else {
            elements[0] = null;
        }
        return max;
    }

    private void siftDown(int index) {
        Object value = elements[index];
        while (true) {
            long firstChild = ((long) index <<branchFactorExponent) +1;
            if (firstChild >= size) break; // no childrem - done

            long lastChildExclusive = Math.min(firstChild + childrenPerNode, size);
            int largestChildIndex = (int) firstChild;
            Object largestChildValue = elements[largestChildIndex];

            for (long c = firstChild + 1; c < lastChildExclusive; c++) {
                Object candidate = elements[(int) c];
                if (compare(candidate, largestChildValue) > 0) {
                    largestChildIndex = (int) c;
                    largestChildValue = candidate;
                }
            }

            if (compare(largestChildValue, value) <=0) break;
            elements[index] = largestChildValue;
            index = largestChildIndex;
        }
        elements[index] = value;
    } 

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public T peekMax() {
        if (size == 0) throw new NoSuchElementException("heap is empty");
        return (T) elements[0];
    }   
}
