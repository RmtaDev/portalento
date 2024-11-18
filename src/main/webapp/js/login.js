// Función para alternar la visibilidad de la contraseña
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

function loginServidor() {
    const usuario = document.getElementById("nombreUsuario").value;
    const password = document.getElementById("contraseña").value;

    if (validarNombre(usuario) && validarPassword(password)) {
        const infousuario = { usuario, password };

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
            });
    } else {
        alert("Por favor, introduce datos válidos");
		}
}

