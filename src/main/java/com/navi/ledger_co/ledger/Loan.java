package com.navi.ledger_co.ledger;

import com.navi.ledger_co.dto.BalanceDetails;

import java.util.HashMap;

public class Loan {

    private static HashMap<String, HashMap<String, BalanceDetails>> loanMap;

    public Loan() {
        loanMap = new HashMap<>();
    }

    public void addLoanDetailsForUser(String bankName, String userName,
                                        String principalAmount, String years, String rate) {

        BalanceDetails balanceDetails = createBalanceDetails(principalAmount, years, rate);

        HashMap<String, BalanceDetails> balanceDetailsHashMap = new HashMap<>();
        balanceDetailsHashMap.put(userName, balanceDetails);
        loanMap.put(bankName, balanceDetailsHashMap);
    }

    public HashMap<String, HashMap<String, BalanceDetails>> getLoanMap() {
        return loanMap;
    }

    private BalanceDetails createBalanceDetails(String principalAmount, String years, String rate) {
        int noOfYears = Integer.parseInt(years);
        double interest = calculateInterest(principalAmount, years, rate);
        double totalAmount = Long.parseLong(principalAmount) + interest;
        double monthlyEMIAmount = calculateMonthlyEMIAmount(totalAmount, noOfYears);
        return BalanceDetails.builder()
                .totalAmount(totalAmount)
                .totalEMI(noOfYears * 12)
                .monthlyEMIAmount(monthlyEMIAmount)
                .remainingEMI(noOfYears * 12)
                .build();
    }

    private double calculateMonthlyEMIAmount(double totalAmount, int noOfYears) {
        return Math.ceil(totalAmount / (noOfYears * 12));
    }

    private double calculateInterest(String principalAmount, String years, String rate) {
        return Double.parseDouble(principalAmount) * Integer.parseInt(years) * Integer.parseInt(rate) / 100;
    }
}
