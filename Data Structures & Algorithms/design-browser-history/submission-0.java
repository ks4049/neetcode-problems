class BrowserHistory {
    Page head, currentPage;
    class Page {
        String url;
        Page next;
        Page prev;
        public Page(String url) {
            this.url = url;
            this.next = null;
            this.prev = null;
        }
    }

    public BrowserHistory(String homepage) {
        this.head = new Page(homepage);
        this.currentPage = head;
    }
    
    public void visit(String url) {
        Page newPage = new Page(url);
        if(currentPage != null) {
            currentPage.next = newPage;
            newPage.prev = currentPage;
        } else {
            head = newPage;
        }
        currentPage = newPage;
    }
    
    public String back(int steps) {
        Page temp = currentPage;
        int i=0;
        while(temp != null && i<steps) {
            currentPage = temp;
            temp = temp.prev;
            i++;
        }
        if(temp!= null) {
            currentPage = temp;
        }
        return currentPage!=null? currentPage.url: null;
    }
    
    public String forward(int steps) {
        Page temp = currentPage;
        int i=0;
        while(temp != null && i<steps) {
            currentPage = temp;
            temp = temp.next;
            i++;
        }
         if(temp!= null) {
            currentPage = temp;
        }
        return currentPage!=null? currentPage.url: null;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */