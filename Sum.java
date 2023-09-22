public class Sum {
    public static int findSum(int[] nums) {
        int maxSum = nums[0];
        int currentMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            maxSum = Math.max(maxSum, currentMax);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {  1, -2, 3,-8, 5,1 };
        int Sum = findSum(nums);

        System.out.println(maxSum);
    }
}