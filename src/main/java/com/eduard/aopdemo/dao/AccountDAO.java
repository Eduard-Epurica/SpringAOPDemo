package com.eduard.aopdemo.dao;

import com.eduard.aopdemo.Account;

import java.util.List;

public interface AccountDAO {

    void addAccount(Account theAccount, boolean vipFlag);

    void showAccount(Account theAccount);

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);

    List<Account> findAccounts();

    List<Account> findAccounts(boolean tripWire);
}
