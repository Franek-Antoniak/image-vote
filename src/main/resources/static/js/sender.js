const loadFile = function (id) {
    const output = document.getElementById('output');
    output.src = URL.createObjectURL(document.getElementById(id).files[0]);
    output.onload = function () {
        URL.revokeObjectURL(output.src) // free memory
    }
};

let files = [];
$(document).on("change", "#imageLoader", function (event) {
    files = event.target.files;
})

$(document).on("click", "#imageSubmit", function () {
    document.getElementById("imageSubmit").disabled = true;
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
        success: function () {
            window.location.href = "/";
        },
        error: function (data) {
            document.getElementById("imageSubmit").disabled = false;
            console.log(data);
        }
    });
}

function deleteAllData() {
    $.ajax({
        type: "DELETE", url: "/admin/delete/data", success: function () {
            window.location.href = "/";
        }, error: function (data) {
            console.log(data);
        }
    });
}