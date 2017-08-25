/**
 * 
 */
package com.paxos.paxosathidemo.blockchain;

/**
 * @author Athi
 *
 */
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ledger ledger = new Ledger();
		
		UTXO accountA = new UTXO("A", DepositMode.CREDIT, 0);
		UTXO accountB = new UTXO("B", DepositMode.CREDIT, 0);
		UTXO accountC = new UTXO("C", DepositMode.CREDIT, 0);
		
		ledger.create(accountA);
		ledger.create(accountB);
		ledger.create(accountC);
		
		ledger.add(accountB, 400);
		ledger.add(accountB, 100);
		ledger.add(accountC, 200);
		
		ledger.getBalance();
		
		//Transaction 1
		ledger.debit(accountB, 400);
		ledger.credit(accountC, 300);
		ledger.credit(accountB, 100);
		
		ledger.getBalance();
		
		//Transaction 2
		ledger.debit(accountC, 200);
		ledger.debit(accountC, 300);
		ledger.credit(accountA, 400);
		ledger.credit(accountC, 100);	
		
		ledger.getBalance();
		
		//Transaction 3
		ledger.debit(accountB, 500);
		
		ledger.getLedgerTransactions();
		
		ledger.getBalance();
	}
}
