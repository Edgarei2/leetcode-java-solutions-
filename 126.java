
import java.util.*;

public class Solution {
    private List<List<String>> ans = new ArrayList<>();
    private HashSet<String> wordSet;
    private HashMap<String, Set<String>> successors = new HashMap<>();
    private LinkedList<String> list = new LinkedList<>();
    public boolean bfs(String beginWord, String endWord){
        boolean found = false;
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);
        Queue<String> q = new LinkedList<>();
        HashSet<String> next = new HashSet<>();
        q.offer(beginWord);
        while(q.isEmpty() == false){
            int size = q.size();
            for(int i = 0; i < size; i++){
                String curStr = q.poll();
                int len = curStr.length();
                char[] tmp = curStr.toCharArray();
                for(int j = 0; j < len; j++){
                    char oriChar = tmp[j];
                    for(char c = 'a'; c <= 'z'; c++){
                        if(c == oriChar){
                            continue;
                        }
                        tmp[j] = c;
                        String nxtStr = new String(tmp);
                        if(wordSet.contains(nxtStr) == true){
                            if(visited.contains(nxtStr) == false){
                                if(nxtStr.equals(endWord)){
                                    found = true;
                                }
                                q.offer(nxtStr);
                                if (successors.containsKey(curStr)) {
                                    successors.get(curStr).add(nxtStr);
                                } else {
                                    Set<String> newSet = new HashSet<>();
                                    newSet.add(nxtStr);
                                    successors.put(curStr, newSet);
                                }
                                next.add(nxtStr);
                            }
                        }
                    }
                    tmp[j] = oriChar;
                }
            }
            if(found == true){
                break;
            }
            visited.addAll(next);
            next.clear();
        }
        return found;
    }

    public void dfs(String curWord, String endWord){
        if(curWord.equals(endWord)){
            ans.add(new LinkedList<>(list));
            return;
        }
        if(successors.containsKey(curWord) == false){
            return;
        }
        Set<String> s = successors.get(curWord);
        for(String str: s){
            list.addLast(str);
            dfs(str, endWord);
            list.removeLast();
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        wordSet = new HashSet<>(wordList);
        if(wordSet.contains(endWord) == false || wordList.size() == 0){
            return ans;
        }
        boolean found = false;
        found = bfs(beginWord, endWord);
        if(found == false){
            return ans;
        }
        list.addLast(beginWord);
        dfs(beginWord, endWord);
        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"hot","dog","dot"};
        List<String> wordList = new ArrayList<>();
        Collections.addAll(wordList, words);

        Solution solution = new Solution();
        String beginWord = "hot";
        String endWord = "dog";
        List<List<String>> res = solution.findLadders(beginWord, endWord, wordList);
        System.out.println(res);
    }
}