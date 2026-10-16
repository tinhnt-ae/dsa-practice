import util.TestRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SolutionTest {
  public static void main(String[] args) {
    Solution solution = new Solution();

    TestRunner.check(
        normalize(solution.threeSum(new int[] { -1, 0, 1, 2, -1, -4 })),
        normalize(List.of(List.of(-1, -1, 2), List.of(-1, 0, 1))),
        "finds every unique triplet");
    TestRunner.check(
        normalize(solution.threeSum(new int[] { 0, 1, 1 })),
        List.of(),
        "returns no triplets when no sum is zero");
    TestRunner.check(
        normalize(solution.threeSum(new int[] { 0, 0, 0 })),
        List.of(List.of(0, 0, 0)),
        "returns one triplet for three zeros");
    TestRunner.check(
        normalize(solution.threeSum(new int[] { -2, 0, 0, 2, 2 })),
        List.of(List.of(-2, 0, 2)),
        "does not return duplicate triplets");

    TestRunner.check(
        normalize(solution.threeSum(new int[] { -10, 1, 2, 3, 8 })),
        List.of(List.of(-10, 2, 8)),
        "");

    TestRunner.summary();
  }

  private static List<List<Integer>> normalize(List<List<Integer>> triplets) {
    List<List<Integer>> normalized = new ArrayList<>();
    for (List<Integer> triplet : triplets) {
      List<Integer> sortedTriplet = new ArrayList<>(triplet);
      sortedTriplet.sort(Integer::compareTo);
      normalized.add(sortedTriplet);
    }
    normalized.sort(Comparator
        .comparing((List<Integer> triplet) -> triplet.get(0))
        .thenComparing(triplet -> triplet.get(1))
        .thenComparing(triplet -> triplet.get(2)));
    return normalized;
  }
}
