$(document).ready(function () {
    loadUser();
    startReload();
    loadBrand();
    loadBody();
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
        url: './loadAllAdverts.do',
        dataType: 'json'
    }).done(function (data) {
        for (var i = 0; i < data.length; i++) {
            var $newTr = $('<tr>');
            $newTr.addClass("car");
            var $photo = $('<td>');
            $photo.append(photoLoad(data[i].photo.name));
            var $brand = $('<td>');
            $brand.prepend(data[i].car.brand.name);
            $brand.addClass(data[i].car.brand.name);
            $brand.attr("id", "filterBrand");
            var $model = $('<td>');
            $model.prepend(data[i].car.modelCar.name);
            $model.attr("id", "filterModel");
            $model.addClass(data[i].car.modelCar.name);
            var $body = $('<td>');
            $body.prepend(data[i].car.bodyCar.name)
            $body.attr("id", "filterBody");
            $body.addClass(data[i].car.bodyCar.name);
            var $engine = $('<td>');
            $engine.prepend(data[i].car.engine.name + " ("
                + data[i].car.engine.power + " H.P.)");
            var $production = $('<td>');
            $production.prepend(data[i].car.production);
            var $user = $('<td>');
            $user.prepend(data[i].car.users[0].name);
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
            $advertTable.append($newTr);
        }
    }).fail(function (err) {
        console.log(err);
    })
}

function photoLoad(name) {
    return '<img src=http://localhost:8080/cars/photoLoader.do?name=' + name + ' height="100px"/>';
}

function loadBrand() {
    var $brand = $('#brand');
    $.ajax({
        type: "GET",
        url: "./loadBrand.do",
        dataType: 'json'
    }).done(function (data) {
        for (var i = 0; i < data.length; i++) {
            $brand.append('<option value=\"' + data[i].id  + '\">' + data[i].name + '</option>');
        }
    }).fail(function (err) {
        alert(err);
    })
}

function loadModel() {
    var $brand = $('#brand');
    var $model = $('#model');
    $model.html("");
    $model.append('<option value="">Не выбрано</option>');
    $.ajax({
        type: "GET",
        url: "./loadModel.do",
        data: jQuery.param({ id: $brand.val()}),
        dataType: 'json'
    }).done(function (data) {
        $model.removeAttr("disabled");
        for (var i = 0; i < data.length; i++) {
            $model.append('<option value=\"' + data[i].id  + '\">' + data[i].name + '</option>');
        }
    }).fail(function (err) {
        alert(err);
    })
}

function loadBody() {
    var $body = $('#body');
    $body.html("");
    $body.append('<option value="">Не выбрано</option>');
    $.ajax({
        type: "GET",
        url: "./loadBody.do",
        dataType: 'json'
    }).done(function (data) {
        for (var i = 0; i < data.length; i++) {
            $body.append('<option value=\"' + data[i].id  + '\">' + data[i].name + '</option>');
        }
    }).fail(function (err) {
        alert(err);
    })
}

function filterAdvert() {
    var $brand = $('#brand option:selected').text();
    var $model = $('#model option:selected').text();
    var $body = $('#body option:selected').text();
    var $allCar = $('.car');
    $allCar.hide();
    if ($brand === 'Не выбрано' && $model === 'Не выбрано' && $body === 'Не выбрано') {
        $allCar.show();
    } else if ($model === 'Не выбрано' && $body === 'Не выбрано') {
        $allCar.children("." + $brand).parent()
            .show();
    } else if ($brand === 'Не выбрано' && $model === 'Не выбрано') {
        $allCar.children("." + $body).parent()
            .show();
    } else if ($body === 'Не выбрано') {
        $allCar.children("." + $brand).parent()
            .children("." + $model).parent()
            .show();
    } else if ($model === 'Не выбрано') {
        $allCar.children("." + $brand).parent()
            .children("." + $body).parent()
            .show();
    } else {
        $allCar.children("." + $brand).parent()
            .children("." + $model).parent()
            .children("." + $body).parent()
            .show();
    }
}

function resetFilter() {
    $('#brand option:first').prop('selected', true);
    $('#body option:first').prop('selected', true);
    $('#model option:first').prop('selected', true);
    startReload();
}

function logOut() {
    $.ajax({
        type: "get",
        url: "./logout.do"
    }).always(function () {
        window.location.href = './login.html';
    })
}
