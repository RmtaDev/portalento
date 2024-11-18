// Validar nombre de usuario
function validarNombre(nombre) {
    return nombre && nombre.length >= 4 && nombre.length <= 100;
}

// Validar contraseña
function validarPassword(password) {
    return password && password.length >= 4 && password.length <= 50;
}

// Alternar visibilidad de la contraseña
function alternarVisibilidadPassword(inputId, iconId) {
    const passwordInput = document.getElementById(inputId);
    const toggleIcon = document.getElementById(iconId);

    if (passwordInput.type === "password") {
        passwordInput.type = "text"; // Muestra la contraseña
        toggleIcon.textContent = "visibility_off"; // Cambia al ícono de ojo tachado
    } else {
        passwordInput.type = "password"; // Oculta la contraseña
        toggleIcon.textContent = "visibility"; // Cambia al ícono de ojo abierto
    }
}

// Enviar datos al servidor para iniciar sesión
function loginServidor() {
    const usuario = document.getElementById("nombreUsuario").value.trim();
    const password = document.getElementById("contraseña").value.trim();

    // Validar datos antes de enviar
    if (validarNombre(usuario) && validarPassword(password)) {
        const infousuario = { usuario, password };
        
        // Bloqueo temporal del botón para evitar múltiples envíos
        const loginButton = document.querySelector("button[type='submit']");
        loginButton.disabled = true;

        fetch("Login", {
            method: "POST",
            body: JSON.stringify(infousuario),
            headers: { "Content-Type": "application/json" },
        })
            .then((respuesta) => {
                switch (respuesta.status) {
                    case 200:
                        window.location.href = "perfil.html";
                        break;
                    case 400:
                        alert("Datos no validados");
                        break;
                    case 403:
                        alert("No existe ese usuario o contraseña");
                        break;
                    case 500:
                        alert("Error en la autenticación");
                        break;
                    default:
                        alert("Error desconocido");
                }
            })
            .catch((error) => {
                console.error("Error en la solicitud:", error);
                alert("Ocurrió un error");
            })
            .finally(() => {
                // Reactivar el botón al finalizar la petición
                loginButton.disabled = false;
            });
    } else {
        alert("Por favor, introduce datos válidos");
    }
}
