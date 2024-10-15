function obtenerPerfilUsuario() {
	fetch('ObtenerPerfilUsuario', {
		method: 'GET'
	})
		.then(respuesta => {
            switch (respuesta.status) {
                case 200:
                    respuesta.json().then (infoUsuario=>mostrarPerfil(infoUsuario));
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
			console.error('Error al obtener el perfil de usuario:', error);
		});
}

function mostrarPerfil (infousuario)
{
	console.log ("INFO = " + infousuario);
	document.getElementById ("nombre").innerHTML = infousuario.usuario
	document.getElementById ("fotoPerfil").src = "ObtenerFoto?idfoto="+infousuario.rutaFoto.substring(infousuario.rutaFoto.lastIndexOf('\\') + 1);
	//fotosperfil\foto1728286525650
}


// Llamamos a la función para obtener el perfil del usuario cuando se cargue la página
window.onload = () => {
	obtenerPerfilUsuario();
}
	
