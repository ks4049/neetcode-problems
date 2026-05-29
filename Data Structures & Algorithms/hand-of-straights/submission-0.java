/*
 * You are given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize.

You want to rearrange the cards into groups so that each group is of size groupSize, and card values are consecutively increasing by 1.

Return true if it's possible to rearrange the cards in this way, otherwise, return false.
Input: hand = [1,2,4,2,3,5,3,4], groupSize = 4
1,2,2,3,3,4,4,5
1 -> 0
2 -> 1,2,
3 -> 3,4
4 -> 5,6
5 -> 7



*/
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(hand.length%groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);// sort the input array to pick elements in order
        Map<Integer,List<Integer>> handMap = computeHandMap(hand);
        Set<Integer> visitedIndices = new HashSet<>();
        int i=0;
        while(visitedIndices.size() < hand.length) {
        int startIndex = chooseStartElementForNextGroup(hand, visitedIndices);
        if(startIndex == -1) {
            return false;
        }
        System.out.println("before "+handMap);
        boolean formedGroup = formGroupWithStartElement(startIndex, handMap, visitedIndices, groupSize, hand);
        System.out.println("after "+handMap + "group formed "+ formedGroup);
        if(!formedGroup) {
            return false;
        }
        }
        return true;
    }

    Map<Integer,List<Integer>> computeHandMap(int[] hand) {
        Map<Integer,List<Integer>> handMap = new HashMap<Integer,List<Integer>>();
        for(int i=0; i<hand.length; i++) {
        int element = hand[i];
        List<Integer> indices = handMap.getOrDefault(element, new ArrayList<>());
        indices.add(i);
        handMap.put(element, indices);
        }
        return handMap;
    }

    boolean formGroupWithStartElement(int startIndex, Map<Integer,List<Integer>> handMap, Set<Integer> visitedIndices, int groupSize, int[] hand) {
        int i=0, currentIndex=startIndex;
        while(i<groupSize) {
            visitedIndices.add(currentIndex); // add to visited
            
            int element = hand[currentIndex];
            handMap.get(element).remove((Integer)currentIndex);// remove from the map
            if(handMap.get(element).isEmpty()) {
                handMap.remove(element);
            }
            if(i!= groupSize-1) {
                if(!handMap.containsKey(element+1) || handMap.get(element+1).isEmpty()) {
                    break;
                }
                currentIndex = handMap.get(element+1).get(0);
            }
            i++;
        }

        return i==groupSize;
        
    }



    int chooseStartElementForNextGroup(int[] hand, Set<Integer> visitedIndices) {
        int chosenIndex=-1;
        for(int i=0; i<hand.length; i++) {
        if(!visitedIndices.contains(i)) {
            chosenIndex=i;
            break;
        }
        }
        return chosenIndex;
    }

    }
