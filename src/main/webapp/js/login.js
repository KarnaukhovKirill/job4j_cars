$('#loginForm').submit(function (e) {
    e.preventDefault();
    var $email = $('#email');
    var $password = $('#password');
    $.ajax({
        type: "POST",
        url: "./login.do",
        data: jQuery.param({ email: $email.val(), password : $password.val()}),
        dataType: 'json'
    }).done(function (data) {
        alert(data);
    }).fail(function () {
        window.location.href = './index.html';
    })
})