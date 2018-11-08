public class Driver{
  public static void main(String[] arg){
    WordSearch n = new WordSearch(3,3);

    System.out.println("~ ~ ~ TESTING toString AND CONSTRUCTORS ~ ~ ~");

    System.out.println("\n~ Should print 3x3 grid of \"_\" (Spaces in between) ~");
    System.out.println(n);

    System.out.println("\n~ ~ ~ TESTING addWordHorizontal ~ ~ ~");

    System.out.println("\n~ Should print out \"hi\" in the first two places in first row ~");
    n.addWordHorizontal("hi",0,0);
    System.out.println(n);

    System.out.println("~ ~ These Tests See If Nothing Happens When (Mismatched) OverLapping Words Occur Or When Desired Word Is Too Large ~ ~");
    System.out.println("~ Should Return False 3x and Grid with Just \"hi\" and \"X\" ~");
    System.out.println(n.addWordHorizontal("X",2,0));
    System.out.println(n.addWordHorizontal("food",1,0));
    System.out.println(n.addWordHorizontal("REE",2,0));
    System.out.println(n);
  }
}
