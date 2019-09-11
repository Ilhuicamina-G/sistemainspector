package artbot.com.sistemainspector.model;

public class Report {

    private String name;
    private String comment;
    private String report;
    private String date;

    public Report(String name, String comment, String report, String date) {
        this.name = name;
        this.comment = comment;
        this.report = report;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
