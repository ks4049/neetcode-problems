class LRUCache {
    int capacity=0;
    Map<Integer,ListNode> nodeMap = new HashMap<>();
    ListNode head, lastNode;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = null;
    }
    
    public int get(int key) {
        ListNode fetchedNode = nodeMap.getOrDefault(key, null);
        if(fetchedNode == null) {
            return -1;
        }
        ListNode prevNode = fetchedNode.prev;
        ListNode nextNode = fetchedNode.next;

        if(prevNode != null) {
            prevNode.next = nextNode;
            // update the lastNode as well
            if(lastNode == fetchedNode) {
               lastNode = prevNode;
            }
        }
        if(nextNode != null) {
            nextNode.prev = prevNode;
        }
        
        
        if(head != fetchedNode) {
            fetchedNode.next = head; //move to front
            head.prev = fetchedNode;
        } 
        head = fetchedNode;
        return fetchedNode.val;
    }
    
    public void put(int key, int value) {
        if(nodeMap.containsKey(key)) {
            get(key); // move it to front
            nodeMap.get(key).val = value; // update the value
        } else {
             // check the capacity
            if(nodeMap.size() >= capacity) {
                // if exceeding capacity, then remove the least frequently used element
                ListNode prevLastNode = lastNode == null? null: lastNode.prev;
                if(lastNode != null && nodeMap.containsKey(lastNode.key)) {
                    nodeMap.remove(lastNode.key);
                }
                if(prevLastNode != null) {
                    prevLastNode.next = null;
                }
                lastNode = prevLastNode;      
            }
            //then add the element to the hashmap and in front
            ListNode newNode = new ListNode(key, value);
            nodeMap.put(key, newNode);
            newNode.next = head;
            if(head == null) {
                lastNode = newNode;
            } else {
                head.prev = newNode;
            }
            head = newNode;
        }
       
    }

    static class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }
}
