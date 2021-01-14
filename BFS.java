import java.util.*;

/*

Problem: Given a directed graph can you reach an end node from a start node?
Input format:
First line is N, E. The number of nodes and edges
The next E lines contain two integers a and b,
which means there is a directed edge from a to b.
The last line is start and end.

INPUT DATA
5 5
3 5
2 3
3 2
1 2
4 2
2 4
replace the last line of input (2 4) with 2 5, you will now get true because
there is the path 2->3->5.
*/

public class BFS {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt(); //Number of Nodes
        int E = scan.nextInt(); //Number of Edges
        
        //Initialize the Adjacency List, this is one method we can use to store our graph 
        //If adjacencyList[i].contains(j) then there is an edge from i to j.
        HashSet<Integer> adjacencyList[] = new HashSet[N];
        for(int i=0;i<N;i++)
        {
            adjacencyList[i] = new HashSet<>();
        }
        
        //Read in edges
        for(int i=0;i<E;i++)
        {
            int start = scan.nextInt()-1;
            int dest = scan.nextInt()-1;
            adjacencyList[start].add(dest);
        }
        
        //Read in query data, can we get from start to end?
        int start = scan.nextInt()-1;
        int end = scan.nextInt()-1;
        
        //Our queue, this will store our list of points we need to visit
        ArrayDeque<Integer> que = new ArrayDeque<>();
        
        //Our visited set, this will make sure we don't visit a spot more than once (you can get stuck in an infinite loop)
        HashSet<Integer> visited = new HashSet<>();
        
        que.addLast(start);//Add in our starting point.
        boolean can_reach_end = false;
        while(!que.isEmpty())
        {
            int current_point = que.removeFirst();//Get the next spot to visit (Note: if you replace this with removeLast() it is now a DFS)
            if(current_point==end)
            {
                can_reach_end = true;
                break;
            }
            for(int dest: adjacencyList[current_point])
            {
                if(visited.contains(dest)) continue;
                visited.add(dest);
                que.addLast(dest);
            }
        }
        System.out.printf("can_reach_end: %b",can_reach_end);
    }
}
