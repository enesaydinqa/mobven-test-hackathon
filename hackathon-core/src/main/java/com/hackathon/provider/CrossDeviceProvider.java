package com.hackathon.provider;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class CrossDeviceProvider implements ArgumentsProvider
{
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
    {
        int[] fork = IntStream.range(0, Integer.parseInt(System.getProperty("junit.jupiter.execution.parallel.config.fixed.parallelism"))).toArray();
        return Arrays.stream(fork).boxed().map(Arguments::of);
    }
}