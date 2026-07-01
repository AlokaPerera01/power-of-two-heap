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

The constructor parameter is called `branchFactorExponent` (not `x`) — it's the number of doublings applied to get the branching factor: `branchFactorExponent = 0` → 1 child per node, `= 1` → 2 children (an ordinary binary heap), `= 2` → 4 children, and so on.

**Compiling and running**
javac PowerOfTwoMaxHeap.java PowerOfTwoMaxHeapDemo.java
java PowerOfTwoMaxHeapDemo

The demo prints the same sorted sequence for several different branching factors (`x = 0, 1, 2, 3, 30`), proving that `x` changes the shape and performance of the tree without ever changing correctness. It also checks two edge cases: popping from an empty heap (should throw `NoSuchElementException`) and inserting duplicate values (should still pop in correct non-increasing order).

**Performance characteristics**
Where `n` is the number of elements and `d = 2^branchFactorExponent`:

- **`insert`**: `O(log_d n)` — sifting up only ever compares a node to its one parent per level, so a larger `x` (shorter tree) makes insert cheaper.
- **`popMax`**: `O(d · log_d n)` — sifting down has to scan up to `d` children per level, so a larger `x` makes each pop more expensive even though the tree is shorter.

That trade-off - cheap inserts vs. cheap pops as `x` grows — is the reason the branching factor is exposed as a tunable constructor parameter rather than fixed.


~ AP ;)
