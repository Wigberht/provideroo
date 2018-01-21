// run command without form, but on link click
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


// start jQuery
$(function () {
    
    // init datepicker
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
    
    // init materialize select
    $('select').material_select();
    
    // language selector
    $("#lang-select").on("change", () => {
        console.log("select changed ", $("#lang-select").val());
        const langCode = $("#lang-select").val();
        
        var form = document.createElement("form");
        form.style.display = "none";
        form.method = "POST";
        form.action = "/MainController";
        
        var commandInput = document.createElement("input");
        commandInput.name = "command";
        commandInput.value = "change_language";
        form.appendChild(commandInput);
        
        var langInput = document.createElement("input");
        langInput.name = "language";
        langInput.value = langCode;
        form.appendChild(langInput);
        
        document.body.appendChild(form);
        
        form.submit();
    })
    
    
});

$(document).ready(function () {
    $('.collapsible').collapsible();
});