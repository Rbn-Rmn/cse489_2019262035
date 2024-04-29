package com.example.cse489_2019262035;

public class ClassSummary {
    String id = "";
    String course = "";
    String type = "";
    long date = 0;
    String lecture = "";
    String topic = "";
    String summary = "";


    public ClassSummary(String id, String course, String type, long date, String lecture, String topic, String summary){
        this.id = id;
        this.course = course;
        this.topic = topic;
        this.type = type;
        this.date = date;
        this.lecture = lecture;
        this.summary = summary;
    }
}
