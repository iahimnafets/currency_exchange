package com.exchange;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class ExecuteExchange {

    private static HashMap<String, CurrencyDTO>   allCurrency = new HashMap<>();

    public static void main(String[] args) {
        try {
              String message = "This program transforms one currency into another based on what the user enters, an example can be:\n"+
            " Enter currency: 10.40 \n" +
            " Enter source currency code: EUR \n"+
            " Enter target currency code: USD \n" +
            " it means that I transform the entered value from source to target!";

               System.out.println(message);
               runExchange(true, false );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runExchange(boolean continueWithAnotherExchange, boolean inTheDecision) throws Exception{

        if(continueWithAnotherExchange && !inTheDecision){
            importingFileIntoSystem("input.txt");
            ExchangeDTO exchangeDTO = new ExchangeDTO();
            inputValueForExchange(exchangeDTO);
            calculateExchange(exchangeDTO);
            inTheDecision = true;
        }
        try{
          if(inTheDecision){
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter 1 if you want to continue with another change currency\n" +
                              " if not press 2 and stop the program");
            Integer choice = scanner.nextInt();
            if(choice == 1){
                runExchange(true, false );
            }else if(choice == 2){
                runExchange(false, false );
            }else{
                runExchange(true, false );
            }
          }
        }catch(InputMismatchException ex){
            runExchange(true, true );
        }
    }

    /**
     * Method that calculates the result starting from the
     *  entered value and the target source currencies
     * @param exchangeDTO
     */
    private static void calculateExchange(ExchangeDTO exchangeDTO){
        CurrencyDTO source = allCurrency.get(exchangeDTO.getSourceCode());
        CurrencyDTO target = allCurrency.get(exchangeDTO.getTargetCode());

        BigDecimal sourceRate = source.getRate().setScale(2, RoundingMode.HALF_UP);
        BigDecimal targetRate = target.getRate().setScale(2, RoundingMode.HALF_UP);
        BigDecimal inputAmount = exchangeDTO.getCurrency().setScale(2, RoundingMode.HALF_UP);

        BigDecimal curencySource =  inputAmount.divide(sourceRate, RoundingMode.HALF_UP);
        BigDecimal finalResult = curencySource.multiply(targetRate);

        System.out.println( "Country: " + source.getCountry() +" Name: " + source.getName() + ", Currency: " + exchangeDTO.getSourceCode() + ": " + exchangeDTO.getCurrency() +
                " to --> Country: " + target.getCountry() + " Name: " + target.getName() +", Currency: "+ exchangeDTO.getTargetCode() + " = " + finalResult );
    }


    /**
     * Method that accepts input only values that can be entered,
     * then proceeds only when the data has been completed
     * @param exchangeDTO
     */
    private static void inputValueForExchange(ExchangeDTO exchangeDTO ){
       try {
           Scanner scanner = new Scanner(System.in);
           if( exchangeDTO.getCurrency() == null ) {
               System.out.println("Enter currency:");
               exchangeDTO.setCurrency( scanner.nextBigDecimal() );
           }
           if( exchangeDTO.getSourceCode() == null ) {
               System.out.println("Enter source currency code:");
               String sourceCode = scanner.next();
               sourceCode = sourceCode.toUpperCase(Locale.ROOT);
               if (!allCurrency.containsKey(sourceCode)) {
                   System.out.println("Source code not exist, verify: " + sourceCode + " in the upload file, or insert an existing code");
                   inputValueForExchange(exchangeDTO);
               }else {
                   exchangeDTO.setSourceCode(sourceCode);
               }
           }
           if( exchangeDTO.getTargetCode() == null ) {
               System.out.println("Enter target currency code:");
               String targetCode = scanner.next();
               targetCode = targetCode.toUpperCase(Locale.ROOT);
               if (!allCurrency.containsKey(targetCode)) {
                   System.out.println("Target code not exist, verify: " + targetCode + " in the upload file, or insert an existing code");
                   inputValueForExchange(exchangeDTO);
               }else {
                   exchangeDTO.setTargetCode(targetCode);
               }
           }
       }catch(InputMismatchException ex){
           inputValueForExchange(exchangeDTO);
       }
    }

    /**
     * Method that imports the parameters of the various currencies into memory
     * @param urlNameFileInput
     */
    private static void importingFileIntoSystem(String urlNameFileInput) {
        try {
            String userDirectory = Paths.get("").toAbsolutePath().toString();
            BufferedReader reader = new BufferedReader(new FileReader(userDirectory+"/input/"+urlNameFileInput));
            String line;
            while((line = reader.readLine()) != null) {
                if(!line.trim().equals("")){
                    String [] splitRow = line.split(",");
                    CurrencyDTO currency = new CurrencyDTO();
                    currency.setCountry(splitRow[0].trim());
                    currency.setName(splitRow[1].trim());
                    currency.setCode(splitRow[2].trim());
                    currency.setRate( new BigDecimal(splitRow[3].trim()) );
                    allCurrency.put( currency.getCode() ,currency);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
