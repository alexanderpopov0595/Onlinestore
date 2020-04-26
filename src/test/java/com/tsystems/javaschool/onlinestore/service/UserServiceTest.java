package com.tsystems.javaschool.onlinestore.service;

import com.tsystems.javaschool.onlinestore.dao.user.UserDao;
import com.tsystems.javaschool.onlinestore.domain.user.Address;
import com.tsystems.javaschool.onlinestore.domain.user.User;
import com.tsystems.javaschool.onlinestore.enums.Status;
import com.tsystems.javaschool.onlinestore.service.user.UserService;
import com.tsystems.javaschool.onlinestore.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserDao userDaoMock = mock(UserDao.class);

    private UserService userService = new UserServiceImpl(userDaoMock);

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setLogin("login");
    }

    @Test
    public void shouldReturnUserById() {
        when(userDaoMock.selectUser(user.getId())).thenReturn(user);
        User selectedUser = userDaoMock.selectUser(1);
        assertEquals(user, selectedUser);
    }

    @Test
    public void shouldReturnUserByLogin() {
        when(userDaoMock.selectUser(user.getLogin())).thenReturn(user);
        User selectedUser = userDaoMock.selectUser("login");
        assertEquals(user, selectedUser);
    }

    @Test
    public void shouldAddNewAddress() {
        User original = new User();
        original.setId(1);
        Address a1 = new Address();
        a1.setId(1);
        List<Address> addressList = new ArrayList<Address>();
        addressList.add(a1);
        original.setAddressList(addressList);
        when(userDaoMock.selectUser(user.getId())).thenReturn(original);
        userService.updateUser(user);
        assertEquals(1, user.getAddressList().size());
    }

    @Test
    public void shouldSetStatusDeleted() {
        User original = new User();
        original.setId(1);
        Address a1 = new Address();
        a1.setId(1);
        a1.setCountry("cs");
        List<Address> addressList = new ArrayList<Address>();
        addressList.add(a1);
        original.setAddressList(addressList);
        when(userDaoMock.selectUser(user.getId())).thenReturn(original);
        userService.updateUser(user);
        System.out.println(user.getAddressList().get(0).getStatus());
        assertEquals(Status.DELETED, user.getAddressList().get(0).getStatus());

    }
}
