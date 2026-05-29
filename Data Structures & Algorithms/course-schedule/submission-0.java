/*
 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.

The pair [0, 1], indicates that must take course 1 before taking course 0.

There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

Return true if it is possible to finish all courses, otherwise return false.
 * 
 * 
 * 
 * */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length==0) {
          return true;
        }
      	Map<Integer,List<Integer>> prerequisitesMap = new HashMap<>();
      	for(int i=0; i<prerequisites.length; i++) {
          List<Integer> existing = prerequisitesMap.getOrDefault(prerequisites[i][0], new ArrayList<>());
          existing.add(prerequisites[i][1]);
          prerequisitesMap.put(prerequisites[i][0], existing);
        }
        System.out.println("PReq map" + prerequisitesMap);
      	Set<Integer> globalNodes = new HashSet<>();
      	for(int i=0; i<numCourses; i++) {
          if(!globalNodes.contains(i)) {
            if(!canFinishUtil(i, prerequisitesMap, new HashSet<Integer>(), globalNodes)) {
                return false;
            }
          }
        }
      	return true;
    }
  	
  	private boolean canFinishUtil(int courseNum, Map<Integer,List<Integer>> prerequisiteMap, Set<Integer> visitedNodes, Set<Integer> globalNodes) {
      if(visitedNodes.contains(courseNum)) {
        return false;
      }
      visitedNodes.add(courseNum);
      globalNodes.add(courseNum);
      List<Integer> prerequisites = prerequisiteMap.getOrDefault(courseNum, new ArrayList<>());
      boolean canFinishPreq = true;
      for(int i=0; i<prerequisites.size(); i++) {
        if(!canFinishUtil(prerequisites.get(i), prerequisiteMap, visitedNodes, globalNodes)) {
          canFinishPreq = false;
          break;
        } else {
          canFinishPreq = true;
        } 
      }
      visitedNodes.remove(courseNum);
      return canFinishPreq;
      
    }
  
  
}
