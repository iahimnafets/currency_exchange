1) Unzip the .ZIP file

2) Import into IntelliJ as an existing project

3) Change the configuration file as desired, in this project
    "input/input.txt"

4) Open the class: ExecuteExchange and launch the application

5) Enter the values required by the system:
   currency:
    1,34
   Enter source currency code:
    USD
   Enter target currency code:
    EUR

  Wait for the calculation results
  
See the video how it works:
 https://www.youtube.com/watch?v=QaMCpARoxE8
  

Java Currency Converter
We require you to develop a command line currency converter application that prompts for
a user input of;

• A source currency amount in the format x.yy
• A source ISO 4217 currency code
• A target ISO 4217 currency code

The application should return the value of the source amount converted to the target
currency, rounded to 2 decimal places (rounded up), together with the target country and
name. 
After displaying the converted value the program should to continue.
need an action from user to exit 

The available exchange rates should be contained in a comma separated value file and be all
to GBP. This file should be in the format of:

COUNTRY,NAME,CODE,RATE
Example file contents:
United Arab Emirates, Dirhams, AED, 7.2104
Australia, Dollars, AUD, 1.51239
Bosnia and Herzegovina, Convertible Marka, BAM, 2.60565
Bulgaria, Leva, BGN, 2.60948

This is a simple application, we are looking for written code that is extensible, maintainable
and adheres to good modern design principals.
