function success_handler(data) {
  console.log(data);

  $("#number-res").text(data.numbers);
  $("#response-success").removeClass("invisible");
  $("#response-failed").addClass("invisible");
}

function error_handler(data) {
  console.log(data);
  $("#response-success").addClass("invisible");
  $("#response-failed")
    .removeClass("invisible")
    .text(`Error: ${data.status} - ${data.responseJSON.error}`);
}

$("#number-button").on("click", () => {
  const data = $("#number-input").val();
  if (isNaN(data)) {
    alert("Its not a number you are sending");
    return;
  }
  $.ajax("https://localhost:8080/api/number", {
    type: "post",
    data: JSON.stringify({ number: data }),
    contentType: "application/json",
    dataType: "json",
    success: success_handler,
    error: error_handler,
  });
});

async function console_fire() {
  $.ajax("https://localhost:8080/api/number", {
    type: "post",
    data: JSON.stringify({ number: "test" }),
    contentType: "application/json",
    dataType: "json",
    success: (data) => console.log(data),
    error: error_handler,
  });
}
