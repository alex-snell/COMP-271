import java.util.Arrays;
import java.util.Random;

public class Person implements Comparable<Person>, SillyActions {

    private static final String DEFAULT_LAST_NAME = "LNU";
    private static final String DEFAULT_FIRST_NAME = "FNU";
    private static final int DEFAULT_YEAR_BORN = 1800;

    private String firstName;
    private String lastName;
    private int yearBorn;

    public Person(String firstName, String lastName, int yearBorn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearBorn = yearBorn;
    }

    public Person(String firstName) {
        this(firstName, DEFAULT_LAST_NAME, DEFAULT_YEAR_BORN);
    }

    /** Default constructor */
    public Person() {
        this(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_YEAR_BORN);
    }

    /**
     * Implements the Comparable interface to determine the relative order of two
     * persons based on their age.
     * 
     */
    public int compareTo(Person other) {
        return other.getYearBorn() - this.yearBorn;
    } // method compareTo

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", lastName=" + lastName + ", yearBorn=" + yearBorn + "]";
    } // method toString

/** MAIN METHOD */
public static void main(String[] args){
    Person kermit = new Person("Kermit", "The Frog", 1995);
    Person missPiggy = new Person("Miss Piggy", "A Pig", 1974);

    kermit.winStateLottery();
    System.out.println(kermit.reciteAlphabetBackwards());
    
    missPiggy.makeRandomSound();
    missPiggy.performSillyDance();
    kermit.countToTenWeirdly();
    System.out.println(missPiggy.createWhimsicalPoem("Pigs"));
    System.out.println(kermit.createWhimsicalPoem("Frogs"));
}

@Override
public void makeRandomSound() {
    int length = PoemWords.words.length;
    Random random = new Random();
    String word = PoemWords.words[random.nextInt(length)];
    System.out.println(this.firstName + " says: " + word);
}

@Override
public void performSillyDance() {
    //top line
    System.out.print("\\O/     ");   
    System.out.print(" O      ");
    System.out.print("\\O      ");
    System.out.println(" O/");
   
    //middle line
    System.out.print(" |      ");
    System.out.print("/|\\     ");
    System.out.print(" |\\     ");
    System.out.println("/|");
   
    //bottom line
    for(int i = 0; i < 4; i++){
        System.out.print("/ \\     ");
    }
    System.out.println();
    
}

@Override
public String reciteAlphabetBackwards() {  
    return this.firstName + " says: Z Y X W V U T S R Q P O N M L K J I H G F E D C B A";
}

@Override
public void countToTenWeirdly() {
    // https://www.youtube.com/watch?v=wbTFXZOiwiM&ab_channel=VestonBruno

    System.out.println(this.firstName + " says: un deux tres cuatro five eight three two nueve ten");
}

@Override
public String createWhimsicalPoem(String topic) {
    Random ran = new Random();
    int length = PoemWords.words.length;
    int numWords = ran.nextInt(15);
    String poem = topic;
    for(int i = 0; i < numWords; i++){
        int x = ran.nextInt(length);
        poem = poem + " " + PoemWords.words[x];
    }
    return this.firstName + " says: " + poem;
}

@Override
public void winStateLottery() {
    Random cardNum = new Random();
    int[] userCardNum = new int[6];
    int[] winNum = new int[6];
    for(int i = 0; i < userCardNum.length; i++){
        userCardNum[i] = cardNum.nextInt(20);
        winNum[i] = cardNum.nextInt(20);
    }
    System.out.println("Your ticket is: " + Arrays.toString(userCardNum));
    boolean didTheyWin = false;
    for(int i = 0; i < winNum.length; i++){
        didTheyWin = winNum[i] == userCardNum[i];
    }
    System.out.println("Winning ticket is: " + Arrays.toString(winNum));
    if(didTheyWin == true){
        System.out.println("Congratulation you just won a fake lottery!");
    }else{
        System.out.println("You lost.");
    }
    
}

    //------------------- AUTO GENERATED METHODS ------------------------------ 
    
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getYearBorn() {
        return this.yearBorn;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

}