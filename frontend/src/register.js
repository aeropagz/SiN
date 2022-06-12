function getQr(url) {
  var qr = new QRious({
    element: document.getElementById("qr"),
    value: url,
  });
}

function register() {
  const username = document.getElementById("#username");
  const password = document.getElementById("#password");
  const response = await fetch("https://localhost:4444/register",{
    headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify({username, password})
  });
  console.log(response);
}


