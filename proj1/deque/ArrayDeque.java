package deque;

public class ArrayDeque<T> implements Deque<T>{

    public T[] arr;
    private int size;

    /*
      invariant:
        0 <= left_end, right_end < arr.length
        left_end, right_end所指向的偏移内存位置尚未存值
        left_end向左移动，right_end向右移动
     */
    private int left_end = 0;
    private int right_end = 1;

    int initial_length = 16;
    double shrink_factor = 0.25;

    public ArrayDeque()
    {
        arr = (T[]) new Object[initial_length];
        size = 0;
    }
    @Override
    public void addFirst(T item) {
        if (size == arr.length-1)
        {
            assert left_end == right_end;
            resize_arr(2);
        }
        arr[left_end] = item;
        left_end = (left_end - 1 + arr.length) % arr.length;
        size += 1;
    }


    private void resize_arr(double factor)
    {
        int new_len = (int) (arr.length * factor);
        T[] arr1 = (T[]) new Object[new_len];

        if (left_end >= right_end)
        {
            int p1_size = right_end;
            int p2_size = arr.length - left_end - 1;
            assert p1_size + p2_size == size;

            System.arraycopy(arr, 0, arr1, 0, p1_size);
            // System.arraycopy(arr, right_end+1, arr1, arr1.length-p2_size, p2_size);
            System.arraycopy(arr, left_end+1, arr1, arr1.length-p2_size, p2_size);
            left_end = arr1.length - p2_size - 1;
        } else
        {
            System.arraycopy(arr, left_end+1, arr1, 0, size);
            left_end = arr1.length - 1;
            right_end = size;
        }

        arr = arr1;
    }

    @Override
    public void addLast(T item) {
        if (size == arr.length-1)
            resize_arr(2);
        arr[right_end] = item;
        right_end = (right_end + 1) % arr.length;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for(int i=1; i<=size; i++)
        {
            int pos = (left_end + i) % arr.length;
            System.out.print(arr[pos]+" ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        // 检查是否要resize
        if (arr.length > 16 && size <= arr.length * shrink_factor)
            resize_arr(0.5);

        if (size == 0) {
            return null;
        }

        left_end = (left_end + 1) % arr.length;
        T res = arr[left_end];
        size -= 1;


        return res;
    }


    @Override
    public T removeLast() {
        if (arr.length > 16 && size <= arr.length * shrink_factor)
            resize_arr(0.5);

        if (size == 0) {
            return null;
        }

        right_end = (right_end - 1 + arr.length) % arr.length;
        T res = arr[right_end];
        size -= 1;

        return res;
    }

    @Override
    public T get(int index) {
        return arr[(left_end + index + 1) % arr.length];
    }

    public int len(){
        return arr.length;
    }
}
