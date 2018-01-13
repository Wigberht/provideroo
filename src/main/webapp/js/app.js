// run command without form
function runCommand(command, method, action) {
    var form = document.createElement("form");
    form.style.display = "none";
    form.method = method === undefined ? "POST" : method;
    form.action = action === undefined ? "/MainController" : action;

    var commandInput = document.createElement("input");
    commandInput.name = "command";
    commandInput.value = command;

    form.appendChild(commandInput);

    document.body.appendChild(form);

    form.submit();
}

function banUser(userId) {
    $.get("someservlet", function (responseJson) {
        console.log("Response", responseJson);
    });
}

function unbanUser(userId) {

}

// start jQuery
$(function () {

    $('#birth_datepicker').pickadate({
        format: "yyyy-mm-dd",
        max: new Date(),
        selectMonths: true, // Creates a dropdown to control month
        lowestYear: 1900,
        selectYears: 100,
        today: 'Today',
        clear: 'Clear',
        close: 'Ok',
        closeOnSelect: false // Close upon selecting a date,
    });

});