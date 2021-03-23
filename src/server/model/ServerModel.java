package server.model;

import util.NamedPropertyChangeSubject;

import java.util.ArrayList;

public interface ServerModel extends NamedPropertyChangeSubject
{
  ArrayList<String> getOnlineUsers();
  String getChatLog();
  void login(String username);
  void disconnect(String username);
}
