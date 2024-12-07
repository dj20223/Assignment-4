public class LinearProbingHashST<Key, Value> implements HashTable<Key, Value> {
    private int M; // table size
    private int N; // number of key-value pairs
    private Key[] keys;
    private Value[] val;
    private int comps;

    public LinearProbingHashST(int capacity) {
        M = capacity;
        keys = (Key[]) new Object[M];
        val = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }


    public Value get(Key key) {
        comps = 0;
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            comps++;
            if (keys[i].equals(key)){
                return val[i];
            }
        }
        return null;
    }


    public void put(Key key, Value value) {
        if (N >= M / 2) resize(2 * M);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                val[i] = value;
                return;
            }
        }
        keys[i] = key;
        val[i] = value;
        N++;
    }



    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int getComps() {
        return comps;
    }

    public void resetComps() {
        comps = 0;
    }
    

    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], val[i]);
            }
        }
        val = temp.val;
        keys = temp.keys;
        M = temp.M;
    }
}
