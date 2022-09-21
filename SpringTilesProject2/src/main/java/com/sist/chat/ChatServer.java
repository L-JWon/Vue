package com.sist.chat;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.*;


@ServerEndpoint("/site/chat/chat-ws")//url주소를 받아오는 부분
public class ChatServer {
	
	private Set<Session> users=Collections.synchronizedSet(new HashSet<Session>());//비동기화가 아닌 동기화
	//접속자의 중복을 없애기 위한 Set 사용 =======> 저장 시에는 하나하나 동기화를 주면서 처리해야한다 
	@OnOpen //연결했을 때 
	public void onOpen(Session session)
	{
		users.add(session);
		System.out.println("클라이언트 접속...:"+session.getId());
	}
	@OnClose //퇴장했을 때
	public void onClose(Session session)
	{
		users.remove(session);
		System.out.println("클라이언트 퇴장...:"+session.getId());
	}
	@OnMessage //메시지 보낼 때
	public void onMessage(String msg,Session session) throws Exception
	{
		//synchronized => 쓰레드를 동기화 시키는것 
		synchronized (users) {
			for(Session s:users)
			{
				s.getBasicRemote().sendText(msg);
			}
		}
	}
}
