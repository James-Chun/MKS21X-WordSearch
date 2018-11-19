import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
import java.util.Random;//random stuff

public class WordSearch{
    private char[][]data;
    //the random seed used to produce this WordSearch
    private int seed;
    //a random Object to unify your random calls
    private Random randgen;
    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd;
    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded;

    //Default constructor
    public WordSearch( int rows, int cols, String fileName){
      Random randgen = new Random(); //making random object
      seed = (Math.abs(randgen.nextInt() % 10000)); //making seed from that randgen
      String word = "";
      wordsToAdd = new ArrayList<String>(0);

      try{
        File f = new File(fileName);//can combine
        Scanner in = new Scanner(f);//into one line
        while(in.hasNext()){
          word = in.next();
          wordsToAdd.add(word);
        };
      }catch(FileNotFoundException e){System.out.println("File Not Found");}

      data = new char[rows][cols];
      for (int row = 0; row < data.length;row++){
        for (int col = 0; col < data[row].length;col++){
          data[row][col]=' ';
        }
      }
      wordsAdded = new ArrayList<>(0);
    }

    public WordSearch( int rows, int cols, String fileName, int randSeed){
      seed = randSeed;
      String word = "";
      wordsToAdd = new ArrayList<String>(0);

      try {
        File f = new File(fileName);//can combine
        Scanner in = new Scanner(f);//into one line
        while(in.hasNext()){
          word = in.next();
          wordsToAdd.add(word);
        };
      }catch(FileNotFoundException e){System.out.println("File Not Found");}

      data= new char[rows][cols];
      for (int row = 0; row < data.length;row++){
        for (int col = 0; col < data[row].length;col++){
          data[row][col]=' ';
        }
      }
      wordsAdded = new ArrayList<>(0);
      if (randSeed<0 || randSeed>10000){
        throw new ArrayIndexOutOfBoundsException("Index Out of Bounds");
      }
    }

    public String toString(){
      String s = "";
      for (int row = 0;row<data.length;row++){
        s = s + "| ";
        for (int col=0;col<data[row].length;col++){
          s = s + data[row][col] + " ";
        }
        s = s + "|\n";
      }
      s = s +"Words: ";
      for (int i = 0;i<wordsAdded.size();i++){
        s = s + wordsAdded.get(i);
        if (i < wordsAdded.size()-1){
          s = s +", ";
        }
      }
      return s + " (seed: "+seed+")";
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for (int row = 0; row < data.length;row++){
        for (int col = 0; col < data[row].length;col++){
          data[row][col]='_';
        }
      }
    }



   private boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
     int rowplace = row;
     int colplace = col;
     if (!(Math.abs(rowIncrement+colIncrement)>0&&row+word.length()*rowIncrement>=-1 && row+word.length()*rowIncrement<=data.length && col+word.length()*colIncrement>=-1 && col+word.length()*colIncrement <=data[rowplace].length)){   //See if the word will fit in the row
       return false;
     }
     for (int i=0;i<word.length();i++){
         if ( !(data[rowplace][colplace] == ' ' || data[rowplace][colplace] == word.charAt(i)) )  { //If current place at row is empty then all is good, or if place is same as char at word
           return false;
         }
         rowplace=rowplace+rowIncrement;
         colplace=colplace+colIncrement;
       }
      rowplace = row;
      colplace = col;

