public class DualPivotQuickSort {
    

    public static void sort(int[] arr) {
        dualPivotQuickSort(arr, 0, arr.length-1);
    }

    private static void dualPivotQuickSort(int[] arr, int lo, int hi) {
        if(lo>=hi) return;
        int[] pivots = partition(arr, lo, hi);
        dualPivotQuickSort(arr, lo, pivots[0]-1);
        dualPivotQuickSort(arr, pivots[0] + 1, pivots[1] - 1);
        dualPivotQuickSort(arr, pivots[1] + 1, hi);
    }

    private static int[] partition(int[] arr, int left, int right) {

        // 피벗들을 배열의 끝으로 보낸다.
        if(arr[left] > arr[right]) 
            swap(arr, left, right);

        int pivot1 = arr[left];
        int pivot2 = arr[right];

        int i = left + 1;
        int lt = left + 1; // 첫번째 피벗보다 작은 요소의 인덱스
        int gt = right - 1; // 두번째 피벗보다 큰 요소의 인덱스

        while(i <= gt) {
            if(arr[i] < pivot1)
                swap(arr, i++, lt++); // 현재 요소가 첫번째 피벗보다 작으면 lt와 i를 증가시키고 lt 위치에 있는 요소와 교환
            else if(arr[i] > pivot2)
                swap(arr, i, gt--); // 현재 요소가 두번째 피벗보다 크면 gt를 감소시키고 현재 요소와 gt 위치에 있는 요소 교환
            else 
                i++;
        }

        swap(arr, left, --lt); // 첫번째 피벗을 그룹 사이의 마지막 작은 요소와 교환
        swap(arr, right, ++gt); // 두번째 피벗을 그룹 사이의 첫번째 큰 요소와 교환

        return new int[]{lt, gt};
        
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
