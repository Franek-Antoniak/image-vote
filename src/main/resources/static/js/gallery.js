function vote(uniqueId) {
    $.ajax({
        type: "PATCH",
        url: "/user/image/vote/" + uniqueId,
        error: function (xhr, status, err) {
            console.error(xhr, status, err.toString());
        }.bind(this)
    });
    delayUrlLoad('/', 1000);
}

function unvote(uniqueId) {
    $.ajax({
        type: "delete",
        url: "/user/image/unvote/" + uniqueId,
        error: function (xhr, status, err) {
            console.error(xhr, status, err.toString());
        }.bind(this)
    });
    delayUrlLoad('/', 1000);
}

function delayUrlLoad(url, mils) {
    setTimeout(function () {
        window.location.href = url;
    }, mils)
}

function deleteImage(uniqueId) {
    $.ajax({
        type: 'DELETE',
        url: '/user/image/delete/' + uniqueId,
        error: function (xhr, status, err) {
            console.error(xhr, status, err.toString());
        }.bind(this)
    });
    delayUrlLoad('/', 1000);
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

document.addEventListener(
    "click",
    function (event) {
        // If user either clicks X button OR clicks outside the modal window, then close modal by calling closeModal()
        if (
            event.target.closest(".modal")
        ) {
            closeModal()
        }
    },
    false
)

function closeModal() {
    document.querySelector(".modal").style.display = "none"
}
