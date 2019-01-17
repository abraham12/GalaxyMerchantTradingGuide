/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxymerchanttradingguide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author TIUS
 */
public class IOProcess {
    public static final String WRONGINPUT = "I have no idea what you are talking about";
    
    public static Map data = new HashMap();  // to store the data input
    public static Map basisPreciousMetal = new HashMap();  // to store the basis of precious metal
    public static Map dataRoman = new HashMap();  // to store the data input
    
    
    //Function to get Format Roman Number input without the precious Metal
    //Example Input : glob prok Gold
    //Output : IV
    //P.S. : glob is I, prok is V
    public static String FormatRomanNumber(String _input){
        String result="";
        String[] _inputArr=_input.split(" ");
        
        for(int i=0; i<_inputArr.length-1; i++){
            if(dataRoman.get(_inputArr[i])!=null)
                result += dataRoman.get(_inputArr[i]);
        }
        
        return result;
    }
    
    public static void RunProgram(String _pathFile) throws FileNotFoundException, IOException{
        RomanNumber rn = new RomanNumber(); // set roman Number
        
        
        File file = new File(_pathFile); 
  
        BufferedReader br = new BufferedReader(new FileReader(file) ); 

        String st; 
        while ((st = br.readLine()) != null) {
            String _input=st;
            if(_input.contains("is") && !_input.contains("how much") && !_input.contains("how many")){ // if input contain is, save the value
                String[] _inputArr=_input.split(" is "); // split the input
                
                String[] _metal = _inputArr[0].split(" "); // to check if input is using Credits Score or not
                if(_metal.length > 1){ // Input using Credits Score
                    String _romanNumber=FormatRomanNumber(_inputArr[0]); // String to Get Roman Number
                    boolean isValidRomanNumber = rn.RomanNumberValidation(_romanNumber); // if Roman Number is invalid, then write the wrong input
                    int sizeMetal = _metal.length;
                    boolean _existPreciousMetal = data.containsKey(_metal[sizeMetal-1]);
                    String _preciousMetal="";
                    if(_existPreciousMetal){
                        _preciousMetal=data.get(_metal[sizeMetal-1]).toString();
                    }
                    if(_romanNumber.contains("null") || !isValidRomanNumber ){ // Wrong Input
                        System.out.println(WRONGINPUT);
                    }
                    else if((_preciousMetal.contains("null") || _preciousMetal=="") && isValidRomanNumber){
                        String[] _basis = _inputArr[1].split(" ");
                        //Calculate the exact price of 1 Precious Metal
                        int _pricePreciousMetal = Math.round(Integer.parseInt(_basis[0])/rn.RomanNumberBasis(_romanNumber));
                        
                        data.put(_metal[sizeMetal-1], _pricePreciousMetal); //Store the exact Price of 1 Precious Metal
                        basisPreciousMetal.put(_metal[sizeMetal-1], _basis[1]);
                    }
                }
                else{//Input using Roman Number
                    int _romanNumberValue=rn.getRomanNumber(_inputArr[1].trim());
                    if(_romanNumberValue!= -1){
                        data.put(_inputArr[0].trim(), _romanNumberValue);
                        dataRoman.put(_inputArr[0].trim(), _inputArr[1].trim());  //save input with roman number
                    }
                    else{
                        System.out.println(WRONGINPUT);
                    }
                }
            }
            else if(_input.contains("how much")){
                String[] _inputArr=_input.split(" is ",2); // split the input
                if(_inputArr.length>1){
                    String _romanNumber=FormatRomanNumber(_inputArr[1]); // String to Get Roman Number
                    if(_romanNumber.length()>0){
                        boolean isValidRomanNumber = rn.RomanNumberValidation(_romanNumber);
                        if(_romanNumber.contains("null") || !isValidRomanNumber){ // Wrong Input
                            System.out.println(WRONGINPUT);
                        }
                        else{
                            String[] _ansArr = _inputArr[1].split(" ");
                            for(int i=0; i<(_ansArr.length-1); i++){
                                System.out.print(_ansArr[i]+" ");
                            }
                            System.out.println("is "+rn.RomanNumberBasis(_romanNumber));
                        }
                    }
                    else{
                        System.out.println(WRONGINPUT);
                    }
                }
                else{
                    System.out.println(WRONGINPUT);
                }
            }
            else if(_input.contains("how many")){
                //Kurang checking roman number Validation
                String[] _inputArr=_input.split(" is ",2); // split the input
                String _stringBasis = _inputArr[0].split(" ")[2];
                
                //Copy String after is and before question mark
                String[] _metal = Arrays.copyOf(_inputArr[1].split(" "),_inputArr[1].split(" ").length-1);
                StringBuilder sb = new StringBuilder();
                for(String s : _metal) {
                    sb.append(s+" ");
                }
                String _inputMetal = sb.toString().trim();
                String _romanNumber=FormatRomanNumber(_inputMetal); // String to Get Roman Number
                boolean isValidRomanNumber = rn.RomanNumberValidation(_romanNumber);
                if(_romanNumber.contains("null") || !isValidRomanNumber || data.get(_metal[_metal.length-1])==null){ // Wrong Input
                    System.out.println(WRONGINPUT);
                }
                else{
                    System.out.println(_inputMetal +" is "+(rn.RomanNumberBasis(_romanNumber)*(int)data.get(_metal[_metal.length-1]) ) +" "+basisPreciousMetal.get(_metal[_metal.length-1]).toString());
                }
            }
            else{
                System.out.println(WRONGINPUT);
            }
        }      
    }
    
}
