function vote(uniqueId) {
    $.ajax({
        type: "PATCH", url: "/user/image/vote/" + uniqueId, success: function (data) {
            window.location.href = "/";
        }, error: function (data) {
            console.log(data);
        }
    });
}

function un_vote(uniqueId) {
    $.ajax({
        type: "PATCH", url: "/user/image/unvote/" + uniqueId, success: function () {
            window.location.href = "/";
        }, error: function (data) {
            console.log(data);
        }
    });
}

function deleteImage(uniqueId) {
    $.ajax({
        type: "DELETE", url: "/user/image/delete/" + uniqueId, success: function () {
            window.location.href = "/";
        }, error: function (data) {
            console.log(data);
        }
    });
}

function renderModal(imageId) {
    // Get the modal
    const modal = document.getElementById("modal");
    // Get the image and insert it inside the modal - use its "alt" text as a caption
    const img = document.getElementById(imageId);
    const modalImg = document.getElementById("img01");
    modal.style.display = "block";
    modalImg.src = img.src;
    // Get the <span> element that closes the modal
    const span = document.getElementsByClassName("close")[0];
    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }
}

document.addEventListener("click", function (event) {
    // If user either clicks X button OR clicks outside the modal window, then close modal by calling closeModal()
    if (event.target.closest(".modal")) {
        closeModal()
    }
}, false)

function closeModal() {
    document.querySelector(".modal").style.display = "none"
}
