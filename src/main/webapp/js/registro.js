// Función para limpiar mensajes de error
// Recorre todos los elementos con la clase 'mensaje-error' y elimina su contenido.
function limpiarMensajesError() {
    const mensajes = document.querySelectorAll('.mensaje-error');
    mensajes.forEach(mensaje => mensaje.innerHTML = '');
}

// Variable global para almacenar el texto del campo 'Habla sobre ti'.
let hablaSobreTi; // Para setearlo individualmente en el FormData.

// Función para validar los campos del formulario.
function validarCampos() {
    // Capturamos los valores de los campos del formulario.
    const usuario = document.getElementById('usuario').value.trim();
    const nombre = document.getElementById('nombre').value.trim();
    const apellidos = document.getElementById('apellidos').value.trim();
    const edad = document.getElementById('edad').value;
    const genero = document.querySelector('input[name="genero"]:checked'); // Verifica si hay un género seleccionado.
    const telefono = document.getElementById('telefono').value;
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const foto = document.getElementById('foto').value;
    hablaSobreTi = document.getElementById('hablaSobreTi').value.trim();

    let esValido = true; // Bandera que indica si el formulario es válido.

    // Validaciones de cada campo.
    if (!usuario || usuario.length === 0) {
        document.getElementById("mensajeUsuario").innerHTML = 'El usuario es obligatorio.';
        esValido = false;
    }
    if (!nombre || nombre.length === 0) {
        document.getElementById("mensajeNombre").innerHTML = 'El nombre es obligatorio.';
        esValido = false;
    }
    if (!apellidos || apellidos.length === 0) {
        document.getElementById("mensajeApellidos").innerHTML = 'Los apellidos son obligatorios.';
        esValido = false;
    }
    if (!edad || isNaN(edad) || edad <= 0 || edad > 110) {
        document.getElementById("mensajeEdad").innerHTML = 'Por favor, ingresa una edad válida.';
        esValido = false;
    }
    if (!genero) {
        document.getElementById("mensajeGenero").innerHTML = 'Selecciona el género.';
        esValido = false;
    }
    if (!telefono) {
        document.getElementById("mensajeTelefono").innerHTML = 'El teléfono no es válido.';
        esValido = false;
    }
    if (!email) {
        document.getElementById("mensajeCorreo").innerHTML = 'El correo electrónico no es válido.';
        esValido = false;
    }
    if (!password) {
        document.getElementById("mensajeContraseña").innerHTML = 'La contraseña debe tener al menos 8 caracteres.';
        esValido = false;
    }
    if (confirmPassword !== password) {
        document.getElementById("mensajeCContraseña").innerHTML = 'La contraseña no coincide.';
        esValido = false;
    }
    if (!foto) {
        document.getElementById("mensajeFoto").innerHTML = 'Inserta una foto.';
        esValido = false;
    }
    if (!hablaSobreTi) {
        document.getElementById("mensajeSobreTi").innerHTML = 'Comenta sobre ti.';
        esValido = false;
    }

    console.log("validado"); // Registro en consola para depuración.
    return esValido; // Devuelve si el formulario es válido o no.
}

// Función para validar y enviar el formulario.
function validarFormulario(event) {
    limpiarMensajesError(); // Limpiamos los mensajes previos.

    const esValido = validarCampos(); // Validamos los campos del formulario.

    if (esValido) {
        // Creamos un objeto FormData con los datos del formulario.
        let infoUsuario = new FormData(event.target);
        infoUsuario.append("hablaSobreTi", hablaSobreTi); // Añadimos el texto de 'Habla sobre ti' porque no se captura automáticamente.

        // Enviamos los datos al servidor mediante una solicitud fetch.
        fetch("AltaNuevoUsuario", {
            method: "POST",
            body: infoUsuario
        })
        .then(respuesta => {
            // Manejo de los diferentes estados de la respuesta del servidor.
            switch (respuesta.status) {
                case 200:
                    alert("Registrado correctamente");
                    window.location.href = "perfil.html"; // Redirige a la página de perfil.
                    break;
                case 400:
                    alert("No ha sido posible el registro");
                    break;
                case 404:
                    alert("Nombre de usuario repetido");
                    break;
                case 500:
                    alert("Error en el registro");
                    break;
            }
        })
        .catch(error => {
            console.error("Error en la solicitud:", error);
            mensajeDiv.innerHTML += 'Error en la conexión con el servidor.<br>';
        });
    }
}

// Función para alternar la visibilidad de la contraseña.
// Cambia entre mostrar y ocultar la contraseña, y actualiza el icono correspondiente.
function alternarVisibilidadPassword(inputId, iconId) {
    const passwordInput = document.getElementById(inputId); // Campo de contraseña.
    const toggleIcon = document.getElementById(iconId); // icono del ojo.

    if (passwordInput.type === "password") {
        passwordInput.type = "text"; // Cambia el tipo a texto para mostrar la contraseña.
        toggleIcon.textContent = "visibility_off"; // Cambia el icono al ojo tachado.
    } else {
        passwordInput.type = "password"; // Cambia el tipo a contraseña para ocultarla.
        toggleIcon.textContent = "visibility"; // Cambia el icono al ojo abierto.
    }
}

