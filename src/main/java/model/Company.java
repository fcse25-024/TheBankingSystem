
package model;

public class Company {

    private String companyName;
    private String registrationNumber;
    private String businessAddress;
    private String contactNumber;
    private String taxIdentificationNumber;

    public Company(String companyName, String registrationNumber, String businessAddress, String contactNumber,
                   String taxIdentificationNumber) {

        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
        this.businessAddress = businessAddress;
        this.contactNumber = contactNumber;
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public void provideCompanyDetails() {
        System.out.println("Providing company details.");
    }

    public void submitSupportingDocuments() {
        System.out.println("Submitting supporting documents.");
    }

    public void viewBusinessAccount() {
        System.out.println("Viewing business account.");
    }
}
