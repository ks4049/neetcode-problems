class LRUCache {

    Map<Integer, ListNode> keyValueMap;
    ListNode head, tail;
    int capacity;

    class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;
        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }

    }

    public LRUCache(int capacity) {
        this.keyValueMap = new HashMap<>(capacity);
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!keyValueMap.containsKey(key)) {
            return -1;
        } else {
            // detach the key node and add it in the front (head)
            ListNode node = keyValueMap.get(key);
            addNodeAtFront(node);       
            return node.val;
        }
    }
    
    public void put(int key, int value) {
        if(keyValueMap.containsKey(key)) {
            // update the map with the new value, detach and attach it in front
            ListNode node = keyValueMap.get(key);
            node.val = value;
            addNodeAtFront(node);
        } else {
            // check the map size and accordingly handle the new insertion
            if(keyValueMap.size() < capacity) {
                ListNode newNode = new ListNode(key, value);
                keyValueMap.put(key, newNode);
                addNodeAtFront(newNode);
            } else {
                ListNode prevNode = tail.prev;
                // remove the tail at the end
                keyValueMap.remove(tail.key);
                if(prevNode != null) {
                    prevNode.next = null;
                    tail = prevNode;
                } else {
                    // if tail is null then head also will be null
                    head = null;
                    tail = null;
                }
                ListNode newNode = new ListNode(key, value);
                keyValueMap.put(key, newNode);
                addNodeAtFront(newNode);
            }
        }
    }

    private void addNodeAtFront(ListNode node) {
        if(node == head) return;
        ListNode prevNode = node.prev;
        ListNode nextNode = node.next;
        if(node != head) {
            if(node == tail) {
                tail = prevNode;
            }
            if(prevNode != null) {
                prevNode.next = nextNode;
            }
            if(nextNode != null) {
                nextNode.prev = prevNode;
            }
            node.next = head;
            if(head != null) {
                head.prev = node;
            } else {
                tail = node;
            }
            head = node;
        }
    }
}
