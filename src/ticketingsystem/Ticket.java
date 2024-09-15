/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketingsystem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PanDim
 */
public class Ticket {
    //ΚΑΤΑΣΤΑΣΕΙΣ ΑΙΤΗΜΑΤΟΣ
    public enum Status {
        WAITING,
        ASSIGNED,
        IN_PROGRESS,
        COMPLETE
    }
    //ΚΑΤΗΓΟΡΙΕΣ ΑΙΤΗΜΑΤΟΣ
    public enum Category {
        COMPUTER,
        PRINTER,
        OS,
        APPLICATION,
        INTERNET,
        PHONE
    }
    //ΚΩΔΙΚΟΣ ΓΙΑ ΑΝΑΘΕΣΗ ΣΤΟ ticketID
    private static int id = 0;
    //ΜΟΝΑΔΙΚΟΣ ΚΩΔΙΚΟΣ ΑΙΤΗΜΑΤΟΣ
    private final int ticketID;
    //ΧΡΗΣΤΗΣ ΠΟΥ ΥΠΟΒΑΛΛΕΙ ΤΟ ΑΙΤΗΜΑ
    private final User user;
    //ΠΕΡΙΓΡΑΦΗ ΑΙΤΗΜΑΤΟΣ
    private final String description;
    //ΚΑΤΗΓΟΡΙΑ ΑΙΤΗΜΑΤΟΣ
    private final Category category;
    //ΚΑΤΑΣΤΑΣΗ ΑΙΤΗΜΑΤΟΣ
    private Status status;
    //ΕΝΔΕΙΞΗ ΠΡΟΟΔΟΥ ΟΛΟΚΛΗΡΩΣΗΣ ΕΡΓΑΣΙΩΝ
    private int progress;
    //Ο ΤΕΧΝΙΚΟΣ ΠΟΥ ΑΝΑΛΑΜΒΑΝΕΙ ΤΟ ΑΙΤΗΜΑ
    private Technician technician;
    //ΛΙΣΤΑ ΕΝΕΡΓΕΙΩΝ ΔΙΕΚΠΕΡΑΙΩΣΗΣ ΑΙΤΗΜΑΤΟΣ
    private List <TicketAction> ticketActions;
   

    
    public Ticket(User user, String description, Category category) {
        this.ticketID = ++id;
        this.user = user;
        this.description = description;
        this.category = category;
        this.status = Status.WAITING;
        this.progress = 0;
        this.technician = null;
        this.ticketActions = new ArrayList<>();
    }
   
    public void setStatus(Status status) {
        this.status = status;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    } 
    
    public void addTicketAction(TicketAction ticketAction) {
        ticketActions.add(ticketAction);
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    
    public int getTicketID() {
        return ticketID;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Status getStatus() {
        return status;
    }

    public int getProgress() {
        return progress;
    }

    public Technician getTechnician() {
        return technician;
    }

    public List<TicketAction> getTicketActions() {
        return ticketActions;
    }
    
    public String toString(){
        return "Ticket ID: " + ticketID + "\tDescription: " + description + 
                "\tCategory: " + category  +"\tStatus: " + status + 
                "\tTechnician: " + technician.getName() + "-" + 
                technician.getSpecialisation() + "\tProgress: " + progress + "\n";
    }
}
