package com.paxos.paxosathidemo.blockchain;

public class UTXO {
	String accountName;
	DepositMode depositMode;
	double amount;
	
	public UTXO(String accountName, DepositMode depositMode, double amount) {
		this.accountName = accountName;
		this.depositMode = depositMode;
		this.amount = amount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public DepositMode getDepositMode() {
		return depositMode;
	}

	public void setDepositMode(DepositMode depositMode) {
		this.depositMode = depositMode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((depositMode == null) ? 0 : depositMode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UTXO other = (UTXO) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		return true;
	}
}
