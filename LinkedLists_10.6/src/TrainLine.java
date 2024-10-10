public class TrainLine {

    /** The name of the trainline */
    private String name;
    /** Points to the first station in the trainline */
    private TrainStation head;
    /** Points to the last station in the trainline */
    private TrainStation tail;
    /** Keeps a running tally of train stations in the trainline */
    private int numberOfStations;

    /** Full constructor */
    public TrainLine(String name, TrainStation head) {
        this.name = name;
        this.head = head;
        this.numberOfStations = 0;
        if (this.head != null) {
            // If head is not null, there is one station in the line
            this.numberOfStations = 1;
        }
        // At initialization head and tail point to the same station even if null
        this.tail = null;
    } // full constructor

    /** Basic constructor */
    public TrainLine(String name) {
        this(name, null);
    } // basic constructor

    /**
     * Creates a new station with the given name and adds it to the end of the line.
     */
    public void add(String name) {
        // Create the new station to add
        TrainStation newStation = new TrainStation(name);
        // Determine where to place the new station
        if (this.head == null) {
            // Trainline is empty, make new station the head of the line
            this.head = newStation;
        } else {
            // When there is a head station already, add the new station after the last
            // station in the line.
            this.tail.setNext(newStation);
        }
        // The new station becomes the tail station of the line
        this.tail = newStation;
        // Update station count
        this.numberOfStations++;
    } // method add

    /** Returns the number of stations in the line >= 0 */
    public int getNumberOfStations() {
        return numberOfStations;
    } // method getNumberOfStations

    public TrainStation remove(int position) {
        TrainStation removedStation = null;
        if (position >= 1 && position <= this.numberOfStations) {
            // Commence safe operations
            if (position == 1) {
                // Remove head
                removedStation = this.head;
                this.head = this.head.getNext();
            } else {
                // Find the station prior to the one to be removed
                TrainStation cursor = this.head;
                for (int i = 1; i < position-1; i++) {
                    cursor = cursor.getNext();
                }
                // cursor should be at the prior station
                if (cursor.getNext() == this.tail) {
                    this.tail = cursor;
                }
                removedStation = cursor.getNext();
                cursor.setNext(cursor.getNext().getNext());
            }
            this.numberOfStations--;
            removedStation.setNext(null);
        }
        return removedStation;
    }
/**
 * Inserts a trainStation into the line at the given index with the given name
 * @param name String name of train station inserting
 * @param index int index of inserting (not overwriting)
 */
    public void insert(String name, int index){
        TrainStation newStation = new TrainStation(name);
        TrainStation cursor = this.head;
        if(index >= 0 && index < this.numberOfStations){

            if(index == 0){
                newStation.setNext(cursor);
                this.head = newStation;
            }else{
            for(int i = 0; i < index -1; i++){
                cursor = cursor.getNext();
            }
            newStation.setNext(cursor.getNext()); 
            cursor.setNext(newStation);
            
            }
            numberOfStations++;
        }
          
    }
    /**
     * Creates a String with all stations in a train line in a snake formation
     * @return returns String containing snake data
     */
    public String toString(){
        int maxPerLine = 80; //max char per line
        int numPerLine = 4; //number of stations per line on snake, + 1 for each end 
        String returns = ""; //The string that will store the snake pattern and return it
        int lineNumber = 1; //the amount of lines the snake pattern has
        int numChar = 0; //current number of char in single line
        String indent = ""; //indentation required for formmating
        int numEndingChar = 3; // "+--" is 3 char. to account for that being added on later subtract numChar by this
        TrainStation cursor = this.head; //cursor
        //check if trainLine contains anything
        if(this.head != null){
            //stop when cursor is null
            while(cursor != null && cursor.hasNext()){
                //start has slightly different code then everything else
                if (lineNumber == 1) {
                    String firstLine = "\n+ ";
                    int i = 0; //number of stations
                    //length < max char, i < number stations per (text) line, and cursor has next
                    while(firstLine.length() < maxPerLine && i < numPerLine+1 && cursor.hasNext()){
                        firstLine = firstLine + cursor.getName() + " --> ";
                        cursor = cursor.getNext();
                        i++;
                    }
                    //null pointer exception check
                    if(cursor.hasNext()){
                        firstLine = firstLine + cursor.getName();
                        cursor = cursor.getNext();
                    }
                    //add line ending
                    firstLine = firstLine + " --+\n";
                    //calculate number of characters
                    numChar = firstLine.length()-1;
                    //increment number of text lines
                    lineNumber++;
                    //padding to add spacing for |
                    String padding = " ".repeat(numChar-1);
                    //store in return String
                    returns = returns + firstLine + padding +"|\n";
                }else  if(lineNumber %2 == 1){ //odd number line code (except for line 1)
                    String oddString =  indent + "+ ";
                    int i = 0; //number of stations
                    //length < max char, i < number stations per (text) line, and cursor is not equal to tail
                    while(oddString.length() < maxPerLine && i < numPerLine && cursor != this.tail){
                        oddString = oddString + cursor.getName() + " --> ";
                        cursor = cursor.getNext();
                        i++;
                    }
                    //cursor check
                    if(cursor.hasNext()){
                        oddString = oddString + cursor.getName();
                        cursor = cursor.getNext();
                    }
                    //if cursor is tail to avoid null error
                    if (cursor == this.tail) {
                        oddString = oddString + cursor.getName() + " --> null\n";
                        returns = returns + oddString;
                    }else{
                        oddString = oddString + " --+\n";
                        numChar = oddString.length()-1;
                        String padding = " ".repeat(numChar-1);
                        returns = returns + oddString + padding +"|\n";
                    }
                    //increment by 1
                    lineNumber++;
                }else if(lineNumber % 2 == 0){ //even number line code 
                    int i = 0; //number of stations
                    String evenString = " +";
                    /// length < numChar (in odd) - ending chracters for space, i < numPerline, prevent cursor == tail,
                    while(evenString.length() < numChar -numEndingChar && i < numPerLine && cursor != this.tail){
                        evenString = " <-- " + cursor.getName() + evenString;
                        cursor = cursor.getNext();
                        i++;
                    }
                    //cursor check
                    if(cursor.hasNext()){
                        evenString = cursor.getName() + evenString ;
                        cursor = cursor.getNext();
                    }
                    //if cursor is tail to avoid null error
                    if (cursor == this.tail) {
                        evenString = "null" + " <-- "+ cursor.getName() + evenString;
                        returns = returns + indent + evenString + "\n";
                    }else{
                        evenString = "+-- " + evenString;
                        int indentNum = numChar - evenString.length();
                        indent = " ".repeat(indentNum);
                        returns = returns + indent + evenString + "\n" + indent +  "|\n";
                    }
                    //increment by 1
                    lineNumber++;
                }
                
            }

        }
        return returns;
    }//method toString
    public static void main(String[] args) {
        // A few station names
        String[] stationNames = { "Howard", "Jarvis", "Morse",
                "Loyola", "Granville", "Thorndale" };
        // A populated trainline
        TrainLine redLineSB = new TrainLine("Red Line SB");
        for (String station : CTA.RED_LINE_SB_NAMES) {
            redLineSB.add(station);
        }
        //System.out.println(redLineSB.toString());
        // An empty trainline
        TrainLine brownLineSB = new TrainLine("Brown Line SB");
        // A random station name
        String randomName = "Oak Park";
        redLineSB.insert(randomName, 13);
        System.out.println(redLineSB.toString());
    } // method main
} // class TrainLine