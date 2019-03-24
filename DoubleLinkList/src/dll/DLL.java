package dll;

class Node<E> {

    public E info;
    public Node<E> next, prev;

    public Node() {
        this(null, null, null);
    }

    public Node(E el) {
        this(el, null, null);
    }

    public Node(E el, Node<E> n, Node<E> p) {
        info = el;
        next = n;
        prev = p;
    }
}

public class DLL<E> implements ListOf<E> {

    Node head, tail;
    int size;

    public void print() {
        Node a = head;
        if (size > 0) {
            System.out.println(a.info);
            for (int i = 0; i < size - 1; i++) {
                a = a.next;
                System.out.println(a.info);
            }
        } else {
            System.out.println("List is empty!");
        }
    }

    public void printSubList(ListOf<E> list) {
        int count = 1;
        for (int i = 1; i < list.size(); i++) {
            E element = list.get(i);
            System.out.println(element);
            count++;
        }
    }

    @Override
    public boolean add(E e) {
        // TODO Auto-generated method stub
        Node newest = new Node();
        newest.info = e;
        if (head == null) {
            head = newest;
            newest.next = null;
            newest.prev = null;
            tail = newest;
        } else {
            tail.next = newest;
            newest.next = null;
            newest.prev = tail;
            tail = newest;
        }
        size++;
        return false;
    }

    @Override
    public void add(int index, E element) {
        // TODO Auto-generated method stub
        Node newest = new Node();
        newest.info = element;
        if (index > size || index < 0) {
            System.out.println("Invalid!");
        } else {
            if (index == 0) {
                if (size == 1) {
                    newest.next = head;
                    head.prev = newest;
                    tail = head;
                    head = newest;
                    size++;
                } else if (size == 0) {
                    head = newest;
                    newest.next = null;
                    newest.prev = null;
                    tail = newest;
                    size++;
                } else {
                    newest.next = head;
                    head.prev = newest;
                    newest.prev = null;
                    head = newest;
                    size++;
                }
            } else if (index == size) {
                add(element);
                size++;
            } else {
                Node pre = head;
                for (int i = 0; i < index - 2; i++) {
                    pre = pre.next;
                }
                Node post = new Node();
                post = pre.next;
                pre.next = newest;
                newest.prev = pre;
                newest.next = post;
                post.prev = newest;
                size++;
            }
        }
    }

    @Override
    public boolean addAll(ListOf<E> c) {
        // TODO Auto-generated method stub
        for (int i = 0; i < c.size(); i++) {
            E info = c.get(i);
            add(info);
        }
        return false;
    }

    @Override
    public boolean addAll(int index, ListOf<E> l) {
        // TODO Auto-generated method stub
        for (int i = 0; i < l.size(); i++) {
            E info = l.get(i);
            add(index, info);
        }
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        head = null;
        tail = null;
        size = 0;

    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        Node tmp = head;
        while (tmp != null) {
            if (tmp.info.equals(o) || tmp == o) {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(ListOf<E> l) {
        // TODO Auto-generated method stub
        for (int i = 0; i < l.size(); i++) {
            E info = l.get(i);
//            System.out.println(info);
            if (!contains(info)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public E get(int index) {
        Node pre = head;
        if (index > size || index < 0) {
//            System.out.println("Not exist!");
        } else {
            for (int i = 1; i < index; i++) {
                pre = pre.next;
            }
        }
        return (E) pre.info;
    }

    @Override
    public int indexOf(Object o) {
        // TODO Auto-generated method stub
        for (int i = 1; i <= size(); i++) {
            E info = get(i);
            if (info == o || info.equals(o)) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return head == null;
    }

    @Override
    public E remove(int index) {
        // TODO Auto-generated method stub
        Node tmp = head;
        if (index > size || index < 0) {
//            System.out.println("Not exist!");
        } else {
            if (isEmpty()) {
                System.out.println("Empty!");
            } else {
                if (index == 1) { //xoa dau
                    head = head.next;
                    head.prev = null;
                    if (size == 1) {
                        clear();
                        System.out.println("Empty!");
                    }
                } else if (index == size) { //xoa cuoi
                    tail = tail.prev;
                    tail.next = null;

                } else {
                    for (int i = 0; i < index - 2; i++) {
                        tmp = tmp.next;
                    }
                    Node remove = tmp.next;
                    Node after = remove.next;

                    tmp.next = after;
                    after.prev = tmp;
                }
                size--;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return remove(indexOf(o)) != null;
    }

    @Override
    public boolean removeAll(ListOf<E> l) {
        // TODO Auto-generated method stub
        for (int i = 1; i <= l.size(); i++) {
            E info = l.get(i);
//            System.out.println("test " + info);
            remove(info);
        }
        return false;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            return 0;
        }
        int count = 1;
        Node pre = head;
        while (pre.next != null) {
            pre = pre.next;
            count++;
        }
        size = count;
        return size;
    }

    

}

interface ListOf<E> {

    boolean add(E e);
    //Appends the specified element to the end of this list (optional operation).

    void add(int index, E element);
    //Inserts the specified element at the specified position in this list (optional operation).

    boolean addAll(ListOf<E> c);
    //Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator (optional operation).

    boolean addAll(int index, ListOf<E> l);
    //Inserts all of the elements in the specified collection into this list at the specified position (optional operation).

    void clear();
    //Removes all of the elements from this list (optional operation).

    boolean contains(Object o);
    //Returns true if this list contains the specified element.

    /*	Parameters:
		o - element whose presence in this list is to be tested
		Returns:
		true if this list contains the specified element
		Throws:
		ClassCastException - if the type of the specified element is incompatible with this list (optional)
		NullPointerException - if the specified element is null and this list does not permit null elements (optional)*/
    boolean containsAll(ListOf<E> l);
    //Returns true if this list contains all of the elements of the specified collection.

    boolean equals(Object o);
    //Compares the specified object with this list for equality.

    E get(int index);
    //Returns the hash code value for this list.

    int indexOf(Object o);
    //Returns the index of the first occurrence of the specified element in this list, 4
    //or -1 if this list does not contain the element.

    boolean isEmpty();
    //Returns true if this list contains no elements.

    E remove(int index);
    //Removes the element at the specified position in this list (optional operation).

    boolean remove(Object o);
    //Removes the first occurrence of the specified element from this list, 
//if it is present (optional operation).

    boolean removeAll(ListOf<E> l);
    //Removes from this list all of its elements that are contained in the specified collection (optional operation).


    int size();
    //Returns the number of elements in this list.


}
