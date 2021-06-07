package com.navi.ledger_co;

import com.navi.ledger_co.input.FileProcessor;
import com.navi.ledger_co.ledger.Loan;

import java.io.File;

public class Solution {

    public static void main(String... args) {
        try {
            File file = new File(args[0]);
            Loan loan = new Loan();
            FileProcessor processor = new FileProcessor();
            processor.processInputFile(file, loan);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter file location(s)!");
        }
    }
}
