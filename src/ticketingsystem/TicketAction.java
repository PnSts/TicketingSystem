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
public class TicketAction {
    
    private final Technician technician;
    private final int progress;
    private final String action;

    
    public TicketAction(Technician technician, int progress, String action) {
        this.technician = technician;
        this.progress = progress;
        this.action = action;
    }   
    

    @Override
    public String toString(){
        return "Technician: " + technician.getName() + "-" + 
                technician.getSpecialisation() + "\tProgress: " +
                progress + "\tAction: " + action;
    }
}
