# COMP2240 - Operating Systems
## Assignment 2
### Task
Assignment was split into three separate problems

1. Create a starvation free controller for a system of Warehouse Assistance Robots (WAR's) that are concurrently operating on two intersecting tracks to ensure that no WAR will enter the intersection at the same time as another. Using Semaphores, start threads for each WAR read in from a file and output the results in real time as the WARs wait at the intersection, cross, and then either unload at the dock or load at the storage and return to the intersection
2. Simulate a printer with three concurrent print heads. Use a monitor to ensure mutual exclusion as jobs are run according to data read from a file. The printer can handle both Monochrome jobs and Colour jobs, although when the printer is in Monochrome mode, all print heads can only run Monochrome jobs and Colour jobs must wait, and vice versa. Solution should be starvation free, that is, a long stream of Monochrome jobs should still run any Colour jobs as they arrive in the queue.
3. Repeat the implementation of Problem 2, but using a Semaphore instead of a Monitor

### Compile
1. `javac P1/P1.java`
2. `javac P2/P2.java`
3. `javac P3/P3.java`

### Run
1. `java P1/P1 P1-1in.txt`
2. `java P2/P2 P2-1in.txt`
3. `java P3/P3 P2-1in.txt`
