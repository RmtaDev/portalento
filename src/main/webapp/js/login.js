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
        toggleIcon.textContent = "visibility_off"; // Cambia el icono al ojo tachado
    } else {
        passwordInput.type = "password"; // Oculta la contraseña
        toggleIcon.textContent = "visibility"; // Cambia el icono al ojo abierto
    }
}

// Mostrar el spinner
function mostrarSpinner() {
    const spinner = document.getElementById("spinner");
    if (spinner) {
        spinner.style.display = "block";
        console.log("Spinner mostrado correctamente.");
    } else {
        console.error("No se encontró el elemento spinner en el DOM.");
    }
}

// Ocultar el spinner inmediatamente
function ocultarSpinner() {
    const spinner = document.getElementById("spinner");
    if (spinner) {
        spinner.style.display = "none";
        console.log("Spinner ocultado correctamente.");
    } else {
        console.error("No se encontró el elemento spinner en el DOM.");
    }
}

// Ocultar el spinner con un retraso de 2 segundos
function ocultarSpinnerConRetraso() {
    console.log("Iniciando temporizador para ocultar el spinner...");
    setTimeout(() => {
        const spinner = document.getElementById("spinner");
        if (spinner) {
            spinner.style.display = "none";
            console.log("Spinner ocultado después del retraso.");
        } else {
            console.error("No se encontró el elemento spinner en el DOM.");
        }
    }, 2000); // Retraso de 2 segundos (2000 ms)
}

// Función para manejar el inicio de sesión
function loginServidor() {
    mostrarSpinner(); // Mostrar el spinner al iniciar el proceso de login

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
                ocultarSpinnerConRetraso(); // Ocultar el spinner con retraso
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
                ocultarSpinnerConRetraso(); // Ocultar el spinner incluso en caso de error
                console.error("Error en la solicitud:", error);
                alert("Ocurrió un error en la conexión");
            });
    } else {
        ocultarSpinner(); // Si no pasa las validaciones, oculta el spinner directamente
        alert("Por favor, introduce datos válidos");
    }
}

// Ocultar el spinner al cargar la página
document.addEventListener("DOMContentLoaded", () => {
    ocultarSpinner(); // Asegurarse de que el spinner esté oculto al cargar la página
    console.log("Página cargada. Spinner ocultado.");
});

