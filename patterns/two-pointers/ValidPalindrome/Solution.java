import java.lang.Character;

public class Solution {
    public boolean isPalindrome(String value) {
        // Two pointers approach
        int left = 0;
        int right = value.length() - 1;
        while(left < right) {
            while(left < right && !Character.isLetterOrDigit(value.charAt(left))) {
                left++;
            }
            while(left < right && !Character.isLetterOrDigit(value.charAt(right))) {
                right--;
            }
            if(Character.toLowerCase(value.charAt(left)) != Character.toLowerCase(value.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
