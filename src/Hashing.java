import java.util.*;


class MyMap {
    /*
     * generic type arguments
     */
    static class HashMap<K, V> {
        private class NodeHash {
            K key;
            V value;

            public NodeHash(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
        /*
         * Declaring variable for node
         */
        private int p;
        /*
         * Declaring variables for buckets
         */
        private int Q;
        /*
         * variable for calculating length of bucket
         */
        private LinkedList<NodeHash> buckets[];

        @SuppressWarnings("unchecked")
        public HashMap() {
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
            LinkedList<NodeHash> ll = buckets[bi];

            for (int i = 0; i < ll.size(); i++) {
                if (ll.get(i).key == key) {
                    return i; // di
                }
            }

            return -1;
        }

        @SuppressWarnings("unchecked")
        private void rehash() {
            LinkedList<NodeHash> oldBucket[] = buckets;
            buckets = new LinkedList[Q * 2];
            for (int i = 0; i < Q * 2; i++) {
                buckets[i] = new LinkedList<>();
            }
            for (int i = 0; i < oldBucket.length; i++) {
                LinkedList<NodeHash> ll = oldBucket[i];
                for (int j = 0; j < ll.size(); j++) {
                    NodeHash node = ll.get(j);
                    put(node.key, node.value);
                }
            }
        }

        public void put(K key, V value) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi); // di = -1
            if (di == -1) { // key doesn't exist
                buckets[bi].add(new NodeHash(key, value));
                p++;
            } else { // key exists
                NodeHash node = buckets[bi].get(di);
                node.value = value;
            }
            double lambda = (double) p / Q;

            if (lambda > 2.0) {
                rehash();
            }
        }

        public boolean containsKey(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi); // di = -1
            if (di == -1) { // key doesn't exist
                return false;
            } else { // key exists
                return true;
            }
        }

        public V remove(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi); // di = -1
            if (di == -1) { // key doesn't exist
                return null;
            } else { // key exists
                NodeHash node = buckets[bi].remove(di);
                p--;
                return node.value;
            }
        }

        public V get(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi); // di = -1
            if (di == -1) { // key doesn't exist
                return null;
            } else { // key exists
                NodeHash node = buckets[bi].get(di);
                return node.value;
            }
        }

        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();
            for (int i = 0; i < buckets.length; i++) { // bi
                LinkedList<NodeHash> ll = buckets[i];
                for (int j = 0; j < ll.size(); j++) { // di
                    NodeHash node = ll.get(j);
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

        String sentence = "to be or not to be";

        String[] str = sentence.split(" ");

        for (int i = 0; i < str.length; i++) {
            map.put(str[i], map.getOrDefault(str[i], 0) + 1);
        }

        System.out.println(map);

    }

}

