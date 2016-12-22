package com.capgemini.test;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;
import static org.mockito.Mockito.when;
public class AccountTest {

	@Mock
	AccountRepository accountRepository;
	
	AccountService accountService;
	
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImpl(accountRepository);
	}

	/*
	 * create account
	 * 1. when the amount is less than 500 system should throw exception
	 * 2. when the valid info is passed account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InsufficientInitialBalanceException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialBalanceException
	{
		accountService.createAccount(101, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialBalanceException
	{
		Account account = new Account();
		
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		when(accountRepository.save(account)).thenReturn(true);
		
		assertEquals(account, accountService.createAccount(101, 5000));
		
	}/*
	show balance 
	1.when account number is incorrect system should throw exception
	2.when account number is correct system should display the account info*/
	/*@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenTheAccountNumberInCorrectSystemShouldThrowException(int accountNumber)
	{
		Account account=accountRepository.searchAccount(accountNumber);
		if(account.getAccountNumber()!=accountNumber)
		{
			
		}
		
	}*/
	@Test
	public void whenTheAccountNumberIsCorrectSystemDispalyTheAccountInfo()
	{
		Account account=new Account();
		account.setAccountNumber(102);
		account.setAmount(30000);
		when(accountRepository.searchAccount(102)).thenReturn(account);
		assertEquals(account, accountService.showBalance(102));
	}
	/*withdraw amount
	when account number is correct then*/
	@Test
	public void whenAccountNumberAmountIsCorrectSystemShouldReturnWithdrawAmount() throws InsufficientBalanceException
	{
		Account account=new Account();
		account.setAccountNumber(107);
		account.setAmount(5000);
		when(accountRepository.searchAccount(107)).thenReturn(account);
		assertEquals(account, accountService.withdrawAmount(107, 5000));
	}
	public void whenAccountNumberIsCorrectSystemShouldDepositeTheAmount()
	{
		Account account=new Account();
		account.setAccountNumber(71);
		account.setAmount(1000);
		when(accountRepository.searchAccount(71)).thenReturn(account);
		/*assertEquals(account, accountService.depositeAmount(71, 1000));*/
	}
	
}
