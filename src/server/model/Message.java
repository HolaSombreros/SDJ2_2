package server.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message
{
  private String text;
  private String username;
  private LocalDateTime time;

  public Message(String text, String ipAddress)
  {
    this.text = text;
    this.username = ipAddress;
    this.time = LocalDateTime.now();
  }

  public String getText()
  {
    return text;
  }

  public String getIpAddress()
  {
    return username;
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
    return getDateTimeAsString() + " " + username + " " + text;
  }
}