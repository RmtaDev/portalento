// Seleccionar los elmentos del DOM necesarios
const inputMensaje = document.querySelector('.message-input');
const botonEnviar = document.querySelector('.send-button');

// realizando pruebas
const idintercambio = 5;
const emisor = 5; // ID del usuario autenticado (emisor)
const receptor = 8; // ID del receptor (puede variar según el intercambio)

// Agregamos el evento del click al botón de enviar
botonEnviar.addEventListener('click', () =>{
	const textoMensaje = inputMensaje.value; // Obtenemos el valor del mensaje de texto
	console.log("Texto del mensaje capturado: ", textoMensaje);
	// Crear el objeto 'mensaje'
	const mensaje = {
		idintercambio: 5,
		emisor: 5,
		receptor: 8,
		fechaHora: new Date().toISOString(),
		//texto: textoMensaje // Asignamos el texto capturado en el input
		texto: "Este es un mensaje de prueba"
};
console.log("Objeto mensaje creado: ", mensaje);
// Llamada a la funcion con el mensaje a enviar
insertarMensaje(mensaje);
});

// Función para enviar el mensaje al servlet
async function insertarMensaje(mensaje){
	try{
		console.log("Enviando mensaje al servidor...");
		const response = await fetch('InsertarMensaje',{
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(mensaje) // Convertimos el objeto mensaje a JSON
		});
		console.log("Respuesta recibida del servidor: ", response);
		
		if(response.status === 201){
			const mensajeRespuesta = await response.json(); // JSON de respuesta con el mensaje insertado
			console.log('Mensaje insertado: ', mensajeRespuesta);
		} else {
			console.error('Error al insertar el mensaje', response.status);
		}
	} catch(error){
		console.error('Error en la solicitud: ', error);
	}
}

