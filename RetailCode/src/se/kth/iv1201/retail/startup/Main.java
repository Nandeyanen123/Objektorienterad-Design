package se.kth.iv1201.retail.startup;

import se.kth.iv1201.retail.controller.Controller;
import se.kth.iv1201.retail.integration.Printer;
import se.kth.iv1201.retail.integration.LoggingCreator;
import se.kth.iv1201.retail.view.View;

/**
 * The <code>Main</code> class is responsible for starting up
 * the application.
 */
public class Main {

    public static void main(String[] args){
        LoggingCreator creator = new LoggingCreator();
        Printer printer = new Printer();
        Controller controller = new Controller(creator, printer);
        new View(controller).sampleExecution();
    }
}
