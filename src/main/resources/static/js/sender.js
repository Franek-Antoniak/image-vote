const loadFile = function (i) {
    const output = document.getElementById("output");
    output.src = URL.createObjectURL(document.getElementById("imagesLoader").files[i]);
    output.onload = function () {
        URL.revokeObjectURL(output.src)
    }
}

let files;
$(document).on("change", "#imagesLoader", function () {
    files = new DataTransfer();
    const filesHTML = document.getElementById("imagesLoader").files;
    const target = document.getElementById("imagesLoader");
    const maxAllowedSize = 5 * 1024 * 1024;
    console.log(target.files[0].size);
    let smallFiles = true;
    for (let i = 0; i < filesHTML.length; i++) {
        if (target.files[i].size > maxAllowedSize) {
            smallFiles = false;
        } else {
            files.items.add(filesHTML[i]);
        }
    }
    if (!smallFiles) {
        alert("One of your files is too big!\n" +
            "Please, load files with size less than 5MB\n" +
            "Rest of your files will be uploaded");
    }
    document.getElementById("imagesLoader").files = files.files;
    if (files.files.length > 0) {
        loadFiles();
    }
})

let iterator = 0;

const previewNextImage = function () {
    iterator++;
    iterator %= document.getElementById("imagesLoader").files.length;
    loadFile(iterator);
}

const loadFiles = function () {
    iterator = 0;
    loadFile(iterator);
    document.getElementById("nextImage").disabled = document.getElementById("imagesLoader").files.length <= 1;
}

$(document).on("click", "#submitImages", function () {
    document.getElementById("submitImages").disabled = true;
    processUpload();
})

function processUpload() {
    const oMyForm = new FormData();
    for (let i = 0; i < files.files.length; i++) {
        oMyForm.append("imagesFiles", files.files[i]);
    }
    $.ajax({
        type: "POST",
        url: "/user/images/upload",
        data: oMyForm,
        processData: false,
        contentType: false,
        enctype: 'multipart/form-data',
        success: function () {
            window.location.href = "/";
        },
        error: function (data) {
            document.getElementById("submitImages").disabled = false;
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