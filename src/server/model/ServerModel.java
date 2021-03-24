package server.model;

import util.NamedPropertyChangeSubject;

import java.util.ArrayList;

public interface ServerModel extends NamedPropertyChangeSubject
{
  ArrayList<String> getOnlineUsers();
  void login(String username);
  void disconnect(String username);
  void sendMessage(Message message);
}
