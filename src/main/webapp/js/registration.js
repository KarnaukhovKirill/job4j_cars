$('#form').submit(function (e) {
    e.preventDefault();
    var $email = $('#email');
    var $password = $('#password');
    var $name = $('#name');
    $.ajax({
        type: "POST",
        url: "./registration.do",
        data: jQuery.param({ email: $email.val(), password : $password.val(), name : $name.val()}),
        dataType: 'json'
    }).done(function (data) {
        alert(data);
        $email.val('');
        $password.val('');
        $name.val('');
    }).fail(function () {
        window.location.href = './index.html';
    })
})