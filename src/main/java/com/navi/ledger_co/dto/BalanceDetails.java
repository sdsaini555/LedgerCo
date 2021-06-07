package com.navi.ledger_co.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class BalanceDetails {

    private double totalAmount;
    private int totalEMI;
    private int remainingEMI;
    private double monthlyEMIAmount;
    private HashMap<Integer, Double> lumpsumPaidMap;

    @Builder
    public BalanceDetails(double totalAmount, int totalEMI,
                          int remainingEMI, double monthlyEMIAmount,
                          HashMap<Integer, Double> lumpsumPaidMap) {
        this.totalAmount = totalAmount;
        this.totalEMI = totalEMI;
        this.remainingEMI = remainingEMI;
        this.monthlyEMIAmount = monthlyEMIAmount;
        this.lumpsumPaidMap = lumpsumPaidMap;
    }
}
