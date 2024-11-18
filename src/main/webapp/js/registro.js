function limpiarMensajesError() {
    const mensajes = document.querySelectorAll('.mensaje-error');
    mensajes.forEach(mensaje => mensaje.innerHTML = '');
}

let hablaSobreTi; //para setearlo individualemente
function validarCampos() {
    const usuario = document.getElementById('usuario').value.trim();
    const nombre = document.getElementById('nombre').value.trim();
    const apellidos = document.getElementById('apellidos').value.trim();
    const edad = document.getElementById('edad').value;
    const genero = document.querySelector('input[name="genero"]:checked');
    const telefono = document.getElementById('telefono').value;
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const foto = document.getElementById('foto').value;
    hablaSobreTi = document.getElementById('hablaSobreTi').value.trim();

    let esValido = true;
	
    if (!usuario || usuario.length ==0) {
        document.getElementById("mensajeUsuario").innerHTML = 'El usuario es obligatorio.';
        esValido = false;
    }
    if (!nombre || nombre.length ==0) {
        document.getElementById("mensajeNombre").innerHTML = 'El nombre es obligatorio.';
        esValido = false;
    }
    if (!apellidos || apellidos.length ==0) {
        document.getElementById("mensajeApellidos").innerHTML = 'Los apellidos son obligatorios.';
        esValido = false;
    }
    if (!edad || isNaN(edad) || edad <= 0 || edad > 110 ) {
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
    if (confirmPassword !== password ) {
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
	    	console.log("validado");
		   return esValido;
	}

	
		
	//PARA VALIDAR TIPO DE FORMATO Y TAMAÑO MAXIMO
	//if (foto) {
		 //if (!foto.type.startsWith("image/")) {
			  //alert("Por favor, selecciona un archivo válido de imagen.");
			  //esValido = false;
	//}
		// if (foto.size > 2 * 1024 * 1024) { // Máximo 2MB
			  //alert("El archivo de imagen no debe superar los 2MB.");
			 // esValido = false;
	//}
		//} else {
			 // alert("Por favor, selecciona una imagen.");
			 // esValido = false;
	//}
    
 	    
	
function validarFormulario(event) {
    
    limpiarMensajesError(); // Limpiamos mensajes previos

    const esValido = validarCampos();

    if (esValido) {
        
		let infoUsuario = new FormData(event.target);
		infoUsuario.append("hablaSobreTi", hablaSobreTi);//hay que setear el textarea a parte pq no lo pilla

        fetch("AltaNuevoUsuario", {
            method: "POST",
            body: infoUsuario
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


