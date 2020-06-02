// https://leetcode-cn.com/problems/boolean-evaluation-lcci/
public class Solution {
    public int countEval(String s, int result) {
        int digits = s.length() / 2 + 1;
        int[][][] dp = new int[digits][digits][2];
        for(int i = 0; i < digits; i++){
            if(s.charAt(2 * i) == '1'){
                dp[i][i][0] = 0;
                dp[i][i][1] = 1;
            }
            else{
                dp[i][i][0] = 1;
                dp[i][i][1] = 0;
            }
        }
        for(int length = 2; length <= digits; length++){
            for(int left = 0; left <= digits - length; left++){
                int right = left + length - 1;
                dp[left][right][0] = dp[left][right][1] = 0;
                for(int mid = left; mid < right; mid++){
                    int m0 = dp[left][mid][0], m1 = dp[left][mid][1];
                    int n0 = dp[mid + 1][right][0], n1 = dp[mid + 1][right][1];
                    switch (s.charAt(2 * mid + 1)){
                        case('&'):
                            dp[left][right][0] += (m0 * n0 + m0 * n1 + m1 * n0);
                            dp[left][right][1] += m1 * n1;
                            break;
                        case('|'):
                            dp[left][right][0] += m0 * n0 ;
                            dp[left][right][1] += (m1 * n0 + m0 * n1 + m1 * n1);
                            break;
                        case('^'):
                            dp[left][right][0] += (m0 * n0 + m1 * n1);
                            dp[left][right][1] += (m0 * n1 + m1 * n0);
                            break;
                    }
                }
            }
        }
        if(result == 0){
            return dp[0][digits - 1][0];
        }
        else{
            return dp[0][digits - 1][1];
        }
    }
}