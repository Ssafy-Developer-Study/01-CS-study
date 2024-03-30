package 연결리스트;

import java.util.NoSuchElementException;

public class SLinkedList<E> implements List<E>{
    
    private Node<E> head;   // 노드의 첫부분
    private Node<E> tail;   // 노드의 마지막 부분
    private int size;       // 요소 개수

    public SLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    // 특정 위치의 노드를 반환하는 메소드
    private Node<E> search(int index) {

        if(index<0 || index>=size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> x = head;

        for(int i=0; i<index; i++) {
            x = x.next;
        }
        return x;
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<E>(value);
        newNode.next = head;
        head = newNode;
        size++;

        // 다음에 가리킬 노드가 없는 경우
        if(head.next == null) {
            tail = head;
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value);

        if(size == 0) {     // 처음 넣는 노드일 경우
            addFirst(value);
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E value) {

       if(index<0 || index> size) {
            throw new IndexOutOfBoundsException();
       }

       if(index == 0) {
            addFirst(value);
            return;
       }

       if(index == size) {
            addLast(value);
            return;
       }

       // 추가하려는 위치 이전의 노드
       Node<E> prev_Node = search(index - 1);

       Node<E> next_Node = prev_Node.next;

       Node<E> newNode = new Node<E>(value);

       prev_Node.next = null;
       prev_Node.next = newNode;
       newNode.next = next_Node;
       size++;

    }

    public E remove() {

        Node<E> headNode = head;

        if(headNode == null) {
            throw new NoSuchElementException();
        }

        // 삭제된 노드를 반환하기 위함
        E element = headNode.data;

        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        if(size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {
        if(index == 0) {
            remove();
        }

        if(index <0 || index >size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> prevNode = search(index - 1);
        Node<E> removeNode = prevNode.next;
        Node<E> nextNode = removeNode.next;

        E element = removeNode.data;

        prevNode.next = nextNode;

        // 삭제한 노드가 마지막 노드라면
        if(prevNode.next == null) {
            tail = prevNode;
        }

        removeNode.next = null;
        removeNode.data = null;
        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {
        
        Node<E> prevNode = head;
        boolean hasValue = false;
        Node<E> x = head;

        for(; x!= null; x=x.next) {
            if(value.equals(x.data)) {
                hasValue = true;
                break;
            }
            prevNode = x;
        }

        // 일치하는 요소가 없음
        if(x==null) {
            return false;
        }

        // 삭제하려는 노드가 head라면 기존 remove() 사용
        if(x.equals(head)) {
            remove();
            return true;
        } else {

            prevNode.next = x.next;

            if(prevNode.next == null) {
                tail = prevNode;
            }
            x.data = null;
            x.next = null;
            size--;
            return true;

        }
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        Node<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = value;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;

        for(Node<E> x= head; x!=null; x=x.next) {
            if(value.equals(x.data)){
                return index;
            }
            index++;
        }

        return -1;
    }


    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int size() {
        
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for(Node<E> x=head; x!=null;) {
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x = nextNode;
        }
        head = tail = null;
        size = 0;
    }
}
