function obtenerMensajes(idIntercambio) {
	let url = "ConsultarMensajes?idintercambio=" + idIntercambio;
	fetch(url)
		.then(respuesta => {
			switch (respuesta.status) {
				case 200:
					console.log("Mensajes obtenidos");
					return respuesta.json()
						.then(listaMensajes => mostrarMensajes(listaMensajes));
				case 404:
					console.log("Error en la solicitud");
					break;
				case 500:
					console.log("Error al obtener los mensajes");
					break;
			}
		})
		.catch(error => {
			console.error('Error al obtener los mensajes:', error);
		});
}

function mostrarMensajes(listaMensajes) {
	console.log("INFO = " + listaMensajes);
	listaMensajes.forEach(mensaje => {
		let divMensaje = document.createElement("div");
		divMensaje.innerText = mensaje.texto;
		
		if (mensaje.emisor == usuarioSesion){
			divMensaje.classList.add("message", "user1");
		} else {
			divMensaje.classList.add("message", "user2");
		}
		 
		let divContenedorMensajes = document.getElementById("contenedor-mensajes");
		divContenedorMensajes.appendChild(divMensaje);
	});
}