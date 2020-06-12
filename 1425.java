import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public void push(Deque<Integer> q, int x){
        while(q.isEmpty() == false){
            int tmp = q.getLast();
            if(tmp < x){
                q.pollLast();
            }
            else{
                break;
            }
        }
        q.addLast(x);
    }

    public void pop(Deque<Integer> q, int x){
        if(q.isEmpty() == false){
            if(q.getFirst() == x){
                q.pollFirst();
            }
        }
    }

    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        int[] dp = new int[n];
        if(k > n - 1){
            k = n - 1;
        }
        Deque<Integer> q = new LinkedList<>();
        dp[0] = nums[0];
        push(q, nums[0]);
        for(int i = 1; i < n; i++){
            dp[i] = Math.max(0, q.getFirst()) + nums[i];
            System.out.println(dp[i]);
            if(i >= k){
                pop(q, dp[i - k]);
            }
            push(q, dp[i]);
        }
        int ans = dp[0];
        for(int i = 1; i < n; i++){
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,2,-10,5,20};
        System.out.println(new Solution().constrainedSubsetSum(nums, 2));
    }
}