export function saveSession(session) {
  localStorage.setItem("session", session);
}

export function getSession() {
  return localStorage.getItem("session");
}

export function deleteSession() {
  localStorage.removeItem("session");
}

export function isAuthenticated() {
  const session = localStorage.getItem("session");
  return session !== null;
}
