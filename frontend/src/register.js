function getQr(url) {
  var qr = new QRious({
    element: document.getElementById("qr"),
    size: 300,
    value: url,
  });
}

async function register() {
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  if (!(username && password)) {
    alert("Fill out the form");
    return;
  }
  const response = await fetch("http://localhost:5000/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      // 'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: JSON.stringify({ username, password }),
  });
  const qrUrl = (await response.json()).qr_url;
  console.log(qrUrl);
  getQr(qrUrl);
  document.querySelector(".validate").style.display = "block";
}
