/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import galaxymerchanttradingguide.IOProcess;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TIUS
 */
public class TestGalaxyMerchant {
    private static String _filePath="";
    public TestGalaxyMerchant() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        //Default File Path
        _filePath="C:\\\\Users\\\\TIUS\\\\Desktop\\\\InputTest.txt";
    }
    
    @AfterClass
    public static void tearDownClass() {
        _filePath="";
    }

    //
    @Test
    public void TestGalaxyMerchant() throws IOException {
        IOProcess.RunProgram(_filePath);
    }
}
