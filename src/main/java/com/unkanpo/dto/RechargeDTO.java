package com.unkanpo.dto;

public class RechargeDTO implements DataTransferObject<RechargeDTO> {
    private int coinRecharge;

    public RechargeDTO() {
    }

    public int getCoinRecharge() {
        return coinRecharge;
    }

    public void setCoinRecharge(int coinRecharge) {
        this.coinRecharge = coinRecharge;
    }

    public RechargeDTO(int coinRecharge) {
        this.coinRecharge = coinRecharge;
    }

    @Override
    public RechargeDTO getDTO() {
        return this;
    }
}
