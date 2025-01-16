package com.vehicle.valuation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class ReadConfigFile {
    public static List<String> getVehicleRegistrationDetails(String inputName) throws IOException {
        BufferedReader bufferedReader = null;
        String[] text = null;
        List<String> regDetails = new ArrayList<String>();
        try {
            File inputFile = getFile(inputName);
            bufferedReader = new BufferedReader(new FileReader(inputFile.getAbsolutePath()));
            String regName;
            while ((regName = bufferedReader.readLine()) != null) {
                String reg = "(^[A-Z]{2}[0-9]{2}([A-Z]{3}|\\s[A-Z]{3})$)";
                text = regName.split(" ");
                for (String str : text) {
                    if (Pattern.matches(reg, str)) {
                        regDetails.add(str);
                    }
                }

            }

        } catch (Exception e) {
            System.err.println("Error Details: " + e.getMessage());
        } finally {
            bufferedReader.close();
        }
        return regDetails;
    }

    public static Map<String, List<String>> getExpectedDetails(String outputName) throws IOException {
        BufferedReader bufferedReader = null;
        String outLine = " ";
        List<String> outputDetails = new ArrayList<String>();
        Map<String, List<String>> expectedDetails = new HashMap<String, List<String>>();
        try {
            File inputFile = getFile(outputName);
            bufferedReader = new BufferedReader(new FileReader(inputFile.getAbsolutePath()));
            while ((outLine = bufferedReader.readLine()) != null) {
                outputDetails.add(outLine);
            }
            outputDetails.remove(0);

            for (String expectedValue : outputDetails) {
                String[] exp = expectedValue.split(",");
                List<String> list = Arrays.asList(exp);
                expectedDetails.put(list.get(0), list);
            }

        } catch (Exception e) {
            System.err.println("Error Details: " + e.getMessage());
        } finally {
            bufferedReader.close();
        }

        return expectedDetails;
    }

    private static File getFile(String filepath) {
        ClassLoader classLoader = ReadConfigFile.class.getClassLoader();
        return new File(classLoader.getResource(filepath).getFile());
    }
}


