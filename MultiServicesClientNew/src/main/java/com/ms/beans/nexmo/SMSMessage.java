package com.ms.beans.nexmo;

/**
 * Created with IntelliJ IDEA.
 * User: Ovi
 * Date: 6/13/13
 * Time: 9:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class SMSMessage {

    private String from;
    private String to;
    private String text;

    public SMSMessage() {
    }

    public SMSMessage(String from, String to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
