
public class BubbleSort {
    
    public static void bubble_sort(int[] a) {
        bubble_sort(a, a.length);
    }

    private static void bubble_sort(int[] a, int size) {

        // 라운드
        for(int i=1; i<size; i++) {

            // 각 라운드별 비교횟수는 배열 크기에 현재 라운드를 뺀만큼
            // 한 라운드 진행할 때마다 가장 큰 수가 가장 오른쪽에 쌓임(오름차순 기준)
            for(int j=0; j<size-i; j++) {

                if(a[j] > a[j+1]) {
                    swap(a, j, j+1);
                }
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
