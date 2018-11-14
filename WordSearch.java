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

    //opens file and adds words in it to wordsToAdd
    private void woo(String fileName)throws FileNotFoundException{
      File f = new File(fileName);//can combine
      Scanner in = new Scanner(f);//into one line
      while(in.hasNext()){
        wordsToAdd.add(in.next());
        }
    }
    //Default constructor
    public WordSearch( int rows, int cols, String fileName){
      Random randgen = new Random(); //making random object
      seed = (randgen.nextInt() % 1000); //making seed from that randgen
      try{
        woo(fileName);
      }catch(FileNotFoundException e){
      }
      data = new char[rows][cols];
      for (int row = 0; row < data.length;row++){
        for (int col = 0; col < data[row].length;col++){
          data[row][col]='_';
        }
      }

      wordsAdded = new ArrayList<>(0);

    }

    public WordSearch( int rows, int cols, String fileName, int randSeed){
      Random randgen = new Random(randSeed);
      seed = (randgen.nextInt()%1000);
      try{
        woo(fileName);
      }catch(FileNotFoundException e){}
      data= new char[rows][cols];
      for (int row = 0; row < data.length;row++){
        for (int col = 0; col < data[row].length;col++){
          data[row][col]='_';
        }
      }
      wordsAdded = new ArrayList<>(0);
    }
/*
    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      for (int row = 0; row < data.length;row++){
        for (int col = 0; col < data[row].length;col++){
          data[row][col]='_';
        }
      }
      seed = 1111;
      wordsAdded = new ArrayList<>(0);
    }*/

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



   public boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
     char[][] temp= new char[row][col];
     temp = data;
     int rowplace = row;
     int colplace = col;
     if (Math.abs(rowIncrement+colIncrement)>0&&row+word.length()*rowIncrement>=-1 && row+word.length()*rowIncrement<=data.length && col+word.length()*colIncrement>=-1 && col+word.length()*colIncrement <=data[rowplace].length){   //See if the word will fit in the row

       for (int i=0;i<word.length();i++){

         if ( data[rowplace][colplace] == '_' || data[rowplace][colplace] == word.charAt(i) )  { //If current place at row is empty then all is good, or if place is same as char at word
           data[rowplace][colplace] = word.charAt(i); //Change spot row
           rowplace=rowplace+rowIncrement;
           colplace=colplace+colIncrement;
         }else{
           data= temp;
           return false;
         }

       }
     }else{
       data=temp;
       return false;
     }
     wordsAdded.add(word);
     return true;
   }







    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
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

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
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

    /**Attempts to add a given word to the specified position of the WordGrid.
    *The word is added from top left to bottom right, must fit on the WordGrid,
    *and must have a corresponding letter to match any letters that it overlaps.
    *
    *@param word is any text to be added to the word grid.
    *@param row is the vertical locaiton of where you want the word to start.
    *@param col is the horizontal location of where you want the word to start.
    *@return true when the word is added successfully. When the word doesn't fit,
    *or there are overlapping letters that do not match, then false is returned.
    */
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
