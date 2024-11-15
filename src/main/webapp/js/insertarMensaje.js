document.addEventListener('DOMContentLoaded', function () {
    // El DOM está completamente cargado aquí

    // Seleccionar los elementos del DOM necesarios
    const idIntercambio = document.querySelector('idIntercambio')
    const emisorV = document.querySelector('nombre-usuario2')
    const inputMensaje = document.querySelector('.message-input');
    const botonEnviar = document.querySelector('.send-button');

    // Verificar si los elementos existen
    if (inputMensaje && botonEnviar) {
        // Agregamos el evento del click al botón de enviar
        botonEnviar.addEventListener('click', () => {
            const textoMensaje = inputMensaje.value.trim(); // Obtenemos el valor del mensaje de texto

            if (textoMensaje === "") {
                alert("El mensaje no puede estar vacío.");
                return;
            }

            console.log("Texto del mensaje capturado: ", textoMensaje);

            // Crear el objeto 'mensaje'
            const mensaje = {
                idintercambio: idIntercambio,
                emisor: emisorV,
                receptor: 5,
                fecha_hora: new Date().toISOString().slice(0,19), // Elimina la zona horaria
                texto: textoMensaje // Asignamos el texto capturado en el input
            };

            console.log("Objeto mensaje creado: ", mensaje);

            // Llamada a la función con el mensaje a enviar
            insertarMensaje(mensaje);
        });
    } else {
        console.error('Los elementos del DOM no se encontraron.');
    }
});

// Función para enviar el mensaje al servlet
async function insertarMensaje(mensaje) {
    try {
        console.log("Enviando mensaje al servidor...");
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
        } else {
            console.error('Error al insertar el mensaje', response.status);
        }
    } catch (error) {
        console.error('Error en la solicitud: ', error);
    }
}

