async function login() {
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  const token = document.getElementById("token").value;
  if (!(username && password && token)) {
    alert("Fill out the form");
    return;
  }
  const response = await fetch("http://localhost:5000/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ username, password, token }),
  });
  if (response.status === 200) {
    document.querySelector(".ok").style.display = "block";
    document.querySelector(".fail").style.display = "none";
  } else {
    document.querySelector(".ok").style.display = "none";
    document.querySelector(".fail").style.display = "block";
  }
  console.log(response.status);
}
