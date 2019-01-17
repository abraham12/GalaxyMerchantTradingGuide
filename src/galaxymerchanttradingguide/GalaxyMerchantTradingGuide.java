/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxymerchanttradingguide;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import galaxymerchanttradingguide.RomanNumber;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author TIUS
 */
public class GalaxyMerchantTradingGuide {
 
    public static String DEFAULT_PATH="C:\\\\Users\\\\TIUS\\\\Desktop\\\\InputTest.txt";
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //The Program will read the file path as input
        String _filePath = "";
        
        if(args.length>0){
            _filePath=args[0];
        }
        else{
            _filePath=DEFAULT_PATH;
        }
        try {
            IOProcess.RunProgram(_filePath);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
