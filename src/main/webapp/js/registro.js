function limpiarMensajesError() {
    const mensajes = document.querySelectorAll('.mensaje-error');
    mensajes.forEach(mensaje => mensaje.innerHTML = '');
}

function validarCampos() {
    const usuario = document.getElementById('usuario').value.trim();
    const nombre = document.getElementById('nombre').value.trim();
    const apellidos = document.getElementById('apellidos').value.trim();
    const edad = document.getElementById('edad').value;
    const genero = document.querySelector('input[name="genero"]:checked');
    const telefono = document.getElementById('telefono').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const foto = document.getElementById('foto').value;
    const hablaSobreTi = document.getElementById('hablaSobreTi').value.trim();

    let esValido = true;

    // Mensajes de error
    if (!usuario) {
        document.getElementById("mensajeUsuario").innerHTML = 'El nombre de usuario es requerido.';
        esValido = false;
    }
    if (!nombre) {
        document.getElementById("mensajeNombre").innerHTML = 'El nombre es requerido.';
        esValido = false;
    }
    if (!apellidos) {
        document.getElementById("mensajeApellidos").innerHTML = 'Los apellidos son requeridos.';
        esValido = false;
    }
    if (!edad || isNaN(edad) || edad <= 0) {
        document.getElementById("mensajeEdad").innerHTML = 'Por favor, ingresa una edad válida.';
        esValido = false;
    }
    if (!genero) {
        document.getElementById("mensajeGenero").innerHTML = 'El género es requerido.';
        esValido = false;
    }
    if (!telefono) {
        document.getElementById("mensajeTelefono").innerHTML = 'El teléfono es inválido.';
        esValido = false;
    }
    const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!regexEmail.test(email)) {
        document.getElementById("mensajeCorreo").innerHTML = 'El correo electrónico no es válido.';
        esValido = false;
    }
    if (!password) {
        document.getElementById("mensajeContraseña").innerHTML = 'La contraseña debe tener al menos 8 caracteres.';
        esValido = false;
    }
    if (password !== confirmPassword) {
        document.getElementById("mensajeContraseña").innerHTML = 'Las contraseñas no coinciden.';
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

    return esValido;
}

function validarFormulario(event) {
    event.preventDefault(); // Evitamos que se recargue la página
    limpiarMensajesError(); // Limpiamos mensajes previos

    const esValido = validarCampos();

    if (esValido) {
        const infoUsuario = {
            usuario: document.getElementById('usuario').value.trim(),
            nombre: document.getElementById('nombre').value.trim(),
            apellidos: document.getElementById('apellidos').value.trim(),
            edad: document.getElementById('edad').value,
            genero: document.querySelector('input[name="genero"]:checked') ? document.querySelector('input[name="genero"]:checked').value : '',
            telefono: document.getElementById('telefono').value,
            email: document.getElementById('email').value.trim(),
            password: document.getElementById('password').value,
            foto: document.getElementById('foto').value,
            hablaSobreTi: document.getElementById('hablaSobreTi').value.trim()
        };

        fetch("AltaNuevoUsuario", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(infoUsuario)
        })
        .then(respuesta => {
            switch (respuesta.status) {
                case 200:
                    alert("Registrado correctamente");
                    window.location.href = "perfil.html";
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

window.onload = () => {
    document.getElementById('formulario').addEventListener('submit', validarFormulario);
};
