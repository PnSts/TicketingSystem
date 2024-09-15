/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketingsystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ticketingsystem.Ticket.Status;

/**
 *
 * @author PanDim
 */
public class TechnicianManager extends Technician{
    
    Map <Technician.Specialization, Technician> m = new HashMap <>(); 
    
    public TechnicianManager(String name, Specialization specialisation) {
        super(name, specialisation);
    }
    
    //ΧΡΗΣΗ HASHMAP ΓΙΑ ΑΝΤΙΣΤΟΙΧΙΣΗ ΕΞΕΙΔΙΚΕΥΣΗΣ ΜΕ ΤΕΧΝΙΚΟ
    public void mapTechnician(Technician technician){
        m.put(technician.getSpecialisation(), technician);
    }

    public void assignTicketsToTechnicians(List <Ticket> submittedTickets, List <Technician> technicians){
        //ΓΙΑ ΚΑΘΕ ΑΙΤΗΜΑ ΑΝΑΛΟΓΑ ΜΕ ΤΗΝ ΚΑΤΗΓΟΡΙΑ ΤΟΥ ΒΡΙΣΚΕΙ ΤΟΝ ΑΝΑΛΟΓΑ 
        //ΕΞΕΙΔΙΚΕΥΜΕΝΟ ΤΕΧΝΙΚΟ, ΠΡΟΣΘΕΤΕΙ ΣΤΗ ΛΙΣΤΑ ΤΟΥ ΤΟ ΑΙΤΗΜΑ ΚΑΙ ΤΟΝ 
        //ΚΑΤΑΧΩΡΕΙ ΩΣ ΥΠΕΥΘΥΝΟ ΓΙΑ ΤΟ ΑΙΤΗΜΑ 
        for (Ticket ticket : submittedTickets) {
            if(null != ticket.getCategory())
                switch (ticket.getCategory()) {
                    case COMPUTER:
                    case PRINTER:
                        m.get(Specialization.HARDWARE).addTicket(ticket);
                        ticket.setTechnician(m.get(Specialization.HARDWARE));
                        break;
                    case OS:
                    case APPLICATION:
                        m.get(Specialization.SOFTWARE).addTicket(ticket);
                        ticket.setTechnician(m.get(Specialization.SOFTWARE));
                        break;
                    case INTERNET:
                        m.get(Specialization.NETWORK).addTicket(ticket);
                        ticket.setTechnician(m.get(Specialization.NETWORK));
                        break; 
                    case PHONE:
                        m.get(Specialization.COMMUNICATIONS).addTicket(ticket);
                        ticket.setTechnician(m.get(Specialization.COMMUNICATIONS));
                        break;
                    default:
                        break;
            }
            //ΤΟ ΑΙΤΗΜΑ ΠΕΡΙΕΡΧΕΤΑΙ ΣΕ ΚΑΤΑΣΤΑΣΗ ASSIGNED
            ticket.setStatus(Status.ASSIGNED);
        }
    }
    
}
