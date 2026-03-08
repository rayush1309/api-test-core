package com.sdetlabs.tests.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

        // 1. Spy System: Ye print karega jab bhi TestNG isko bulayega
        System.out.println("🛠️ Master Switch (Listener) is waking up and attaching Retry Logic!");

        // 2. Brute Force: Bina koi condition check kiye, seedha RetryAnalyzer laga do
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}