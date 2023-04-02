$.validator.setDefaults({
    errorElement: 'span',
    errorClass: 'has-error',
});
function checkValid() {
    $("#formLogin").validate({
        rules: {
            username: {
                required: true,
            },
            password: {
                required: true,
                minlength: 6
            },
        },
        messages: {
            username: {
                required: "Please enter your username"
            },
            password: {
                required: "Please enter your password",
            }
        }
    })
}