     for (int i=0;i<word.length();i++){
          data[rowplace][colplace]=word.charAt(i);
          rowplace=rowplace+rowIncrement;
          colplace=colplace+colIncrement;
        }
     wordsAdded.add(word);
     return true;
   }


   private void addAllWords(){
     randgen = new Random(seed);
     int tries = 0; //how many attempts before changing up the direction
     int index = Math.abs(randgen.nextInt()%wordsToAdd.size());//choosing a random index to get a random word from the file
     String word = wordsToAdd.get(index);
     while(wordsToAdd.size()>0&&tries<100){
       int rowIncrement = randgen.nextInt()%2;//should give either -1,0,or 1
       int colIncrement = randgen.nextInt()%2;//^^^

       int row = Math.abs(randgen.nextInt()%(data.length-1));//choosing random start row
       int col = Math.abs(randgen.nextInt()%(data[0].length-1));//choosing random start col

       if (addWord(word,row,col,rowIncrement,colIncrement)){
         tries = 0;
         wordsAdded.add(wordsToAdd.remove(index));

         if (wordsToAdd.size()!=0){
           index = Math.abs(randgen.nextInt()%wordsToAdd.size());
           word=wordsToAdd.get(index);
         }
       }else{
         tries++;
       }
     }
   }

   private char randLetter(){
     Random n = new Random();
     String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
     return alpha.charAt(Math.abs(n.nextInt())%26);
   }
   private void fillSpaces(){
     for (int row = 0; row < data.length;row++){
       for (int col = 0; col < data[row].length;col++){
         if (data[row][col]==' '){
           data[row][col]=randLetter();
         };
       }
     }
   }

   public static void main(String[] args){
     if (args.length==5) {
       if (args[4].equals("key")){
       try{
         WordSearch puzzle = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]), args[2],Integer.parseInt(args[3]));
         puzzle.addAllWords();
         System.out.println(puzzle);
       }catch (ArrayIndexOutOfBoundsException e){System.out.println("Row and Column Dimensions Must be Greater Than 0; randSeed Must Be Between 0 and 10000 Inclusive; randSeed Must Be Between 0 and 10000 Inclusive");}
       catch(NegativeArraySizeException e){System.out.println("Row and Column Dimensions Must be Greater Than 0; randSeed Must Be Between 0 and 10000 Inclusive");}
       catch(IllegalArgumentException e){System.out.println("//ERROR// USE: java WordSearch [int rows, int cols, String filename, [int randomSeed], [String key]]");}
        catch(ArithmeticException e){System.out.println("or File Is Empty");}
     }else{
       try{
         WordSearch puzzle = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]), args[2],Integer.parseInt(args[3]));
         puzzle.addAllWords();
         puzzle.fillSpaces();
         System.out.println(puzzle);
       }catch (ArrayIndexOutOfBoundsException e){System.out.println("Row and Column Dimensions Must be Greater Than 0; randSeed Must Be Between 0 and 10000 Inclusive");}
       catch(NegativeArraySizeException e){System.out.println("Row and Column Dimensions Must be Greater Than 0; randSeed Must Be Between 0 and 10000 Inclusive");}
       catch(IllegalArgumentException e){System.out.println("//ERROR// USE: java WordSearch [int rows, int cols, String filename, [int randomSeed], [String key]]");}
       catch(ArithmeticException e){System.out.println("or File Is Empty");}
     }
     }

     if (args.length == 4){
       if (args[3].equals("key")){
       try{
         WordSearch puzzle = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]), args[2]);
         puzzle.addAllWords();
         System.out.println(puzzle);
       }catch (ArrayIndexOutOfBoundsException e){System.out.println("Row and Column Dimensions Must be Greater Than 0; randSeed Must Be Between 0 and 10000 Inclusive");}
       catch(NegativeArraySizeException e){System.out.println("Row and Column Dimensions Must be Greater Than 0; randSeed Must Be Between 0 and 10000 Inclusive");}
       catch(IllegalArgumentException e){System.out.println("//ERROR// USE: java WordSearch [int rows, int cols, String filename, [int randomSeed], [String key]]");}
        catch(ArithmeticException e){System.out.println("or File Is Empty");}
     }
       try{
         WordSearch puzzle = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]));
         puzzle.addAllWords();
         puzzle.fillSpaces();
         System.out.println(puzzle);
       }catch (ArrayIndexOutOfBoundsException e){System.out.println("Row and Column Dimensions Must be Greater Than 0; randSeed Must Be Between 0 and 10000 Inclusive");}
       catch(NegativeArraySizeException e){System.out.println("Row and Column Dimensions Must be Greater Than 0; randSeed Must Be Between 0 and 10000 Inclusive");}
       catch(IllegalArgumentException e){System.out.println("//ERROR// USE: java WordSearch [int rows, int cols, String filename, [int randomSeed], [String key]]");}
        catch(ArithmeticException e){System.out.println("or File Is Empty");}
     }
     if (args.length==3){
       try{
        WordSearch puzzle = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]), args[2]);
        puzzle.addAllWords();
        puzzle.fillSpaces();
        System.out.println(puzzle);
      }catch (ArrayIndexOutOfBoundsException e){System.out.println("Row and Column Dimensions Must be Greater Than 0; randSeed Must Be Between 0 and 10000 Inclusive");}
      catch(NegativeArraySizeException e){System.out.println("Row and Column Dimensions Must be Greater Than 0; randSeed Must Be Between 0 and 10000 Inclusive");}
      catch(IllegalArgumentException e){System.out.println("//ERROR// USE: java WordSearch [int rows, int cols, String filename, [int randomSeed], [String key]]");}
       catch(ArithmeticException e){System.out.println("or File Is Empty");}
     }
     if (args.length<=2){
       System.out.print("//ERROR// USE: java WordSearch [int rows, int cols, String filename, [int randomSeed], [String key]]");
     }
   }


   public boolean addWordHorizontal(String word,int row, int col){
      char[][] I = data;           //If things go bad, set data back to this
      String temp = word;
      int place = 0;                             //Keep track of which char in temp we are looking at
      if (col+word.length() <= data[row].length){   //See if the word will fit in the row

        for (int i=col;i<temp.length() + col ;i++){

          if ( data[row][i] == '_' || Character.toString( data[row][i] ).equals( temp.substring (place,place+1) ) ) { //If current place at row is empty then all is good, or if place is same as char at word
            data[row][i]=temp.charAt(place);    //Change spot row and move place up one
            wordsAdded.add(temp.substring (place,place+1));
            place++;
          }else{
            data=I; //If
            return false;
          }

        }
        return true;
      }

      data=I;
      return false;
    }


   public boolean addWordVertical(String word,int row, int col){
      char[][] I = data;           //If things go bad, set data back to this
      String temp = word;
      int place = 0;                             //Keep track of which char in temp we are looking at
      if (row+word.length() <= data.length){   //See if the word will fit in the col

        for (int i=row;i<temp.length() + row ;i++){

          if ( data[i][col] == '_' || Character.toString( data[i][col] ).equals( temp.substring (place,place+1) ) ) { //If current place at row is empty then all is good, or if place is same as char at word
            data[i][col]=temp.charAt(place);    //Change spot row and move place up one
            wordsAdded.add(temp.substring (place,place+1));
            place++;
          }else{
            data=I; //If
            return false;
          }

        }
        return true;
      }

      data=I;
      return false;
    }


   public boolean addWordDiagonal(String word,int row, int col){
     char[][] I = data;           //If things go bad, set data back to this
     String temp = word;
     int place = 0;                             //Keep track of which char in temp we are looking at
     int colplace = col;
     if (row+word.length() <= data.length && col+word.length() <= data[row].length){   //See if the word will fit in the col

       for (int rowplace=row;rowplace<temp.length() + row ;rowplace++){

         if ( data[rowplace][colplace] == '_' || Character.toString( data[rowplace][colplace] ).equals( temp.substring (place,place+1) ) ) { //If current place at row is empty then all is good, or if place is same as char at word
           data[rowplace][colplace]=temp.charAt(place);    //Change spot row and move place up one
           colplace ++;
           place++;
         }else{
           data=I; //If
           return false;
         }

       }
       return true;
     }

     data=I;
     return false;
   }



}
