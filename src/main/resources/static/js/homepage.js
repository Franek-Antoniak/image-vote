function vote(uniqueId) {
    $.ajax({
        type: "PATCH",
        url: "/user/vote/" + uniqueId,
        error: function(xhr, status, err) {
            console.error(xhr, status, err.toString());
        }.bind(this)
    });
}