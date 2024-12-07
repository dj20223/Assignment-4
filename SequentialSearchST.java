public class SequentialSearchST<Key, Value> {
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public void put(Key key, Val val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, value, first);
    }

    public Value getCost(Key key, SeparateChainingHashST<Key, Value> tracker) {
        for (Node x = first; x != null; x = x.next) {
            tracker.incrementComparisons();
            if (key.equals(x.key)) return x.val;
        }
        return null;
    }
}
