$("#login-btn").on("click", () => {
    const data = $("#login-form").serializeArray();
    const json = {}
    for (const line of data){
        json[line.name] = line.value
    }
    $.ajax("http://localhost:8080/api/user/login",{data: json, type: "post"})
    console.log(json);
});