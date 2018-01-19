function test() {
    $.ajax({
        url: "/rest/user/test", success: function (result) {
            console.log(result);
        }
    });
}

function makeEditable(id) {
    let row = $('.tariff-row[data-tid="' + id + '"]');
    row.children().prop("contenteditable", true);
    row.find(".button-data").prop("contenteditable", false);
}

function subscribe(id) {
    let row = $('.tariff-row[data-tid="' + id + '"]');
    let title = row.find('[data-tfield="title"]').html();
    console.log("title ", title);
    $.ajax({
        url: "/rest/user/test", success: function (result) {
            console.log(result);
        }
    });
}