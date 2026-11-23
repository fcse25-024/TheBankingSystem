
package model;

public class BankEmployee {

    private String employeeID;
    private String employeeName;

    public BankEmployee(String employeeID, String employeeName) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void registerCustomer() {
        System.out.println("Registering customer.");
    }

    public void openAccount() {
        System.out.println("Opening customer account.");
    }

    public boolean verifyCompanyDocuments() {
        System.out.println("Verifying company documents.");
        return true;
    }
}
