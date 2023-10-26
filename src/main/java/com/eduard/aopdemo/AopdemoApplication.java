package com.eduard.aopdemo;

import com.eduard.aopdemo.dao.AccountDAO;
import com.eduard.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.SortedMap;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

		return runner -> {

			//demoTheBeforeAdvice(theAccountDAO,theMembershipDAO);
			//demoTheAfterReturningAdvice(theAccountDAO);

			//demoTheAfterThrowingAdvice(theAccountDAO);

			demoTheAfterAdvice(theAccountDAO);

		};
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {

		// call the method to find the accounts
		List<Account> papaya = null;

		try {
			// add a boolean flag to simulate exception
			boolean tripWire=false;

			papaya = theAccountDAO.findAccounts(tripWire);
		}catch (Exception exc){
			System.out.println("\n\nException on find accounts: " + exc);
		}

		//display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice ");
		System.out.println("--------");
		System.out.println(papaya);
		System.out.println("\n");


	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {

		// call the method to find the accounts
		List<Account> papaya = null;

		try {
			// add a boolean flag to simulate exception
			boolean tripWire=true;

			papaya = theAccountDAO.findAccounts(tripWire);
		}catch (Exception exc){
			System.out.println("\n\nException on find accounts: " + exc);
		}

		//display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice ");
		System.out.println("--------");
		System.out.println(papaya);
		System.out.println("\n");


	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {

		// call the method to find the accounts
		List<Account> papaya = theAccountDAO.findAccounts();

		//display the accounts
		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice ");
		System.out.println("--------");
		System.out.println(papaya);
		System.out.println("\n");

	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

		//call the business method
		Account myAccount = new Account();
		myAccount.setLevel("silver");
		myAccount.setName("Papaya");
		theAccountDAO.addAccount(myAccount, true);

		//call another method in the dao package
		theAccountDAO.showAccount(myAccount);

		// call the accountdao getter/setter methods
		theAccountDAO.setName("Foo Bar");
		theAccountDAO.setServiceCode("Silver");
		String name = theAccountDAO.getName();
		String serviceCode = theAccountDAO.getServiceCode();

		//call the membership business method
		theMembershipDAO.addSillyMember();
	}

}
