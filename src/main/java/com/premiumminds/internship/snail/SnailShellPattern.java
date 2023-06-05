package com.premiumminds.internship.snail;

import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {

  /**
   * Method to get snailshell pattern
   * 
   * @param matrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public Future<int[]> getSnailShell(int[][] matrix) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<int[]> futureTask = new FutureTask<>(() -> {
            List<Integer> result = new ArrayList<>();
            if (matrix == null || matrix.length == 0) {
                return result.stream().mapToInt(Integer::intValue).toArray();
            }

            int rowBegin = 0;
            int rowEnd = matrix.length - 1;
            int colBegin = 0;
            int colEnd = matrix[0].length - 1;

            while (rowBegin <= rowEnd && colBegin <= colEnd) {
                // Traverse right
                for (int i = colBegin; i <= colEnd; i++) {
                    result.add(matrix[rowBegin][i]);
                }
                rowBegin++;

                // Traverse down
                for (int i = rowBegin; i <= rowEnd; i++) {
                    result.add(matrix[i][colEnd]);
                }
                colEnd--;

                // Traverse left
                if (rowBegin <= rowEnd) {
                    for (int i = colEnd; i >= colBegin; i--) {
                        result.add(matrix[rowEnd][i]);
                    }
                    rowEnd--;
                }

                // Traverse up
                if (colBegin <= colEnd) {
                    for (int i = rowEnd; i >= rowBegin; i--) {
                        result.add(matrix[i][colBegin]);
                    }
                    colBegin++;
                }
            }

            return result.stream().mapToInt(Integer::intValue).toArray();
        });

        executor.execute(futureTask);
        executor.shutdown();

        return futureTask;
    //throw new RuntimeException("Not Implemented Yet");
  };
}
