public class FastQ {

    private String[] array;
    private int size;
    private int used;
    private int front;
    private int back;

    private static final int DEFAULT_SIZE = 4;

    /** Full constructor */
    public FastQ(int size) {
        if (size <1){
            size = DEFAULT_SIZE;
        }
        this.size = size;
        this.array = new String[this.size];
        this.used = 0;
        this.front = 0;
        this.back = 0;
    } // full constructor

    /** Default constructor */
    public FastQ() {
        this(DEFAULT_SIZE);
    } // default constructor
    /**
     * Adds a value into the array at the last used spot
     * Returns boolean
     * @param string
     * @return success
     */
    public boolean add(String string) {
        boolean success = false;
        if(this.used < this.array.length){
            this.array[this.back] = string;
            success = true;
            this.used++; //increment
            this.back++; //increment to not overwrite
        }
        return success;
    } // method add
    /**
     * Removes value in array from front to back
     * Replaces with null
     * returns removed value
     * @return removed
     */
    public String remove() {
        String removed = this.array[this.front];
        this.array[this.front] = null;
        this.used--; 
        this.front++; //increment
        if(this.front == this.array.length){
            this.front = 0; //reset so no out of bounds exception
        }
        return removed;
    } // method remove
    
} // class FastQ