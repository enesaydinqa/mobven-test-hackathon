package com.hackathon.annotation;

import com.hackathon.extensions.AndroidExtension;
import com.hackathon.extensions.ExtentReportExtension;
import com.hackathon.provider.CrossDeviceProvider;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({AndroidExtension.class, ExtentReportExtension.class})
@ParameterizedTest
@ArgumentsSource(CrossDeviceProvider.class)
public @interface AirBnbTest
{
}
