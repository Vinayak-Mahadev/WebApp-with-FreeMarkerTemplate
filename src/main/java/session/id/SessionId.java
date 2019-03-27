package session.id;

public class SessionId {
private static String sessionId;

public static String getSessionId() {
	return SessionId.sessionId;
}

public static  void setSessionId(final String sessionId) {
	SessionId.sessionId = sessionId;
}
}
