function validarCampos() {
    const nombreUsuario = document.getElementById('nombreUsuario').value.trim();
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
    
    // Validaciones individuales
    if (!nombreUsuario || !nombre || !apellidos || !edad || isNaN(edad) || edad <= 0 || 
        !genero || !telefono || telefono.length < 10 || !email || 
        password.length < 8 || password !== confirmPassword || !foto || !hablaSobreTi) {
        esValido = false;
    }

    // Habilita o deshabilita el botón de enviar según validez
    document.getElementById('enviar').disabled = !esValido;
}

// Validación del formulario cuando se envía
function validarFormulario(event) {
    event.preventDefault(); // Evitar que se recargue la página
		console.log("validarFormulario está funcionando");
    const mensajeDiv = document.getElementById('mensaje');
    mensajeDiv.innerHTML = '';

    const nombreUsuario = document.getElementById('nombreUsuario').value.trim();
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
    if (!nombreUsuario) {
        mensajeDiv.innerHTML += 'El nombre de usuario es requerido.<br>';
        esValido = false;
    }
    if (!nombre) {
        mensajeDiv.innerHTML += 'El nombre es requerido.<br>';
        esValido = false;
    }
    if (!apellidos) {
        mensajeDiv.innerHTML += 'Los apellidos son requeridos.<br>';
        esValido = false;
    }
    if (!edad || isNaN(edad) || edad <= 0) {
        mensajeDiv.innerHTML += 'Por favor, ingresa una edad válida.<br>';
        esValido = false;
    }
    if (!genero) {
        mensajeDiv.innerHTML += 'El género es requerido.<br>';
        esValido = false;
    }
    if (!telefono || telefono.length < 10) {
        mensajeDiv.innerHTML += 'El teléfono es inválido.<br>';
        esValido = false;
    }
    if (!email) {
        mensajeDiv.innerHTML += 'El correo electrónico no es válido.<br>';
        esValido = false;
    }
    if (!password || password.length < 8) {
        mensajeDiv.innerHTML += 'La contraseña debe tener al menos 8 caracteres.<br>';
        esValido = false;
    }
    if (password !== confirmPassword) {
        mensajeDiv.innerHTML += 'Las contraseñas no coinciden.<br>';
        esValido = false;
    }
    if (!foto) {
        mensajeDiv.innerHTML += 'Inserta una foto.<br>';
        esValido = false;
    }
    if (!hablaSobreTi) {
        mensajeDiv.innerHTML += 'Comenta sobre ti.<br>';
        esValido = false;
    }

    // Si el formulario es válido, crea el objeto infoUsuario y envía los datos
    if (esValido) {
        const infoUsuario = {nombreUsuario, nombre, apellidos, edad, genero: genero ? genero.value : '',
                           telefono, email, password, foto, hablaSobreTi
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

// Asociamos la función de validación al formulario
window.onload = () => {
    document.getElementById('formulario').addEventListener('submit', validarFormulario);
    
};
