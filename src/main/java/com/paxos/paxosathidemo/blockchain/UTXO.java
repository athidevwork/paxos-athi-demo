package com.paxos.paxosathidemo.blockchain;

public class UTXO {
	String accountName;
	DepositMode depositMode;
	double amount;
	
	//List<Double> amountList = new LinkedList<Double>();
	
	public UTXO(String accountName, DepositMode depositMode, double amount) {
		this.accountName = accountName;
		this.depositMode = depositMode;
		this.amount = amount;
		//amountList.add(amount);
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

	
	/*public void getBalance() {
		double balance = 0;
		for (Double amount : amountList) {
			balance += amount;
		}
		System.out.println("Account " + accountName + " : " + balance);
	}
	
	public void addUTXO(double amount) {
		amountList.add(amount);
	}

	public void debit(double amount) {
		amountList.add(-amount);
		
	}

	public void credit(double amount) {
		amountList.add(amount);
	}*/
}
