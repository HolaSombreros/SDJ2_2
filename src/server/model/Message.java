package server.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message
{
  private String text;
  private String ipAddress;
  private String date;
  private String time;

  public Message(String text, String ipAddress, String date, String time)
  {
    this.text = text;
    this.ipAddress = ipAddress;
    this.date = getDate();
    this.time = getTime();
  }

  public String getText()
  {
    return text;
  }

  public String getIpAddress()
  {
    return ipAddress;
  }

  public String getDate()
  {
    LocalDate localDate = LocalDate.now();
    return localDate.toString();
  }

  public String getTime()
  {
    LocalTime localTime = LocalTime.now();
    return localTime.toString();
  }

  public String toString()
  {
    return text + " " + ipAddress + " " + date + " " + time;
  }
}