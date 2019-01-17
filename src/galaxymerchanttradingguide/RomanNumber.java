/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxymerchanttradingguide;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TIUS
 */
public class RomanNumber {
    private static Map romanNumber = new HashMap();
    
    public RomanNumber(){
        /*
        I 1   V 5   X 10   L 50   C 100   D 500   M 1,000
        */
        romanNumber.put("I",1);
        romanNumber.put("IV",4);
        romanNumber.put("V",5);
        romanNumber.put("IX",9);
        romanNumber.put("X",10);
        romanNumber.put("XL",40);
        romanNumber.put("L",50);
        romanNumber.put("XC",90);
        romanNumber.put("C",100);
        romanNumber.put("CD",400);
        romanNumber.put("D",500);
        romanNumber.put("CM",900);
        romanNumber.put("M",1000);
        
    }
    
    public int getRomanNumber(String _romanNumber){
        if(romanNumber.get(_romanNumber)==null){
            return -1;
        }
        else{
            return (int)romanNumber.get(_romanNumber);
        }
    }
    //Kurang Getter untuk ambil dari Roman Number MAP
    //To Convert the String in Roman Number to Integer
    public static int RomanNumberBasis(String _romanNumber){
        int result=0;
        
        int tempResult1=0, tempResult2=0; //to collect the temporary result each digit roman number
        int sizeRomanNumber=_romanNumber.length(); // length of string input Roman Number
        
        
        for(int i=0; i<sizeRomanNumber; i++){
            tempResult1=(int)romanNumber.get(""+_romanNumber.charAt(i)); // Get Current Roman Number in Decimal Base
            
            if((i+1)<sizeRomanNumber){
                tempResult2=(int)romanNumber.get(""+_romanNumber.charAt(i+1)); // Get Next Roman Number in Decimal Base
                //Rule 1: If the Current Roman Number less than Next Roman Number, Next Roman Number must be substract with Current Roman Number to get the real result in Decimal Base
                if(tempResult1<tempResult2){    
                    result+=(tempResult2-tempResult1);
                    i++; // to move the pointer in next index
                }
                //Rule 2: If the Current Roman Number equal or more than Next Roman Number, Add Current Roman Number to get the real result in Decimal Base
                else{
                    result+=(tempResult1);
                }
            }
            else{
                result+=tempResult1;
                i++;
            }
        }
        
        return result;
    }
    
    //To Check the String in Roman Number is Valid or Not
    public static boolean RomanNumberValidation(String _romanNumber){
        boolean result=true;
        char temp = _romanNumber.charAt(0);
        int count=0; // To Check how many Character sequence Roman Number
        int sizeRomanNumber=_romanNumber.length();
        for(int i=1; i<sizeRomanNumber; i++){
            if(count>2){
                // count>2 because index start at 1
                // maximum repeated roman number is 3 times in sequence
                result=false;
                break;
            }
            if(_romanNumber.charAt(i)==temp){
                if(temp=='D'||temp=='L'||temp=='V'){
                    //Roman Number D L V never can be repeated
                    //if repeated, roman number is invalid
                    result=false;
                    break;
                }
                else{
                    count++;
                }
            }
            else{
                temp=_romanNumber.charAt(i);
                count=0;
            }
        }
        
        if(result){
            temp = _romanNumber.charAt(0);
            // Check the roman number
            for(int i=1; i<sizeRomanNumber; i++){
                if(temp=='I'){
                    // I Can not be placed before L, C, D or M
                    if(_romanNumber.charAt(i)=='L' || _romanNumber.charAt(i)=='C' ||
                        _romanNumber.charAt(i)=='D' ||  _romanNumber.charAt(i)=='M'){
                        result=false;
                        break;
                    }
                    else{
                        temp=_romanNumber.charAt(i);
                    }
                }
                else if(temp=='X'){
                    // X Can not be placed before D or M
                    if(_romanNumber.charAt(i)=='D' || _romanNumber.charAt(i)=='M'){
                        result=false;
                        break;
                    }
                    else{
                        temp=_romanNumber.charAt(i);
                    }
                }
            }
        }
        
        if(result){
            // to check in special case : example: MCMM is invalid
            int tempResult1=0, tempResult2=0, tempResult3=0; //to collect the temporary result each digit roman number
            
            for(int i=0; i<sizeRomanNumber; i++){
                if(romanNumber.get(""+_romanNumber.charAt(i))!=null){
                    tempResult1=(int)romanNumber.get(""+_romanNumber.charAt(i)); // Get Current Roman Number in Decimal Base

                    if((i+1)<sizeRomanNumber){
                        if(romanNumber.get(""+_romanNumber.charAt(i+1))!=null){
                            tempResult2=(int)romanNumber.get(""+_romanNumber.charAt(i+1)); // Get Next Roman Number in Decimal Base
                            // example: CM
                            if(tempResult1<=tempResult2){    
                                if((i+2) < sizeRomanNumber){
                                    if(romanNumber.get(""+_romanNumber.charAt(i+2))!=null){
                                        tempResult3=(int)romanNumber.get(""+_romanNumber.charAt(i+2)); // Get Next Next Roman Number in Decimal Base
                                        if(tempResult1<tempResult3){ //the most left must be greater
                                            result=false;
                                            break;
                                        }
                                        else{
                                            tempResult1=(tempResult2-tempResult1);
                                            if(tempResult1>tempResult3){

                                                i+=2; // to move the pointer in next index
                                            }
                                            else{
                                                result=false;
                                                break;
                                            }
                                        }
                                    }
                                    else{
                                        result=false;
                                        break;
                                    }
                                }
                            }
                        }
                        else{
                            result=false;
                            break;
                        }
                    }
                }
                else{
                    result=false;
                    break;
                }
            }
        }
        return result;
    }
}
