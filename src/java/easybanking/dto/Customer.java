/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easybanking.dto;

/**
 *
 * @author hp
 */
public class Customer {

    @Override
    public String toString() {
        return "Customer{" + "fname=" + fname + ", lname=" + lname + ", bdate=" + bdate + ", userid=" + userid + ", pword=" + pword + ", actno=" + actno + ", gender=" + gender + ", bal=" + bal + '}';
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBal() {
        return bal;
    }

    public void setBal(String bal) {
        this.bal = bal;
    }
             private  String fname;
		private String lname;
		private String bdate;
		private String userid;
		private String pword;
		private String actno;
		private String gender;
		private String bal;
    
    
    
    
    
}
