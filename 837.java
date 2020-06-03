public class Solution {
    public double new21Game(int N, int K, int W) {
        double[] dp = new double[K + W];
        int divider = Math.min(K + W - 1, N);
        for(int i = K; i <= divider; i++){
            dp[i] = 1;
        }
        for(int i = divider + 1; i < K + W; i++){
            dp[i] = 0;
        }
        if(K >= 1){
            for(int w = 1; w <= W; w++){
                dp[K - 1] += dp[K - 1 + w];
            }
            dp[K - 1] /= W;
            for(int i = K - 2; i >= 0; i--){
                dp[i] = ((1 + W) * dp[i + 1] - dp[i + W + 1]) / W;
            }
        }
        return dp[0];
    }
}