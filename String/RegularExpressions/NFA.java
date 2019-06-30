public class NFA
{
    private int M;
    private char[] re;
    private Digraph G;
    
    public NFA(String regexp)
    {
        M = regexp.length();
        re = regexp.toCharArray();
        G = new Digraph(M+1);
        Stack<Integer> stack = new Stack<Integer>();
        
        for (int i = 0; i < M; i++) {
            char c = re[i];
            int lp = i;
            if (c == '|' || c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int or = stack.pop();
                if (re[or] == '|') {
                    lp = stack.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                } else {
                    lp = or;
                }
            }
            
            if (i < M-1 && re[i+1] == '*') {
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }
            
            if (c == '(' || c == '*' || c == ')') {
                G.addEdge(i, i+1);
            }
        }
    }
    
    public boolean recognizes(String txt)
    {
        Bag<Integer> pc = new Bag<Integer>();        
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) {
                pc.add(v);
            }
        }
        
        for (int i = 0; i < txt.length(); i++) {
            Bag<Integer> match = new Bag<Integer>();
            for (int j : pc) {
                if (j >=  M) continue;
                if (txt.charAt(i) == re[j] || re[j] == '.') {
                    match.add(j+1);
                }
            }
            pc = new Bag<Integer>();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v)) {
                    pc.add(v);
                }
            }
        }
        
        for (int v : pc) {
            if (v == M) return true;
        }
        return false;
    }
}