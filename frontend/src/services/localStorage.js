export function saveSession(session){
    localStorage.setItem("session", session);
}

export function getSession() {
    return localStorage.getItem("session");
}

