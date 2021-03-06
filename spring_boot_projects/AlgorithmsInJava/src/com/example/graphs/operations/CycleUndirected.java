package com.example.graphs.operations;


//A Java Program to detect cycle in an undirected graph
import java.io.*;
import java.util.*;

//This class represents a directed graph using adjacency list
//representation
public class CycleUndirected {


 private int V;   // No. of vertices
 private LinkedList<Integer> adj[]; // Adjacency List Represntation

 // Constructor
 CycleUndirected(int v) {
     V = v;
     adj = new LinkedList[v];
     for(int i=0; i<v; ++i)
         adj[i] = new LinkedList();
 }

 // Function to add an edge into the graph
 void addEdge(int v,int w) {
     adj[v].add(w);
     adj[w].add(v);
     System.out.print("v:"+v+"=");
     System.out.println(adj[v]);
     System.out.print("w:"+w+"=");
     System.out.println(adj[w]);
 }

 // A recursive function that uses visited[] and parent to detect
 // cycle in subgraph reachable from vertex v.
 Boolean isCyclicUtil(int v, Boolean visited[], int parent)
 {
     // Mark the current node as visited
     visited[v] = true;
     Integer i;

     // Recur for all the vertices adjacent to this vertex
     Iterator<Integer> it = adj[v].iterator();
     while (it.hasNext())
     {
         i = it.next();

         // If an adjacent is not visited, then recur for that
         // adjacent
         System.out.println("push : v:" + v +" :child:i:" +i+ " :parent:"+parent);
         if (!visited[i])
         {
             if (isCyclicUtil(i, visited, v))
                 return true;
         }
         // If an adjacent is visited and not parent of current
         // vertex, then there is a cycle.
         else if (i != parent) {
             return true;
         }
         System.out.println("pop  :--->>> v-:" + v +" :child:i:"+ i + " :parent:"+parent);
     }
     return false;
 }

 // Returns true if the graph contains a cycle, else false.
 Boolean isCyclic()
 {
     // Mark all the vertices as not visited and not part of
     // recursion stack
     Boolean visited[] = new Boolean[V];
     for (int i = 0; i < V; i++)
         visited[i] = false;

     // Call the recursive helper function to detect cycle in
     // different DFS trees
     for (int u = 0; u < V; u++)
         if (!visited[u]) // Don't recur for u if already visited
             if (isCyclicUtil(u, visited, -1))
                 return true;

     return false;
 }

 static void printGraph(CycleUndirected graph)
 {       
     for(int v = 0; v < graph.V; v++)
     {
         System.out.println("Adjacency list of vertex "+ v);
         System.out.print("head");
         for(Integer pCrawl: graph.adj[v]){
             System.out.print(" -> "+pCrawl);
         }
         System.out.println("");
     }
 }

 // Driver method to test above methods
 public static void main(String args[])
 {
     // Create a graph given in the above diagram
	 CycleUndirected g1 = new CycleUndirected(5);
     g1.addEdge(1, 0);
     g1.addEdge(0, 2);
     g1.addEdge(2, 0);
     g1.addEdge(0, 3);
     g1.addEdge(3, 4);
     printGraph(g1);
     
     if (g1.isCyclic())
         System.out.println("Graph contains cycle");
     else
         System.out.println("Graph doesn't contains cycle");

     CycleUndirected g2 = new CycleUndirected(3);
    // g2.addEdge(0, 1);
    // g2.addEdge(1, 2);
     if (g2.isCyclic())
         System.out.println("Graph contains cycle");
     else
         System.out.println("Graph doesn't contains cycle");
 }
}