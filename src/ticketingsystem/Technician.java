/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ticketingsystem.Ticket.Status;

/**
 *
 * @author PanDim
 */
public class Technician extends User{
    //ΕΞΕΙΔΙΚΕΥΣΕΙΣ
    public enum Specialization {
        HARDWARE,
        SOFTWARE,
        NETWORK,
        COMMUNICATIONS
    }
    //ΕΞΕΙΔΙΚΕΥΣΗ ΤΕΧΝΙΚΟΥ
    private Specialization specialisation;
    //ΛΙΣΤΑ ΑΙΤΗΜΑΤΩΝ ΠΟΥ ΕΧΟΥΝ ΑΝΑΤΕΘΕΙ ΣΤΟΝ ΤΕΧΝΙΚΟ
    private List <Ticket> ticketsAssigned; 
    
    Random r;
    
   
    public Technician(String name, Specialization specialisation) {
        super(name);
        this.r = new Random();
        this.specialisation = specialisation;
        this.ticketsAssigned = new ArrayList<>();
    }

    public Specialization getSpecialisation() {
        return specialisation;
    }

    public List<Ticket> getTicketsAssigned() {
        return ticketsAssigned;
    }

    public void setSpecialisation(Specialization specialisation) {
        this.specialisation = specialisation;
    }
    
    //ΜΕΘΟΔΟΣ ΕΙΣΑΓΩΓΗΣ ΑΙΤΗΜΑΤΟΣ ΣΤΗ ΛΙΣΤΑ ΑΙΤΗΜΑΤΩΝ ΠΟΥ ΕΧΟΥΝ ΑΝΑΤΕΘΕΙ ΣΤΟΝ ΤΕΧΝΙΚΟ
    public void addTicket(Ticket ticket){
        ticketsAssigned.add(ticket);
    }
    
    //ΜΕΘΟΔΟΣ ΕΙΣΑΓΩΓΗΣ ΕΝΕΡΓΕΙΑΣ ΤΕΧΝΙΚΟΥ ΣΤΗ ΛΙΣΤΑ ΕΝΕΡΓΕΙΩΝ ΤΟΥ ΑΙΤΗΜΑΤΟΣ ΚΑΙ 
    //ΕΝΗΜΕΡΩΣΗΣ ΤΗΣ ΠΡΟΟΔΟΥ ΕΡΓΑΣΙΩΝ
    public void addAction(Ticket ticket, int randomProgress, String action){       
        do { 
            //ΕΙΣΑΓΩΓΗ ΕΝΕΡΓΕΙΑΣ ΤΕΧΝΙΚΟΥ ΣΤΗ ΛΙΣΤΑ ΕΝΕΡΓΕΙΩΝ ΤΟΥ ΑΙΤΗΜΑΤΟΣ
            ticket.addTicketAction(new TicketAction(this, randomProgress, action));
            //ΕΝΗΜΕΡΩΣΗ ΠΡΟΟΔΟΥ ΕΡΓΑΣΙΩΝ
            ticket.setProgress(ticket.getProgress() + randomProgress);
            //ΤΥΧΑΙΑ ΕΠΙΛΟΓΗ ΠΡΟΟΔΟΥ ΕΡΓΑΣΙΑΣ
            randomProgress = r.nextInt(50);
            //ΑΝ randomProgress ΕΙΝΑΙ ΜΕΓΑΛΥΤΕΡΗ ΑΠΟ ΤΗΝ ΥΠΟΛΟΙΠΩΜΕΝΗ ΓΙΑ 
            //ΟΛΟΚΛΗΡΩΣΗ ΠΡΟΟΔΟ ΕΡΓΑΣΙΩΝ
            if (randomProgress > 100-ticket.getProgress())
                //randomProgress = ΥΠΟΛΟΙΠΩΜΕΝΗ ΓΙΑ ΟΛΟΚΛΗΡΩΣΗ
                randomProgress = 100-ticket.getProgress();
        } while (ticket.getProgress()<100);//ΟΣΟ Η ΠΡΟΟΔΟΣ ΕΡΓΑΣΙΩΝ ΔΕΝ ΕΧΕΙ ΟΛΟΚΛΗΡΩΘΕΙ
    }
    
