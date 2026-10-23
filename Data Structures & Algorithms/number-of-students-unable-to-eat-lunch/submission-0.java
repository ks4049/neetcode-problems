class Solution {
    class Student  {
        int id;
        int preferredSandwich;

        public Student(int id, int preferredSandwich) {
            this.id = id;
            this.preferredSandwich = preferredSandwich;
        }

    }
    public int countStudents(int[] students, int[] sandwiches) {
        Map<Integer, Student> studentsUnableToEat = new HashMap<>();

        Stack<Integer> sandwichStack = prepareStack(sandwiches);
        Queue<Student> studentQueue = prepareQueue(students);

        while(!sandwichStack.isEmpty() && !studentQueue.isEmpty()) {
            if(sandwichStack.peek() == studentQueue.peek().preferredSandwich) {
                if(studentsUnableToEat.containsKey(studentQueue.peek().id)) {
                    studentsUnableToEat.remove(studentQueue.peek().id);
                }
                sandwichStack.pop();
                studentQueue.poll();
            } else {
                if(studentsUnableToEat.containsKey(studentQueue.peek().id) && noneOfStudentsMatchTop(studentsUnableToEat, sandwichStack.peek())) {
                    break;
                }
                studentsUnableToEat.put(studentQueue.peek().id, studentQueue.peek());
                studentQueue.add(studentQueue.poll());
            }
        }
        return studentsUnableToEat.size();
    }

    private boolean noneOfStudentsMatchTop(Map<Integer, Student> studentMap, int preferredSandwich) {
        for(int id: studentMap.keySet()) {
            if(studentMap.get(id).preferredSandwich == preferredSandwich) {
                return false;
            }
        }
        return true;
    }


    private Stack<Integer> prepareStack(int[] sandwiches) {
        Stack<Integer> sandwichStack = new Stack<Integer>();

        for(int i=sandwiches.length-1; i>=0; i--) {
            sandwichStack.push(sandwiches[i]);
        }
        return sandwichStack;
    }

    private Queue<Student> prepareQueue(int[] students) {
        Queue<Student> studentQueue = new LinkedList<Student>();
        for(int i=0; i<students.length; i++) {
            Student curr = new Student(i, students[i]);
            studentQueue.add(curr);
        }
        return studentQueue;
    }
}