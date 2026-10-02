

public class Solution {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int s1 = height[left];
            int s2 = height[right];
            int width = right - left;
            int minS = min(s1, s2);
            int area = minS * width;
            if (area > maxArea) {
                maxArea = area;
            }
            if (s1 < s2) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public int min(int n1, int n2) {
        if(n1 < n2) {
            return n1;
        }
        return n2;
    }
}