    public void randomProcessTickets(){
        //ΟΣΟ ΥΠΑΡΧΟΥΝ ΑΙΤΗΜΑΤΑ ΓΙΑ ΤΟΝ ΤΕΧΝΙΚΟ
        while (!ticketsAssigned.isEmpty()){
            //ΤΥΧΑΙΑ ΕΠΙΛΟΓΗ ΠΡΟΟΔΟΥ ΕΡΓΑΣΙΑΣ
            int randomProgress = r.nextInt(50);
            //ΠΡΩΤΟ ΑΙΤΗΜΑ ΟΥΡΑΣ - FIFO
            Ticket ticketToServe = ticketsAssigned.get(0);
            //ΑΝ ΕΧΕΙ ΠΕΡΙΕΛΘΕΙ ΣΕ ΚΑΤΑΣΤΑΣΗ ASSIGNED
            if (ticketToServe.getStatus().equals(Status.ASSIGNED))
                //ΕΝΑΡΞΗ ΔΙΕΚΠΕΡΑΙΩΣΗΣ ΑΙΤΗΜΑΤΟΣ
                startTicket(ticketToServe);                        
            //ΑΝ randomProgress ΕΙΝΑΙ ΜΕΓΑΛΥΤΕΡΗ ΑΠΟ ΤΗΝ ΥΠΟΛΟΙΠΩΜΕΝΗ ΓΙΑ 
            //ΟΛΟΚΛΗΡΩΣΗ ΠΡΟΟΔΟ ΕΡΓΑΣΙΩΝ
            if (randomProgress > 100-ticketToServe.getProgress())
                //randomProgress = ΥΠΟΛΟΙΠΩΜΕΝΗ ΓΙΑ ΟΛΟΚΛΗΡΩΣΗ
                randomProgress = 100-ticketToServe.getProgress();
            //ΕΝΑΡΞΗ ΕΝΕΡΓΕΙΩΝ ΤΕΧΝΙΚΟΥ
            addAction(ticketToServe, randomProgress, "Simulating fix");
            //ΑΝ Η ΠΡΟΟΔΟΣ ΕΡΓΑΣΙΩΝ ΕΧΕΙ ΟΛΟΚΛΗΡΩΘΕΙ
            if (ticketToServe.getProgress() == 100){
                //ΟΛΟΚΛΗΡΩΣΗ ΑΙΤΗΜΑΤΟΣ
                endTicket(ticketToServe);
                //ΑΦΑΙΡΕΣΗ ΑΙΤΗΜΑΤΟΣ ΑΠΟ ΛΙΣΤΑ - FIFO
                ticketsAssigned.remove(0);
            }            
        }
        System.out.println("Technician "+ this.getName() +" has finished his tickets");
    }
    
    //ΜΕΘΟΔΟΣ ΕΝΑΡΞΗΣ ΔΙΕΚΠΕΡΑΙΩΣΗΣ ΑΙΤΗΜΑΤΟΣ
    public void startTicket(Ticket ticket){  
        //ΕΙΣΑΓΩΓΗ ΕΝΕΡΓΕΙΑΣ ΕΝΑΡΞΗΣ ΑΙΤΗΜΑΤΟΣ
        ticket.addTicketAction(new TicketAction(this, 0, "Starting ticket"));
        //ΤΟ ΑΙΤΗΜΑ ΠΕΡΙΕΡΧΕΤΑΙ ΣΕ ΚΑΤΑΣΤΑΣΗ IN_PROGRESS
        ticket.setStatus(Status.IN_PROGRESS);
        //ΑΡΧΙΚΟΠΟΙΗΣΗ ΠΡΟΟΔΟΥ ΜΕ ΜΗΔΕΝ
        ticket.setProgress(0);        
    }
    
    public void endTicket(Ticket ticket){
        //ΕΙΣΑΓΩΓΗ ΕΝΕΡΓΕΙΑΣ ΟΛΟΚΛΗΡΩΣΗΣ ΑΙΤΗΜΑΤΟΣ
        ticket.addTicketAction(new TicketAction(this, 100, "Ticket Complete"));
        //ΤΟ ΑΙΤΗΜΑ ΠΕΡΙΕΡΧΕΤΑΙ ΣΕ ΚΑΤΑΣΤΑΣΗ COMPLETE
        ticket.setStatus(Status.COMPLETE);
    }
    

    
}
