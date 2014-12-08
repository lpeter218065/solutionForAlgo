package learn;

/**
 * Created by rxu on 12/5/2014.
 */
public class BST {

    static class Node {
        int val;
        Node left;
        Node right;

        Node(int v) {
            val = v;
            left = null;
            right = null;
        }
    }


    static Node buildBST(int[] a) {
        Node root = new Node(a[0]);
        for (int i = 1; i < a.length; ++i) {
            build(root, a[i]);
        }
        return root;
    }

    static Node build(Node root, int a) {
        if (root == null) {
            root = new Node(a);
        } else if (a <= root.val) {
            root.left = build(root.left, a);
        } else if (a > root.val) {
            root.right = build(root.right, a);
        }
        return root;
    }

    static Node insert(Node root, int a) {
        if (root == null) {
            return new Node(a);
        } else if (a <= root.val) {
            root.left = insert(root.left, a);
        } else {
            root.right = insert(root.right, a);
        }
        return root;
    }


    static Node delete(Node root, int a) {
        if (root == null) return null;
        else if (a < root.val) {
            root.left = delete(root.left, a);
        } else if (a > root.val) {
            root.right = delete(root.right, a);
        } else {
            if (root.left != null && root.right != null) {
                // try to find the largest one in the left tree
                // then append the right tree
                Node t = root.left;
                while (t.right != null) {
                    t = t.right;
                }
                t.right = root.right;
                root = root.left;
            } else if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                root = null;
            }
        }
        return root;
    }


    static void dfs(Node root) {
        if (root == null) return;
        dfs(root.left);
        System.out.print(root.val + " ");
        dfs(root.right);
    }


    public static void main(String[] args) {
        int[] a = new int[]{2, 4, 3, 5, 1, 6, 7};
        Node tree = buildBST(a);
        tree = insert(tree, 0);
        dfs(tree);
        System.out.println();
        tree = delete(tree, 7);
        dfs(tree);
    }

}
