class Twitter {
    Map<Integer,List<Tweet>> tweetsByUser;
    int tweetTime;
    Map<Integer, Set<Integer>> followeesByUser;


    public Twitter() {
        tweetsByUser = new HashMap<>();
        tweetTime = 0;
        followeesByUser = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        tweetTime++;
        Tweet newTweet = new Tweet(tweetTime, tweetId);
        List<Tweet> existingTweets = tweetsByUser.getOrDefault(userId, new ArrayList<>());
        existingTweets.add(newTweet);
        tweetsByUser.put(userId, existingTweets);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        // pull the followees of the user & the tweets for all of those users including self
        PriorityQueue<Tweet> newsFeed = new PriorityQueue<>((t1,t2)->Integer.compare(t1.tweetTime, t2.tweetTime));
        Set<Integer> users = followeesByUser.getOrDefault(userId, new HashSet<>());
        users.add(userId);
        for(int uid: users) {
            List<Tweet> userTweets = tweetsByUser.getOrDefault(uid, new ArrayList<>());
            for(int i=0; i<userTweets.size(); i++) {
                if(newsFeed.size() < 10) {
                    newsFeed.add(userTweets.get(i));
                } else {
                    // maintain the top ten tweets in queue
                    if(userTweets.get(i).tweetTime > newsFeed.peek().tweetTime) {
                        List<Tweet> tempTweets = new ArrayList<Tweet>();
                        while(!newsFeed.isEmpty() && newsFeed.peek().tweetTime <= userTweets.get(i).tweetTime) {
                            tempTweets.add(newsFeed.poll());
                        }
                        newsFeed.add(userTweets.get(i));
                        int index=tempTweets.size()-1;
                        while(index>=0 && newsFeed.size()<10) {
                            newsFeed.add(tempTweets.get(index));
                            index--;
                        }
                    }
                    // else if the tweetTime is lesser than or equal to the peek time then skip it
                    
                }
            }
        }
        List<Integer> resultList = new ArrayList<Integer>(10);
        while(!newsFeed.isEmpty()) {
            resultList.add(newsFeed.poll().tweetId);
        }
        Collections.reverse(resultList);
        return resultList;
    }
    
    public void follow(int followerId, int followeeId) {
        Set<Integer> followees = followeesByUser.getOrDefault(followerId, new HashSet<>());
        followees.add(followeeId);
        followeesByUser.put(followerId, followees);
    }
    
    public void unfollow(int followerId, int followeeId) {
        // update the followees
        Set<Integer> followees = followeesByUser.getOrDefault(followerId, new HashSet<>());
        if(followees.contains(followeeId)) {
            followees.remove(followeeId);
            followeesByUser.put(followerId, followees); // remove and update hashmap
        }
    }
    public class Tweet {
        int tweetTime;
        int tweetId;
        public Tweet(int tweetTime, int tweetId) {
            this.tweetTime = tweetTime;
            this.tweetId = tweetId;
        }
    }
}
