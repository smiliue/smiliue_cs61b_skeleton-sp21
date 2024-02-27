package deque;


public class LinkedListDeque<T> implements Deque<T>{

    public static class Node<T>{
        T item;
        Node<T> l;
        Node<T> r;

        public Node(T item, Node<T> l, Node<T> r)
        {
            this.item = item;
            this.l = l;
            this.r = r;
        }

        public Node(T item)
        {
            this.item = item;
        }
    }

    /*
        双向循环列表，有头结点
     */
    private final Node<T> head;
    private int size;

    public Node<T> getHead()
    {
        return this.head;
    }

    public LinkedListDeque(T item)
    {
        head = new Node<T>(item);
        head.l = head;
        head.r = head;

        size = 0;
    }
    public LinkedListDeque()
    {
        head = new Node<T>((T) new Object());
        head.l = head;
        head.r = head;

        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node<T> p = new Node<>(item);

        p.l = head;
        p.r = head.r;
        p.l.r = p;
        p.r.l = p;

        this.size += 1;
    }

    @Override
    public void addLast(T item) {
        Node<T> p = new Node<>(item);

        p.l = head.l;
        p.r = head;
        p.l.r = p;
        p.r.l = p;

        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for(Node<T> cur=head.r; cur!=head; cur=cur.r)
        {
            System.out.print(cur.item+" ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (this.size == 0)
            return null;
        Node<T> first = head.r;
        first.l.r = first.r;
        first.r.l = first.l;

        size -= 1;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (this.size == 0)
            return null;
        Node<T> last = head.l;
        last.l.r = last.r;
        last.r.l = last.l;

        size -= 1;
        return last.item;
    }

    @Override
    public T get(int index) {
        if (index >= this.size)
            return null;
        Node<T> p = head;
        for(int i=0; i<=index; i++)
        {
            p = p.r;
        }
        return p.item;
    }


    public T getRecursive(int index)
    {
        return getRecursiveHelper(head, index+1);
    }


    private T getRecursiveHelper(Node<T> ptr, int dis)
    {
        if (dis == 0)
            return ptr.item;
        return getRecursiveHelper(ptr.r, dis-1);
    }
}
