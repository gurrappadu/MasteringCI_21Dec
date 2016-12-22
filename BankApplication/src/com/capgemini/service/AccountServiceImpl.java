package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl  implements AccountService{
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	AccountRepository accountRepository;
	Account account;
	
	public AccountServiceImpl(AccountRepository accountRepository)
	{
		this.accountRepository=accountRepository;
	}
	@Override
	public Account createAccount(int accountNumber,int amount)throws InsufficientInitialBalanceException
	{
		if(amount<500)
		{
			throw new InsufficientInitialBalanceException();
		}
		
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(accountRepository.save(account))
		{
			return account;
		}
		
		return null;
		
	}
	public int showBalance(int accountNumber)
	
	{  Account account=accountRepository.searchAccount(accountNumber);
		if(account.getAccountNumber()==accountNumber)
		{ 
			System.out.println("account number is:"+account.getAccountNumber());
			System.out.println("account balance:"+account.getAmount());
		}
		return accountNumber;
	}
	public int withdrawAmount(int accountNumber,int amount) throws InsufficientBalanceException
	{
		Account account=accountRepository.searchAccount(accountNumber);
		if(amount>account.getAmount()||amount<=0)
		{
			throw new InsufficientBalanceException();
		}
		if(amount<=account.getAmount())
		{
		   int acctualAmount=account.getAmount();
		   acctualAmount=acctualAmount-amount;
		   account.setAmount(acctualAmount);
		}
		return account.getAmount();
	}
	public void depositeAmount(int accountNumber,int amount) throws InsufficientBalanceException
	{
		Account account=accountRepository.searchAccount(accountNumber);
		if(amount<=0)
		{
			throw new InsufficientBalanceException();
		}
		if(amount>=0)
		{
			int acctualAmount=account.getAmount();
			   acctualAmount=acctualAmount+amount;
		}
	}
	/*public StringBuffer fundTransfer(int fromAccountNumber,int toAccountNumber,int amount)
	{
		
	}*/

}
