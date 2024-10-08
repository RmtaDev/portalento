function validarNombre(nombre) {
	valido = false;
		valido = ((null!=nombre)&&(nombre.length()>=4)&&(nombre.length()<=100));
	return valido;
}

function validarPassword(password) {
	valido = false;
		valido = ((null!=password)&&(password.length()>=4)&&(password.length()<=50));
	return valido;
}

function loginServidor() {
	let usuario = document.getElementById("usuario").value;
	let password = document.getElementById("password").value;

	let infousuario = {
		usuario: usuario,
		password: password
	}

	if (validarNombre(usuario) && validarPassword(password))
	{
		let infousuarioJson = JSON.stringify(infousuario);

		fetch("Login", {
			method: "POST",
			body: infousuarioJson
		})//12 ACTUALIZO LA INTERFAZ DE USUARIO
			.then(respuesta => {
				console.log ("Procesando la vuelta ..");
				switch (respuesta.status) {
					case 200:
						console.log("La autencicación ha ido bien");
						alert ("La autencicación ha ido bien");
						window.location.href="bienvenida.html";
						break;
					case 400:
						console.log("Datos incorrectos");
						alert("Datos incorrectos");
						break;
					case 403:
						console.log("No existe ese usuario password");
						alert("No existe ese usuario password");
						break;
					case 500:
						console.log("Error en la autenticación");
						alert("Error en la autenticación");
						break;
				}
			})
	}
}