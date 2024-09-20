//File used only and specifically for testing the code
//do not alter the Main.java unless you are implementing
//the main for the final project

//Check with the others if they are using the TestingMain before
//Deleting its code and doing your own testing

import java.util.ArrayList;

public class TestingMain {
    public static void main(String[] args){
        String palavra1 = "abacates";
        String palavra2 = "abacate";
        String palavra3 = "abacate";
        String palavra4 = "ABACATE";

        //CompareTo function requires a default UPPER or LOWER case comparison

        //How the algorithm works:
        //First compare the letters
        //Second: compare the String size
        //Smaller words with same letters go to the left

        System.out.println("Negativo: palavra 1 vem antes");
        System.out.println("Positivo: palavra 1 vem depois");
        System.out.println("0: palavras iguais");
        System.out.println(palavra1.compareTo(palavra2));
    }
}
