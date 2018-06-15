package com.horses.architecture.data.repository

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class JedisRepositoryTest {

    @Before
    fun setUp() {

    }

    @Test
    fun printTest() {
        print("Hello test")
    }
}