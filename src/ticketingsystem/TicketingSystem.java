/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketingsystem;

/**
 *
 * @author PanDim
 */
public class TicketingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //ΔΗΜΙΟΥΡΓΙΑ ΕΤΑΙΡΕΙΑΣ
        Company company = new Company("The Lab");
        //ΔΗΜΙΟΥΡΓΙΑ CONTROLLER
        TicketingController tController = new TicketingController();
        
        //ΔΗΜΙΟΥΡΓΙΑ ΚΑΙ ΚΑΤΑΧΩΡΙΣΗ ΧΡΗΣΤΩΝ
        User user1 = new User("Παπαδόπουλος Ιωάννης");
        company.addUser(user1);
        User user2 = new User("Λαδάς Κωνσταντίνος");
        company.addUser(user2);
        User user3 = new User("Καραβασίλης Πέτρος");
        company.addUser(user3);
        
        //ΔΗΜΙΟΥΡΓΙΑ ΤΕΧΝΙΚΏΝ
        Technician technician1 = new Technician("Παύλος", Technician.Specialization.HARDWARE);
        TechnicianManager technician2 = new TechnicianManager("Κατερίνα", Technician.Specialization.SOFTWARE);        
        Technician technician3 = new Technician("Δημήτρης", Technician.Specialization.COMMUNICATIONS);
        //ΚΑΘΟΡΙΣΜΟΣ ΤΕΧΝΙΚΟΥ ΥΠΕΥΘΥΝΟΥ
        company.setTechnicianManager(technician2);
        //ΚΑΤΑΧΩΡΙΣΗ ΤΕΧΝΙΚΏΝ
        company.addTechnician(technician1);
        company.addTechnician(technician2);
        company.addTechnician(technician3);
        
       
                
              
        //ΔΗΜΙΟΥΡΓΙΑ ΑΙΤΗΜΑΤΩΝ ΧΡΗΣΤΩΝ       
        tController.addTicket(new Ticket(user1, "Βλάβη σκληρού δίσκου", Ticket.Category.COMPUTER));
        tController.addTicket(new Ticket(user2, "Δεν ανοίγει το κινητό", Ticket.Category.PHONE));
        tController.addTicket(new Ticket(user3, "Εγκατάσταση Windows 10", Ticket.Category.OS));
        tController.addTicket(new Ticket(user3, "Η οθόνη του PC δεν ενεργοποιείται", Ticket.Category.COMPUTER));
        tController.addTicket(new Ticket(user2, "Θέλω να κάνω format στο Laptop", Ticket.Category.OS));
        tController.addTicket(new Ticket(user1, "Ο εκτυπωτής έχει χαλάσει", Ticket.Category.PRINTER));
        tController.addTicket(new Ticket(user2, "Δεν λειτουργεί το ακουστικό του κινητού", Ticket.Category.PHONE));
        tController.addTicket(new Ticket(user3, "Εγκατάσταση νέας κάρτας γραφικών", Ticket.Category.COMPUTER));
        tController.addTicket(new Ticket(user1, "Αντικατάσταση οθόνης κινητού", Ticket.Category.PHONE));
        
        //ΑΝΑΘΕΣΗ ΑΙΤΗΜΑΤΩΝ ΣΤΟΥΣ ΤΕΧΝΙΚΟΥΣ
        company.getTechnicianManager().assignTicketsToTechnicians(tController.getSubmittedTickets(), company.getTechnicians());
        //ΚΛΗΣΗ ΜΕΘΟΔΟΥ ΓΙΑ ΕΜΦΑΝΙΣΗ ΑΙΤΗΜΑΤΩΝ
        tController.printSubmittedTickets();
        
        //ΓΙΑ ΚΑΘΕ ΤΕΧΝΙΚΟ --> ΕΠΙΛΥΣΗ ΑΙΤΗΜΑΤΟΣ
        for (Technician technician : company.getTechnicians()) { 
            technician.randomProcessTickets();
        }    
        System.out.println("========================================================================================");
        
        //ΕΜΦΑΝΙΣΗ ΟΛΟΚΛΗΡΩΜΕΝΩΝ ΑΙΤΗΜΑΤΩΝ ΚΑΙ ΕΝΕΡΓΕΙΩΝ ΠΟΥ ΚΑΤΑΧΩΡΗΘΗΚΑΝ ΓΙΑ 
        //ΤΗΝ ΕΠΙΛΥΣΗ ΤΟΥΣ 
        tController.printCompletedTickets();
        
        //ΕΜΦΑΝΙΣΗ ΚΑΤΑΤΑΞΗΣ ΑΞΙΟΛΟΓΗΣΗΣ ΤΕΧΝΙΚΩΝ ΜΕ ΦΘΙΝΟΥΣΑ ΣΕΙΡΑ 
        System.out.println("Η κατάταξη αξιολόγησης των τεχνικών είναι:");
        tController.printTechniciansRanking();
    }
    
}
