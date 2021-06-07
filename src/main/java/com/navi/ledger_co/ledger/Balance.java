package com.navi.ledger_co.ledger;

import com.navi.ledger_co.dto.BalanceDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Balance {

    public String getBalanceForUser(Loan loan, String bankName, String userName, String emiNo) {
        HashMap<String, HashMap<String, BalanceDetails>> balanceMap = loan.getLoanMap();

        HashMap<String, BalanceDetails> balanceDetailsHashMap = balanceMap.get(bankName);

        BalanceDetails balanceDetails = balanceDetailsHashMap.get(userName);

        HashMap<Integer, Double> lumpsumPaidMap = balanceDetails.getLumpsumPaidMap();
        if (null == lumpsumPaidMap) {
            double amountPaid = balanceDetails.getMonthlyEMIAmount() * Integer.parseInt(emiNo);
            int remainingEMI = balanceDetails.getTotalEMI() - Integer.parseInt(emiNo);
            return String.join(" ", bankName, userName, String.format("%.0f",amountPaid), String.valueOf(remainingEMI));
        }
        List<Integer> keysList = new ArrayList<>(lumpsumPaidMap.keySet());
        double amountPaid;
        int emiNumber = Integer.parseInt(emiNo);
        int index = 0;
        for (index = 0; index < keysList.size(); index++){
            if (emiNumber >= keysList.get(index))
                break;
        }

        if (index - 1 == 0)
            amountPaid = balanceDetails.getMonthlyEMIAmount() * Integer.parseInt(emiNo);
        else {
            double amount = 0;
            for (int ind = 0; ind <= index; ind++) {
                amount += lumpsumPaidMap.get(keysList.get(ind));
            }
            amountPaid = balanceDetails.getMonthlyEMIAmount() * Integer.parseInt(emiNo) + amount;
        }
        double remainingEMI = Math.ceil((balanceDetails.getTotalAmount() - amountPaid) / balanceDetails.getMonthlyEMIAmount());

        return String.join(" ", bankName, userName, String.format("%.0f",amountPaid), String.format("%.0f",remainingEMI));
    }
}
