package 연결리스트;

public class Node<E> {
    
    E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
        this.next = null;
    }
}
