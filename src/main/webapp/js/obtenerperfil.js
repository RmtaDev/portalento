const usuarioAutorizado = localStorage.getItem("login");

function obtenerPerfilUsuario () {
	
	if (!usuarioAutorizado) {
		console.log("Usuario no autorizado");
		location.href = "inicio.html";
		return;
	}
	
	fetch('ObtenerPerfilUsuario', {
		method: "GET"
		})
		.then(respuesta => {
		    switch (respuesta.status) {
		       case 200:
			    console.log("Perfil obtenido");
				respuesta.json()
				.then (infoUsuario => mostrarPerfil(infoUsuario));
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
	document.getElementById ("nombre").innerHTML = infousuario.usuario
	document.getElementById("apellidos").innerHTML = infousuario.usuario
	document.getElementById("edad").innerHTML = infousuario.usuario
	document.querySelector('input[name="genero"]:checked').innerHTML.usuario
	document.getElementById("telefono").innerHTML.usuario
	document.getElementById("email").innerHTML.usuario
	document.getElementById("password").innerHTML.usuario
	document.getElementById("confirmPassword").innerHTML.usuario
	document.getElementById('hablaSobreTi').innerHTML.usuario
	document.getElementById ("foto").src = "ObtenerFoto?idfoto=" + infousuario.rutaFoto.substring(infousuario.rutaFoto.lastIndexOf('\\') + 1);
}  
              
window.onload = () => {
	obtenerPerfilUsuario();  
}
        