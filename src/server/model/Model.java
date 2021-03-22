package server.model;

import util.NamedPropertyChangeSubject;

public interface Model extends NamedPropertyChangeSubject
{
  public String getOnlineUsers();
  public String getChatLog();

}
