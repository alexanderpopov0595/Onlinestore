package com.tsystems.javaschool.onlinestore.service;

import com.tsystems.javaschool.onlinestore.dao.deposit.DepositDao;
import com.tsystems.javaschool.onlinestore.dao.user.UserDao;
import com.tsystems.javaschool.onlinestore.domain.user.Deposit;
import com.tsystems.javaschool.onlinestore.service.deposit.DepositService;
import com.tsystems.javaschool.onlinestore.service.deposit.DepositServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DepositServiceTest {

    private DepositDao depositDaoMock = mock(DepositDao.class);
    private UserDao userDaoMock = mock(UserDao.class);
    private DepositService depositService = new DepositServiceImpl(depositDaoMock, userDaoMock);
    private Deposit deposit;

    @Before
    public void setUp() {
        deposit = new Deposit();
        deposit.setId(1);
        deposit.setBalance(100);
    }


    @Test
    public void shouldReturnUpdatedBalance() {
        when(depositDaoMock.selectDeposit(deposit.getId())).thenReturn(deposit);
        Deposit newDeposit = new Deposit();
        newDeposit.setBalance(100);
        newDeposit.setId(1);
        doNothing().when(depositDaoMock).updateDeposit(newDeposit);
        depositService.updateDepositBalance(newDeposit);
        assertEquals(200, newDeposit.getBalance());
    }
}
