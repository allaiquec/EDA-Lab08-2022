package com.example.project;

public class BTreeGeneric<E extends Comparable<? super E>> {
    BNodeGeneric<E> root;
    int MinDeg;

    // Constructor
    public BTreeGeneric(int deg){
        this.root = null;
        this.MinDeg = deg;
    }

    public void traverse(){
        if (root != null){
            root.traverse();
        }
    }
    
    public BNodeGeneric<E> search(E key){
        return root == null ? null : root.search(key);
    }
    
    // public void insert(int key){
    public void add(E value) {
        if (root == null){
            root = new BNodeGeneric<E>(MinDeg,true);
            root.keys.set(0, value);
            root.num = 1;
        }
        else {
            // When the root node is full, the tree will grow high
            if(root.num == 2 * MinDeg - 1){
                BNodeGeneric<E> s = new BNodeGeneric<>(MinDeg,false);
                // The old root node becomes a child of the new root node
                s.children.set(0, root);
                // Separate the old root node and give a key to the new node
                s.splitChild(0, root);
                // The new root node has 2 child nodes. Move the old one over there
                int i = 0;
                if(s.keys.get(0).compareTo(value) < 0)
                    i++;
                s.children.get(i).insertNotFull(value);

                root = s;
            }
            else
                root.insertNotFull(value);
        }
    }
    
    public void remove(E value) {
        if (root == null){
            System.out.println("The tree is empty");
            return;
        }

        root.remove(value);

        if (root.num == 0){ // If the root node has 0 keys
            // If it has a child, its first child is taken as the new root,
            // Otherwise, set the root node to null
            if (root.isLeaf)
                root = null;
            else
                root = root.children.get(0);
        }
    }
    
    public void clear() {
        this.root = null;
    }
    
    public static void main(final String[] args) {
        BTreeGeneric<Integer> t = new BTreeGeneric<Integer>(2); // A B-Tree with minium degree 2
        t.add(1);
        t.add(3);
        t.add(7);
        t.add(10);
        t.add(11);
        t.add(13);
        t.add(14);
        t.add(15);
        t.add(18);
        t.add(16);
        t.add(19);
        t.add(24);
        t.add(25);
        t.add(26);
        t.add(21);
        t.add(4);
        t.add(5);
        t.add(20);
        t.add(22);
        t.add(2);
        t.add(17);
        t.add(12);
        t.add(6);

        System.out.println("Traversal of tree constructed is");
        t.traverse();
        System.out.println();

        t.remove(6);
        System.out.println("Traversal of tree after removing 6");
        t.traverse();
        System.out.println();

        t.remove(13);
        System.out.println("Traversal of tree after removing 13");
        t.traverse();
        System.out.println();

        t.remove(7);
        System.out.println("Traversal of tree after removing 7");
        t.traverse();
        System.out.println();

        t.remove(4);
        System.out.println("Traversal of tree after removing 4");
        t.traverse();
        System.out.println();

        t.remove(2);
        System.out.println("Traversal of tree after removing 2");
        t.traverse();
        System.out.println();

        t.remove(16);
        System.out.println("Traversal of tree after removing 16");
        t.traverse();
        System.out.println();
        
        t.clear();
        t.traverse();
        
    }
}
