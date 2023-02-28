package com.donggl.common.junit4;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


class HelloTest {
    private Hello hello;

    //在当前类中的所有@Test方法前执行一次
    @BeforeEach
    void setUp() {
        System.out.println("=====start=====");
        hello = new Hello();
    }

    //在当前类中的每个@Test方法后执行一次
    @AfterEach
    void tearDown() {
        System.out.println("=====end=====");
    }

    @Test
    void helloWorld() {
        Assert.assertEquals("Hello World",hello.helloWorld());
    }

    @Test
    void addition() {
        Assert.assertEquals(10,hello.addition(3,7));
    }
}