public class Student {

    private int roll;
    private String name;
    private int age;
    private String course;

    private int english;
    private int hindi;
    private int mathematics;
    private int science;
    private int sst;

    private double percentage;

    public Student(int roll, String name, int age, String course,
                   int english, int hindi, int mathematics,
                   int science, int sst) {

        this.roll = roll;
        this.name = name;
        this.age = age;
        this.course = course;

        this.english = english;
        this.hindi = hindi;
        this.mathematics = mathematics;
        this.science = science;
        this.sst = sst;

        this.percentage = (english + hindi + mathematics + science + sst) / 5.0;
    }

    public int getRoll() { return roll; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    public int getEnglish() { return english; }
    public int getHindi() { return hindi; }
    public int getMathematics() { return mathematics; }
    public int getScience() { return science; }
    public int getSst() { return sst; }

    public double getPercentage() {
        return percentage;
    }
}
