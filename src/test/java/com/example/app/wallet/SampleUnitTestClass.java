package com.example.app.wallet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SampleUnitTestClass {

    Calculator calculatorTest;

    @BeforeEach
    void setup() {
        System.out.println("inside before each");
        this.calculatorTest = new Calculator();
    }

    @AfterEach
    void afterEach() {
        System.out.println("*************");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("inside BEFORE ALLL -----------");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("inside AFTER ALLL -----------");
    }

    @Nested
    class TestingAddMethod {

        @Test
        public void should_returnEquals_when_addTwoNumber(){

            // BDD --> behaviour driven development
            // given - when - then

            //given
            int firstNumber = 10;
            int secondNumber = 20;
            int expected = 30;

            //when
            int actual = calculatorTest.add(firstNumber,secondNumber);

            //then
            //Assertions.assertEquals(expected, actual);
            assertEquals(expected, actual);
        }

        @Test
        //@DisplayName(value = "test2")
        //@RepeatedTest(10)
        public void should_returnNotEquals_when_addTwoNumber(){

            // BDD --> behaviour driven development
            // given - when - then

            //given
            int firstNumber = 10;
            int secondNumber = 20;
            int expected = 40;

            //when
            int actual = calculatorTest.add(firstNumber,secondNumber);

            //then
            assertNotEquals(expected, actual);
        }

    }

    @Nested
    class TestingMultiplyMethod {
        @ParameterizedTest
        @ValueSource(ints = {-10, 1 , 0, 25})
        public void should_returnZero_when_multipNumberwithZero(int givenSource){

            int firstNumber = givenSource;
            int secondNumber = 0;

            int actual = calculatorTest.multip(firstNumber,secondNumber);

            assertTrue(actual == 0);
        }


        @ParameterizedTest(name = "1st={0}, 2th={1} ")
        @CsvSource(value = {"-10, -1" , "-3, -25", "-3 , -45"})
        public void should_returnTrue_when_multipTwoNegativeNumber(int givenfirstNumber, int givenSecondNumber){

            //given
            int firstNumber = givenfirstNumber;
            int secondNumber = givenSecondNumber;

            //when
            int actual = calculatorTest.multip(firstNumber,secondNumber);

            //then
            assertTrue(actual > 0);
        }

    }

    @Nested
    class TestingDevideMethod {
        @Test
        public void should_throwException_When_devideNumberWithZero(){
            // given
            int firstNumber = 10;
            int secondNumber = 0;

            // when
            Executable executable = () -> calculatorTest.devide(firstNumber, secondNumber);

            // then
            assertThrows(ArithmeticException.class, executable);
        }

    }

    class Calculator{

        int add(int a, int b) {return a+b;}
        int multip(int a, int b) {return a*b;}
        int devide(int a, int b) {return a / b;}

    }

}
