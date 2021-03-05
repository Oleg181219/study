public class DLNode<T> {
    private final T data;
    DLNode<T> head;
    DLNode<T> tail;

    public DLNode(T data) {
        this.data = data;
    }

    public DLNode<T> getHead() {
        return head;
    }

    public void setHead(DLNode<T> head) {
        this.head = head;
    }

    public DLNode<T> getTail() {
        return tail;
    }

    public void setTail(DLNode<T> tail) {
        this.tail = tail;
    }
}
