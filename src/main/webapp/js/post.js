$(document).ready(function () {
    loadBrand();
    loadBody();
    loadEngine();
    loadUser();
});

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

$('#advertForm').submit(function (e) {

    alert("inside submit");
    if (validate()) {
        save();
    }
    e.preventDefault();
})

// $('#advertForm').submit(function(e) {
//     alert("inside submit");
//     if (validate()) {
//         save();
//     }
//     e.preventDefault();
// });

function validate() {
    alert("in validate");
    let mark = 0;
    var msg = "Не заполнены следующие поля:\n";
    if ($('#brand option:selected').val() === 'Марка') {
        msg += "Марка\n";
        mark++;
    }
    if ($('#model option:selected').val() === 'Модель') {
        msg += "Модель\n";
        mark++;
    }
    if ($('#body option:selected').val() === 'Кузов') {
        msg += "Кузов\n";
        mark++;
    }
    if ($('#production').val() === '') {
        msg += "Год производства\n";
        mark++;
    }
    if ($('#engine option:selected').val() === 'Двигатель') {
        msg += "Двигатель\n";
        mark++;
    }
    var $vin = $('#vin');
    if ($vin.val() === undefined || $vin.val().length !== 17) {
        msg += "Vin номер. Убедитесь, что ввели 17 знаков.\n";
        mark++;
    }
    if ($('#description').val() === '') {
        msg += "Описание\n";
        mark++;
    }
    if ($('#photo').val() === '') {
        msg += "Фото не прикреплено";
        mark++;
    }
    if (mark === 0) {
        return true;
    } else {
        alert(msg);
        return false;
    }
}

function save() {
    alert("in save method");

    var $brand = $('#brand');
    var $model = $('#model');
    var $body = $('#body');
    var $production = $('#production');
    var $engine = $('#engine');
    var $vin = $('#vin');
    var $description = $('#description');
    $.ajax({
        type: "POST",
        url: "./createAdvert.do",
        data: jQuery.param({
            brand : $brand.val(),
            model : $model.val(),
            body : $body.val(),
            production : $production.val(),
            engine : $engine.val(),
            vin : $vin.val(),
            description : $description.val()}),
        dataType: 'json',
        cache: false
    }).done(function (advertId) {
        alert(advertId);
        addPhoto(advertId);

    }).fail(function (err) {
        alert("err in save method");
        console.log(err);
    })
}

function addPhoto(advertId) {
    alert("in addPhoto method");
    let formData = new FormData();
    jQuery.each($('#photo')[0].files, function (i, file) {
        formData.append('photo', file);
    });

    $.ajax({
        type: 'POST',
        url: './photoUpload.do?id=' + advertId,
        processData: false,
        contentType: false,
        cache: false,
        data: formData,
        enctype: 'multipart/form-data'
    }).done(function () {
        window.location.href = './index.html';
    }).fail(function (err) {
        console.log(err);
    });
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
    $model.append('<option value="" disabled>Модель</option>');
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

function loadEngine() {
    var $engine = $('#engine');
    $engine.html("");
    $engine.append('<option selected disabled>Двигатель</option>');
    $.ajax({
        type: "GET",
        url: "./loadEngine.do",
        dataType: 'json'
    }).done(function (data) {
        for (var i = 0; i < data.length; i++) {
            $engine.append('<option value=\"' + data[i].id  + '\">'
                + data[i].name
                + ' (' + data[i].power + ' HP)'
                + '</option>');
        }
    }).fail(function (err) {
        alert(err);
    })
}

function loadBody() {
    var $body = $('#body');
    $body.html("");
    $body.append('<option value="" disabled>Кузов</option>');
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