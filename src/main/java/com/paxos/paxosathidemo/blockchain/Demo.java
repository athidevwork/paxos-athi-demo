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
		
		/*accountB.addUTXO(400);
		accountB.addUTXO(100);
		accountC.addUTXO(200);*/
		
		ledger.create(accountA);
		ledger.create(accountB);
		ledger.create(accountC);
		
		ledger.add(accountB, 400);
		ledger.add(accountB, 100);
		ledger.add(accountC, 400);
		
		/*accountA.getBalance();
		accountB.getBalance();
		accountC.getBalance();*/
		ledger.getBalance(accountA);
		ledger.getBalance(accountB);
		ledger.getBalance(accountC);
		
		//Transaction 1
		ledger.debit(accountB, 400);
		ledger.credit(accountC, 300);
		ledger.credit(accountB, 100);
		
		/*accountB.debit(400);
		accountC.credit(300);
		accountB.credit(100);*/
		
		//Transaction 2
		ledger.debit(accountC, 200);
		ledger.debit(accountC, 300);
		ledger.credit(accountA, 400);
		ledger.credit(accountC, 100);
		
		/*accountC.debit(200);
		accountC.debit(300);
		accountA.credit(400);
		accountC.credit(100);*/		
	}

}
