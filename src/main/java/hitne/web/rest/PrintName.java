package hitne.web.rest;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class PrintName {

    public String printName(){

        PrintService service =
                PrintServiceLookup.lookupDefaultPrintService();

           String printServiceName = service.getName();
           return printServiceName;
//            System.out.println("Print Service Name is " + printServiceName);

    }
}
