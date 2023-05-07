import java.util.*;


class MyHashMap {

    static class HashMapiing<K, V> { // generics
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        /*
        Decalring variable for node
         */
        private int p;
        /*
        Declaring variable for bucket
         */
        private int Q;

        /*
        Calculating length of bucket
         */
        private LinkedList<Node> buckets[];

        public HashMapiing() {
            this.Q = 4;
            this.buckets = new LinkedList[4];
            for (int i = 0; i < 4; i++) {
                this.buckets[i] = new LinkedList<>();
            }
        }

        private int hashFunction(K key) {
            int bi = key.hashCode();
            return Math.abs(bi) % Q;
        }

        private int searchInLL(K key, int bi) {
            LinkedList<Node> ll = buckets[bi];

            for (int i = 0; i < ll.size(); i++) {
                if (ll.get(i).key == key) {
                    return i;
                }
            }

            return -1;
        }


        private void rehash() {
            LinkedList<Node> oldBucket[] = buckets;
            buckets = new LinkedList[Q * 2];
            for (int i = 0; i < Q * 2; i++) {
                buckets[i] = new LinkedList<>();
            }
            for (int i = 0; i < oldBucket.length; i++) {
                LinkedList<Node> ll = oldBucket[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node = ll.get(j);
                    put(node.key, node.value);
                }
            }
        }

        public void put(K key, V value) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi); //
            if (di == -1) {
                buckets[bi].add(new Node(key, value));
                p++;
            } else { // key exists
                Node node = buckets[bi].get(di);
                node.value = value;
            }
            double check = (double) p / Q;

            if (check > 2.0) {
                rehash();
            }
        }

        public boolean containsKey(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di == -1) {
                return false;
            } else { // key exists
                return true;
            }
        }

        public V remove(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di == -1) {
                return null;
            } else {
                Node node = buckets[bi].remove(di);
                p--;
                return node.value;
            }
        }

        public V get(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di == -1) {
                return null;
            } else { // key exists
                Node node = buckets[bi].get(di);
                return node.value;
            }
        }

        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();
            for (int i = 0; i < buckets.length; i++) {
                LinkedList<Node> ll = buckets[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node = ll.get(j);
                    keys.add(node.key);
                }
            }
            return keys;
        }

        public boolean isEmpty() {
            return p == 0;
        }
    }
}



/*
 * This is our main class
 */

public class Hashing {

    /*
     * Main method
     */
    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();

        String s = "Paranoids are not paranoid because they are paranoid but because they keep putting themselves deliberately into paranoid avoidable situations";

        String[] str = s.split(" ");

        for (int i = 0; i < str.length; i++) {
            map.put(str[i], map.getOrDefault(str[i], 0) + 1);
        }
        map.remove("avoidable");
        System.out.println(map);


    }

}

