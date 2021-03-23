package server.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message
{
  private String type;
  private String username;
  private String text;
  private LocalDateTime time;

  public Message(String type, String username, String text)
  {
    this.type = type;
    this.username = username;
    this.text = text;
    this.time = LocalDateTime.now();
  }

  public String getType()
  {
    return type;
  }
  public String getText()
  {
    return text;
  }

  public String getUsername()
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