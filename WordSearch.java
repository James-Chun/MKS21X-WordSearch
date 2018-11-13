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

    /*
    public WordSearch( int rows, int cols, String fileName){

    }
    //Choose a randSeed using the clock random

    public WordSearch( int rows, int cols, String fileName, int randSeed){

    }
    //Use the random seed specified.




    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      for (int row = 0; row < data.length;row++){
        for (int col = 0; col < data[row].length;col++){
          data[row][col]='_';
        }
      }
      seed = 1111;
      wordsAdded = new ArrayList<>(0);
    }



    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for (int row = 0; row < data.length;row++){
        for (int col = 0; col < data[row].length;col++){
          data[row][col]='_';
        }
      }
    }




    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
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



     /**Attempts to add a given word to the specified position of the WordGrid.
    *The word is added in the direction rowIncrement,colIncrement
    *Words must have a corresponding letter to match any letters that it overlaps.
    *
    *@param word is any text to be added to the word grid.
    *@param row is the vertical locaiton of where you want the word to start.
    *@param col is the horizontal location of where you want the word to start.
    *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
    *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction
    *@return true when: the word is added successfully.
    *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
    *        OR there are overlapping letters that do not match
    */
   public boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
     char[][] ifThingsGoWrong = data;           //If things go bad, set data back to this
     int rowplace = row;
     int colplace = col;
     if (row+word.length()*rowIncrement>=-1 && row+word.length()*rowIncrement<=data.length && col+word.length()*colIncrement>=-1 && col+word.length()*colIncrement<=data[rowplace].length){   //See if the word will fit in the row

       for (int i=0;i<word.length();i++){

         if ( data[rowplace][colplace] == '_' || Character.toString( data[rowplace][colplace] ).equals( word.substring (i,i+1) ) ) { //If current place at row is empty then all is good, or if place is same as char at word
           data[rowplace][colplace]=word.charAt(i);    //Change spot row
           rowplace=rowplace+rowIncrement;
           colplace=colplace+colIncrement;
         }else{
           data=ifThingsGoWrong; //If
           return false;
         }

       }
     }else{
       data = ifThingsGoWrong;
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
      char[][] ifThingsGoWrong = data;           //If things go bad, set data back to this
      String temp = word;
      int place = 0;                             //Keep track of which char in temp we are looking at
      if (col+word.length() <= data[row].length){   //See if the word will fit in the row

        for (int i=col;i<temp.length() + col ;i++){

          if ( data[row][i] == '_' || Character.toString( data[row][i] ).equals( temp.substring (place,place+1) ) ) { //If current place at row is empty then all is good, or if place is same as char at word
            data[row][i]=temp.charAt(place);    //Change spot row and move place up one
            wordsAdded.add(temp.substring (place,place+1));
            place++;
          }else{
            data=ifThingsGoWrong; //If
            return false;
          }

        }
        return true;
      }

      data=ifThingsGoWrong;
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
      char[][] ifThingsGoWrong = data;           //If things go bad, set data back to this
      String temp = word;
      int place = 0;                             //Keep track of which char in temp we are looking at
      if (row+word.length() <= data.length){   //See if the word will fit in the col

        for (int i=row;i<temp.length() + row ;i++){

          if ( data[i][col] == '_' || Character.toString( data[i][col] ).equals( temp.substring (place,place+1) ) ) { //If current place at row is empty then all is good, or if place is same as char at word
            data[i][col]=temp.charAt(place);    //Change spot row and move place up one
            wordsAdded.add(temp.substring (place,place+1));
            place++;
          }else{
            data=ifThingsGoWrong; //If
            return false;
          }

        }
        return true;
      }

      data=ifThingsGoWrong;
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
     char[][] ifThingsGoWrong = data;           //If things go bad, set data back to this
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
           data=ifThingsGoWrong; //If
           return false;
         }

       }
       return true;
     }

     data=ifThingsGoWrong;
     return false;
   }




}
