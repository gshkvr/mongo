function createTourPagination(data) {
    $('#tours-table tbody').empty();
    $.each(data[0], function (i, tour) {
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
    var pages = data[1];
    var page = data[2];
    var pagination = paginationText(pages, page);
    $('#pagination').empty();
    $('#pagination').append(pagination);
}

function createCountryPagination(data) {
    $('#countries-table tbody').empty();
    $.each(data[0], function (i, country) {
        $("#countries-table tbody").append("<tr>" +
            "<td>" + country.id + "</td>" +
            "<td>" + country.name + "</td>" +
            "</tr>");
    });
    var pages = data[1];
    var page = data[2];
    var pagination = paginationText(pages, page);
    $('#pagination').empty();
    $('#pagination').append(pagination);
}

function createUserPagination(data) {
    $('#users-table tbody').empty();
    $.each(data[0], function (i, user) {
        $("#users-table tbody").append("<tr onclick=\"document.location='/user/" + user.id + "'\">" +
            "<td>" + user.id + "</td>" +
            "<td>" + user.login + "</td>" +
            "<td>" + user.password + "</td>" +
            "<td>" + user.role + "</td>" +
            "</tr>");
    });
    var pages = data[1];
    var page = data[2];
    var pagination = paginationText(pages, page);
    $('#pagination').empty();
    $('#pagination').append(pagination);
}

function createReviewPagination(data) {
    $('#reviews-table tbody').empty();
    $.each(data[0], function (i, review) {
        if (review.text.length > 40) {
            review.text = review.text.substring(0, 40) + "...";
        }
        if (review.tourDescription.length > 30) {
            review.tourDescription = review.tourDescription.substring(0, 40) + "...";
        }
        $("#reviews-table tbody").append("<tr onclick=\"document.location='/review/" + review.id + "'\">" +
            "<td>" + review.id + "</td>" +
            "<td>" + $.format.date(review.date, "dd.MM.yyyy") + "</td>" +
            "<td>" + review.text + "</td>" +
            "<td><a href='/user/" + review.userId + "'>" + review.userName + "</a></td>" +
            "<td><a href='/tour/" + review.tourId + "'>" + review.tourDescription + "</a></td>" +
            "</tr>");
    });
    var pages = data[1];
    var page = data[2];
    var pagination = paginationText(pages, page);
    $('#pagination').empty();
    $('#pagination').append(pagination);
}

function createHotelPagination(data) {
    $('#hotels-table tbody').empty();
    $.each(data[0], function (i, hotel) {
        var features = "";
        $.each(hotel.features, function (i, feature) {
            features = features + feature + ", ";
        });
        if(features.length > 0){
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
    var pages = data[1];
    var page = data[2];
    var pagination = paginationText(pages, page);
    $('#pagination').empty();
    $('#pagination').append(pagination);
}

function paginationText(pages, page) {
    var pagination;
    var activeBack;
    var activeForward;
    var numbers = paginationNumbers(pages, page);
    if (page == 1) {
        activeBack = "<li class=\"prev-page disable\">";
    } else {
        activeBack = "<li class=\"prev-page\">";
    }
    if (page == pages) {
        activeForward = "<li class=\"prev-page disable\">";
    } else {
        activeForward = "<li class=\"prev-page\">";
    }
    pagination = "<ul class=\"uui-pagination\">" +
        "<li class=\"actions-wrapper\">" +
        "<ul class=\"pagination-items\">" + activeBack +
        "<a onclick=\"getPage(" + (page - 1) + ")\">" +
        "<i class=\"fa fa-angle-left\"></i>" +
        "</a>" +
        "</li>" +
        "</ul>" +
        "</li>" +
        numbers +
        "<li class=\"actions-wrapper\">" +
        "<ul class=\"pagination-items\">" + activeForward +
        "<a onclick=\"getPage(" + (page + 1) + ")\">" +
        "<i class=\"fa fa-angle-right\"></i>" +
        "</a>" +
        "</li>" +
        "</ul>" +
        "</ul>";
    return pagination;
}

function paginationNumbers(pages, page) {
    var numbers;
    var activeLi = "<li class=\"active\">";
    var li = "<li>";
    numbers = "<li class=\"pages-wrapper\">" +
        "<ul class=\"pagination-items\">";
    if (pages > 7) {
        for (var i = 1; i <= 3; i++) {
            if (page == i) {
                numbers = numbers + activeLi;
            } else {
                numbers = numbers + li;
            }
            numbers = numbers +
                "<a onclick=\"getPage(" + i + ")\">" + i + "</a>" +
                "</li>";
        }
        numbers = numbers +
            "<li>" +
            "<a>..</a>" +
            "</li>";
        for (var i = pages - 2; i <= pages; i++) {
            if (page == i) {
                numbers = numbers + activeLi;
            } else {
                numbers = numbers + li;
            }
            numbers = numbers +
                "<a onclick=\"getPage(" + i + ")\">" + i + "</a>" +
                "</li>";
        }
    } else {
        for (var i = 1; i <= pages; i++) {
            if (page == i) {
                numbers = numbers + activeLi;
            } else {
                numbers = numbers + li;
            }
            numbers = numbers +
                "<a onclick=\"getPage(" + i + ")\">" + i + "</a>" +
                "</li>";
        }
    }
    numbers = numbers +
        "</ul>" +
        "</li>";
    return numbers;
}