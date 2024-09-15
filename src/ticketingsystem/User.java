/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketingsystem;

import ticketingsystem.Ticket.Category;


/**
 *
 * @author PanDim
 */
public class User {
    
    protected String name;    

    public User(String name){
        this.name = name;
    }

    public Ticket createTicket(String description, Category category){
        Ticket ticket = new Ticket(this, description, category);        
        return ticket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
