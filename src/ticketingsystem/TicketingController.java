/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketingsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PanDim
 */
public class TicketingController {
    private List <Ticket> submittedTickets;    

    public TicketingController() {
        this.submittedTickets = new ArrayList<>();
    }

    public List<Ticket> getSubmittedTickets() {
        return submittedTickets;
    }
    
    
    //ΜΕΘΟΔΟΣ ΕΙΣΑΓΩΓΗΣ ΑΙΤΗΜΑΤΟΣ ΣΤΗ ΛΙΣΤΑ submittedTickets
    public void addTicket(Ticket ticket){
        submittedTickets.add(ticket);
    }

    //ΜΕΘΟΔΟΣ ΓΙΑ ΕΜΦΑΝΙΣΗ ΥΠΟΒΛΗΘΕΝΤΩΝ ΑΙΤΗΜΑΤΩΝ
    public void printSubmittedTickets(){
        //ΕΜΦΑΝΙΣΗ ΑΙΤΗΜΑΤΩΝ
        for (Ticket ticket : submittedTickets) { 
            System.out.println(ticket.toString());
        }
        System.out.println("==================================================");
    }
    
    //ΜΕΘΟΔΟΣ ΓΙΑ ΕΜΦΑΝΙΣΗ ΟΛΟΚΛΗΡΩΜΕΝΩΝ ΑΙΤΗΜΑΤΩΝ
    public void printCompletedTickets(){
        //ΕΜΦΑΝΙΣΗ ΑΙΤΗΜΑΤΩΝ
        for (Ticket ticket : submittedTickets) { 
            System.out.println(ticket.toString());
            //ΕΜΦΑΝΙΣΗ ΚΑΤΑΧΩΡΗΜΕΝΩΝ ΕΝΕΡΓΕΙΩΝ ΑΙΤΗΜΑΤΟΣ
            for (TicketAction action : ticket.getTicketActions()) { 
                System.out.println(action.toString());               
            }
            System.out.println("----------------------------------------------");
        }
    }

        
    public void printTechniciansRanking(){
        HashMap <Technician, Double> technicianMeanActions = new HashMap<>();

        for (Ticket ticket : submittedTickets) {
            Technician technician = ticket.getTechnician();
            double numberOfActions = ticket.getTicketActions().size();
            if (technicianMeanActions.containsKey(technician)){
                double mo = (double)(technicianMeanActions.get(technician) + numberOfActions)/2.0;
                technicianMeanActions.put(technician, mo); 
            }
            else {
                technicianMeanActions.put(technician, numberOfActions); 
            }
        }

        sortByValue(technicianMeanActions).forEach((key,value) ->System.out.println(key.getName() + " = " + value));
    }

    // function to sort hashmap by values 
    private static HashMap <Technician, Double> sortByValue(HashMap <Technician, Double> hm) { 
        // Create a list from elements of HashMap 
        List <Map.Entry <Technician, Double>> list = new LinkedList<Map.Entry<Technician, Double>>(hm.entrySet()); 

        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<Technician, Double>>() { 
            public int compare(Map.Entry<Technician, Double> o1,  
            Map.Entry<Technician, Double> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 

        // put data from sorted list to hashmap
        HashMap <Technician, Double> temp = new LinkedHashMap <Technician, Double>(); 
        for (Map.Entry <Technician, Double> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }

}
