function altaServidor(evento) {
	
	let infoUsuario = new FormData(evento.target);

	fetch("AltaNuevoUsuario", {
		method: "POST",
		body: infoUsuario
	})//12 ACTUALIZO LA INTERFAZ DE USUARIO
		.then(respuesta => {
			console.log ("Procesando la vuelta ..");
			switch (respuesta.status) {
				case 200:
					
					break;
				case 400:
					
					break;
				case 500:
					
					break;

			}
		})
}