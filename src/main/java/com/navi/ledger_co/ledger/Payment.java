package com.navi.ledger_co.ledger;

import com.navi.ledger_co.dto.BalanceDetails;

import java.util.HashMap;

public class Payment {


    public void paidAmountByUser(Loan loan, String bankName, String userName, String lumpsumAmount, String emiNo) {

        HashMap<String, HashMap<String, BalanceDetails>> balanceMap = loan.getLoanMap();
        HashMap<String, BalanceDetails> balanceDetailsHashMap = balanceMap.get(bankName);

        BalanceDetails balanceDetails = balanceDetailsHashMap.get(userName);
        int emi = Integer.parseInt(emiNo);
        HashMap<Integer, Double> lumpsumPaidMap = balanceDetails.getLumpsumPaidMap();
        Double amountPaid;
        if (null != lumpsumPaidMap && !lumpsumPaidMap.isEmpty()) {
            amountPaid = lumpsumPaidMap.get(emi);
            lumpsumPaidMap.put(emi, amountPaid + Double.parseDouble(lumpsumAmount));
        } else {
            lumpsumPaidMap = new HashMap<>();
            lumpsumPaidMap.put(emi, Double.parseDouble(lumpsumAmount));
        }
        balanceDetails.setLumpsumPaidMap(lumpsumPaidMap);
        
    }
}
