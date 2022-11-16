public class Subject {
    // class headers
    private String name;
    private String code;
    private int points;

    // class contructors
    public Subject(String name, String code, int points) {
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

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // toString method
    @Override
    public String toString() {
        return getCode() + " " + getName() + " with " + getPoints() + " credit points.";
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Subject)) {
            return false;
        }
        Subject subject = (Subject) obj;
        // they are equal if they have the same subject code
        return subject.getCode().equals(getCode());
    }
}
