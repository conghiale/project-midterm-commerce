$.validator.setDefaults({
    errorElement: 'span',
    errorClass: 'has-error',
});
function checkValid() {
    $("#formRegister").validate({
        rules: {
            name: {
                required: true,
            },
            username: {
                required: true,
                namespace: true,
            },
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6
            },
            confirmPassword: {
                required: true,
                minlength: 6,
                equalTo: "#password"
            },
            dob: {
                required: true,
            },
            gender: {
                required: true,
            },
        },
        messages: {
            username: {
                required: "Please enter your username"
            },
            email: {
                required: "Please enter your email",
                email: "Email invalid"
            },
            password: {
                required: "Please enter your password",
            },
            confirmPassword: {
                required: "Please enter your confirm password",
                equalTo: "Invalid password"
            },
            dob: {
                required: "Please enter your date of birth",
            },
            gender: {
                required: "Please enter your gender",
            }
        },
    })
}