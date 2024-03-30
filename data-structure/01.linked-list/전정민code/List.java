package 연결리스트;

public interface List<E> {

    /*
     * 리스트에 요소를 추가
     * 
     * 리스트에서 중복을 허용하지 않을 경우에 이미 중복되는 요소가 있으면 false 반환
     */
    boolean add(E value);

    void add(int index, E value);

    E remove(int index);

    /*
     * 리스트에서 특정 요소 삭제
     * 동일한 요소가 여러 개일 경우 가장 처음 발견한 요소만 삭제
     * 
     * 삭제에 성공했을 때만 true
     */
    boolean remove(Object value);

    E get(int index);

    void set(int index, E value);

    boolean contains(Object value);

    /*
     * 리스트에 특정 요소가 몇 번째 위치에 있는지를 반환한다.
     * 
     * 리스트에서 처음으로 요소와 일치하는 위치를 반환
     */
    int indexOf(Object value);

    int size();
    
    boolean isEmpty();

    void clear();
    
}