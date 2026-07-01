***Power-of-Two Max Heap***

A max-heap where every node has `2^x` children instead of the usual 2 (like abinary heap). The exponent `x` is a parameter passed to the constructor, so the branching factor of the tree can be tuned per instance.

**Files**
- `PowerOfTwoMaxHeap.java` — the heap implementation (`insert`, `popMax`, `peekMax`, `size`, `isEmpty`)
- `PowerOfTwoMaxHeapDemo.java` — a runnable demo exercising the heap across several branching factors and a couple of edge cases

**Usage**

```]java
PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(2); // 2^2 = 4 children per node

heap.insert(5);
heap.insert(9);
heap.insert(1);

heap.popMax();  // 9
```

The constructor parameter is called `branchFactorExponent` (not `x`) — it's
the number of doublings applied to get the branching factor:
`branchFactorExponent = 0` → 1 child per node, `= 1` → 2 children (an ordinary
binary heap), `= 2` → 4 children, and so on.

~ AP ;)
