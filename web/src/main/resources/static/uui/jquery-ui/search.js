function searchTours() {
    var searchData = {};
    if ($("#country").val() !== "") {
        searchData["countryId"] = $("#country").val();
    }
    if ($("#duration").val() !== "") {
        searchData["duration"] = $("#duration").val();
    }
    if ($("#hotel").val() !== "") {
        searchData["hotelId"] = $("#hotel").val();
    }
    if ($("#tourType").val() !== "") {
        searchData["tourType"] = $("#tourType").val();
    }
    if ($("#cost").val() !== "") {
        searchData["cost"] = $("#cost").val();
    }
    $.ajax({
        url: 'search-tour',
        type: 'post',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data) {
            createToursTable(data);
        },
        data: JSON.stringify(searchData)
    });
}

function createToursTable(data) {
    $("#target").removeClass("hidden");
    $('#tours-table tbody').empty();
    if (data.length === 0) {
        $("#tours-table tbody").append("<tr>" +
            "<td colspan='9'> No tours were found </td>" +
            "</tr>");

    } else {
        $.each(data, function (i, tour) {
            if (tour.description.length > 30) {
                tour.description = tour.description.substring(0, 30) + "...";
            }
            $("#tours-table tbody").append("<tr onclick=\"document.location='/tour/" + tour.id + "'\">" +
                "<td>" + tour.id + "</td>" +
                "<td>" + tour.country + "</td>" +
                "<td>" + $.format.date(tour.date, "dd.MM.yyyy") + "</td>" +
                "<td>" + tour.duration + " days" + "</td>" +
                "<td>" + tour.description + "</td>" +
                "<td><a href='/hotel/" + tour.hotelId + "'>" + tour.hotelName + "</a></td>" +
                "<td>" + tour.stars + "</td>" +
                "<td>" + tour.tourType + "</td>" +
                "<td>" + "$" + tour.cost + "</td>" +
                "</tr>");
        });
    }
}

function searchHotels() {
    var searchData = {};
    if ($("#name").val() !== "") {
        searchData["name"] = $("#name").val();
    }
    if ($("#stars").val() !== "") {
        searchData["stars"] = $("#stars").val();
    }
    if ($("#website").val() !== "") {
        searchData["website"] = $("#website").val();
    }
    $.ajax({
        url: 'search-hotel',
        type: 'post',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data) {
            createHotelsTable(data);
        },
        data: JSON.stringify(searchData)
    });
}

function createHotelsTable(data) {
    $("#target").removeClass("hidden");
    $('#hotels-table tbody').empty();
    if (data.length === 0) {
        $("#hotels-table tbody").append("<tr>" +
            "<td colspan='7'> No hotels were found </td>" +
            "</tr>");

    } else {
        $.each(data, function (i, hotel) {
            var features = "";
            $.each(hotel.features, function (i, feature) {
                features = features + feature + ", ";
            });
            if (features.length > 0) {
                features = features.substring(0, features.length - 2);
            }
            $("#hotels-table tbody").append("<tr onclick=\"document.location='/hotel/" + hotel.id + "'\">" +
                "<td>" + hotel.id + "</td>" +
                "<td>" + hotel.name + "</td>" +
                "<td>" + hotel.stars + "</td>" +
                "<td>" + hotel.website + "</td>" +
                "<td>" + hotel.longitude + "</td>" +
                "<td>" + hotel.latitude + "</td>" +
                "<td>" + features + "</td>" +
                "</tr>");
        });
    }
}