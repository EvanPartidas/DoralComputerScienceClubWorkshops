import java.util.*;
import java.io.*;

public class MarkovChain {
    public static void main(String args[]) {
        HashMap<String,Node> graph = new HashMap();
        graph.put("#",new Node("#"));//Starting node
        graph.put("$",new Node("$"));//Terminating node
        
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){
            String ln = scan.nextLine();
            String words[] = ln.split(" ");
            
            for(int i=0;i<words.length;i++){
                
                //Insert word into graph
                if(!graph.containsKey(words[i])){
                    graph.put(words[i],new Node(words[i]));
                }
                
                //Connecting edges
                if(i==0)
                    graph.get("#").addEdge(words[i]);
                else
                    graph.get(words[i-1]).addEdge(words[i]);
                
                //Case: Last word
                if(i==words.length-1)
                    graph.get(words[i]).addEdge("$");
            }
            int n = 5; //Number of times to simulate
            for(int i=0;i<n;i++){
                String curnode = "#";
                while(!curnode.equals("$")){
                    System.out.print(curnode+" ");
                    curnode = graph.get(curnode).nextWord();
                }
                System.out.println();
            }
        }
    }
    static class Node{
        int outgoingwords;
        String name;
        HashMap<String,Integer> destinations = new HashMap<>();
        public Node(String n){
            name = n;
            outgoingwords = 0;
        }
        public String nextWord(){
            String[] votes = new String[outgoingwords];
            int index=0;
            for(String key: destinations.keySet()){
                for(int i=destinations.get(key);i>0;i--){
                    votes[index++] = key;
                }
            }
            int rnd = (int)(Math.random()*outgoingwords);
            return votes[rnd];
        }
        public void addEdge(String dest){
            outgoingwords++;
            if(!destinations.containsKey(dest)){
                destinations.put(dest,0);
            }
            destinations.put(dest,destinations.get(dest)+1);
        }
    }
}
