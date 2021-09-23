import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Thing> listOfThings = new ArrayList<>();
    static int numOfTries = 0;
    static final String[] things = {"A phone",
                                    "Anti-genie",
                                    "Jerry Seinfeld",
                                    "Lethal dose of Cyanide",
                                    "Deep Thought",
                                    "A raft",
                                    "Something not on the list",
                                    "Plenty of food",
                                    "A very good friend",
                                    "Immortality"};

    static void mainMenu() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("It's inevitable, you and 9 strangers " +
                "\nwill soon be stranded on a desert island." +
                "\n");
        System.out.println("You are only allowed to take one of the following items with you." +
                "\nVote on which item you should bring.");
        System.out.println("-----------------------------------------------------------" +
                "\n");

        setupList();

        while (listOfThings.size() > 1) {

            numOfTries++;

            if (numOfTries > 1) {
                System.out.println("\n___________________________");
                System.out.println("---------------------------");
                System.out.println("There are more then one popular thing: \n");
                for (Thing thing : listOfThings) {
                    System.out.println(thing.toStringResults());
                    System.out.println("---------------------------");
                    thing.setTimesChosen(0);
                }
                System.out.println("\nYou have to settle on one thing to bring." +
                        "\nVote again on the most popular choices:\n");
            }
            printListOfThings();
            System.out.println();

            placeVote();
            updateList(getMostPopularThing());
        }
        scan.close();
    }

    static void printEndText(){
        String popThing = listOfThings.get(0).getDescription();

        System.out.println("--------------------------------------------");
        System.out.println("Congratulations! You settled on:");
        System.out.println("!!!!!!!!!!-" + popThing + "-!!!!!!!!!!");
        System.out.println("!!!!!!!!-" + "Got " + listOfThings.get(0).getTimesChosen() + "/10 votes" + "-!!!!!!!!");


        switch (popThing){
            case "A phone" :
                System.out.println("Good luck getting a connection!" +
                        "\nDo you even know if the battery is charged?");
                break;
            case "Anti-genie" :
                System.out.println("The opposite of Robin Williams --> Will Smith??");
                break;
            case "Jerry Seinfeld" :
                System.out.println("Good choice, he's a lot of fun!" +
                        "\nThat is of course, if you like his style of comedy");
                break;
            case "Lethal dose of Cyanide" :
                System.out.println("Hope is overrated anyways!");
                break;
            case "Deep Thought" :
                System.out.println("'42'");
                break;
            case "A raft" :
                System.out.println("Taking your chances on the ocean, eh!");
                break;
            case "Something not on the list" :
                System.out.println("This might be the best thing on the list!");
                break;
            case "Plenty of food" :
                System.out.println("Bon Appetit!");
                break;
            case "A very good friend" :
                System.out.println("Hope your friend is tolerable in longer periods of time.");
                break;
            case "Immortality" :
                System.out.println("Nice!");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + popThing);
        }
        System.out.println("Bye now!");
    }

    static void setupList(){

        for (int i = 0; i < things.length; i++) {
            listOfThings.add(new Thing(things[i]));
        }
    }

    static void printListOfThings(){
        for (Thing thing : listOfThings) {
            System.out.print("\t\tNo. " + (listOfThings.indexOf(thing) + 1) + "\t-\t");
            System.out.println(thing.getDescription());
        }
    }

    static void updateList(ArrayList<Thing> popThings){

        listOfThings.clear();
        for (Thing thing : popThings) {
            listOfThings.add(thing);
        }
    }

    static ArrayList<Thing> getMostPopularThing() {
        ArrayList<Thing> mostPopThings = new ArrayList<>();
        int timesChossen = 0;

        for (int i = 0; i < listOfThings.size(); i++) {

            if (listOfThings.get(i).getTimesChosen() > timesChossen) {
                timesChossen = listOfThings.get(i).getTimesChosen();
                mostPopThings.clear();
                mostPopThings.add(listOfThings.get(i));
            } else if (listOfThings.get(i).getTimesChosen() == timesChossen) {
                mostPopThings.add(listOfThings.get(i));
            }
        }
        return mostPopThings;
    }

    static void placeVote(){
        int personCounter = 1;
        while (personCounter <= 10) {
            System.out.println("---------------------------------------");
            System.out.println("C=|===> Person " + (personCounter) + " <===|=D");
            System.out.println("---------------------------------------");
            System.out.println("Place your vote:");
            int thingIndex = validateIntFromUser() - 1;
            try {
                listOfThings.get(thingIndex).addTimesChosen();
                personCounter++;
            } catch (IndexOutOfBoundsException e){
                System.out.println("That thing is not on the list" +
                        "\nTry again");
            }
        }
    }

    static int validateIntFromUser(){
        int num = 0;
        while (true) {
            try {
                num = scan.nextInt();
                break;
            } catch (InputMismatchException notAnInteger) {
                System.out.println("Invalid input! try a number");
                scan.next();
            }
        }
        return num;
    }

    public static void main(String[] args) {

        mainMenu();
        printEndText();

    }
}
