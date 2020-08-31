package com.school.fa.entity;

import javax.persistence.*;

@Entity
@Table(name = "principal",uniqueConstraints={@UniqueConstraint(columnNames={"principal_email"})})
public class PrincipalEntity {

    @Id
    @GeneratedValue
    @Column(name="principal_id")
    private int principalId;
    @Column(name="principal_name")
    private String principalName;
    @Column(name="principal_pro_pic")
    private String principalPP;
    @Column(name="principal_email")
    private String principalEmail;
    @Column(name="principal_password")
    private String principalPassword;

    public int getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(int principalId) {
        this.principalId = principalId;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPrincipalPP() {
        return principalPP;
    }

    public void setPrincipalPP(String principalPP) {
        this.principalPP = principalPP;
    }

    public String getPrincipalEmail() {
        return principalEmail;
    }

    public void setPrincipalEmail(String principalEmail) {
        this.principalEmail = principalEmail;
    }

    public String getPrincipalPassword() {
        return principalPassword;
    }

    public void setPrincipalPassword(String principalPassword) {
        this.principalPassword = principalPassword;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "principalId=" + principalId +
                ", principalName='" + principalName + '\'' +
                ", principalPP='" + principalPP + '\'' +
                ", principalEmail='" + principalEmail + '\'' +
                ", principalPassword='" + principalPassword + '\'' +
                '}';
    }
}
