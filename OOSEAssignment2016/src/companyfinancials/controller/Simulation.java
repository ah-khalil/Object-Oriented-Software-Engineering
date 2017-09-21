package companyfinancials.controller;

import java.io.*;
import java.util.*;
import companyfinancials.controller.*;
import companyfinancials.view.*;

public class Simulation{
    public static void main(String [] args){
        PropertiesFileParser prop = new PropertiesFileParser();
        EventFileParser event = new EventFileParser();
        PlanFileParser plan = new PlanFileParser();

        /*if there isn't five arguments, throw an exception*/
        if(args.length != 5){
            throw new InsufficientArgumentsException("There needs to be five arguments; three input files and a period of time (in years)");
        }

        try{
            prop.setYearDomain(Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            prop.read(args[0]);
            event.read(args[1]);
            plan.read(args[2]);
        }catch(IOException ioe){
            System.out.println("An IO Error occurred");
        }
    }
}
