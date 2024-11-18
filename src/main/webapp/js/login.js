// Validar el nombre de usuario (mínimo 4 caracteres, máximo 100)
function validarNombre(nombre) {
    return nombre && nombre.length >= 4 && nombre.length <= 100;
}

// Validar la contraseña (mínimo 4 caracteres, máximo 50)
function validarPassword(password) {
    return password && password.length >= 4 && password.length <= 50;
}

// Alternar visibilidad de la contraseña
function alternarVisibilidadPassword() {
    const passwordInput = document.getElementById("contraseña");
    const toggleIcon = document.getElementById("togglePasswordIcon");

    if (passwordInput.type === "password") {
        passwordInput.type = "text"; // Muestra la contraseña
        toggleIcon.textContent = "visibility_off"; // Cambia el ícono al ojo tachado
    } else {
        passwordInput.type = "password"; // Oculta la contraseña
        toggleIcon.textContent = "visibility"; // Cambia el ícono al ojo abierto
    }
}

// Función para manejar el inicio de sesión
function loginServidor() {
    const usuario = document.getElementById("nombreUsuario").value;
    const password = document.getElementById("contraseña").value;

    // Validar que los campos estén correctamente llenados
    if (validarNombre(usuario) && validarPassword(password)) {
        const infousuario = { usuario, password };

        // Realizar la solicitud al servidor con fetch
        fetch("Login", {
            method: "POST",
            body: JSON.stringify(infousuario),
            headers: { "Content-Type": "application/json" },
        })
            .then((respuesta) => {
                switch (respuesta.status) {
                    case 200:
                        window.location.href = "perfil.html"; // Redirigir al perfil en caso de éxito
                        break;
                    case 400:
                        alert("Datos no validados"); // Error en los datos
                        break;
                    case 403:
                        alert("No existe ese usuario o contraseña"); // Usuario no encontrado
                        break;
                    case 500:
                        alert("Error en la autenticación"); // Error del servidor
                        break;
                    default:
                        alert("Error desconocido"); // Otros errores
                }
            })
            .catch((error) => {
                console.error("Error en la solicitud:", error);
                alert("Ocurrió un error en la conexión");
            });
    } else {
        alert("Por favor, introduce datos válidos"); // Mensaje en caso de que no se cumplan las validaciones
    }
}
