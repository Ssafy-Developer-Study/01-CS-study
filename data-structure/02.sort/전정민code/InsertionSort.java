
public class InsertionSort {
    
    public static void insertion_sort(int[] a) {
        insertion_sort(a, a.length);
    }

    private static void insertion_sort(int[] a, int size) {

        for(int i=1; i<size; i++) {

            int target = a[i];

            int j = i-1;

            // 타겟이 이전 원소보다 크기 전까지 반복
            while(j>=0 && target < a[j]) {
                a[j + 1] = a[j]; // 이전 원소를 한 칸씩 뒤로 미룬다.
                j--;
            }


            /*
             * 위 반복문에서 탈출하는 경우 앞의 원소가 타켓보다 작다는 의미이므로
             * 타겟 원소는 j번째 원소 뒤에 와야한다.
             */
            a[j + 1] = target;

        }
    }
}
