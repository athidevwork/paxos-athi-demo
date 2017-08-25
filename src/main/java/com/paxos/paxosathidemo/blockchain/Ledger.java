package com.paxos.paxosathidemo.blockchain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Ledger {
	List<UTXO> ledger = new LinkedList<UTXO>();

	public void add(UTXO account, double amount) {
		ledger.add(new UTXO(account.getAccountName(), DepositMode.CREDIT, amount));
	}

	public void create(UTXO account) {
		System.out.println("Adding amount " + account.getAmount() + " to account " + account.accountName);
		ledger.add(new UTXO(account.getAccountName(), DepositMode.CREDIT, account.getAmount()));
	}

	public void debit(UTXO account, int amount) {
		double acctBal = getBalance(account);
		if (acctBal < amount) {
			System.out.println("Not enough balance on account " + account.accountName + ": Balance = " + acctBal);
			return;
		}
		System.out.println("Debiting amount " + amount + " from account " + account.accountName);		
		ledger.add(new UTXO(account.getAccountName(), DepositMode.DEBIT, -amount));
	}
	
	public void credit(UTXO account, int amount) {
		System.out.println("Crediting amount " + amount + " from account " + account.accountName);	
		ledger.add(new UTXO(account.getAccountName(), DepositMode.CREDIT, amount));
	}
	
	public double getBalance(UTXO account) {
		double balance = 0;
		for (UTXO acct : ledger) {
			//System.out.println(acct.getAccountName() + " : acct.equals(account) : " + acct.equals(account) + ", " + acct.getAmount());
			if (acct.equals(account)) {
				balance += acct.amount;
			}
		}
		return balance;
	}
	
	public void getBalance() {
		double balance = 0;
		HashMap<String, Double> balanceMap = new HashMap<String, Double>();
		
		for (UTXO acct : ledger) {
			if (balanceMap.containsKey(acct.getAccountName())) {				
				balance = balanceMap.get(acct.getAccountName()) + acct.amount;
				balanceMap.put(acct.getAccountName(), balance);
			}
			else
				balanceMap.put(acct.getAccountName(), acct.amount);
		}
		System.out.println("----------------------------------------------");
		System.out.println("Ledger Balances : " + balanceMap);
		System.out.println("----------------------------------------------");
	}
	
	public void getLedgerTransactions() {
		System.out.println("===========================");
		System.out.println("Ledger Transactions:");
		for (UTXO item : ledger) {
			System.out.println(item.getAccountName() + " : " + item.getDepositMode() + ", " + item.getAmount());
		}
		System.out.println("===========================");
	}
}
