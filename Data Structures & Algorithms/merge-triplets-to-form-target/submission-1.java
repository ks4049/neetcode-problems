class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        /*
        - Check if the numbers exists in the respective index?
        - If triplets already contains the target?
        - Take a start triplet
        - Compare it with the consecutive ones and decide if you need to merge or take only one of them and proceed with next
        */
        if(targetExistsAlready(triplets, target)) {
            return true;
        }
        if(!numbersExistsInTheirPostion(triplets, target)) {
            return false;
        }
        int[] initial = triplets[0];
        boolean containsTarget = false;
        for(int i=1; i<triplets.length; i++) {
            if(isValid(initial, target) || isValid(triplets[i], target)) {
                int[] mergedOrSingle = compareTripletsAndMergeIfNeeded(initial, i, triplets, target);
                if(areTripletsSame(mergedOrSingle, target)){
                    containsTarget = true;
                    break;
                }
                initial = mergedOrSingle;
            } else {
                // move initial to the i
                if(i!= triplets.length-1) {
                    initial = triplets[i+1];
                    i++;
                }
            }
        }
        return containsTarget;
    }

    int[] compareTripletsAndMergeIfNeeded(int[] triplet1, int index, int[][] triplets, int[] target) {
        int[] triplet2 = triplets[index];
        boolean isTriplet1Valid = isValid(triplet1, target);
        boolean isTriplet2Valid = isValid(triplet2, target);
        if(isTriplet1Valid && isTriplet2Valid) {
            int matchingCount1= findMatchingCount(triplet1, target); 
            int matchingCount2= findMatchingCount(triplet2, target);
            int[] mergedTriplet = merge(triplet1, triplet2);
            int matchingCount3 = findMatchingCount(mergedTriplet, target);
            if(matchingCount3 >= Math.max(matchingCount1, matchingCount2)) {
                triplets[index][0] = mergedTriplet[0]; triplets[index][1] = mergedTriplet[1]; triplets[index][2] = mergedTriplet[2];
                return triplets[index];
            } else {
                if(matchingCount1 > matchingCount2) {
                    return triplet1;
                } else{
                    return triplet2;
                }
            }

        } else if(isTriplet1Valid) {
            return triplet1;
        } else {
            return triplet2;
        }
    }

    int[] merge(int[] triplet1, int[] triplet2) {
        return new int[]{Math.max(triplet1[0], triplet2[0]), Math.max(triplet1[1], triplet2[1]), Math.max(triplet1[2], triplet2[2])};
    }

    int findMatchingCount(int[] triplet, int[] target) {
        int matchingCount=0;
        for(int i=0; i<3; i++) {
            if(triplet[i] == target[i]) {
                matchingCount++;
            }
        }
        return matchingCount;
    }

    boolean isValid(int[] triplet, int[] target) {
        return triplet[0]<=target[0] && triplet[1]<=target[1] && triplet[2]<=target[2];
    }

    boolean targetExistsAlready(int[][] triplets, int[] target) {
        boolean foundTarget=false;
        for(int i=0; i<triplets.length; i++) {
            if(areTripletsSame(triplets[i], target)) {
                foundTarget = true;
                break;
            }
        }
        return foundTarget;
    }

    boolean numbersExistsInTheirPostion(int[][] triplets, int[] target) {
        int[] matchingPositions = new int[]{0,0,0};
        for(int i=0; i<triplets.length; i++) {
            if(triplets[i][0] == target[0]){
                matchingPositions[0]++;
            }
            if(triplets[i][1] == target[1]){
                matchingPositions[1]++;
            }
            if(triplets[i][2] == target[2]){
                matchingPositions[2]++;
            }
        }
        return matchingPositions[0]!=0 && matchingPositions[1]!=0 && matchingPositions[2]!=0;
    }

    boolean areTripletsSame(int[] triplet1, int[] triplet2) {
        return triplet1[0]==triplet2[0] && triplet1[1]==triplet2[1] && triplet1[2]==triplet2[2];
    }
}
