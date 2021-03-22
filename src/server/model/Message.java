package server.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message
{
  private String text;
  private String ipAddress;
  private LocalDateTime time;

  public Message(String text, String ipAddress)
  {
    this.text = text;
    this.ipAddress = ipAddress;
    this.time = LocalDateTime.now();
  }

  public String getText()
  {
    return text;
  }

  public String getIpAddress()
  {
    return ipAddress;
  }

  public LocalDateTime getDateTime()
  {
    return time;
  }

  public String getDateTimeAsString()
  {
    DateTimeFormatter dtf;
    dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
    return time.format(dtf);
  }

  public String toString()
  {
    return getDateTimeAsString() + " " + ipAddress + " " + text;
  }
}