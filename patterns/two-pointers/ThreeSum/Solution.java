import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Solution {
  public List<List<Integer>> threeSum(int[] numbers) {

    Arrays.sort(numbers);
    List<List<Integer>> response = new ArrayList<>();

    for (int i = 0; i < numbers.length - 1; i++) {
      int left = i + 1;
      int right = numbers.length - 1;

      if (i > 0 && numbers[i] == numbers[i - 1]) {
        continue;
      }

      while (left < right) {

        int sum = numbers[left] + numbers[i] + numbers[right];
        if (sum == 0) {
          response.add(List.of(numbers[left], numbers[i], numbers[right]));

          if (numbers[left] == numbers[left + 1]) {
            left++;
          }

          if (numbers[right] == numbers[right - 1]) {
            right--;
          }
          left++;
          right--;
        } else if (sum < 0) {
          left++;
        } else if (sum > 0) {
          right--;
        }
      }
    }
    return response;
  }
}
