package main;

import io.InputReader;
import io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;

public class poj1466 {


    ArrayList<Integer> g[];
    int[] match;
    boolean[] vis;
    int N;

    void addEdge(int u, int v) {
        g[u].add(v);
        g[v].add(u);
    }

    boolean dfs(int v) {
        vis[v] = true;
        for (int i = 0; i < g[v].size(); i++) {
            int u = g[v].get(i);
            int w = match[u];
            if (w == -1 || !vis[w] && dfs(w)) {
                match[u] = v;
                match[v] = u;
                return true;
            }
        }
        return false;
    }

    int bigraphMatch() {
        int res = 0;
        Arrays.fill(match, -1);
        for (int i = 0; i < N; i++) {
            Arrays.fill(vis, false);
            if (match[i] < 0 && dfs(i))
                res++;
        }
        return res;
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (!in.isExhausted()) {
            N = in.readInt();
            match = new int[N];
            vis = new boolean[N];
            g = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                g[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < N; i++) {
                String[] s = in.readLine().split(" ");
                int m = Integer.parseInt(s[0].substring(0, s[0].length() - 1));
                int nn = Integer.parseInt(s[1].substring(1, s[1].length() - 1));
                for (int j = 0; j < nn; j++) {
                    int nnn = Integer.parseInt(s[2 + j]);
                    addEdge(m, nnn);
                }
            }

            int r = bigraphMatch();
            out.printLine(N - r);
        }
    }
}
