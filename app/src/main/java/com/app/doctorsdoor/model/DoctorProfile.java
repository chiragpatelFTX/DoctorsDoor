package com.app.doctorsdoor.model;

/**
 * Created by Damini Mehra on 30-06-2019 at 19:37.
 */
public class DoctorProfile {
    private String user_id, degree, experience, registration_no, consultant, ifsc_code;
    private long new_case_fee, old_case_fee, bank_account_no;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public long getNew_case_fee() {
        return new_case_fee;
    }

    public void setNew_case_fee(long new_case_fee) {
        this.new_case_fee = new_case_fee;
    }

    public long getOld_case_fee() {
        return old_case_fee;
    }

    public void setOld_case_fee(long old_case_fee) {
        this.old_case_fee = old_case_fee;
    }

    public long getBank_account_no() {
        return bank_account_no;
    }

    public void setBank_account_no(long bank_account_no) {
        this.bank_account_no = bank_account_no;
    }
}
