package com.navi.ledger_co.input;

import com.navi.ledger_co.ledger.Balance;
import com.navi.ledger_co.ledger.Loan;
import com.navi.ledger_co.ledger.Payment;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.navi.ledger_co.constants.Constants.*;

public class FileProcessor {

    public void processInputFile(File file, Loan loan) {
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String command = sc.nextLine();

                processInputCommand(command, loan);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!! Please check the file and the location provided!");
        } catch (Exception e) {
            System.out.println("exception occurred " + e);
        }
    }

    private void processInputCommand(String command, Loan loan) throws Exception {
        if (StringUtils.isEmpty(command))
            throw new Exception("Empty line is found");

        String[] commandParams = command.split(" ");
        String commandResult = "";
        Balance balance = new Balance();
        Payment payment = new Payment();

        switch (commandParams[0]) {
            case LOAN:
                loan.addLoanDetailsForUser(commandParams[1],
                        commandParams[2], commandParams[3],
                        commandParams[4], commandParams[5]);
                break;

            case PAYMENT:
                payment.paidAmountByUser(loan, commandParams[1],
                        commandParams[2], commandParams[3],
                        commandParams[4]);
                break;

            case BALANCE:
                commandResult = balance.getBalanceForUser(loan, commandParams[1],
                        commandParams[2], commandParams[3]);
                break;

            default:
                commandResult = INVALID_COMMAND;
                break;
        }

        System.out.println(commandResult);
    }
}
