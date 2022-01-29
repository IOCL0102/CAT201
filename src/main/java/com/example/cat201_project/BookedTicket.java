package com.example.cat201_project;

public class BookedTicket {
    private String movieName;
    private String date;
    private String time;
    private String seat;
    private String QRsource;

    public void printData(){
        System.out.println(movieName);
        System.out.println(date);
        System.out.println(time);
        System.out.println(seat);
        System.out.println(QRsource);

    }
    public String getQRsource() {
        return QRsource;
    }

    public void setQRsource(String QRsource) {
        this.QRsource = QRsource;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSeat() {
        return seat;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

}
