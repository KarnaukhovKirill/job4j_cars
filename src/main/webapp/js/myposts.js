$(document).ready(function () {
    loadUser();
    startReload();
})

function loadUser() {
    $.ajax({
        type: "get",
        url: "./loadUser.do",
        dataType: "json"
    }).done(function (user) {
        $('#exit').text(user.name + ' | Выйти');
    }).fail(function () {
        console.log('ERROR LOAD USERNAME')
    });
}

function startReload() {
    var $advertTable = $('#advertTableBody');
    $advertTable.html('');
    $.ajax({
        type: 'GET',
        url: './loadMyAdverts.do',
        dataType: 'json'
    }).done(function (data) {
        for (var i = 0; i < data.length; i++) {
            var $newTr = $('<tr>');
            var $photo = $('<td>');
            $photo.append(photoLoad(data[i].photo.name));
            var $brand = $('<td>');
            $brand.prepend(data[i].car.brand.name);
            var $model = $('<td>');
            $model.prepend(data[i].car.modelCar.name);
            var $body = $('<td>');
            $body.prepend(data[i].car.bodyCar.name)
            var $engine = $('<td>');
            $engine.prepend(data[i].car.engine.name + " ("
                + data[i].car.engine.power + " H.P.)");
            var $production = $('<td>');
            $production.prepend(data[i].car.production);
            var $user = $('<td>');
            $user.prepend(data[i].car.users[0].name);
            var $deleteUser = $('<td>');
            $deleteUser.prepend('<a href=\'#\' onclick=deleteAdvert(' + data[i].id + ',' + data[i].photo.id + ')><img src=\'./images/1.png\'></a>');
            var $description = $('<td>');
            $description.prepend(data[i].description);

            $newTr.append($photo);
            $newTr.append($brand);
            $newTr.append($model);
            $newTr.append($body);
            $newTr.append($engine);
            $newTr.append($production);
            $newTr.append($user);
            $newTr.append($description);
            $newTr.append($deleteUser);
            $advertTable.append($newTr);
        }

    }).fail(function (err) {
        console.log(err);
    })
}

function photoLoad(name) {
    return '<img src=http://localhost:8080/cars/photoLoader.do?name=' + name + ' height="100px"/>';
}

function deleteAdvert(userId, photoId) {
    $.ajax({
        type: 'post',
        url: './deleteAdvert.do',
        data: jQuery.param({ userId: userId, photoId: photoId}),
    }).done(function () {
        startReload();
    }).fail(function (err) {
        console.log(err);
    });
}

function logOut() {
    $.ajax({
        type: "get",
        url: "./logout.do"
    }).always(function () {
        window.location.href = './login.html';
    })
}