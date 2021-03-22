package server.model;

import util.NamedPropertyChangeSubject;

public interface Model extends NamedPropertyChangeSubject
{
  String getOnlineUsers();
  String getChatLog();

}
