
/**
 * A simple class to demonstrate dynamic behavior with arrays. Objects of this
 * class store strings in an array that grows to match the demand for storage.
 * 
 * The class is based on an underlying string array. Objects can be initialized
 * to any size; otherwise they'll be initialized to the default size. For
 * example,
 * 
 * DynamicArray da1 = new DynamicArray(10);
 * 
 * will have initially room for 10 strings, while
 * 
 * DynamicArray da2 = new DynamicArray();
 * 
 * will have initially room for 4 strings.
 */
public class DynamicArray {

    /** Default size for underlying array */
    private static final int DEFAULT_SIZE = 4;

    /** The underlying array for this class */
    private String[] foundation;

    /** Measures how many places in the array are in use */
    private int occupancy;

    /**
     * Full constructor. Initializes the underlying array to the specified size. The
     * size must be a positive, non zero value. Otherwise the constructor uses the
     * default size value.
     */
    public DynamicArray(int size) {
        // If size <= 0 use default -- this is a good time to demo ternary operator
        size = (size > 0) ? size : DEFAULT_SIZE;
        this.foundation = new String[size];
        this.occupancy = 0;
    } // full constructor

    /**
     * Array-based constructor -- used for testing.
     * 
     * WARNING: SHALLOW ARRAY COPY
     * 
     * @param data
     */
    public DynamicArray(String[] data) {
        this(DEFAULT_SIZE);
        if (data != null) {
            this.foundation = data;
            this.occupancy = data.length;
        }
    } // array-based constructor

    /**
     * Default constructor
     */
    public DynamicArray() {
        this(DEFAULT_SIZE);
    } // default constructor

    /**
     * Checks if the specified string is present in the dynamic array.
     * 
     * @param target The string to search for in the array
     * @return true if the string is found, false otherwise
     */
    public boolean contains(String target) {
        boolean found = false;
        /*
         * Before introducing this.occupancy in the object, the method traversed
         * this.foundation through its entire length, i.e., the while loop allowed its
         * index i to reach this.foundation.length. As we saw, however, not every
         * element in this.foundation may be used. this.occupancy tells us what is the
         * last used element in this.foundation. There is no point searching after that
         * element, as all values are going to be null. So, for the while loop here we
         * change the condition from while(i<this.foundation length &...) to
         * while(i<this.occupancy &&...)
         */
        if (target != null && this.foundation != null) {
            int i = 0;
            // No need to guard against occupancy==0, because if array is empty, loop will
            // not even run.
            while (i < this.occupancy && !found) {
                found = this.foundation[i] != null && this.foundation[i].equals(target);
                i++;
            }
        }
        return found;
    } // method contains

    /**
     * Retrieves the string at the specified index in the array.
     * 
     * @param index The index of the string to retrieve
     * @return The string at the specified index, or null if the index is invalid
     */
    public String get(int index) {
        String string = null;
        // No need to guard against occupancy==0, because if array is empty, the method
        // will return null anyway
        if (index >= 0 && this.foundation != null && index < this.foundation.length) {
            string = this.foundation[index];
        }
        return string;
    } // method get

    /**
     * Removes the string at the specified index in the array and sets its position
     * to null. Then it moves every element to the right of the removed element, one
     * position to the left. The position of the last element to be copied to the
     * left is then emptied out (null).
     * 
     * @param index The index of the string to remove
     * @return The string that was removed, or null if the index is invalid
     */
    public String remove(int index) {
        String removed = null;
        // We check occupancy, because there is no reason to perform this in an empty
        // array
        if (this.occupancy > 0 && index >= 0 && index < this.foundation.length) {
            removed = this.foundation[index];
            this.foundation[index] = null;
            // Shift things after the removed string, one position to the left
            for (int i = index; i < occupancy - 1; i++) {
                this.foundation[i] = this.foundation[i + 1];
            }
            // Previously last occupied cell, now empty
            this.foundation[occupancy - 1] = null;
            // update occupancy
            this.occupancy--;
        }
        return removed;
    } // method remove

    /**
     * Deletes the string at the specified index in the array.
     * 
     * This method uses this.remove and simply ignores the returned string.
     * 
     * @param index The index of the string to delete
     */
    public void delete(int index) {
        String whatEver = remove(index);
    } // method delete

    /**
     * Resizes the underlying array by increasing its capacity by 1.
     * 
     * This method is called internally when the current array reaches its capacity
     * and a new element needs to be inserted.
     */
    private void resize() {
        String[] temp = new String[this.foundation.length + 1];
        /*
         * Instead of:
         * for (int i = 0; i < this.foundation.length; i++) {
         * we can write
         * for (int i = 0; i < this.occupancy; i++) {
         * since there is no reason to copy null values from one array to another.
         */
        for (int i = 0; i < this.occupancy; i++) {
            temp[i] = this.foundation[i];
        }
        this.foundation = temp;
    } // method resize

    /**
     * Inserts a new string into the dynamic array.
     * 
     * If the string is not null and the array is full, it will be resized to
     * accommodate the new element.
     * 
     * @param string The string to insert into the array
     */
    public void insert(String string) {
        // Guard against null argument
        if (string != null) {
            // If there is no room left in underlying array, resize it first
            if (this.occupancy == this.foundation.length) {
                this.resize();
            }
            // Room in underlying array assured
            this.foundation[this.occupancy] = string;
            this.occupancy++;
        }
    } // method insert
    /**
     * Sends entire method into a single String.
     * 
     * saftey catch if null
     * converts all elements in array to string
     * @return  string; of the data in array
     */
    public String toString(){
        String arrStr = "";
        for (String element : this.foundation) {
            if(element != null){
                arrStr = arrStr + element + " ";
            }
        }
        return arrStr;
    }//method toString

    /**
     * Locates index of a string within an aray from given string
     * 
     * @param string The string to find in array
     * @return i;  The index of the string if found, -1 if not
     */
    public int index(String string){
        int i = 0; 
        while( i >= 0 && !this.foundation[i].equals(string)){
            i++;
            if(i == this.foundation.length){
                i = -1;
            }
        }
        
        return i;
    }//method index
    /**
     * Counts values in array that are NOT null
     * 
     * @return usage/100; to get the proper decimal places returns 0.xx values
     */
    public double usage(){
        double numNull = 0; //num values in array not null
        for (String foundation1 : this.foundation) {
            if (foundation1 != null) {
                numNull++;
            }
        }
        double usage = Math.round((numNull / this.foundation.length)*100);
        return usage/100;
    }//method usage

    /** Driver/test code */
    public static void main(String[] args) {
        final String PASS = "Pass";
        final String FAIL = "Fail";
        final String NON_EXISTING = "COBOL";
        // Test data
        String[] testData = { "Java", "C","Python", "C", "C++", null, "Fortran", null};
        DynamicArray test = new DynamicArray(testData);
        int i = test.index("C++");
        System.out.println("Index w/given string: " + i);
        String arrStr = test.toString();
        System.out.println("Array to string is: " + arrStr);
        double val = test.usage();
        System.out.println("Amount filled: " + val);
        
    } // method main

} // class DynamicArray