const loadFile = function (id) {
    const output = document.getElementById('output');
    output.src = URL.createObjectURL(document.getElementById(id).files[0]);
    output.onload = function () {
        URL.revokeObjectURL(output.src) // free memory
    }
};

let files = [];
$(document).on(
    "change",
    "#imageLoader",
    function (event) {
        files = event.target.files;
    })

$(document).on(
    "click",
    "#imageSubmit",
    function () {
        processUpload();
    })

function processUpload() {
    const oMyForm = new FormData();
    oMyForm.append("imageFile", files[0]);
    $.ajax({
        type: "POST",
        url: "/user/image/upload",
        data: oMyForm,
        processData: false,
        contentType: false,
        enctype: 'multipart/form-data',
        success: function (data) {
            console.log(data);
            window.location.href = "/";
        }
    });

/*    $.ajax({
        dataType: 'json',
        url: "/user/image/upload",
        data: oMyForm,
        type: "POST",
        enctype: 'multipart/form-data',
        error: function (xhr, status, err) {
            console.error(xhr, status, err.toString());
        }
    });*/
}

function delayUrlLoad(url, mils) {
    setTimeout(function () {
        window.location.href = url;
    }, mils)
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