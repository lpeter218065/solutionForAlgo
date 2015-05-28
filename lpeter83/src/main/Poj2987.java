package main;

import io.InputReader;
import io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Poj2987 {

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        level = new int[n + 2];
        cnt = new int[n + 2];

        g = new ArrayList[n + 2];
        for (int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<E>();
        }

        long sum = 0;
        for (int i = 0; i < n; ++i) {
            int t = in.readInt();
            if (t > 0) {
                addEdge(n, i, t);
                sum += t;
            } else if (t < 0) {
                addEdge(i, n + 1, -t);
            }
        }

        for (int i = 0; i < m; i++) {
            int a = in.readInt() - 1;
            int b = in.readInt() - 1;
            addEdge(a, b, Long.MAX_VALUE / 2);
        }

        long flow = maxFlow(n, n + 1);
        vis = new boolean[n + 2];
        Arrays.fill(vis, false);
        out.printLine(dfs2(n, n + 1), sum - flow);

    }

    void bfs(int s) {
        Arrays.fill(level, -1);
        level[s] = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < g[cur].size(); ++i) {
                E e = g[cur].get(i);
                if (e.cap > 0 && level[e.to] < 0) {
                    level[e.to] = level[cur] + 1;
                    q.add(e.to);
                }
            }
        }
    }


    long dfs(int v, int t, long f) {
        if (v == t) return f;
        for (int i = cnt[v]; i < g[v].size(); cnt[v] = ++i) {
            E e = g[v].get(i);
            if (e.cap > 0 && level[e.to] > level[v]) {
                long d = dfs(e.to, t, Math.min(e.cap, f));
                if (d > 0) {
                    e.cap -= d;
                    g[e.to].get(e.rev).cap += d;
                    return d;
                }
            }
        }
        return 0;
    }

    int dfs2(int s, int t) {
        if (s == t) return 0;
        vis[s] = true;
        int res = 0;
        for (int i = 0; i < g[s].size(); i++) {
            E e = g[s].get(i);
            if (e.cap > 0 && !vis[e.to]) {
                res += dfs2(e.to, t) + 1;
            }
        }
        return res;
    }

    long maxFlow(int s, int t) {
        long res = 0;
        while (true) {
            bfs(s);
            if (level[t] < 0) {
                return res;
            }
            Arrays.fill(cnt, 0);
            long flow = 0;
            while ((flow = dfs(s, t, Long.MAX_VALUE)) > 0) {
                res += flow;
            }
        }
        //return res;
    }


    int[] level;
    int[] cnt;
    boolean[] vis;


    ArrayList<E> g[];

    class E {
        int to, rev;
        long cap;

        public E(int to, long cap, int rev) {
            this.to = to;
            this.cap = cap;
            this.rev = rev;
        }
    }

    void addEdge(int u, int v, long cap) {
        g[u].add(new E(v, cap, g[v].size()));
        g[v].add(new E(u, 0, g[u].size() - 1));
    }



    /*
    class V {
        ArrayList<E> es;

        V () {
            es = new ArrayList<E>();
        }

        void connect(V v0, int cap) {
            E e = new E(v0, cap, null);
            E erev = new E(this, 0, null);
            e.rev = erev;
            erev.rev = e;
            es.add(e);
            v0.es.add(erev);
        }
    }

    class E {
        V to;
        int cap;
        E rev;
        E(V _to, int _cap, E _rev) {
            to = _to;
            cap = _cap;
            rev = _rev;
        }
    }
    */
}
