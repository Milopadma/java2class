public class Subject {
    // class headers
    private String name;
    private String code;
    private integer points;

    // class contructors
    public Subject(String name, String code, integer points) {
        this.name = name;
        this.code = code;
        this.points = points;
    }

    // the no-argument constructor
    public Subject() {
        this.name = "No name";
        this.code = "No code";
        this.points = 0;
    }

    // class methods (getters and setters)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public integer getPoints() {
        return this.points;
    }

    public void setPoints(integer points) {
        this.points = points;
    }

}
