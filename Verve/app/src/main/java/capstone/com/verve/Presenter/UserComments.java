package capstone.com.verve.Presenter;

public class UserComments {

    private String firstname, middlename, lastname, date, time, comment;

    public UserComments() {
    }

    public UserComments(String firstname, String middlename, String lastname, String date, String time, String comment) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.date = date;
        this.time = time;
        this.comment = comment;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
