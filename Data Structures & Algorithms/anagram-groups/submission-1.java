class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramGroups = new HashMap<String, List<String>>();

        for(int i=0; i<strs.length; i++) {
            String frequencyStr = computeFrequencyStr(strs[i]);
            if(anagramGroups.containsKey(frequencyStr)) {
                anagramGroups.get(frequencyStr).add(strs[i]);
            } else {
                List<String> newGroupList = new ArrayList<>();
                newGroupList.add(strs[i]);
                anagramGroups.put(frequencyStr, newGroupList);
            }
        }
        return new ArrayList<>(anagramGroups.values());

    }

    private String computeFrequencyStr(String input) {
        int[] frequency = new int[26];
        for(int i=0; i<input.length(); i++) {
            int index = input.charAt(i)-'a';
            frequency[index]++;
        }
        StringBuilder frequencyStr = new StringBuilder();
        for(int i=0; i<26; i++) {
            frequencyStr.append(frequency[i]);
            if(i != 25) {
                frequencyStr.append(",");
            }
        }
        return frequencyStr.toString();
    }
}
