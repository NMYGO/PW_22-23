$(function() {
	$("#loginForm").validate({
        rules: {
            'password': { required: true, minlength: 4},
            'correo': { required:true, email: true}
        },
    })
});

jQuery.extend(jQuery.validator.messages, {
    required: "El campo es obligatorio.",
    email: "La dirección de correo no es válida",
    minlength: jQuery.validator.format("La contraseña tiene que ser mas de {0} caracteres."),
});