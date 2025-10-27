package  lab.functions;
import java.util.Objects;

public class HashTable {
    private static class Entry {
        Object key;
        Object value;

        Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] table;
    private int size;

    public HashTable(int capacity) {
        table = new Entry[capacity];
        size = 0;
    }

    private int hash(Object key) {
        return Math.abs(Objects.hashCode(key)) % table.length;
    }

    public Object get(Object key) {
        int index = hash(key);
        int start = index;
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                return table[index].value;
            }
            index = (index + 1) % table.length;
            if (index == start) break;
        }
        return null;
    }

    public void put(Object key, Object value) {
        int index = hash(key);
        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + 1) % table.length;
        }
        table[index] = new Entry(key, value);
        size++;
    }

    public Object updateElement(Object theKey, Object theElement) {
        int index = hash(theKey);
        int start = index;
        while (table[index] != null) {
            if (table[index].key.equals(theKey)) {
                Object oldValue = table[index].value;
                table[index].value = theElement;
                return oldValue;
            }
            index = (index + 1) % table.length;
            if (index == start) break;
        }
        return null;
    }

    public Object updateKey(Object theKey, Object theNewKey) {
        Object elementToUpdate = get(theKey);
        if (elementToUpdate != null) {
            delete(theKey);
            put(theNewKey, elementToUpdate);
            return elementToUpdate;
        }
        return null;
    }

    public void delete(Object theKey) {
        int index = hash(theKey);
        int start = index;
        while (table[index] != null) {

            if (table[index].key.equals(theKey)) {
                table[index] = null;
                size--;
                return;
            }
            index = (index + 1) % table.length;
            if (index == start) break;
        }
    }

    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null)
                System.out.println(i + ": " + table[i].key + " → " + table[i].value);
            else
                System.out.println(i + ": null");
        }
    }

    public static void main(String[] args) {
        HashTable ht = new HashTable(10);
        ht.put("A101", "Book");
        ht.put("A102", "Pen");
        ht.put("A103", "Paper");

        ht.get("A101");
        System.out.println("Before update ");
        ht.printTable();

        ht.updateElement("A101", "Notebook");
        ht.updateKey("A102", "A222");
        ht.delete("A103");

        System.out.println("After update ");
        ht.printTable();
    }
}
