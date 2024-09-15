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
public class Company {
    private String name;
    private List < Technician > technicians;
    private TechnicianManager technicianManager;
    private List < User > users;

    public Company(String name) {
        this.name = name;
        technicians = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addUser(User u) {
        users.add(u);
    }

    public void addTechnician(Technician t) {
        technicians.add(t);
        technicianManager.mapTechnician(t);
    }

    public List < Technician >getTechnicians() {
        return technicians;
    }

    public void setTechnicians(List < Technician > technicians) {
        this.technicians = technicians;
    }

    public TechnicianManager getTechnicianManager() {
        return technicianManager;
    }

    public void setTechnicianManager(TechnicianManager technicianManager) {
        this.technicianManager = technicianManager;
    }

}
