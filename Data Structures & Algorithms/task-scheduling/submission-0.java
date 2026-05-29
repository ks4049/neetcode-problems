/*
 *  /*
      A=3, B=1, C=1
      exclusion= {}

      Next task? A
      exclusion(A->0)

      A=2, B=1, C=1  
      Next task? B
      exclusion(A->1)

      A=2,C=1
      Next task? C
      exclusion(A->2)

      A=2
      Next task? no tasks idle
      exclusion {}

      Next task? A
      exclusion: {A=0}

      A=1
      idle
      idle
      idle
      A
 * 
 * */
class Solution {
    public int leastInterval(char[] tasks, int n) {
      
      PriorityQueue<Task> priorityTasks = new PriorityQueue<Task>((t1,t2) -> Integer.compare(t2.frequency, t1.frequency));
      Map<Character,Task> taskMap = new HashMap<>();
      for(int i=0; i<tasks.length; i++) {
        Task currentTask = taskMap.getOrDefault(tasks[i], new Task(0, tasks[i]));
        currentTask.frequency+=1;
        taskMap.put(tasks[i], currentTask);
      }
      for(char ch: taskMap.keySet()) {
        priorityTasks.add(taskMap.get(ch));
      }
      //System.out.println(priorityTasks.peek().task);
      // start scheduling the tasks
      Map<Character,Integer> exclusionMap = new HashMap<>();
      //[A=3,B=1,C=1]
      int totalCpu = 0;
      while(!priorityTasks.isEmpty()) {
        List<Task> tempTasks = new ArrayList<>();
        if(exclusionMap.containsKey(priorityTasks.peek().task)) {
          // chosen the next task available if none available then count for idle time + update exclusion list
          while(!priorityTasks.isEmpty() && exclusionMap.containsKey(priorityTasks.peek().task)) {
            tempTasks.add(priorityTasks.poll()); // load into temp for those which are found in exclusion
          }
        }
        // if not empty then pick one from the queue
        if(!priorityTasks.isEmpty()) {
          // new task scheduled(not part of the exclusion list)
          Task taskChosen = priorityTasks.poll();
          taskChosen.frequency-=1; //reduce the frequency and add it back
          if(taskChosen.frequency>0) {
            priorityTasks.add(taskChosen);
            exclusionMap.put(taskChosen.task, -1); // A->-1
          }
          totalCpu++; //1
        } else {
          totalCpu++; // account for idle time if no task available to pick
        }
        if(!tempTasks.isEmpty()) {
          // add all temptasks back and update exclusion map to increase the cycle time
          for(int i=tempTasks.size()-1; i>=0; i--) {
            priorityTasks.add(tempTasks.get(i));
          }
        }
        updateExclusionMapToIncreaseCycleTime(exclusionMap, n);        
      }
      return totalCpu;
    }
  
  	private void updateExclusionMapToIncreaseCycleTime(Map<Character, Integer> exclusionMap, int n) {
      Set<Character> charSet = exclusionMap.keySet();
      List<Character> charToBeRemoved = new ArrayList<>();
      for(char ch: charSet) {
        if(exclusionMap.containsKey(ch)) {
            int currentCycleTime = exclusionMap.get(ch);
            if(currentCycleTime+1 == n) {
                charToBeRemoved.add(ch);
            }
            exclusionMap.put(ch, currentCycleTime+1);
        }
      }
      for(char ch: charToBeRemoved) {
        exclusionMap.remove(ch);
      }
    }

    
  
	public class Task {
      int frequency;
      char task;
      public Task(int frequency, char task) {
        this.frequency = frequency;
        this.task = task;
      }
    }  
  
}
