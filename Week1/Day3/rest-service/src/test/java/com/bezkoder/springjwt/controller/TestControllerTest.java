package com.bezkoder.springjwt.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestControllerTest {

    @InjectMocks
    TestController testController;

    @Test
    public void allAccess(){
        Assert.assertNotNull(testController.allAccess());
    }

    @Test
    public void userAccess() {
        Assert.assertNotNull(testController.userAccess());
    }

    @Test
    public void moderatorAccess() {
        Assert.assertNotNull(testController.moderatorAccess());
    }

    @Test
    public void adminAccess() {
        Assert.assertNotNull(testController.adminAccess());
    }
}
