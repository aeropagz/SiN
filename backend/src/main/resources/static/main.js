$("#login-btn").on("click", () => {
  const data = $("#login-form").serializeArray();
  const json = {};
  for (const line of data) {
    json[line.name] = line.value;
  }
  $.ajax("http://localhost:8080/api/user/login", {
    data: JSON.stringify(json),
    type: "post",
    contentType: "application/json",
    success: success_handler,
    error: error_handler,
  });
  console.log(json);
});

function success_handler(data) {
  $("#username-res").text(data.username);
  $("#email-res").text(data.email);
  $("#response-success").removeClass("invisible");
  $("#response-failed").addClass("invisible");
}

function error_handler(data) {
  console.log(data);
  $("#response-success").addClass("invisible");
  $("#response-failed").removeClass("invisible");
}
