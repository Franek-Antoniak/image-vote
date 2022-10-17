const loadFile = function (id) {
    const output = document.getElementById('output');
    output.src = URL.createObjectURL(document.getElementById(id).files[0]);
    output.onload = function () {
        URL.revokeObjectURL(output.src) // free memory
    }
};

let files = [];
$(document)
    .on(
        "change",
        "#imageLoader",
        function (event) {
            files = event.target.files;
        })

$(document)
    .on(
        "click",
        "#imageSubmit",
        function () {
            processUpload();
        })

function processUpload() {
    const oMyForm = new FormData();
    oMyForm.append("imageFile", files[0]);
    $
        .ajax({
            dataType: 'json',
            url: "/user/image/upload",
            data: oMyForm,
            type: "POST",
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success: function (result) {
                //...;
            },
            error: function (result) {
                //...;
            }
        });
}

function deleteAllData() {
    $.ajax({
        type: "PATCH",
        url: "/admin/delete/data",
        error: function (xhr, status, err) {
            console.error(xhr, status, err.toString());
        }.bind(this)
    });
}