package hitne.web.rest.vm;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;

public class PrinterAttribute {
    PrintService printer =
        PrintServiceLookup.lookupDefaultPrintService();

        {
        //
        // Getting print service's attribute set.
        //
        AttributeSet attributes = printer.getAttributes();
        for (Attribute a : attributes.toArray()) {
            String name = a.getName();
            String value = attributes.get(a.getClass()).toString();
            System.out.println(name + " : " + value);
        }
    }
}

