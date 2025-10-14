package lab.functions;

import dataStructures.Chain;

public class MyChain extends Chain {
    public Object[] toArray() {
        Object arr[] = new Object[size()];

        for (int i = 0; i < size(); i++) {
            arr[i] = this.get(i);
        }
        return arr;
    }

    public void addRange(Object[] elements) {
        for (Object element : elements) {
            this.add(size(), element);
        }
    }

    public MyChain union(MyChain chain) {
        MyChain result = new MyChain();

        for (int i = 0; i < this.size(); i++) {
            result.add(result.size(), this.get(i));
        }

        for (int i = 0; i < chain.size(); i++) {
            result.add(result.size(), chain.get(i));
        }

        return result;
    }

    public MyChain intersection(MyChain chain) {
        MyChain result = new MyChain();

        for (int i = 0; i < this.size(); i++) {
            Object element = this.get(i);

            for (int j = 0; j < chain.size(); j++) {
                if (element.equals(chain.get(j))) {
                    result.add(result.size(), element);
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MyChain c1 = new MyChain();
        c1.add(0, "A");
        c1.add(1, "B");
        c1.add(2, "C");

        MyChain c2 = new MyChain();
        c2.add(0, "B");
        c2.add(1,   "C");
        c2.add(2, "D");

        Object[] arr = c1.toArray();
        System.out.println(java.util.Arrays.toString(arr));

        c1.addRange(new Object[] { "X", "Y" });
        System.out.println(java.util.Arrays.toString(c1.toArray()));

        MyChain c3 = c1.union(c2);
        System.out.println(java.util.Arrays.toString(c3.toArray()));

        MyChain inter = c1.intersection(c2);

        System.out.println(java.util.Arrays.toString(inter.toArray()));

    }

}