package entities;

public class Member {
    private String memberId;
    private String name;
    private String contact;
    private String email;

    public Member(String memberId, String name, String contact, String email) {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("%-10s %-30s %-15s %-30s",
                memberId, name, contact, email);
    }
}