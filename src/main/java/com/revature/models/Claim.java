package com.revature.models;

public class Claim {

    private int claim_id;
    private int amount;
    private String description;
    private String status;
    private String user_id;
    private boolean pending;


    public Claim(){
        super();
    }

    public Claim(int claim_id, int amount, String description, String status, String user_id, boolean pending){
        this.claim_id = claim_id;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.user_id = user_id;
        this.pending = pending;
    }

    public Claim(int amount, String description) {
		this.amount = amount;
		this.description = description;
		pending=true;
	}


    //================Setters and Getters====================
    public int getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(int claim_id) {
        this.claim_id = claim_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
    //================Setters and Getters====^^^=============

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + claim_id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Claim other = (Claim) obj;
        if (claim_id != other.claim_id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Claim [claim_id=" + claim_id + ", amount=" + amount + ", description=" + description + ", status="
                + status + ", user_id=" + user_id + ", pending=" + pending + "]";
    }
    
    
    

}
