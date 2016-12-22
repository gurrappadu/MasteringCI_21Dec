package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.model.Account;

public interface AccountService {
	public Account createAccount(int accountNumber,int amount)throws InsufficientInitialBalanceException;
	public int showBalance(int accountNumber);
	public int withdrawAmount(int accountNumber,int amount) throws InsufficientBalanceException;
	public void depositeAmount(int accountNumber,int amount) throws InsufficientBalanceException;
	
}
