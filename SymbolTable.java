public interface SymbolTable<Key, Value> {
    void put(Key key, Value value);
    Value get(Key key);
    boolean contains(Key key);
    void zeroComparisons();
    int getComparisons();
}