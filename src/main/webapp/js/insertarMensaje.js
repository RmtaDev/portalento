// Funcion para preparar los datos...
function prepararDatos(user, id) {
	// Primero, asignamos los valores de `id` y `user` que necesitamos
	const idIntercambio = id;
	const receptorV = user;

	console.log("usuario recibido" + receptorV);
	console.log("intercambio recibido" + idIntercambio);

	console.log("Parametros enviados del intercambios: id " + idIntercambio + " usuario receptor " + receptorV);

	// Aquí estamos asegurándonos de que los valores ya estén disponibles antes de manipular el DOM
	const inputMensaje = document.querySelector('.message-input');
	const botonEnviar = document.querySelector('.send-button');

	if (inputMensaje && botonEnviar) {
		// Primero reemplazamos el botón y luego asignamos el eventListener
		//botonEnviar.replaceWith(botonEnviar.cloneNode(true));

		// Verificamos que el texto del mensaje no esté vacío
		const textoMensaje = inputMensaje.value.trim();

		if (textoMensaje === "") {
			alert("El mensaje no puede estar vacío.");
			return;
		}

		console.log("Texto del mensaje capturado: ", textoMensaje);

		// **Ahora que tenemos todos los valores necesarios, creamos el objeto `mensaje`**
		const mensaje = {
			idintercambio: idIntercambio,  // id de intercambio
			receptor: receptorV,           // usuario receptor
			fecha_hora: new Date().toISOString().slice(0, 19),  // fecha y hora actual
			texto: textoMensaje            // el texto capturado del mensaje
		};

		console.log("Objeto mensaje creado: ", mensaje);

		// Finalmente, enviamos el mensaje al servidor
		insertarMensaje(mensaje);


	}
}


// Función para enviar el mensaje al servlet
async function insertarMensaje(mensaje) {
	console.log("Enviando mensaje al servidor...");

	try {
		const response = await fetch('InsertarMensaje', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(mensaje) // Convertimos el objeto mensaje a JSON
		});
		console.log("Respuesta recibida del servidor: ", response);

		if (response.status === 201) {
			const mensajeRespuesta = await response.json(); // JSON de respuesta con el mensaje insertado
			console.log('Mensaje insertado: ', mensajeRespuesta);
			document.querySelector('.message-input').value = "";

			let divContenedorMensajes = document.getElementById("contenedor-mensajes");
			//divContenedorMensajes.innerHTML = "";
			let divMensaje = document.createElement("div");

			console.log(mensajeRespuesta);

			divMensaje.innerHTML = `${mensajeRespuesta.fecha_hora}<br>${mensajeRespuesta.texto}`;
			divMensaje.classList.add("message", "user1");
			divContenedorMensajes.appendChild(divMensaje);
			divContenedorMensajes.scrollTop = divContenedorMensajes.scrollHeight;


			return mensajeRespuesta;



		} else {
			console.error('Error al insertar el mensaje', response.status);
			alert(`Hubo un error al enviar el mensaje: ${response.statusText || response.status}`);
		}




	} catch (error) {
		console.error('Error en la solicitud: ', error);
		alert('Ocurrió un problema al enviar el mensaje. Por favor, inténtalo de nuevo.');
	}
}