package com.paxos.paxosathidemo.blockchain;

import java.util.LinkedList;
import java.util.List;

public class Ledger {
	List<UTXO> ledger = new LinkedList<UTXO>();

	public void add(UTXO account, double amount) {
		//account.addUTXO(amount);
		//account.setAmount(amount);
		ledger.add(new UTXO(account.getAccountName(), DepositMode.CREDIT, amount));
	}

	/*public void create(String accountName, double initalAmount) {	
		ledger.add(new UTXO(accountName, initalAmount));
	}*/

	public void create(UTXO account) {
		ledger.add(account);
	}

	public void debit(UTXO account, int amount) {
		System.out.println("Debiting amount " + amount + " from account " + account.accountName);
		//account.debit(amount);		
		ledger.add(new UTXO(account.getAccountName(), DepositMode.CREDIT, -amount));
	}
	
	public void credit(UTXO account, int amount) {
		System.out.println("Crediting amount " + amount + " from account " + account.accountName);
		//account.credit(amount);	
		ledger.add(new UTXO(account.getAccountName(), DepositMode.CREDIT, amount));
	}
	
	public void getBalance(UTXO account) {
		double balance = 0;
		for (UTXO acct : ledger) {
			if (acct.equals(account)) {
				balance += acct.amount;
			}
		}
		/*for (Double amount : amountList) {
			balance += amount;
		}*/
		System.out.println("Account " + account.accountName + " : " + balance);
	}
	
	public void getLedgerTransactions() {
		System.out.println("Ledger Transactions:");
		for (UTXO item : ledger) {
			System.out.println(item.getAccountName() + " - " + item.getDepositMode() + "-" + item.getAmount());
		}
	}
}
