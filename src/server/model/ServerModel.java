package server.model;

import util.NamedPropertyChangeSubject;

public interface ServerModel extends NamedPropertyChangeSubject
{
  String getOnlineUsers();
  String getChatLog();
  void login(String username);
}
