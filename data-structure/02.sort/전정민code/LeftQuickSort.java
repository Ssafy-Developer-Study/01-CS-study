public class LeftQuickSort {
    
    public static void sort(int[] a) {
        l_pivot_sort(a, 0, a.length - 1);
    }

    private static void l_pivot_sort(int[] a, int lo, int hi) {


        if(lo>=hi) return;
        
        int pivot = partition(a, lo, hi);
        l_pivot_sort(a, lo, pivot-1);
        l_pivot_sort(a, pivot+1, hi);

    }

    private static int partition(int[] a, int left, int right) {
        int lo = left;
        int hi = right;
        int pivot = a[left];

        while(lo < hi) {


            // 피벗보다 작거나 같은 원소 찾을 때까지 hi 감소
            while(a[hi] > pivot && lo < hi) {
                hi--;
            }

            // 피버보다 큰 원소 찾을 때가지 lo 증가
            while(a[lo] <= pivot && lo < hi) {
                lo++;
            }

            swap(a, lo, hi);
        }

        // 맨 처음 pivot으로 설정했던 위치의 원소와 lo가 가리키는 원소를 바꾼다.
        swap(a, lo, left);

        return lo;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    
}
