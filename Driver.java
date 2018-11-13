public class Driver{
  public static void main(String[] arg){
    /* STAGE ONE CODE
    WordSearch n = new WordSearch(3,3);

    System.out.println("~ ~ ~ TESTING toString AND CONSTRUCTORS ~ ~ ~");

    System.out.println("\n~ Should print 3x3 grid of \"_\" (Spaces in between) ~");
    System.out.println(n);

    System.out.println("\n~ ~ ~ TESTING addWordHorizontal ~ ~ ~");

    System.out.println("\n~ Should print out \"hi\" in the first two places in first row ~");
    n.addWordHorizontal("hi",0,0);
    System.out.println(n);

    System.out.println("\n~ ~ These Tests See If Nothing Happens When (Mismatched) OverLapping Words Occur Or When Desired Word Is Too Large ~ ~");
    System.out.println("~ Should Return 2 True and 2 False and Grid with Just \"hin\" and \"X\" ~");
    System.out.println(n.addWordHorizontal("X",2,0));
    System.out.println(n.addWordHorizontal("in",0,1));
    System.out.println(n.addWordHorizontal("food",1,0));
    System.out.println(n.addWordHorizontal("REE",2,0));
    System.out.println("\n"+n);

    System.out.println("\n~ ~ ~ TESTING addWordVertical ~ ~ ~");
    WordSearch n2 = new WordSearch(5,5);
    System.out.println("\n~ Should print \"food\" in second column");
    n2.addWordVertical("food",0,1);
    System.out.print(n2);

    System.out.println("\n~ ~ These Tests See If Nothing Happens When (Mismatched) OverLapping Words Occur Or When Desired Word Is Too Large ~ ~");
    System.out.println("~ Should Return False then True and Grid with Just \"foody\" ~");
    System.out.println(n2.addWordVertical("fe",2,1));
    System.out.println(n2.addWordVertical("dy",3,1));
    System.out.println("\n"+n2);

    System.out.println("\n~ ~ ~ TESTING addWordDiagonal ~ ~ ~");
    System.out.println("~ ~ Should Return Diagonal \"food\" Starting From Top Left Corner ~ ~ ");
    WordSearch n3 = new WordSearch(5,5);
    n3.addWordDiagonal("food",0,0);
    System.out.println("\n"+n3);

    System.out.println("~ ~ Should Return \"toes\" and \"feat\" Connected At The \"e\" At (2,2) ~ ~ "); //made another WordSearch
    WordSearch n4 = new WordSearch(5,5);
    n4.addWordHorizontal("feat",2,1);
    n4.addWordDiagonal("toes",0,0);
    System.out.println(n4);
*/
    WordSearch n = new WordSearch(5,5);

    System.out.println("~ ~ ~TESTING addWord()~ ~ ~");
    System.out.println("~ ~Should Print Empty 5x5 Grid");
    System.out.println(n+"\n");

    n.addWord("bye",0,0,1,1);
    System.out.println("~ ~Should Print 5x5 Grid With \"bye\" From Top-Left In Diagonal And \"Words: bye\"~ ~");
    System.out.println(n);

    System.out.println("~ ~Same Grid With Diagonal \"bye\" And Backwards \"rye\" Cutting Through It");
    n.addWord("rye",1,2,0,-1);
    System.out.println(n);

    System.out.println("~ ~Same Grid With \"dread\" Straight Down Column 2");
    n.addWord("dread",0,2,1,0);
    System.out.println(n);
  }
}
