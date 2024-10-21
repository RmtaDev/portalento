window.onload = () => {
	obtenerPerfilUsuario();  
}

function obtenerPerfilUsuario () {
	
	fetch('ObtenerPerfilUsuario', {
		method: "GET"
		})
		.then(respuesta => {
		    switch (respuesta.status) {
		       case 200:
			    console.log("Perfil obtenido");
				respuesta.json().then (infoUsuario => mostrarPerfil(infoUsuario));
		        break;
							
			   case 404:
				console.log("Nombre de usuario repetido");
				break;
		                
		       case 500:
				console.log("Error al obtener el perfil");
		        break;
			   }
			})
			
			.catch(error => {
				console.error('Error al obtener el perfil de usuario:', error);
			})
}     
function mostrarPerfil(infousuario) {

	console.log ("INFO = " + infousuario);
	document.getElementById ("usuario").innerHTML = infousuario.usuario;
	document.getElementById ("nombre").innerHTML = infousuario.nombre;
	document.getElementById("apellidos").innerHTML = infousuario.apellidos;
	document.getElementById("edad").innerHTML = infousuario.edad;
	document.getElementById("genero").innerHTML = infousuario.genero;
	document.getElementById("telefono").innerHTML = infousuario.telefono;
	document.getElementById("email").innerHTML = infousuario.email;
	document.getElementById('hablasobreti').innerHTML = infousuario.hablaSobreTi;
	document.getElementById ("foto").src = "ObtenerFoto?idfoto=" + infousuario.rutaFoto.substring(infousuario.rutaFoto.lastIndexOf('\\') + 1);
}  
