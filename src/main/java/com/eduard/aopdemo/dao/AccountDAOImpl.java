package com.eduard.aopdemo.dao;

import com.eduard.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;

    private String serviceCode;

    @Override
    public void addAccount(Account account, boolean vipFlag) {

        System.out.println(getClass() + " : DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public void showAccount(Account theAccount) {

        System.out.println(getClass() + " showing account...");

    }

    public String getName() {

        System.out.println("in getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("in setName");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println("in getServiceCode");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println("in setServiceCode");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {

        return findAccounts(false);

    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        //for academic purposes , simualte and exception

        if (tripWire){
            throw new RuntimeException("Tripped by the wire");
        }

        List<Account> myAccounts =  new ArrayList<>();

        //create sample accounts
        Account pap = new Account("Geneve","gold");
        Account pap1 = new Account("Geneveve","silver");
        Account pap2 = new Account("Pope","platinum");

        //add them to our account list
        myAccounts.add(pap);
        myAccounts.add(pap1);
        myAccounts.add(pap2);

        return myAccounts;
    }
}
